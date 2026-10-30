# Failed Breakout Short Trading Playbook — v1.0

Prepared October 29, 2026, Eastern Time. Planning horizon: October 30–December 31, 2026. Educational research framework; rules and thresholds are proposed hypotheses, not a demonstrated trading edge or individualized investment recommendation.

## The objective

Find liquid common stocks that were recently extended, attempted a breakout, and then showed an observable failure. Consider a short only after a separate entry trigger, with a defined invalidation level, available borrow, manageable costs, and enough room before support for a 5% price decline.

Primary holding period: at most five trading sessions, counting the entry session as session one. Record ten-session outcomes as a separate research diagnostic. Do not extend a losing five-day trade merely because a two-week forecast was also requested.

A 5% decline in the stock is a price target. It is not a 5% probability, a guaranteed profit, or a 5% return on the equity or margin supporting the position.

## What is known now, and what is unavailable

AP's October 29 close report showed S&P 500 -0.3%, Nasdaq -1.4%, Russell 2000 +1.0%, and most stocks advancing. Inference: selective weakness warrants company and sector confirmation before adopting a broad bearish view. [1]

The Federal Reserve held its target range at 3.75%–4.00% on October 28 and described inflation as elevated. This is context, not a forecast of the next policy move or stock-market direction. [2]

The connected Massive service reported that the stock market was closed at its returned server timestamp, October 29, 2026, 11:01 p.m. ET. The requested current full-market snapshot and October 29 grouped daily market summary were not included in the connected plan. The SPY historical request returned observations only through October 28, despite a requested October 29 end date. These older bars were not treated as an October 29 signal.

The market summary above comes from AP, independently of Massive. It is not a substitute for synchronized company-level prices, technical indicators, options chains, or executable quotes. No real-time candidate ranking, current borrow availability, or calibrated five-day decline probability was established in this session. Current data coverage can be checked at https://massive.com/pricing.

## How the original screens change

| Original requirement | v1.0 treatment | Reason |
| --- | --- | --- |
| Price above $5 or $7.50 | At least $10 | Proposed filter to reduce low-price execution and squeeze complications |
| Market cap above $1 billion | Keep | Retains the original company-size floor |
| Average volume above 1 million or 3.5 million | ADV20 at least 3.5 million shares; average daily dollar volume at least $100 million | Combine share liquidity with dollar liquidity |
| Average intraday range above 2% | Report it; use ATR14/price of 2%–6% as the proposed volatility gate | ATR includes overnight gaps and permits volatility-adjusted targets |
| RSI14 at least 65 now | Recent extension during the last five sessions | A useful failure can occur after RSI falls below 65 |
| RSI, Stochastic, Bollinger and 52-week proximity all heavily weighted | Keep most as diagnostics; cap extension's contribution | These measures contain overlapping momentum information |
| RVOL at least 1.5 | Score high volume only with a weak close and a down session | High volume during a strong close can support continuation |
| Within 5% of 52-week high | Diagnostic, not mandatory | A failed breakout may already have moved farther below the high |
| Highest closing-range position gets more short points | Low closing-range position supports the failure score | A close near the high generally shows buyers retaining control |
| High P/E means overvalued | Industry comparison as background, with earnings and cash-flow context | Expensive is not a five-day timing signal |
| Options activity determines direction | Treat as positioning and risk context | Puts, calls and open interest can reflect hedges, spreads or closing trades |

Schwab explicitly cautions that overbought conditions can persist during strong trends. RSI and Stochastic are useful descriptions of momentum, but do not establish that a reversal has started. [3]

## 1. Universe and data gates

Use U.S. company common shares listed on NYSE, Nasdaq, or NYSE American. Exclude ETFs, leveraged products, preferred shares, warrants, units, OTC issues and ADRs from this initial research population. Require at least 252 completed trading sessions for comparable 52-week diagnostics.

All thresholds below are starting assumptions to test, not empirically optimal values.

