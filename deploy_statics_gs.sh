#!/usr/bin/env bash
echo Deploying statics files...

gcloud config set project tduhack-dsa

gsutil mb gs://tduhack-dsa

gsutil cp -r target/built gs://tduhack-dsa

gsutil acl ch -u AllUsers:R gs://tduhack-dsa/built/*