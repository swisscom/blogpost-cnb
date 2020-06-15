#!/usr/bin/env bash
LATEST_TAG=$(docker images robachmann/webflux-rest-cnb  --format "{{.Tag}}" | sort -r | head -n 1)
echo $LATEST_TAG
sed -i '' "s/webflux-rest-cnb:.*/webflux-rest-cnb:$LATEST_TAG/g" cf/manifest.yml
# cf create-service secrets-store json openweathermap -c '{ "appId": "<insert-api-key-here>" }'
cf push -f cf/manifest.yml
