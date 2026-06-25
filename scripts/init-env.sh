#!/usr/bin/env bash
set -euo pipefail

env_file="${AIVEN_OPENSEARCH_ENV_FILE:-$HOME/.config/aiven-opensearch-client/opensearch.env}"
env_dir="$(dirname "$env_file")"
example_file="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)/config/opensearch.env.example"

mkdir -p "$env_dir"
chmod 700 "$env_dir"

if [[ -e "$env_file" ]]; then
  echo "Environment file already exists: $env_file"
  echo "Edit it directly, or set AIVEN_OPENSEARCH_ENV_FILE to use another path."
  exit 0
fi

cp "$example_file" "$env_file"
chmod 600 "$env_file"

echo "Created environment file: $env_file"
echo "Edit it with your Aiven OpenSearch credentials before running the app."
