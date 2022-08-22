#/bin/sh

git submodule init

git submodule update

cp ./auth/application-prod.yml ./src/main/resources/

./gradlew clean appNpmBuild build

nohup java -jar --Dspring.active.profiles=prod build/libs/kurly-0.0.1-SNAPSHOT.jar &