| Field | Proposed requirement |
| --- | --- |
| Last completed regular-session close | At least $10 |
| Market capitalization | Greater than $1 billion; record its as-of date |
| ADV20 | At least 3.5 million shares, measured over the 20 completed sessions preceding the signal session |
| Average daily dollar volume | At least $100 million, using the mean of daily close times daily volume over those same 20 sessions |
| ATR14 / close | Between 2% and 6%, inclusive |
| Execution spread | No more than 0.15% of the quote midpoint during regular hours; recheck at entry |
| Borrow | Broker confirms availability and a current indicative fee; missing borrow information prevents an executable designation |
| Company events | No confirmed earnings, regulatory decision, shareholder vote, or similar scheduled binary event during the planned holding window |
| Dividends | Ex-dividend date and any special distribution checked before entry; first training version excludes a position crossing an ex-dividend date |
| Price integrity | Corporate actions reconciled; no unresolved split, special-distribution or stale-bar anomaly |

Borrow availability can change and stock can be recalled. A current quote is necessary, but does not guarantee that borrow will remain available. Shorts can owe dividend payments and face margin calls or forced covering. [8, 9]

Mark an uncertain earnings date or missing essential execution field as DATA INCOMPLETE. A missing value is not zero and is not evidence of safety.

## 2. Watchlist: preserve recent extension

At signal close t, qualify for the watchlist if at least one of these occurred during sessions t-4 through t:

- RSI14 was at least 70.
- The close was at least two ATR14 above SMA20: (close − SMA20) / ATR14 ≥ 2.

Calculate each historical observation using indicators available on that observation's date. Report the latest one-day and two-day returns, but do not require a positive two-day return after a failure has begun.

The watchlist alone gives no entry signal. A stock can stay on it while making higher highs and holding above VWAP. A strong close following genuinely improved earnings or guidance is a reason to investigate continuation risk, not automatically award more short points.

## 3. Failure definition

Freeze breakout level B before the two-session event window:

    B = highest daily high over sessions t-21 through t-2
    a = ATR14 as of t-2

Require an attempted breakout during t-1 or t, with a high greater than B + 0.10a, followed by a close at t below B − 0.10a. Keep B fixed; do not choose a different level after seeing the outcome.

Calculate closing-range position as:

    CRP = (close − low) / (high − low)

CRP of 0 means a close at the low; 1 means a close at the high. A zero-range session is undefined and cannot satisfy a CRP test.

This version focuses on a specific failed-breakout setup. A stock already deep in a downtrend without a recent extension and breakout attempt belongs in a separately tested strategy.

## 4. Short Setup Score: 0–100

Award the listed points only when the corresponding test is true. There are no partial discretionary points. This is a proposed checklist score, not a decline probability.

| Category | Exact tests | Maximum points |
| --- | --- | ---: |
| Price failure | Defined breakout attempt and close back below B: 15; CRP ≤ 0.35: 10; close below the previous session's low: 10 | 35 |
| Relative weakness | Stock's two-session return minus its preassigned sector ETF's return ≤ −2 percentage points: 10; close below SMA5 and SMA5 below its prior-session value: 10 | 20 |
| Recent extension | Maximum RSI14 during the last five sessions ≥ 70: 5; maximum (close − SMA20)/ATR14 during those sessions ≥ 2: 10 | 15 |
| Participation | CMF20 < 0: 5; signal session is down from its open, CRP ≤ 0.35 and RVOL30 ≥ 1.5: 5 | 10 |
| Market and sector | Sector ETF below SMA20 and its SMA20 below its value five sessions earlier: 5; both SPY and QQQ below their SMA20 and common-stock breadth above SMA20 below 40%: 5 | 10 |
| Trade economics | Five-percent target has room above meaningful support and net reward/risk ≥ 2: 5; estimated round-trip costs are at most 10% of gross target profit: 5 | 10 |
| Total | Sum of the above | 100 |

Provisional selection rule: score ≥ 75, price-failure subtotal ≥ 25, relative-weakness subtotal ≥ 10, and every hard gate passes. These cutoffs are hypotheses to evaluate.

Rank passing research setups by total score, then net reward/risk, then lower estimated costs. Ties are not resolved by an invented forecast probability. A passing score still requires the entry trigger.

Fix each company's sector mapping before looking at the day's returns. Use a documented sector ETF with aligned daily bars. Do not choose whichever comparison makes the stock look weakest.

