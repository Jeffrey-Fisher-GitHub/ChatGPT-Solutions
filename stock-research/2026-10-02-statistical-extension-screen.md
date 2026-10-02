# U.S. Statistical Extension Screen — October 2, 2026

**Prepared:** October 1, 2026 after the U.S. regular-session close  
**Purpose:** Pre-session research screen for Friday, October 2, 2026  
**Framework:** Extension/mean-reversion research; not a recommendation to buy or short any security.

## Hard filters

- U.S.-listed common stock
- Price > $5
- Market capitalization > $1B
- 20-day average daily volume (ADV20) >= 1M shares
- 20-session average intraday range > 2%, where daily range = `(High - Low) / prior close`
- RSI(14) >= 65

## Statistical Extension Score

The score uses the requested weights:

- 25% RSI(14)
- 15% 20-day average intraday range
- 15% distance above SMA20
- 10% Bollinger %B
- 10% Stochastic
- 10% relative-volume behavior
- 10% proximity to the 52-week high
- 5% closing-range position

For repeatability across days, this report uses **fixed-scale normalization** rather than re-ranking each component only against the current day's names:

- RSI: 65 = 0, 90 = 100
- Range20: 2% = 0, 10% = 100
- SMA20 extension: 0% = 0, +40% = 100
- Bollinger %B: 0.50 = 0, 1.20 = 100
- Stochastic: 60 = 0, 100 = 100
- RVOL: 0.5x = 0, 2.5x = 100
- 52-week proximity: 20% below high = 0, at high = 100
- Closing-range position: 0-100 directly

Values are capped at 0-100 before weighting. The result measures **technical extension**, not probability of a decline.

## Data-status note

The institutional daily-bar feed used for the rolling 20-session calculations was fully reconciled through September 30 while several public technical/historical feeds had already rolled into October 1 at different timestamps. Therefore:

- Rolling Range20/ADV20/SMA20/Bollinger/CMF/OBV inputs are the latest fully reconciled series.
- October 1 price/RSI is overlaid where a same-day source is available.
- A `~` marks an RSI that remains highly likely to qualify but whose final October 1 daily value was not available from the same standardized feed at report time.
- Names with a clear sub-65 same-day RSI are removed from the qualifying ranking and shown in **Fallouts / failure watch**.

## Oct. 2 qualifying research list

| Rank | Ticker | Extension Score | RSI14 | Range20 | SMA20 Ext. | BB %B | Stoch | RVOL | 52W proximity | Oct. 1 close-location / state |
|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|---|
| 1 | **KOD** | **78.7** | **80.7** | **11.24%** | **+123.7%** | **1.13** | 94/93 | 1.56x | ~4% below high | Mid-high range; event-driven extension |
| 2 | **TWST** | **59.6** | ~71 | **7.22%** | +26.9% | 0.94 | 95/92 | 1.52x | ~1% below high | Strong close; trend still confirmed |
| 3 | **CDNA** | **58.6** | ~77 | 4.92% | +18.0% | 0.96 | **96/94** | 1.44x | ~1% below high | Strong close; continuation profile |
| 4 | **IOVA** | **58.1** | **68.7** | 6.59% | **+46.9%** | **1.17** | 93/92* | 1.65x | ~6% below high | Weak Oct. 1 close after event surge |
| 5 | **FORM** | **53.7** | **69.0** | 4.99% | +27.6% | **1.08** | 97/97* | 1.97x | ~8% below 52W high | Pullback after high-volume breakout |
| 6 | **PGEN** | **49.2** | ~72 | 5.07% | +15.5% | **1.07** | 81/81 | **2.66x** | ~7% below high | High-volume extension; biotech catalyst |
| 7 | **P** | **46.2** | **67.2** | 5.29% | +21.7% | 0.95 | 94/94* | 0.41x* | ~0-1% below high | Near-high close but participation divergence |
| 8 | **RVTY** | **38.7** | ~68 | 3.57% | +10.0% | 0.83 | 85/94 | **2.08x*** | ~4% below high | Prior high-volume failure remains relevant |

* Some oscillator/RVOL components are from the latest fully reconciled daily-bar window rather than a fully finalized October 1 feed.

