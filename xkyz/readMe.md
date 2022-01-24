1.上传文件到服务器
```shell
scp ./xkyz-1.0-SNAPSHOT.jar root@118.31.0.252:/root/xinke/Jars
```
1.服务器启动项目
```shell
nohup java -jar xkyz-1.0-SNAPSHOT.jar >springboot.log 2>&1 &
```

2.查看日志
```shell
tail -f springboot.log
```
