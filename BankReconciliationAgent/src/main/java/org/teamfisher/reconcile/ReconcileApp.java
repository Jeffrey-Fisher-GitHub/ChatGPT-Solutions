package org.teamfisher.reconcile;

import java.io.*;
import java.math.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.time.*;
import java.time.format.*;
import java.time.temporal.*;
import java.util.*;
import java.util.stream.*;

/** Offline, read-only, deterministic parish bank/QB reconciliation prototype. Java 21. */
public final class ReconcileApp {
  record Tx(String id, LocalDate date, String description, long cents, int excelRow) {}
  record Link(String bankIds, String qbId, String rule, String note) {}
  private static final DateTimeFormatter DATE=DateTimeFormatter.ISO_LOCAL_DATE;
  private static final List<String> HEAD=List.of("id","date","description","amount");
  private static List<String> csvParse(String line) {
    List<String> out=new ArrayList<>(); StringBuilder b=new StringBuilder(); boolean quoted=false;
    for(int i=0;i<line.length();i++){char c=line.charAt(i);
      if(c=='"'){ if(quoted && i+1<line.length() && line.charAt(i+1)=='"'){b.append('"'); i++;}else quoted=!quoted; }
      else if(c==','&&!quoted){out.add(b.toString());b.setLength(0);} else b.append(c);
    } if(quoted)throw new IllegalArgumentException("Unclosed CSV quote"); out.add(b.toString());return out;
  }
  private static String esc(Object o){String s=String.valueOf(o==null?"":o);return "\""+s.replace("\"","\"\"")+"\"";}
  private static void writeCsv(Path p,List<String> columns,List<List<String>> records)throws IOException{
    List<String> lines=new ArrayList<>();lines.add(columns.stream().map(ReconcileApp::esc).collect(Collectors.joining(",")));
    for(var r:records)lines.add(r.stream().map(ReconcileApp::esc).collect(Collectors.joining(",")));
    Files.write(p,lines,StandardCharsets.UTF_8);
  }
  private static long money(String s){return new BigDecimal(s.trim().replace("$","").replace(",","").replace("(","-").replace(")","")).setScale(2,RoundingMode.UNNECESSARY).movePointRight(2).longValueExact();}
  private static String fmt(long n){return BigDecimal.valueOf(n,2).toPlainString();}
  private static List<Tx> load(Path f)throws IOException{
    List<String> lines=Files.readAllLines(f,StandardCharsets.UTF_8);
    if(lines.size()<2)throw new IllegalArgumentException("Empty data: "+f);
    List<String> cols=csvParse(lines.get(0));
    if(!cols.equals(HEAD))throw new IllegalArgumentException("Header must be id,date,description,amount in "+f);
    List<Tx> out=new ArrayList<>();Set<String> seen=new HashSet<>();
    for(int i=1;i<lines.size();i++){
      if(lines.get(i).isBlank())continue;
      List<String> v=csvParse(lines.get(i));if(v.size()!=4)throw new IllegalArgumentException("Wrong columns on line "+(i+1));
      String id=v.get(0).trim();if(id.isBlank()||!seen.add(id))throw new IllegalArgumentException("Duplicate/empty ID at line "+(i+1));
      out.add(new Tx(id,LocalDate.parse(v.get(1).trim(),DATE),v.get(2).trim(),money(v.get(3)),i+1));
    }return out;
  }
  private static String norm(String s){return s.toUpperCase(Locale.ROOT).replaceAll("[^A-Z0-9]+"," ").trim();}
  private static String category(Tx t){String x=norm(t.description());
    if(x.contains("FISERV")||x.contains("DISC PAYOUT"))return "FISERV";
    if(x.contains("MERCHANT BANKCD")||x.contains("MERCHANT SETTLEMENT"))return "MERCHANT";
    if(x.contains("PRIMEPAY")&&x.contains("TAX")||x.contains("PAYROLL TAX PAYMENT"))return "PAYROLL_TAX";
    if(x.contains("PAYROLL")&&!x.contains("TAX"))return "PAYROLL";
    if(x.contains("AMAZON"))return "AMAZON";
    if(x.contains("PECO"))return "PECO";
    return "OTHER";
  }
  private static String wk(LocalDate d){LocalDate start=d.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));return start.toString();}
  private static String mon(LocalDate d){return YearMonth.from(d).toString();}
  static Result reconcile(List<Tx> banks,List<Tx> qbs){
    Set<String> usedB=new HashSet<>(),usedQ=new HashSet<>();List<Link> links=new ArrayList<>();
    for(String c:List.of("FISERV","MERCHANT","AMAZON","PECO")){
      boolean weekly=c.equals("FISERV")||c.equals("MERCHANT");
      Map<String,List<Tx>> bg=banks.stream().filter(t->category(t).equals(c)).collect(Collectors.groupingBy(t->weekly?wk(t.date()):mon(t.date()),TreeMap::new,Collectors.toList()));
      for(var e:bg.entrySet()){
        List<Tx> b=e.getValue();long bs=b.stream().mapToLong(Tx::cents).sum();
        List<Tx> candidates=qbs.stream().filter(q->!usedQ.contains(q.id())&&category(q).equals(c)&&(weekly?wk(q.date()):mon(q.date())).equals(e.getKey())).toList();
        List<Tx> equal=candidates.stream().filter(q->q.cents()==bs).toList();
        if(equal.size()==1){Tx q=equal.get(0);usedQ.add(q.id());b.forEach(t->usedB.add(t.id()));
          links.add(new Link(b.stream().map(Tx::id).collect(Collectors.joining(";")),q.id(),weekly?"WEEKLY_"+c:"MONTHLY_"+c,"Period="+e.getKey()+"; summed="+fmt(bs)));}
      }
    }
    for(Tx b:banks){if(usedB.contains(b.id()))continue;
      List<Tx> candidates=qbs.stream().filter(q->!usedQ.contains(q.id())&&q.cents()==b.cents()&&Math.abs(ChronoUnit.DAYS.between(q.date(),b.date()))<=3)
         .filter(q->{String cb=category(b),cq=category(q);return cb.equals(cq)&&(cb.equals("PAYROLL")||cb.equals("PAYROLL_TAX")||norm(b.description()).equals(norm(q.description())));}).toList();
      if(candidates.size()==1){Tx q=candidates.get(0);usedB.add(b.id());usedQ.add(q.id());links.add(new Link(b.id(),q.id(),"EXACT_OR_ALIAS","Amount equal; posting dates within 3 days"));}
    }
    return new Result(links,banks.stream().filter(t->!usedB.contains(t.id())).toList(),qbs.stream().filter(t->!usedQ.contains(t.id())).toList());
  }
  record Result(List<Link> links,List<Tx> unmatchedBank,List<Tx> unmatchedQb) {}
  private static void output(Path dir,List<Tx> banks,List<Tx> qbs,Result r)throws IOException {
    Files.createDirectories(dir);Map<String,Link> bankIndex=new HashMap<>(),qbIndex=new HashMap<>();
    for(Link l:r.links()){for(String id:l.bankIds().split(";"))bankIndex.put(id,l);qbIndex.put(l.qbId(),l);}
    Map<String,Tx> bById=banks.stream().collect(Collectors.toMap(Tx::id,t->t));
    Map<String,Tx> qById=qbs.stream().collect(Collectors.toMap(Tx::id,t->t));
    List<String> headers=List.of("id","date","description","amount","status","opposite_id","opposite_source_rows","matching_rule","note");
    List<List<String>> bRows=new ArrayList<>(),qRows=new ArrayList<>(),exception=new ArrayList<>();
    for(Tx b:banks){Link l=bankIndex.get(b.id());Tx q=l==null?null:qById.get(l.qbId());
      bRows.add(List.of(b.id(),b.date().toString(),b.description(),fmt(b.cents()),l==null?"UNMATCHED":"MATCHED",l==null?"":l.qbId(),q==null?"":"'QB - June'!A"+q.excelRow(),l==null?"":l.rule(),l==null?"Requires review":l.note()));
      if(l==null)exception.add(List.of("BANK",b.id(),b.date().toString(),b.description(),fmt(b.cents()),"No confirmed QB match"));
    }
    for(Tx q:qbs){Link l=qbIndex.get(q.id());
      String refs=l==null?"":Arrays.stream(l.bankIds().split(";")).map(id->"BANK!A"+bById.get(id).excelRow()).collect(Collectors.joining("; "));
      qRows.add(List.of(q.id(),q.date().toString(),q.description(),fmt(q.cents()),l==null?"UNMATCHED":"MATCHED",l==null?"":l.bankIds(),refs,l==null?"":l.rule(),l==null?"Requires review":l.note()));
      if(l==null)exception.add(List.of("QB",q.id(),q.date().toString(),q.description(),fmt(q.cents()),"No confirmed bank match"));
    }
    writeCsv(dir.resolve("BANK_reconciled.csv"),headers,bRows);
    writeCsv(dir.resolve("QB_reconciled.csv"),headers,qRows);
    writeCsv(dir.resolve("Discrepancies.csv"),List.of("source","id","date","description","amount","reason"),exception);
    List<List<String>> audit=List.of(List.of("bank_rows",String.valueOf(banks.size())),List.of("qb_rows",String.valueOf(qbs.size())),List.of("matched_bank_rows",String.valueOf(banks.size()-r.unmatchedBank().size())),List.of("matched_qb_rows",String.valueOf(qbs.size()-r.unmatchedQb().size())),List.of("unmatched_bank",String.valueOf(r.unmatchedBank().size())),List.of("unmatched_qb",String.valueOf(r.unmatchedQb().size())),List.of("approval_status","PENDING_REVIEW"),List.of("writes_to_quickbooks","NEVER"));
    writeCsv(dir.resolve("Audit.csv"),List.of("metric","value"),audit);
  }
  private static void selfTest(){
    var banks=List.of(new Tx("a",LocalDate.parse("2026-06-03"),"Fiserv Pmnt Svcs DISC PAYOUT",6000,2),new Tx("b",LocalDate.parse("2026-06-04"),"Fiserv Pmnt Svcs DISC PAYOUT",4000,3),new Tx("c",LocalDate.parse("2026-06-05"),"ST ANN PRIMEPAY PAYROLL",-100000,4));
    var qb=List.of(new Tx("x",LocalDate.parse("2026-06-05"),"Fiserv",10000,2),new Tx("y",LocalDate.parse("2026-06-05"),"Payroll",-100000,3));
    var r=reconcile(banks,qb);if(r.links().size()!=2||!r.unmatchedBank().isEmpty()||!r.unmatchedQb().isEmpty())throw new AssertionError("group/alias test failed: "+r);
    var u=reconcile(List.of(new Tx("z",LocalDate.parse("2026-06-06"),"OTHER",100,2)),List.of());if(u.unmatchedBank().size()!=1)throw new AssertionError("unmatched test failed");
    System.out.println("PASS: grouping, payroll alias, unmatched detection");
  }
  public static void main(String[] args)throws Exception{
    if(args.length==1&&args[0].equals("--self-test")){selfTest();return;}
    if(args.length!=3){System.err.println("Usage: java ... ReconcileApp bank.csv qb.csv output-dir | --self-test");System.exit(2);}
    var b=load(Path.of(args[0]));var q=load(Path.of(args[1]));var r=reconcile(b,q);output(Path.of(args[2]),b,q,r);
    System.out.printf("BANK=%d QB=%d matched groups=%d unmatched BANK=%d QB=%d; PENDING REVIEW%n",b.size(),q.size(),r.links().size(),r.unmatchedBank().size(),r.unmatchedQb().size());
  }
}