For breadth, use the percentage of point-in-time eligible U.S. common stocks with at least 50 sessions of history that close above their SMA20. Exclude funds, preferred shares and other non-common securities. Report the denominator and date. Do not substitute SPY's daily change for breadth.

If a score input is missing, report a known-points lower bound and an upper bound adding the unavailable weights. Do not rescale the remaining weights to 100. Status remains DATA INCOMPLETE until all score and gate inputs are available. Optional options or retail fields can be unavailable without fabricating them or raising the score.

## 5. Regime rules for the remainder of 2026

The market regime must be measured again at each screen. October 29's news does not establish November's or December's regime.

| Observed state | Research response |
| --- | --- |
| SPY and QQQ both above SMA20, SMA20 above SMA50, and breadth ≥ 60% | Strong broad uptrend: keep this countertrend setup in paper trading only |
| Index/sector signals disagree, or headline index losses coexist with positive breadth | Mixed regime: demand company failure and sector-relative weakness; treat an index decline alone as insufficient |
| SPY and QQQ below SMA20, breadth < 40%, and the candidate's sector meets the weakness test | More conditions align with the short thesis; retain every entry, borrow and cost gate |
| Missing synchronized trend or breadth data | Do not assert a quantified regime; show DATA INCOMPLETE |

Monitor the VIX level and term structure, Treasury yields, earnings revisions and major news as context. A high VIX or a rate increase alone does not make a particular short attractive. Crowded bearish positioning can create rebound and squeeze risk.

For the first paper sample, exclude new entries whose five-session holding window includes a scheduled FOMC decision, CPI release, Employment Situation release, or the general-election results window. This makes the initial sample deliberately sparse and avoids mixing an event strategy with the price-failure strategy. If later testing supports event exposure, version and test that change separately.

Important currently scheduled dates, all Eastern Time where a time is shown:

| Date | Event | Source |
| --- | --- | --- |
| November 3 | Federal general election; flag results overnight November 3–4 | FEC [4] |
| November 6, 8:30 a.m. | Employment Situation | BLS [5] |
| November 10, 8:30 a.m. | CPI | BLS [5] |
| November 26 | Stock-market holiday | NYSE [7] |
| November 27, 1 p.m. | Early regular-session close | NYSE [7] |
| December 4, 8:30 a.m. | Employment Situation | BLS [6] |
| December 8–9 | FOMC meeting; scheduled decision December 9 | Federal Reserve [2] |
| December 10, 8:30 a.m. | CPI | BLS [6] |
| December 24, 1 p.m. | Early regular-session close | NYSE [7] |
| December 25 | Stock-market holiday | NYSE [7] |

Verify calendars again before entry because schedules can change. Do not assume that a year-end rally, tax-loss selling or fund repositioning will occur in a particular stock. On half-days, compare intraday volume with equivalent half-day/time-of-day observations or exclude those signal sessions from v1.0.

## 6. Entry, stop, targets and time exit

This is the exact proposed v1.0 paper-trading execution rule. It is intentionally narrow so its results can be reproduced.

1. Form the signal only after the regular-session close at t. No fill is assumed at that close.
2. On the next trading session, wait for the 9:30–9:45 a.m. regular-session bar to finish. Require its close below the signal session's low and below regular-session VWAP as of 9:45. If either condition fails, there is no entry for that signal. Do not recycle it later that day as though the same rule triggered.
3. Use the first executable quote after that confirmation. Recheck spread, borrow, events, support and net reward/risk at the actual planned sale price E. If the opening gap or slippage makes the trade fail a gate, skip it. A limit order must have an explicit acceptable price; an unfilled order is not counted as a trade.
4. Set the initial buy-to-cover stop S above the greater of the signal-day high and the first 15-minute high, plus 0.25 times signal-day ATR14. Skip if the resulting risk does not permit the target at the required reward/risk.
5. Set the full-position price target T = 0.95E. Require the nearest meaningful chart support to be below T, with enough buffer for ordinary execution. Chart and timestamp the support choice before entry; do not move it after the trade.
6. Cover at the first of the target, the stop, or the regular-session close on the fifth holding session. If a new scheduled company event or ex-dividend date becomes known inside the window, exit before exposure rather than ignore the gate.
7. Never widen the stop or average up into a losing short. Any later setup requires a new independent signal.