### Interpretation

**KOD** is in a separate statistical category. The Phase 3 DAYBREAK gap creates an abnormally large Range20 and SMA20 extension, but accumulation measures remain strongly positive. This is an event-driven repricing, not a classic low-volume blow-off.

**TWST and CDNA** remain high-momentum continuations. Their proximity to recent highs and strong stochastic structure keep them extended, but neither has a strong confirmed distribution signature in the reconciled flow data.

**IOVA** is a more useful failure-watch candidate after its event-driven surge. The underlying catalyst is strong, but October 1 price action was weaker and the Oct. 2 weekly options chain is extremely active around $14-$16.

**FORM** remains technically extended after the Deutsche Bank/Nvidia-TSMC catalyst, but its October 1 pullback introduces the first meaningful test of the breakout.

**P (Everpure)** is near its high and remains above SMA20, but low relative participation and mixed CMF/OBV are a warning that the price extension is no longer being confirmed as strongly by incremental volume.

## Support, resistance and ATR downside reference zones

These are **profit-taking/cover reference zones only if a bearish move develops**. They are not automatic short entries.

| Ticker | Near support | Resistance | ATR14 | -1 ATR zone | -2 ATR zone | Flow / A-D read |
|---|---:|---:|---:|---:|---:|---|
| **KOD** | $94.6 / ~$91.9 | $103.9-$104.0 | ~$7.5 | **~$91.9** | **~$84.4** | **Strong accumulation**; CMF/OBV strongly positive |
| **TWST** | ~$191.7 / $186 | $200.3 | ~$12.0 | **~$186.5** | **~$174.4** | Positive accumulation; no broad distribution yet |
| **CDNA** | ~$64.2 / $62.1 | ~$67.3 | ~$2.9 | **~$64.0** | **~$61.1** | Positive CMF/OBV; still trend-confirming |
| **IOVA** | ~$14.3 / $13.5 | $15.0-$15.3 | ~$0.93 | **~$13.45** | **~$12.52** | Strong medium-term accumulation; watch post-gap failure |
| **FORM** | ~$145 / $142 | $149.3-$150.7, then $160.3 | ~$7.4 | **~$139-$140** | **~$131-$132** | Strong accumulation; Oct. 1 was first material pullback |
| **PGEN** | ~$8.28 / $8.10 | $8.64-$8.98 | ~$0.46 | **~$7.88** | **~$7.42** | Positive accumulation; event-sensitive |
| **P** | ~$130 / $127.5 | ~$134.6-$135.4 | ~$7.0 | **~$127.5** | **~$120.5** | **Mixed divergence:** CMF negative, OBV positive |
| **RVTY** | ~$150 / $147.5 | ~$154.3-$158.3 | ~$5.4 | **~$146** | **~$140.6** | Positive medium-term flow but prior high-volume failed close |

### Volume divergence / accumulation-distribution

- **KOD:** CMF about +0.46 and normalized OBV-flow about +0.68. Very extended, but the flow data still look like accumulation.
- **IOVA:** CMF about +0.26 / OBV-flow +0.60. The Sep. 29 repricing was volume-confirmed; Oct. 1 weakness matters only if follow-through appears.
- **TWST:** CMF about +0.12 / OBV-flow +0.21. Positive, but less powerful than KOD/IOVA.
- **CDNA:** CMF about +0.20 / OBV-flow +0.38. No clear institutional-distribution proxy yet.
- **FORM:** CMF about +0.39 / OBV-flow +0.65. Strong accumulation proxy; a short thesis needs actual support failure rather than valuation/RSI alone.
- **P:** CMF about **-0.07** while OBV-flow remains strongly positive. This disagreement is a useful participation-divergence signal.
- **PGEN:** CMF about +0.15 / OBV-flow +0.36. Still constructive.
- **RVTY:** CMF about +0.11 / OBV-flow +0.22, but the very poor prior closing-range position on heavy volume remains a failed-breakout warning.

CMF/OBV are **proxies** for accumulation/distribution; they do not identify institutional buyers or sellers directly.

## Options positioning

Current options coverage is uneven. Blank or N/A values are intentional rather than estimates.

