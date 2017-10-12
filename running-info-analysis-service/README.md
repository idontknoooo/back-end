# Process
In 'resource' folder, run
```bash
docker-compose up -d
docker exec -it resources_rias_1 mysql -uroot -p
show databases;
create database rias;
```
In 'running-info-analysis-service' folder, run
```shell
mvn clean install
cd target
java -jar *.jar
```
# Problem
*Solved* My program can only clean installed by mvn, but cannot connect to mysql
  I updated 'resource' folder
*New* Post method doesn't work.