Daily ATR cover references are E − ATR14 and E − 2×ATR14. They are planning landmarks, not probabilities or guaranteed destinations. In v1.0 they do not create additional partial-exit rules; that would change the tested strategy.

For support and resistance, use timestamped recent swing levels, the failed breakout level, and relevant gap boundaries. Mark zones rather than pretend every level is precise to a cent. If support sits only 2% below E, reject the five-percent objective instead of assuming support must break.

Short losses can be unlimited. A buy stop is a trigger, not a guaranteed maximum loss; a stop-limit order can remain unfilled. Overnight gaps, halts and borrow recalls can defeat a planned exit. [8, 9]

## 7. Costs, position sizing and a worked example

Let c be estimated round-trip cost per share, including execution slippage, fees and expected stock-borrow carry. Incorporate dividend liability if studying a later version that permits crossing ex-dividend dates.

    Gross target reward per share = E − T
    Net target reward per share = E − T − c
    Planned loss per share = S − E + c
    Net reward/risk = (E − T − c) / (S − E + c)
    Shares = floor(planned dollar-risk budget / planned loss per share)

For planning, approximate borrow cost as borrowed notional × annual quoted fee × calendar days / broker day-count basis. Use the broker's actual conventions, collateral adjustment, settlement treatment and changing daily rate when available. Five trading sessions can span seven or more calendar days.

An illustrative training budget is 0.25% of hypothetical account equity per trade, with no more than 0.75% total planned open risk and 0.50% concentrated in one sector. These are examples for paper testing, not calibrated suitability limits. Margin and buying-power requirements are separate constraints. Also stress-test correlated positions under adverse overnight gaps; summed stop risk does not capture that exposure.

| Hypothetical input | Value |
| --- | ---: |
| Account equity | $100,000 |
| Planned risk budget, 0.25% | $250 |
| Short entry E | $100 |
| Initial stop S | $102 |
| Five-percent target T | $95 |
| ATR14 | $3 |
| Nearest meaningful support | $94.50 |
| Estimated round-trip cost c | $0.20/share |
| Planned loss/share | $2.20 |
| Net target reward/share | $4.80 |
| Net reward/risk | 2.18 |
| Shares | 113 |
| Planned loss at assumed stop fill | $248.60 |
| Net target profit | $542.40 |
| Loss if a gap forces covering at $105, using the same cost estimate | $587.60 |

The $0.20 cost estimate is hypothetical. It is not a verified quote for any stock. A roughly 2% annual borrow fee over seven calendar days on a $100 notional is about $0.039/share on a 360-day basis, leaving about $0.161 for the other assumed costs. Actual costs can differ.

The target is a 5% stock-price decline; the example's net profit is 4.8% of entry notional. Short-sale proceeds are not the same as spendable account equity. The gap example shows why the stop-sized risk budget is not a loss ceiling.

The training phase uses paper trades. If later considering real positions, monitor actual broker maintenance requirements and available buying power as well as position size. No trade execution or account change is authorized by this research document.

## 8. Definitions for the full daily report

Calculate everything from a consistent price series and completed regular sessions. Specify whether historical prices are split-adjusted, dividend-adjusted or unadjusted. Use split-adjusted OHLC and split-consistent share volumes for this model; reconcile dividends separately. Do not mix an after-hours quote with final daily indicators without labeling both timestamps.

