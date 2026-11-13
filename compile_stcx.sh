#!/bin/bash

set -euo pipefail

if [[ -z "$1" ]]; then
  echo "Error: Missing required argument." >&2
  echo "Usage: $0 /path/to/chart.sctx" >&2
  exit 1
fi

script_dir="$(cd -- "$(dirname -- "$0")" && pwd)"
input_file="$(realpath "$1")"
temporary_json="$script_dir/.tmp.json"

java -jar "$script_dir/kico.jar" -s de.cau.cs.kieler.sccharts.SCTXToJSON -o "$temporary_json" "$input_file"

if [ ! -f "$script_dir/KiCoOO/build/jar/kicooo.jar" ]; then
  echo "compiling KiCoOO..."
  make -C "$script_dir/KiCoOO"
fi

generated_source_dir="$script_dir/src/main/java"
runtime_source_dir="$generated_source_dir/kieler_gen/base_classes"
mkdir -p "$generated_source_dir" "$runtime_source_dir"

java -jar "$script_dir/KiCoOO/build/jar/kicooo.jar" "$generated_source_dir" java --no-main < "$temporary_json"
cp "$script_dir/KiCoOO/misc/base_classes/"*.java "$runtime_source_dir/"
rm -f "$temporary_json"
