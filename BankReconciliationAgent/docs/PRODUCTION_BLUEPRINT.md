# Production architecture — TeamFisherAI Parish Financial Reconciliation

## Services and interfaces
- **IngestionService:** accept XLSX/CSV and QuickBooks Online exports; retain immutable originals and SHA-256 fingerprints; enforce named account and reporting window.
- **NormalizationService:** map dates, signed currency, stable source IDs, account, category, check/reference IDs; never merge duplicate source rows.
- **RuleEngine:** ordered, versioned deterministic rules with precedence and conflict sets. Phase 1 covers Fiserv, Merchant BankCD, PrimePay payroll/tax, Amazon and PECO.
- **ReconciliationService:** one-to-one and one-to-many matching; preserve signed balance equations and transaction-level provenance; outstanding checks and deposits-in-transit are separately classified.
- **EvidenceStore:** SQLite/PostgreSQL schema for SourceFile, ImportRun, Transaction, RuleVersion, CandidateMatch, ConfirmedMatch, Exception, ApprovalEvent and AuditEvent.
- **ExceptionAnalysisService:** OpenAI Java SDK; structured JSON proposes descriptions and evidence to investigate; never determines final arithmetic matches or creates postable journal entries.
- **WorkbookPublisher:** Apache POI XLSX workbook preserving original source data, cross-sheet references, matched groups and discrepancy sheet; formula reconciliation summary.
- **QBConnector:** OAuth2 with least privilege and read-only access initially, pagination, idempotent refresh; production writes excluded until separately designed and authorized.

## Approval state machine
DRAFT → VERIFIED → PENDING_REVIEW → APPROVED / REJECTED. A correction creates a new revision. Only authorized parish accounting staff may approve. The protected approval log contains user, timestamp, source file hashes, rule version, exception dispositions and notes. Proposed ledger matches are **not** a completed bank reconciliation.

## Agent prompts
**INBOX_MAPPING:** Return canonical field mappings and issues under a JSON schema; flag doubtful dates, balances, debit/credit conventions, and sign conventions. Never silently guess.

**EXCEPTION_REVIEW:** Given evidence IDs and deterministic candidates, summarize likely and competing causes, uncertainty, needed verification steps and escalation owner. Do not manufacture evidence or approve matches.

**SUPERVISOR_SUMMARY:** Report bank and QB totals, matched amounts, outstanding debits/credits, exceptions, limitations and approvals needed. Never declare reconciliation complete before opening/closing balances and outstanding items are proven.

## Workflow
1. Select month and exact bank account(s); import bank and QB exports.
2. Validate SHA-256 file hashes, stable row IDs, dates, currency and opening/closing balance where present.
3. Normalize into canonical transactions with original source coordinates.
4. Execute approved rules, settlement calendars, cross-period handling and conflict detection.
5. Freeze proposed matches and the exception report; AI may explain but not calculate matches.
6. Accountant investigates, approves/rejects exceptional candidates, and documents timing differences.
7. Generate versioned reconciliation package, verify balances independently, and sign off.

## Memory
Persist versioned matching rules, vendor aliases, file fingerprints, source row provenance, approved exceptions, accountant decisions, audit events and cross-run regression cases. AI recommendations become active rules only after accountant approval.

## Proposed evaluation targets
- 100% source rows retained with provenance.
- 100% accepted matches have documented rule and signed amount equality.
- 100% ambiguous candidates routed for review.
- Zero unapproved writes to accounting systems.
- ≥95% routine automatic matching rate after calibration.
- Compare manual preparation time against a measured baseline.

## Security and autonomy
Encrypt financial material at rest; keep keys in a secrets vault; use least-privilege credentials and separate organizational storage. Do **not** commit real parish exports, account numbers, personally identifiable information, or credentials. Redact inputs to any optional AI service. Financial approval and all QuickBooks modifications require separate explicit authorization. Keep model and system logs aligned with retention policies.

## Production release gates
Test anonymized months with duplicates, ambiguous candidates, reversals, NSF returns, split payments, adjustments, calendar rollover, bank fees, uncleared checks, account isolation and spreadsheet formula integrity. Require accountant UAT and a security review before integrating live bank or QuickBooks data.