| Requested metric | Definition and use |
| --- | --- |
| Price and percentage gain | Signal-session close and return versus previous close; show two-session return separately; executable quote gets its own timestamp |
| ADV20 | Mean share volume for the 20 completed sessions before the signal session |
| Range20 | Mean of (daily high − daily low) / prior close over the last 20 completed sessions; report as percent |
| SMA20 extension | 100 × (close/SMA20 − 1), plus the ATR-normalized extension used in scoring |
| RSI14 | Wilder smoothing, with a consistent warm-up; an indicator's effective date must match the bar series |
| Stochastic(14,3,3) | Raw %K = 100×(close − lowest low14)/(highest high14 − lowest low14); slow %K is its 3-session SMA; %D is a 3-session SMA of slow %K |
| Bearish Stochastic crossover | Slow %K crosses from at/above %D to below %D; report whether it began above 80; distinguish this event from merely having a high reading |
| Bollinger %B | (close − lower band)/(upper band − lower band), using SMA20 and ±2 population standard deviations; %B above 1 means above the upper band |
| RVOL30 | Signal-session volume divided by mean volume of the preceding 30 sessions, excluding the signal session; use this consistently instead of swapping ADV20 into its denominator |
| Intraday RVOL | Cumulative regular-session volume divided by historical cumulative volume at the same time, using comparable sessions; not current volume divided by full-day average |
| Distance below 52-week high | 100×(1 − close/highest high252), using 252 sessions including signal day |
| CRP | (close − low)/(high − low), reported 0–1 or 0%–100% with units explicit |
| ATR14 | Wilder-smoothed true range; true range = max(high−low, absolute(high−prior close), absolute(low−prior close)) |
| CMF20 | Sum over 20 sessions of volume×(2×close−high−low)/(high−low), divided by total volume; convention for a zero-range day is money-flow multiplier 0 |
| OBV | Cumulative signed daily volume using close versus prior close; a supporting participation proxy, not an investor identity |
| Volume divergence | Predefine comparison swing dates: a higher price high paired with lower participation/OBV; report the dates rather than an unexplained bearish label |
| P/E versus industry | Compare consistent trailing or forward definitions and dates; label nonpositive EPS as not meaningful; review growth, margins, cash flow and share dilution |
| Institutional accumulation/distribution | Label CMF/OBV and price-volume evidence as proxies; ownership filings are lagged and cannot identify today's institutional trades |
| Retail activity | Show a sourced, timestamped measure if available; options volume, odd-lot activity, off-exchange volume or social posts alone do not prove retail buying |
| Short interest | State settlement/publication date, percent of float and days to cover; distinguish from daily short-sale volume [11] |
| Options volume versus average | Aggregate a defined chain/expiry universe, divided by its comparable prior-20-session daily volume; intraday comparisons require same-time baselines |
| Put/call | Put volume/call volume; show open-interest put/call separately, with expiry coverage and effective date |
| IV percentile | Percent of the preceding 252 daily observations below today's comparable 30-day constant-maturity ATM IV; do not confuse percentile with min/max IV rank |
| Unusual options | Predeclare the test: contract volume at least 3× its prior-20-session mean and at least 500 contracts; list strike, expiry, call/put, volume and available OI; without contract history, say concentration only |
| Next-day change in OI | Compare the same contract's next available clearing-cycle OI with the prior observation only after it is published; until then, pending [10] |
| Catalyst | Link the company filing, investor-relations release or official event calendar; explain the economic change and possible adverse-to-short scenario |

Options open interest counts outstanding contracts and is not inherently bullish or bearish. It is updated after clearing, so next-day changes cannot be known in advance. Call volume does not reveal whether customers bought calls, sold covered calls or traded spreads. Do not award directional points for unverified flow narratives. [10]

P/E, IV percentile, options flow and ownership context remain annotations in v1.0. They receive no score points until their incremental usefulness is demonstrated. If IV history or comparable peer estimates are unavailable, report N/A rather than manufacture a percentile or industry median.

## 9. Keep the original Statistical Extension Score for comparison

The earlier stock-research report in this repository defined fixed normalization for the original eight components. Preserve that score as a separate description of extension, not the trade ranking or a probability.

For each component, normalize linearly between the endpoints below and clip to 0–100. Then multiply by its weight and sum.

| Component | Weight | 0-point endpoint | 100-point endpoint |
| --- | ---: | ---: | ---: |
| RSI14 | 25% | 65 | 90 |
| Range20 | 15% | 2% | 10% |
| SMA20 extension | 15% | 0% | 40% |
| Bollinger %B | 10% | 0.50 | 1.20 |
| Slow Stochastic %K | 10% | 60 | 100 |
| Relative volume | 10% | 0.5 | 2.5 |
| Distance below 52-week high | 10% | 20% | 0% |
| CRP | 5% | 0 | 1 |

For repeatability, designate RVOL30 as this version's relative-volume input and record that denominator choice as a version change; the prior report's RVOL horizon was not consistently specified. Historical comparisons require recalculation from the same standardized series. Do not reuse mixed-date overlays as though they were synchronized historical signals.

