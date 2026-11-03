/* New quiz extensions; load before sketch.js. */
globalThis.QuizHelpers = (() => {
  function rankPlazas(plazas) {
    // Q12: return a new array, queueSize descending then milepost ascending.
    // Do not modify the input array or its plaza objects.
    throw new Error('Q12 rankPlazas');
  }

  function drawQueueBadge(p) {
    // Q13: this leaks the transform and drawing style into later drawings.
    translate(mpToX(p.milepost), roadY(p.milepost) - 65);
    fill(p.queueSize >= 9 ? '#b42318' : '#166534');
    noStroke();
    textAlign(CENTER, CENTER);
    textSize(12);
    text(p.queueSize, 0, 0);
  }

  function tripProgress(c, now) {
    // Q14: a teammate tried to simulate congestion with this factor of 0.5.
    // The Java server guarantees positive tripDurationMs for valid cars.
    return Math.max(0, Math.min(1, 0.5 * (now - c.tripStartMs) / c.tripDurationMs));
  }

  function countSegments(plazas, traveling, now) {
    // Q15: plazas are in increasing milepost order, with distinct names/MPs.
    // Return [{from, to, east, west}, ...] for consecutive plazas.
    // Include only status TRAVELING, positive duration, known endpoints,
    // and raw progress >= 0 and < 1. Ignore zero-distance trips.
    // Use [lower milepost, upper milepost) for BOTH directions.
    throw new Error('Q15 countSegments');
  }

  // Supplied p5 drawing adapter. You implement countSegments above.
  function drawSegmentCounts(plazas, traveling, now) {
    const counts = countSegments(plazas, traveling, now);
    push();
    noStroke();
    fill(31, 42, 51);
    textAlign(CENTER, CENTER);
    textSize(9);
    counts.forEach((c, i) => {
      const mid = (plazas[i].milepost + plazas[i + 1].milepost) / 2;
      text('E ' + c.east + ' W ' + c.west, mpToX(mid), roadY(mid) + 43);
    });
    pop();
  }

  return { rankPlazas, drawQueueBadge, tripProgress, countSegments, drawSegmentCounts };
})();
