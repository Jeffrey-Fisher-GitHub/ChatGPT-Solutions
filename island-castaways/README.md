# Castaways — 730 Days

A zero-dependency, illustrated browser simulation about **12 survivors** stranded after an aircraft crash on a **1,200-acre** tropical island.

## Run

Open [index.html](./index.html) in Chrome or Edge. The animation is drawn live with Canvas 2D; nothing to install or host.

## Geography and resources

A forested mountain, freshwater stream leading to the sea, sheltered beaches, coconuts, fish, fruit, vegetables, timber and buildable clearings. The airplane wreck stays visible on the shore.

## Story and animation

* Exactly **730 simulated days**; **15 seconds per day** at 1× speed.
* Total footage at 1×: **10,950 seconds (3:02:30)**.
* Seasonal weather, moving characters, fishing, gardens, huts, signal/lookout and changing village.
* Milestones on days 1, 3, 7, 15, 30, 60, 90, 120, 180, 240, 300, 365, 450, 540, 630 and 730.
* Controls for pause, day selection and accelerated preview.
* JSON export: full reproducible 730-day simulation log.

## Recording video

Use **Record 15-sec day** to generate a WebM for the selected day. **Record all 730 days** records the whole sequence continuously with MediaRecorder at 1×; this requires over three hours of foreground browser runtime plus encoding/storage capacity. Video is generated **in your own browser** and downloaded automatically at stop; video files are not stored in GitHub. Keep your device awake and the browser visible. Browser throttling, memory limits or insufficient disk space can interrupt long captures. For production, record individual days and concatenate with FFmpeg (example below).

```bash
# Record short clips from browser, create a concat-list.txt file of filenames, then:
ffmpeg -f concat -safe 0 -i concat-list.txt -c copy castaways-two-years.webm
```

## Model scope and limitations

This is an **illustrative deterministic narrative** with resource milestones, not a detailed agent-based survival, agriculture or population dynamics model. The castaways are named Avery, Maya, Eli, Sofia, Noah, Zoe, Marcus, Lina, Priya, Theo, Iris and Kai. Weather is synthetic and deaths are not scripted.

Future extensions: explicit food inventories and calorie budgets; individual fatigue, illness, skills and relationships; geographic pathfinding; construction dependencies; storm damage, ecological regeneration and rescue probability; headless offline rendering with a video encoder.

## License

Demo code provided as a starter project.