The high-CRP contribution makes sense as a momentum-description component but must not be mistaken for evidence that buyers have failed. The new Short Setup Score explicitly gives failure points for a low CRP.

## 10. Learning plan and evaluation

Begin with 30 consecutive qualifying paper trades, keeping every signal and rejection. This is a workflow-learning sample, not enough evidence to validate a market edge. Do not fill a quota on days with no qualifying setups.

| Stage | Practice | Evidence to retain |
| --- | --- | --- |
| First 10 paper trades | Identify extension versus actual failure; apply the entry trigger exactly | Chart before entry, frozen B, regime, score, rejected alternatives |
| Next 10 | Verify planned support, stop, borrow and all-in costs | Planned versus achievable fill, actual spread, quote timestamps, gap stress |
| Next 10 | Execute the same exits consistently | Target/stop/time exit, missed fills, adverse/favorable excursion, rule adherence |
| After that sample | Diagnose process errors before adjusting parameters | Separate rule violations from losses taken under the rules |

Before describing any statistical probability, run a sufficiently broad historical study with point-in-time listings including delisted stocks, corporate-action handling, archived news/earnings calendars, synchronized sector mappings, and realistic borrow availability and costs. If historical borrow cannot be reconstructed, disclose the bias rather than call the backtest executable.

Use a chronological development period, a validation period and an untouched later test period. Because labels cover up to ten sessions, purge overlapping examples and leave a ten-session embargo around split boundaries. Group uncertainty estimates by date or week to account for correlated signals. Never fit the thresholds repeatedly to the final test period.

Compare against:

- The original extension-only screen under the same execution and cost assumptions.
- A simple failed-breakout rule without the new score.
- Eligible stocks matched by sector and volatility, with the same holding period and exits.

Measure both the frequency of a 5% target touch and the more relevant frequency of reaching that target before the stop and any earlier implemented exit. Count entry session as day one. A stock that rises through the stop before eventually declining 5% is not a successful implemented trade.

With daily data alone, the order of same-day target and stop hits can be ambiguous. Use intraday observations; otherwise mark ambiguity and apply a disclosed conservative stop-first rule. This v1.0 entry rule needs intraday data; a next-day-open daily backtest is a different strategy.

Report net expectancy, average win/loss in R, profit factor, maximum drawdown, worst overnight gap, target-before-stop hit rate, time-exit frequency, maximum adverse and favorable excursion, rejection rate, and performance by regime and sector. Define R as initial planned dollar risk, retaining realized losses greater than 1R when gaps occur.

For probability estimates, show held-out sample count, a confidence interval and calibration by probability bucket. A score of 80/100 is never an 80% probability. Label five-day and ten-day outcomes separately; do not substitute a ten-day success for a missed five-day objective.

Change one rule at a time in a new version, and keep the earlier version's results. A more elaborate model is worthwhile only if it improves held-out net results or reduces material risk after its additional data and execution costs.

## 11. Reusable daily research request

Use the following as the consolidated query. Supply the attached v1.0 definitions so a new analyst does not infer different formulas.

> Run Failed Breakout Short Model v1.0 for the next U.S. regular trading session, using the latest completed regular-session data and separately timestamped execution quotes. State the as-of date, time zone, actual latest bar dates, source, delay, universe coverage and missing entitlements before listing candidates. Never call delayed or prior-day data real time.
>
> Apply the universe, liquidity, volatility, company-event, dividend, macro-window, regime, borrow, spread and trade-economics gates in this playbook. Preserve stocks with recent extension even if their current RSI is below 65. Use the frozen breakout level, exact failure definition and exact 100-point Short Setup Score. Require score at least 75, failure at least 25 and relative weakness at least 10. Keep the legacy Extension Score separate.
>
> Produce up to ten ranked research candidates, allowing zero. Show watchlist, confirmed setup, entry-trigger pending, ready for review, rejected and data-incomplete states accurately. A missing current quote, borrow check or essential gate prevents a ready designation. No automatic trade execution.
>
> For each candidate, show price, one- and two-session gain, market cap, ADV20, average dollar volume, ATR14/price, Range20, RVOL30, RSI14, Stochastic(14,3,3), SMA20 percent and ATR extension, Bollinger %B, 52-week-high distance, CRP, frozen breakout level, sector-relative return, score subtotals, legacy score, timestamped support/resistance, proposed entry trigger, stop, 5% target, one- and two-ATR cover references, net reward/risk and estimated costs. Recalculate economics at an actual proposed fill, not only the prior close.
>
> Include sourced valuation context, earnings and guidance catalysts, CMF/OBV participation proxies, short-interest date/float/days-to-cover, retail-data availability, options volume versus average, volume and OI put/call ratios, comparable IV percentile, unusual strikes/expirations, and pending or published next-cycle OI change. Mark unavailable fields N/A; never infer institutional or retail identity from aggregate volume.
>
> End with rejected candidates and precise rejection reasons, the strongest evidence against each surviving short thesis, the upcoming macro/company events, and the data gaps that prevent execution. State that no probability of a 5% decline has been estimated unless a documented held-out calibration is available. Keep five-session trade rules separate from ten-session research outcomes.

