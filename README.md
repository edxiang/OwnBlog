# OwnBlog

this is my Blog


# 2018-6-19
博客版面改成 bootstrap4.0， 看起来效果还不错.


# 2017-7-5
 init the github in my host.

### 2018-8-10   necessary data from controling the linux
push: (first time)git remote add origin git@github.com:edxiang/[ProjectName].git 
git push -u origin master.

pull: (first time)git clone git@github.com:edxiang/[ProjectName].git 
git pull origin master




# redis
./redis-server ./redis.conf // 启动redis
./redis-cli -h 127.0.0.1 -p 6379 -a myRedis  
./redis-cli -h 127.0.0.1 -p 6379 -a myRedis shutdown


# mysqldump
mysqldump -u USERNAME -p DATABASE_NAME > /PATH/DUMP_FILE_NAME.sql
PASSWORD

# crontab
service crond status
service crond start
service crond stop

#shadowsocks
ssserver -c /etc/shadowsocks.json -d start


#mysql
service mysqld start
service mysqld stop
service mysqld restart

#crontab
crontab -e
service crond start/stop/restart
/usr/local/mysql_dump/bkOwnblog.sh


#SMTP    foxmail
nwjm zjqk lwuu cbcg


#password of root
Q4je86cHs5D7

#中文乱码：
change the file /etc/sysconfig/i18n:
 LANG="zh_CN.utf-8"
 export LC_ALL="zh_CN.utf-8"
 source /etc/sysconfig/i18n
