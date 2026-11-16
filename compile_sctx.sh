#!/bin/bash

set -euo pipefail

if [[ "$#" -eq 0 ]]; then
  echo "Error: At least one .sctx file is required." >&2
  echo "Usage: $0 /path/to/chart1.sctx [/path/to/chart2.sctx ...]" >&2
  exit 1
fi

script_dir="$(cd -- "$(dirname -- "$0")" && pwd)"

if [ ! -f "$script_dir/KiCoOO/build/jar/kicooo.jar" ]; then
  echo "compiling KiCoOO..."
  make -C "$script_dir/KiCoOO"
fi

generated_source_dir="$script_dir/src/main/java"
runtime_source_dir="$generated_source_dir/kieler_gen/base_classes"
mkdir -p "$generated_source_dir" "$runtime_source_dir"

cp "$script_dir/KiCoOO/misc/base_classes/"*.java "$runtime_source_dir/"

temporary_json="$(mktemp "$script_dir/.tmp.XXXXXX.json")"
trap 'rm -f -- "$temporary_json"' EXIT

for input_file in "$@"; do
  input_file="$(realpath -- "$input_file")"
  java -jar "$script_dir/kico.jar" \
    -s de.cau.cs.kieler.sccharts.SCTXToJSON \
    -o "$temporary_json" \
    "$input_file"
  java -jar "$script_dir/KiCoOO/build/jar/kicooo.jar" \
    "$generated_source_dir" java --no-main < "$temporary_json"
done
