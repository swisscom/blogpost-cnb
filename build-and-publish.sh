#!/usr/bin/env bash
./gradlew bootBuildImage
LATEST_TAG=$(docker images robachmann/webflux-rest-cnb  --format "{{.Tag}}" | sort -r | head -n 1)
echo $LATEST_TAG
docker push robachmann/webflux-rest-cnb:$LATEST_TAG