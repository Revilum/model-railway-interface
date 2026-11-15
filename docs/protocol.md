# Client Protocol

Each exchanged message has a `command` key,
describing the intent of the message. 
For each supported command, the usage is detailed here.

## `connect`

```json
{"command":"connect"}
```

This command causes the daemon to connect the synchronization threads
to the hardware and update the railway state.

No respose is sent by the daemon.

## `disconnect`

```json
{"command":"disconnect"}
```

This command stops the synchronization between the daemon and the arduino.

No respose is sent by the daemon.

## `shutdown`

```json
{"command":"shutdown"}
```

This command performs a disconnect but also terminates 
the daemon completely afterwards.

Use with care!

No respose is sent by the daemon.

## `config`

```json
{"command":"config"}
```

This command queries the daemon for the configuration of the connected arduino
nodes.

The daemon sends a respose containing the attached tracks, points, signals and contacts.
Signals and contacts are identified by the name of the track segment as well as a number (counted in main travel direction). Tracks and points are identified by their name.
Additional hardware like the railway crossing are identified by special names.

```json
{ "command":
    "config",
  "contacts": [
    {"name": "KH_ST_3", "number": 1},
    {"name": "KH_ST_4", "number": 1},
    {"name": "KH_ST_5", "number": 1},
    {"name": "KH_LN_5", "number": 1},
    {"name": "OI_LN_2", "number": 1},
    {"name": "KH_LN_6", "number": 1},
    {"name": "OI_LN_0", "number": 1},
    {"name": "KH_LN_0", "number": 0},
    {"name": "KIO_LN_1", "number": 0},
    {"name": "IC_LN_4", "number": 1},
    {"name": "OC_LN_5", "number": 0},
    {"name": "OC_LN_4", "number": 1},
    {"name": "OI_LN_0", "number": 0},
    {"name": "KH_ST_2", "number": 1},
    {"name": "IC_LN_0", "number": 1},
    {"name": "IC_LN_1", "number": 0},
    {"name": "OC_LN_1", "number": 0},
    {"name": "KH_ST_1", "number": 1} ],
  "signals": [
    {"name": "KH_ST_5", "number": 1},
    {"name": "KH_ST_4", "number": 1},
    {"name": "IC_LN_4", "number": 1},
    {"name": "KH_LN_6", "number": 1},
    {"name": "KH_LN_5", "number": 1},
    {"name": "KH_LN_0", "number": 0},
    {"name": "KIO_LN_1", "number": 0},
    {"name": "KH_ST_1", "number": 1},
    {"name": "KH_ST_2", "number": 1},
    {"name": "KH_ST_3", "number": 1},
    {"name": "OI_LN_2", "number": 1},
    {"name": "IC_LN_0", "number": 1},
    {"name": "OC_LN_4", "number": 1} ],
  "tracks":
    ["KH_ST_5", "IC_JCT_0", "KH_LN_5", "OC_JCT_0", "IC_LN_4", "OI_LN_2", "OC_LN_1", "KH_ST_6", "KH_LN_6", "IC_LN_1", "OI_LN_0", "OC_LN_4"],
  "points":
    ["L_7", "PNT_13", "L_6", "PNT_10", "PNT_14", "PNT_11", "PNT_12", "L_8", "PNT_8", "PNT_9", "L_11", "PNT_7", "PNT_6", "PNT_5", "L_10", "L_9"]
}
```

## `contacts`

```json
{"command":"contacts"}
```

This command queries the daemon for the current state of the reed contacts.

The daemon answers with a list of all connected contacts, their number of recognized events (modulo 4) and the type of the last event.