| Ticker | Put/Call | IV / IV percentile | Unusual / concentrated strikes | Read |
|---|---|---|---|---|
| **IOVA** | Aggregate current P/C not cleanly standardized | Oct. 2 near-ATM IV roughly **111%-125%** | Oct. 2 **$15 call ~2,060 vol / 2,311 OI**, $15.50 call ~1,109 vol / 1,171 OI, $16 call ~819 vol / 1,082 OI; puts active at $13-$14.50 | Heavy short-dated call concentration; high gamma/event risk |
| **P** | Oct. 16 volume P/C ~**1.11**, OI P/C ~**0.60**; all-expiry totals ~0.93 vol / 0.74 OI | Oct. 16 IV ~**63.4%**; IV percentile not reliably current | Max-pain reference near $110 in latest chain snapshot | Options positioning not strongly one-sided |
| **KOD** | N/A current aggregate | N/A current percentile | Post-DAYBREAK options are repricing rapidly | Treat historical IV percentile as unreliable after gap |
| **TWST** | N/A current aggregate | N/A current percentile | No same-date strike set reliable enough for ranking | N/A |
| **CDNA** | N/A current | N/A current | Public chain source is stale | N/A |
| **FORM** | N/A current | N/A current | No clean same-date chain aggregate | N/A |
| **PGEN** | N/A current | Very high biotech/event IV expected, but current percentile unavailable | Public chain currently stale | Do not infer direction |
| **RVTY** | N/A current | N/A current | Public chain currently stale | Do not infer direction |

## Catalyst risk

### KOD — Extreme
Kodiak's pivotal DAYBREAK Phase 3 trial met its primary endpoints. Zenkuda showed meaningful vision gains with 54% of patients on six-month dosing, and Kodiak plans a multi-indication BLA in Q4 2026. This is the principal reason the stock can stay statistically extreme without normal mean reversion.

Source: https://ir.kodiak.com/news-releases/news-release-details/zenkuda-and-tabirafusp-ted-meet-primary-endpoints-pivotal

### IOVA — Extreme/high
Iovance raised full-year 2026 revenue guidance to **$410-$420M** from **$350-$370M**, driven by U.S. demand for Amtagvi and Proleukin. That fundamental repricing remains the dominant catalyst.

Source: https://www.sec.gov/Archives/edgar/data/1425205/000110465926111583/tm2626507d1_ex99-1.htm

### FORM — High
Deutsche Bank initiated coverage with a Buy rating and $200 target, highlighting FormFactor's position as a secondary probe-card supplier for Nvidia GPUs manufactured at TSMC and its HBM test exposure. This is a fresh positive catalyst; it raises squeeze risk for a purely technical short.

Source: https://www.investors.com/news/technology/formfactor-stock-jumps-as-chip-gear-firm-gets-fresh-buy-rating/

### P — High
Everpure's analyst day reset long-term expectations, including projected **39%-45% FY2028 revenue growth** and a much larger hyperscaler/data-architecture opportunity. The catalyst is still recent enough to keep momentum-fund participation relevant.

Source: https://www.marketwatch.com/story/the-s-p-500s-newest-tech-stock-is-now-its-best-performer-5bfeb6e2

### PGEN — High
The FDA granted Precigen's AdenoVerse platform **Platform Technology Designation** on September 21. The designation may accelerate development of current and future therapies built on the platform.

Source: https://investors.precigen.com/news-releases/news-release-details/precigen-receives-fda-platform-technology-designation-adenoverse

### CDNA / TWST / RVTY — Moderate
No single fresh binary catalyst was identified that is comparable with KOD/IOVA/PGEN. Earnings, sector flows and company-specific news remain important, but the technical/flow structure is currently the more useful research signal.

## Fallouts / failed-momentum watch

These names were recently in the extension universe but are **not being forced into the Oct. 2 qualifying rank** because same-day RSI readings fell below 65 or vendor timing conflicts materially with the prior daily screen:

