# telemetry-spark-packages-assembly
A jar containing all the spark packages we want available on Mozilla analysis clusters.

## Deploying to S3

CircleCI will handle building the assembly jar and uploading to S3 whenever
a tag is applied on the master branch.

## Deploying to ATMO and Airflow

Once a new tag has been applied and the jar is present in S3, you'll need to
update `telemetry.sh` via a PR to
[emr-bootstrap-spark](https://github.com/mozilla/emr-bootstrap-spark/)
updating the path to match the new tag.
Once your PR is merged and ops deploys `emr-bootstrap-spark`, new clusters
spun up by ATMO or Airflow will reflect the updated assembly jar.