## 12. One-page paper-trade record

Copy one record for every signal, including rejected or unfilled signals.

| Field | Record |
| --- | --- |
| Signal ID / date / model version | |
| Symbol / sector ETF / source / timestamps | |
| Regime and breadth / upcoming events | |
| Universe and borrow gates / fee / expiry of check | |
| Extension evidence / frozen B / failure evidence | |
| Score A / B / C / D / E / F / total / completeness | |
| First 15-minute trigger / VWAP / actual quote | |
| Entry / initial stop / 5% target / support / ATR | |
| Planned cost/share / net reward/risk | |
| Shares / planned dollar risk / portfolio and sector risk | |
| +5% and +10% overnight-gap stress loss | |
| Strongest evidence against the short thesis | |
| Outcome / exit reason / actual costs / net P&L / R | |
| Maximum favorable and adverse excursion / five-day label | |
| Ten-day diagnostic, separate from implemented trade | |
| Rule adherence / one specific lesson | |

## Sources and scope

The execution thresholds, scoring weights, training budgets and entry rules are proposed research choices. The sources below support the stated market context, calendars, indicator interpretation and market mechanics; they do not validate this strategy's profitability.

1. AP, October 29 close: https://apnews.com/article/wall-street-stocks-dow-nasdaq-2c26b0b1bbc3bfab9f3f4b902172f458
2. Federal Reserve, October 28 statement and 2026 meeting calendar: https://www.federalreserve.gov/newsevents/pressreleases/monetary20261028a.htm and https://www.federalreserve.gov/monetarypolicy/fomccalendars.htm
3. Schwab, trend strength and RSI: https://www.schwab.com/learn/story/spot-and-stick-to-trends-with-adx-and-rsi
4. FEC, general-election date: https://www.fec.gov/introduction-campaign-finance/election-results-and-voting-information/
5. BLS November release schedule: https://www.bls.gov/schedule/2026/11_sched_list.htm
6. BLS December release schedule: https://www.bls.gov/schedule/2026/12_sched_list.htm
7. NYSE holidays and hours: https://www.nyse.com/trade/hours-calendars
8. SEC Investor.gov, short sales: https://www.investor.gov/introduction-investing/general-resources/news-alerts/alerts-bulletins/investor-bulletins-51
9. SEC Investor.gov, stop and stop-limit orders: https://www.investor.gov/introduction-investing/general-resources/news-alerts/alerts-bulletins/investor-bulletins-15 ; Schwab short-selling mechanics and risks: https://www.schwab.com/learn/story/ins-and-outs-short-selling
10. Options Industry Council, open interest and clearing: https://www.optionseducation.org/referencelibrary/faq/general-information ; put/call interpretation: https://www.optionseducation.org/referencelibrary/faq/technical-information
11. FINRA, short interest versus daily short-sale volume: https://syndication.finra.org/content/short-interest-what-it-what-it-not
12. Repository's prior extension methodology, retained for comparison only: https://github.com/Jeffrey-Fisher-GitHub/ChatGPT-Solutions/blob/main/stock-research/2026-10-02-statistical-extension-screen.md

No live candidate list, historical backtest or calibrated decline-probability model accompanies v1.0. The next evidence milestone is an accurately collected paper sample and a properly controlled historical comparison.
