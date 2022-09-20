# get migration db file from github and import to file
curl -o db.sql https://raw.githubusercontent.com/khanhdpdx01/ve-server/main/src/main/java/io/github/khanhdpdx01/veserver/database/db.sql
docker pull mysql
docker run -it -p 3308:3306 --name mysql2 -v /dumps:/home/dumps -e MYSQL_ROOT_PASSWORD=123456 -d mysql
# copy migration db file from host system to container
docker cp db.sql mysql2:./db.sql
# run bash of container
docker exec -it mysql2 bash
  mysql -uroot -p123456 -e "create database vecert;use vecert;source db.sql;select * from diploma;"
#iptables -I INPUT -m state --state NEW -m tcp -p tcp --dport 3308 -j ACCEPT
