# Process
In 'resource' folder, run  
```bash
docker-compose up -d
docker exec -it resources_rias_1 mysql -uroot -p
show databases;
create database riasDB;
```  
In 'running-info-analysis-service' folder, run  
```bash
mvn clean install
cd target
java -jar *.jar
```   
# Problem  
**Solved** My program can only clean installed by mvn, but cannot connect to mysql  
  I updated 'resource' folder.  

**Solved** Post method doesn't work.  
  Now can work with mysql.   

**Problem** Some serialization still show when Http(get). I want to ignore some field like 'latitude'.
