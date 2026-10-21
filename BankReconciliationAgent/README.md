# TeamFisherAI — Parish Financial Reconciliation Agent

Java 21 proof-of-concept for reconciling parish bank statement transactions against QuickBooks accounting exports. **This repository contains synthetic example data only.** It must not be used as evidence that Saint Ann's actual books are reconciled.

## Objective

Preserve every imported transaction and provide an auditable proposed link between BANK and QB records. The engine performs deterministic matching; any AI exception investigator must only propose explanations and cannot modify arithmetic, accounting records, or approvals.

## Architecture

1. **Bank / QuickBooks intake:** CSV now; XLSX and authorized QuickBooks API read-only integrations later.
2. **Normalization:** stable transaction IDs, dates, descriptions, signed integer cents, source references.
3. **Deterministic Java reconciliation engine:** group deposits/expenses, apply approved aliases and exact matching, leave conflicts unresolved.
4. **Exception investigation:** future optional AI component summarizes evidence and suggests what the accountant should investigate.
5. **Reporting:** BANK and QB cross-reference exports, discrepancy report, audit summary.
6. **Human approval:** authorized accountant reviews all exceptions and confirms statements/clearing items. No automatic journal posting.

## Initial matching rules

| Rule | BANK | QB |
|---|---|---|
| Weekly Fiserv | `Fiserv Pmnt Svcs DISC PAYOUT` | one corresponding weekly deposit total |
| Weekly merchant | `MERCHANT BANKCD DEPOSIT` | one corresponding weekly deposit total |
| Payroll | `ST ANN PRIMEPAY PAYROLL` | payroll entry, equal signed amount |
| Tax | `PRIMEPAY LLC PAYROLL TAX COLLECT` | payroll tax payment |
| Amazon | monthly sum of Amazon purchases | one corresponding monthly expense total |
| PECO | monthly sum of PECO payments | one corresponding monthly utility expense |
| Other | normalized description, equal signed amount, date tolerance | corresponding ledger entry |

Weekly groups are Monday–Sunday in the MVP and must be configurable before live use. The prototype does not automatically identify all real settlement cutoffs, outstanding items, or bank balance discrepancies.

## Running in Eclipse or terminal

Use JDK 21. Import as a Java project, mark `src/main/java` as a source folder, and run `org.teamfisher.reconcile.ReconcileApp`.

```sh
mkdir -p build
javac -d build src/main/java/org/teamfisher/reconcile/ReconcileApp.java
java -cp build org.teamfisher.reconcile.ReconcileApp --self-test
java -cp build org.teamfisher.reconcile.ReconcileApp sample/bank.csv sample/qb.csv output
```

Canonical input CSV columns: `id,date,description,amount` (ISO date, positive inflows, negative outflows). Results: `output/BANK_reconciled.csv`, `output/QB_reconciled.csv`, `output/Discrepancies.csv`, `output/Audit.csv`.

**Approval:** All automated linkages are suggestions and the final audit status remains pending accountant review. The application never posts journal entries or changes QuickBooks.

## Security and limitations

Never push real bank exports, donor information, account numbers, credentials, or parish financial statements to GitHub. The included samples are synthetic. Output CSV locators are not true Excel workbook cell references. Source `.xlsx` ingestion, accounting-period balance proof, robust matching ambiguity tests, encrypted history, approval state machine, and OAuth-secured connectors belong to later phases.

See `docs/PRODUCTION_BLUEPRINT.md` for architecture, autonomy boundaries, prompts, memory, workflow, evaluation, and roadmap.