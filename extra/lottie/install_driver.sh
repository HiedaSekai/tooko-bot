#!/usr/bin/env bash

wget https://chromedriver.storage.googleapis.com/79.0.3945.36/chromedriver_linux64.zip &&

unzip chromedriver_linux64.zip && rm chromedriver_linux64.zip &&

mv chromedriver /usr/local/bin