| Ticker | Status | Why it still matters |
|---|---|---|
| **GRAL** | RSI vendor conflict / likely below current threshold | Still enormously extended versus SMA20, but Stochastic and closing-range behavior have deteriorated. Useful failed-momentum watch. |
| **NTRA** | Current RSI readings below 65 | Prior session closed essentially at the low despite strong medium-term CMF/OBV. |
| **ILMN** | Current RSI below 65 | Earlier negative CMF and OBV made it one of the clearest distribution profiles. |
| **ABCL** | Current RSI below 65 | Former extension has normalized sharply; useful case study in post-overbought failure. |
| **MRNA** | Current RSI below 65 | Momentum rollover plus elevated biotech/headline risk. |
| **TXG** | Current RSI below 65 | Prior close near the session low with Stochastic rollover. |
| **SDGR** | Current RSI ~51 | Fails the hard RSI rule despite high Range20. |
| **AMD / CRWD / OKTA** | Below RSI 65 | No longer part of the strict extension population; track separately as prior-momentum normalization names. |

## Macro risk for Friday, Oct. 2

The **September 2026 Employment Situation** is scheduled for **8:30 a.m. ET Friday, October 2**. This is the dominant premarket macro catalyst for rates, index futures and high-duration growth/biotech names.

BLS schedule: https://www.bls.gov/schedule/2026/10_sched_list.htm

On October 1, U.S. equities recovered from an early selloff to close slightly higher after Treasury yields reversed from a 24-year high. The S&P 500 gained 0.20%, the Dow 0.04%, and the Nasdaq 0.04%. That rate sensitivity is important for Friday because payrolls can rapidly change the Treasury/Fed repricing.

Reuters: https://www.reuters.com/business/dow-futures-hit-three-month-low-yields-surge-micron-earnings-offer-support-2026-10-01/

## Oct. 2 failure triggers to monitor

A high Extension Score alone is not a short signal. For mean-reversion research, look for:

1. failed opening breakout / inability to hold a new high;
2. Stochastic %K crossing below %D;
3. closing/opening-range deterioration;
4. RVOL expansion on downside rather than upside;
5. CMF/OBV deterioration;
6. first-ATR support break.

High-information downside references:

- **KOD:** ~$91.9 then ~$84.4
- **IOVA:** ~$13.45 then ~$12.52
- **TWST:** ~$186.5 then ~$174.4
- **FORM:** ~$139-$140 then ~$131-$132
- **P:** ~$127.5 then ~$120.5
- **PGEN:** ~$7.88 then ~$7.42
- **CDNA:** ~$64 then ~$61
- **RVTY:** ~$146 then ~$140.6

## Bottom line

For Oct. 2, the most important distinction is:

- **Extreme but still flow-confirmed:** KOD, TWST, CDNA.
- **Event-driven and vulnerable to post-gap failure:** IOVA.
- **Fresh catalyst + strong accumulation:** FORM.
- **Extension with participation divergence:** P.
- **High-volume biotech extension:** PGEN.
- **Prior failed-breakout warning:** RVTY.

The first 30-60 minutes after the **8:30 a.m. payroll report** should be treated as a new information set. A downside break with expanding volume carries substantially more confirmation value than RSI or the Extension Score by itself.

---

### Primary sources

- U.S. Bureau of Labor Statistics release schedule: https://www.bls.gov/schedule/2026/10_sched_list.htm
- Reuters Oct. 1 market close: https://www.reuters.com/business/dow-futures-hit-three-month-low-yields-surge-micron-earnings-offer-support-2026-10-01/
- Kodiak DAYBREAK: https://ir.kodiak.com/news-releases/news-release-details/zenkuda-and-tabirafusp-ted-meet-primary-endpoints-pivotal
- Iovance revenue guidance / SEC: https://www.sec.gov/Archives/edgar/data/1425205/000110465926111583/tm2626507d1_ex99-1.htm
- Precigen FDA platform designation: https://investors.precigen.com/news-releases/news-release-details/precigen-receives-fda-platform-technology-designation-adenoverse
- IOVA Oct. 2 options chain: https://www.marketbeat.com/stocks/NASDAQ/IOVA/options/
- Everpure options overview: https://optioncharts.io/options/P
- FormFactor catalyst: https://www.investors.com/news/technology/formfactor-stock-jumps-as-chip-gear-firm-gets-fresh-buy-rating/
