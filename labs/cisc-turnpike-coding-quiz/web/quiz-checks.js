globalThis.QuizChecks = (() => {
  function check(ok, label) { if (!ok) throw new Error(label); }
  const plazas = [
    {name: 'Warrendale', milepost: 30, queueSize: 9},
    {name: 'New Stanton', milepost: 75, queueSize: 1},
    {name: 'Somerset', milepost: 110, queueSize: 9}
  ];
  function car(id, entry, exit, tripStartMs = 0) {
    return {id, entry, exit, tripStartMs, tripDurationMs: 8000, status: 'TRAVELING'};
  }
  function fixtureCars() {
    return [
      car('A', 'Warrendale', 'Somerset'),
      car('B', 'Somerset', 'Warrendale'),
      car('C', 'Warrendale', 'Somerset', -500),
      car('D', 'Somerset', 'Warrendale', 500),
      car('E', 'Warrendale', 'Somerset', -4000),
      car('F', 'Warrendale', 'Unknown')
    ];
  }
  const checks = {
    Q12() {
      const saved = JSON.stringify(plazas);
      const out = QuizHelpers.rankPlazas(plazas);
      check(out !== plazas && JSON.stringify(plazas) === saved, 'input must be unchanged');
      check(out.map(p => p.name).join('|') === 'Warrendale|Somerset|New Stanton', 'ranking and ties');
      check(QuizHelpers.rankPlazas([]).length === 0, 'empty ranking');
    },
    Q14() {
      const c = {tripStartMs: 1000, tripDurationMs: 4000};
      check(QuizHelpers.tripProgress(c, 3000) === 0.5, 'halfway time');
      check(QuizHelpers.tripProgress(c, 1000) === 0, 'start time');
      check(QuizHelpers.tripProgress(c, 0) === 0, 'before start');
      check(QuizHelpers.tripProgress(c, 5000) === 1, 'finish time');
      check(QuizHelpers.tripProgress(c, 9000) === 1, 'after finish');
    },
    Q15() {
      const cars = fixtureCars();
      const before = JSON.stringify([plazas, cars]);
      const expected = [
        {from:'Warrendale', to:'New Stanton', east:1, west:1},
        {from:'New Stanton', to:'Somerset', east:1, west:1}
      ];
      const result = QuizHelpers.countSegments(plazas, cars, 4000);
      check(JSON.stringify(result) === JSON.stringify(expected), 'boundary and direction counts');
      check(JSON.stringify([plazas, cars]) === before, 'inputs mutated');
      check(QuizHelpers.countSegments([], [], 4000).length === 0, 'no plazas');
      check(QuizHelpers.countSegments(plazas, [], 4000).every(s => s.east === 0 && s.west === 0), 'no cars');
      const excluded = [
        {...cars[0], tripStartMs:5000}, {...cars[0], tripDurationMs:0},
        {...cars[0], status:'EXITED'}, {...cars[0], exit:'Warrendale'}
      ];
      check(QuizHelpers.countSegments(plazas, excluded, 4000).every(s => s.east === 0 && s.west === 0), 'invalid or inactive trips');
    }
  };
  function run(only) {
    let passed = 0, attempted = 0;
    for (const [id, test] of Object.entries(checks)) {
      if (only && id !== only.toUpperCase()) continue;
      attempted++;
      try { test(); console.log(id + ' PASS'); passed++; }
      catch (e) { console.log(id + ' FAIL: ' + e.message); }
    }
    console.log(passed + '/' + attempted + ' question checks passed');
    return {passed, attempted};
  }
  return {run, fixtureCars};
})();
