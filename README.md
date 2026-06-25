# Aiven OpenSearch Client

Small Java 17 Maven project for connecting to an Aiven OpenSearch service.

## Requirements

- Java 17 JDK
- Maven

## Configuration

The application reads runtime configuration from environment variables:

- `OPENSEARCH_URL`
- `OPENSEARCH_USERNAME`
- `OPENSEARCH_PASSWORD`

Keep real credentials outside this repository. A helper script can create a local config file from the example:

```bash
./scripts/init-env.sh
```

Edit the env file:

```bash
./scripts/edit-env.sh
```

Use a custom path if needed:

```bash
AIVEN_OPENSEARCH_ENV_FILE=/secure/path/opensearch.env ./scripts/init-env.sh
AIVEN_OPENSEARCH_ENV_FILE=/secure/path/opensearch.env ./scripts/run.sh
```

## Build

```bash
mvn test
```

## Run

```bash
./scripts/run.sh
```
