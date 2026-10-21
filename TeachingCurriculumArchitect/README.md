# Teaching & Curriculum Architect (Java 21)

Prototype for CISC 121 and CISC 213 using Java 21, Maven, and the OpenAI Chat Completions API over Java HttpClient. No Python required.

## Architecture
The Java orchestrator loads the course profile, calls a lesson-drafting AI role, then calls a separate reviewing AI role. It writes a **student-only draft**, a separate **instructor-only review**, and an audit file. All files remain DRAFT; no publishing tools are enabled.

## Running
- Prerequisites: Java 21, Maven 3.9+, Eclipse with Maven support.
- Offline example: `mvn test` then `mvn -q exec:java -Dexec.mainClass=edu.teamfisher.curriculum.CurriculumApp -Dexec.args="--demo CISC121"`
- Real AI: set OPENAI_API_KEY in your local environment; optionally OPENAI_MODEL (default gpt-4.1-mini). Run `mvn -q exec:java -Dexec.mainClass=edu.teamfisher.curriculum.CurriculumApp -Dexec.args="CISC213 Extend the PA Turnpike simulation with sorting and binary trees"`

## Scope and restrictions
This is an implementation starter, not a deployed service. The live API path has not been tested against a user's credentials. Offline demo is deterministic. No authenticated source retrieval, verified citations, code sandbox, Slides, GitHub writes, Canvas LMS, or student records. Do not distribute instructor-only content. Never commit API keys.

## Production roadmap
1. Authenticated source ingestion with precise page and source IDs; claims checked against retrieved passages.
2. JSON-schema outputs, robust content validators and objective-assessment mapping.
3. Isolated Java compilation/testing for lab solutions.
4. SQLite/PostgreSQL course profiles, versioned approved memory, run audit, and approval state machine.
5. Scoped Google Drive/Docs/Slides, GitHub and Canvas draft adapters; publish only on explicit instructor approval.
6. Weekly preparation and evaluation runs.

## Agent roles
Researcher (source discovery, evidence), Instructional Designer (depth and objectives), Lab Engineer (tests and artifacts), Assessment Designer (rubrics and alignment), Quality Reviewer (independent critique), Publisher (approved artifacts only). In this MVP, the drafting prompt performs the first four roles together, and an independent reviewer performs the fifth; the sixth is deliberately not implemented.

## Suggested evaluation targets (not measured)
100% cited claims inspectable; 100% lab reference tests passing; 95% objective-to-assessment alignment; 90% conceptual-depth rubric; 85% faculty first-pass acceptance.
