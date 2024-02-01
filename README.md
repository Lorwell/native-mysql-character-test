##  native-mysql-character-test

> 本项目用于验证本地化文件写入mysql的字符乱码问题

依赖版本

* springboot 3.2.1
* gradle 8.5
* kotlin 1.9.20
* graalvm.buildtools 0.9.28

### 分支 master

需要先使用 docker 启动一个 mysql 容器

```bash
docker run -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=123456 --name mysql mysql:8.0.28 --character-set-server=utf8mb4 --collation-server=utf8mb4_unicode_ci --max-connections=5000
```

然后启动项目

```bash
gradlew nativeRun
```

或者打包本地化镜像

```bash
docker  build -t nativemysqlcharactertest .
```

### 分支 no-mysql

去除mysql 相关依赖，用于验证是否是编译的问题，项目直接运行 `gradlew nativeRun`

得出的结论是与编译无关