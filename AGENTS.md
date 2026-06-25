# Agent Instructions

These instructions apply to this repository.

## Working Style

- Keep changes small, direct, and easy to explain during a live coding interview.
- Prefer readable Java over clever abstractions.
- Avoid unrelated refactors.
- Do not introduce extra frameworks unless there is a clear need.
- Before changing project structure, explain the tradeoff.
- Prefer a small compiling solution over a larger incomplete one.

## Java And Build

- Use Java 17 compatibility.
- Use Maven for build and dependency management.
- Keep commands runnable from a fresh checkout where possible.
- Prefer standard Maven lifecycle commands.

## Secrets And Configuration

- Never commit real credentials, passwords, service URIs, certificates, tokens, or generated local config.
- The application should read runtime configuration from environment variables.
- Real environment files should live outside the repository, for example under `~/.config/aiven-opensearch-client`.
- Example config files may contain placeholders only.

## Aiven OpenSearch Safety

- Do not disable TLS certificate verification.
- Do not log credentials or full connection strings.
- Avoid modifying the OpenSearch service unless the current task explicitly requires it.
- If a change would write data, create indexes, delete data, or alter settings, call that out before doing it.

## Verification

- Keep the project compiling after every meaningful code change.
- Run `mvn test` before considering implementation work complete.
- If tests are not available or cannot be run, at minimum run `mvn compile`.
- If a command fails because credentials, network access, or the Aiven service are unavailable, report that explicitly instead of guessing.
- Do not leave partially implemented code unless the user explicitly asks for a sketch or draft.
