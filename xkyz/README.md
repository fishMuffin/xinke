1.上传文件到服务器
```shell
scp ./xkyz-1.0-SNAPSHOT.jar root@118.31.0.252:/root/xinke/Jars
```
2.服务器启动项目
```shell
nohup java -jar xkyz-1.0-SNAPSHOT.jar --server.port=80 >springboot.log 2>&1 &
```

3.查看日志
```shell
tail -f /root/xinke/Jars/springboot.log 
```
4.查看端口占用
```shell
netstat -lanp|grep 端口号
```
5.杀死进程
```shell
kill -9 进程号
```
6.查看进程号
```shell
ps -ef | grep xkyz-1.0-SNAPSHOT.jar
```
7.java查看进程
```shell
jps -m
```
8.查看服务器是否部署成功
```shell
curl localhost:80/api/test/hello
```
9.外部访问
```shell
curl 118.31.0.252:80/api/test/hello
```

10.如何解决阿里云安全组3306端口已配置外部却不能访问：
https://blog.csdn.net/baidu_37895884/article/details/78184466?locationNum=7&fps=1
```shell
 GRANT ALL PRIVILEGES ON *.* TO 'root'@'112.32.24.164' IDENTIFIED BY 'ycwycw123' WITH GRANT OPTION;
 GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' IDENTIFIED BY 'ycwycw123' WITH GRANT OPTION;
```
```sql
-- 查看现有用户,密码及允许连接的主机
SELECT User,Host FROM user;
```
11.server pwd
```shell
789123XKYz
```
12.命令行连接远程mysql数据库
```shell
#mysql -u 用户名 -p密码 -h 服务器IP地址 -P 服务器端MySQL端口号 -D 数据库名
mysql -u root -p ycwycw123 -h 118.31.0.252 -P 3306 -D xinke;
```
```shell
chmod 600 /Users/klein/Desktop/id_rsa.pem
```
13.使用SSH密钥对登录Ubuntu服务器:https://www.jianshu.com/p/38347bb6733d


部署详细流程
上传本地jar包到阿里云服务器
```shell
scp -i /Users/klein/Desktop/id_rsa.pem ./xkyz-1.0-SNAPSHOT.jar root@118.31.0.252:/root/xinke/Jars
```
接入阿里云服务器
```shell
ssh -i /Users/klein/Desktop/id_rsa.pem root@118.31.0.252
```
进入目录
```shell
cd xinke/Jars/
```
查看之前的java进程
```shell
ps -ef | grep xkyz-1.0-SNAPSHOT.jar
```
查看进程id 并杀死该进程
```shell
kill -9 15696
```
使用新的jar包部署新的java程序
```shell
nohup java -jar xkyz-1.0-SNAPSHOT.jar --server.port=80 >springboot.log 2>&1 &
```
swagger Web页面验证部署是否成功
```
https://www.xkyz.fun/swagger-ui.html?urls.primaryName=C#/%E7%94%A8%E6%88%B7%E8%AE%A2%E5%8D%95API/storeTodayIncomeUsingPOST_2
```
日志文件查询
```shell
vim springboot.log
```




数据库相关
1. 注意user表和userProfile表中的skey其实就是userToken
查看表结构 观察comment来确认每个字段的意思
```sql
show create table express_price_reference_jitu;
```




TODO
1.阿里云RDS设置自动备份
2.录入极兔剩下的数据
3.wechatTransfer表应该要加日期字段
4.后续的三家快递公司最好每一家都建立一个新表 然后单独增加逻辑返回价格信息(设计新表注意加上快递公司名称字段,可避免dto数据转换)
5.错误信息和错误码尚未返回给前端,后期如果需要得在controller层返回值进行改变.
6.关于日志:可以使用https://www.elastic.co/cn/downloads/logstash 进行日志收集方便查错. 还有一个就是了解一下springAOP对整个service入参进行日志输出.可以参考(/Users/klein/Desktop/xinke/code/xkyz/src/main/java/com/xkyz/xinke/common/LogAsPect.java)
7.后期可能要做缓存(sprinboot集成redis)
8.魔法数字改成枚举类型统一维护

14.ubuntu重启mysql
```shell
sudo /etc/init.d/mysql start
sudo /etc/init.d/mysql stop
sudo /etc/init.d/mysql restart
```

15.ubuntu terminal 下插入汉字乱码问题解决

16.mysql 备份
```shell
mysqldump --no-defaults -h localhost -u root -p xinke >tmp.sql
```

```sql
-- #查看字符集
show variables like 'character%';
-- #设置客户端字符集为utf8
set character_set_client=utf8;
```

二.jekins配置:
1.以root用户启动以防权限不足 修改配置文件
```shell
vim /etc/sysconfig/jenkins
```

10.reference
1.CICD:
https://www.jianshu.com/p/329818c45260
https://www.cnblogs.com/weschen/p/6867885.html
2.ubuntu安装maven：
https://blog.csdn.net/weixx3/article/details/80331538
3.微信订阅
https://www.jianshu.com/p/34c8c719db4c



