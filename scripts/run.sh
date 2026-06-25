#!/usr/bin/env bash
set -euo pipefail

repo_root="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
env_file="${AIVEN_OPENSEARCH_ENV_FILE:-$HOME/.config/aiven-opensearch-client/opensearch.env}"

if [[ ! -f "$env_file" ]]; then
  echo "Environment file not found: $env_file" >&2
  echo "Create it with ./scripts/init-env.sh, or set AIVEN_OPENSEARCH_ENV_FILE." >&2
  exit 1
fi

set -a
source "$env_file"
set +a

cd "$repo_root"
mvn compile exec:java
