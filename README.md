# Interface for the model railway 4.1

The main task of the interface is to handle the communication between SCCharts and pyRailwayD. This is achieved by compiling lean SCCharts to Java code using [KiCoOO](https://github.com/kieler/KiCoOO/). 

## Installation
before use make sure you downloaded all submodules:
```bash
git submodule update --init --recursive
```

Next: installing kico (not KiCoOO) which is needed to compile the SCCharts to a JSOn file that KiCoOO accepts. Click on the latest completed workflow [here](https://github.com/kieler/semantics/actions?query=branch%3Adam%2Fjson) and scroll all the way down. Then download the artefact `KIELER Compiler CLI Jar`, extract the `kico.jar` and place it in the project root directory.

## How to use
```bash
bash compile_sctx.sh /path/to/your/sctx/file.sctx
mvn install
```

You can find the compiled `.jar` in the `target/` directory.

## How to build an SCChart for the model railway
You need to reference the chart `charts/interface.sctx`. The chart defines all inputs/outputs that are being used to communicate with the model railway. We highly recommend to not modify this chart, but instead create a new chart that references the interface chart. Please name this chart `controller.sctx`, as right now KiCoOO doesn't support renaming charts.

## Current architecture of the model railway
`RailBlocks -> RailSL -> lean SCCharts -> java -> pyRailwayD -> Arduinos`

# TODOs:
- Test everything on the model railway.
- Verify that the behaviour is exactly as the simulator.