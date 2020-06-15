#!/usr/bin/env bash
LATEST_TAG=$(docker images robachmann/webflux-rest-cnb  --format "{{.Tag}}" | sort -r | head -n 1)
echo $LATEST_TAG
sed -i '' "s/webflux-rest-cnb:.*/webflux-rest-cnb:$LATEST_TAG/g" k8s/deployment.yml
kubectl apply -f k8s/secret-openweathermap.yml
kubectl apply -f k8s/deployment.yml
