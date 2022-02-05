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

11.server pwd
```shell
789123XKYz
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



