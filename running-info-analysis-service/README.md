# Process
In 'resource' folder, run
```bash
docker-compose up -d
docker exec -it resources_mysql_1 mysql -uroot -p
```
In 'running-info-analysis-service' folder, run
```shell
mvn clean install
cd target
java -jar *.jar
```
# Problem
My program can only clean installed by mvn, but cannot connect to mysql