```json
{ "command": "contacts",
  "contacts": [
    {"name": "KH_ST_3", "type": 0, "count": 0, "number": 1},
    {"name": "KH_ST_4", "type": 0, "count": 0, "number": 1},
    {"name": "KH_ST_5", "type": 0, "count": 0, "number": 1},
    {"name": "KH_LN_5", "type": 0, "count": 0, "number": 1},
    {"name": "OI_LN_2", "type": 0, "count": 0, "number": 1},
    {"name": "KH_LN_6", "type": 0, "count": 0, "number": 1},
    {"name": "OI_LN_0", "type": 0, "count": 0, "number": 1},
    {"name": "KH_LN_0", "type": 0, "count": 0, "number": 0},
    {"name": "KIO_LN_1", "type": 0, "count": 0, "number": 0},
    {"name": "IC_LN_4", "type": 1, "count": 2, "number": 1},
    {"name": "OC_LN_5", "type": 0, "count": 0, "number": 0},
    {"name": "OC_LN_4", "type": 0, "count": 0, "number": 1},
    {"name": "OI_LN_0", "type": 0, "count": 0, "number": 0},
    {"name": "KH_ST_2", "type": 0, "count": 0, "number": 1},
    {"name": "IC_LN_0", "type": 1, "count": 2, "number": 1},
    {"name": "IC_LN_1", "type": 1, "count": 2, "number": 0},
    {"name": "OC_LN_1", "type": 0, "count": 0, "number": 0},
    {"name": "KH_ST_1", "type": 0, "count": 0, "number": 1} ]}
```

## `status`

```json
{ "command":
    "status",
  "signals": [
    {"name": "KH_ST_5", "number": 1, "value": 4},
    {"name": "KH_ST_4", "number": 1, "value": 4},
    {"name": "IC_LN_4", "number": 1, "value": 2},
    {"name": "KH_LN_6", "number": 1, "value": 4},
    {"name": "KH_LN_5", "number": 1, "value": 2},
    {"name": "KH_LN_0", "number": 0, "value": 4},
    {"name": "KIO_LN_1", "number": 0, "value": 4},
    {"name": "KH_ST_1", "number": 1, "value": 4},
    {"name": "KH_ST_2", "number": 1, "value": 1},
    {"name": "KH_ST_3", "number": 1, "value": 1},
    {"name": "OI_LN_2", "number": 1, "value": 1},
    {"name": "IC_LN_0", "number": 1, "value": 4},
    {"name": "OC_LN_4", "number": 1, "value": 4} ],
  "tracks": [
    {"name": "KH_ST_5", "direction": 1, "pwm": 123},
    {"name": "IC_JCT_0", "direction": 2, "pwm": 123},
    {"name": "KH_LN_5", "direction": 0, "pwm": 0},
    {"name": "OC_JCT_0", "direction": 2, "pwm": 123},
    {"name": "IC_LN_4", "direction": 1, "pwm": 123},
    {"name": "OI_LN_2", "direction": 2, "pwm": 123},
    {"name": "OC_LN_1", "direction": 2, "pwm": 123},
    {"name": "KH_ST_6", "direction": 2, "pwm": 123},
    {"name": "KH_LN_6", "direction": 1, "pwm": 123},
    {"name": "IC_LN_1", "direction": 1, "pwm": 123},
    {"name": "OI_LN_0", "direction": 0, "pwm": 0},
    {"name": "OC_LN_4", "direction": 0, "pwm": 0} ],
  "points": [
    {"name": "L_7", "value": 1},
    {"name": "PNT_13", "value": 0},
    {"name": "L_6", "value": 0},
    {"name": "PNT_10", "value": 0},
    {"name": "PNT_14", "value": 0},
    {"name": "PNT_11", "value": 0},
    {"name": "PNT_12", "value": 1},
    {"name": "L_8", "value": 1},
    {"name": "PNT_8", "value": 1},
    {"name": "PNT_9", "value": 1},
    {"name": "L_11", "value": 1},
    {"name": "PNT_7", "value": 1},
    {"name": "PNT_6", "value": 1},
    {"name": "PNT_5", "value": 1},
    {"name": "L_10", "value": 1},
    {"name": "L_9", "value": 1} ]}
```

The command sets a new global railway state. 
Each signal, track and point can be set to a new value.
The periphery doesn't need to be complete, any track, signal or point can be omitted.
