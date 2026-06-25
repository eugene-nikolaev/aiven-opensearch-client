#!/usr/bin/env bash
set -euo pipefail

repo_root="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
env_file="${AIVEN_OPENSEARCH_ENV_FILE:-$HOME/.config/aiven-opensearch-client/opensearch.env}"

if [[ ! -f "$env_file" ]]; then
  "$repo_root/scripts/init-env.sh"
fi

vim "$env_file"
