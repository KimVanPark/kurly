#/bin/sh

./gradlew clean appNpmBuild build

nohup java -jar build/libs/kurly-0.0.1-SNAPSHOT.jar &
