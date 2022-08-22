#/bin/sh

cp ./auth/application-prod.yml ./src/main/resources/

./gradlew clean appNpmBuild build

nohup java -jar -Dspring.profiles.active=prod build/libs/kurly-0.0.1-SNAPSHOT.jar &
