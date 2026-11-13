# Configuration File Format

The configuration of the PyRailwaD is given as a JSON file upon startup.

Here is an example of a configuration file:

```json
  [ { "serial": "75437303830351714161",
      "contacts": [
        {"name": "", "number": 0},
        {"name": "OI_LN_0", "number": 1},
        {"name": "KH_LN_0", "number": 0},
        {"name": "", "number": 0},
        {"name": "KIO_LN_1", "number": 0},
        {"name": "", "number": 0},
        {"name": "IC_LN_4", "number": 1},
        {"name": "", "number": 0} ],
      "points": ["PNT_12", "L_8", "PNT_8", "PNT_9", "L_11", ""],
      "signals": [
        {"name": "", "number": 0},
        {"name": "", "number": 0},
        {"name": "", "number": 0},
        {"name": "", "number": 0},
        {"name": "KH_LN_0", "number": 0},
        {"name": "KIO_LN_1", "number": 0} ],
      "tracks": ["", "", "IC_LN_4", "OI_LN_2", "OC_LN_1", "KH_ST_6"] },
    { "serial": "7543730383035181F052",
      "contacts": [
        {"name": "KH_ST_3", "number": 1},
        {"name": "KH_ST_4", "number": 1},
        {"name": "", "number": 0},
        {"name": "KH_ST_5", "number": 1},
        {"name": "", "number": 0},
        {"name": "KH_LN_5", "number": 1},
        {"name": "OI_LN_2", "number": 1},
        {"name": "KH_LN_6", "number": 1} ],
      "points": 
        ["L_7", "PNT_13", "L_6", "PNT_10", "PNT_14", "PNT_11"],
      "signals": [
        {"name": "KH_ST_5", "number": 1},
        {"name": "KH_ST_4", "number": 1},
        {"name": "", "number": 0},
        {"name": "IC_LN_4", "number": 1},
        {"name": "KH_LN_6", "number": 1},
        {"name": "KH_LN_5", "number": 1} ],
      "tracks": 
        ["", "", "KH_ST_5", "IC_JCT_0", "KH_LN_5", "OC_JCT_0"] },
    { "serial": "75437303830351712071",
      "contacts": [
        {"name": "OC_LN_5", "number": 0},
        {"name": "OC_LN_4", "number": 1},
        {"name": "OI_LN_0", "number": 0},
        {"name": "KH_ST_2", "number": 1},
        {"name": "IC_LN_0", "number": 1},
        {"name": "IC_LN_1", "number": 0},
        {"name": "OC_LN_1", "number": 0},
        {"name": "KH_ST_1", "number": 1} ],
      "points": [
        "PNT_7", "PNT_6", "PNT_5", "L_10", "L_9", ""],
      "signals": [
        {"name": "KH_ST_1", "number": 1},
        {"name": "KH_ST_2", "number": 1},
        {"name": "KH_ST_3", "number": 1},
        {"name": "OI_LN_2", "number": 1},
        {"name": "IC_LN_0", "number": 1},
        {"name": "OC_LN_4", "number": 1}
      ],
      "tracks": [
        "", "", "KH_LN_6", "IC_LN_1", "OI_LN_0", "OC_LN_4"] } ]
```

The configuration consists of a list of arduinos connected to the railPi. Each arduino is identified by its `serial` number. The serial is used to find the corresponding serial port at `/dev/arduino_%serial%`.

For each arduino there are four data elements, `contacts`, `points`, `signals` and `tracks`. These elements each contain an ordered array of the connected periphery. Points and Tracks are identified by their name, signals and contacts are identified by the associated track name and their number.




