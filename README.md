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

可以使用以下 curl 命令测试

模糊查询

```bash
curl -X GET --location "http://localhost:8080/demo?nickname=%E6%B5%8B%E8%AF%95"
```

添加信息

```bash
curl -X POST --location "http://localhost:8080/demo" \
    -H "Content-Type: application/json" \
    -d '{
          "nickname": "测试999"
        }'
```



最终得出结论：

**jdbc 链接中的 `useUnicode=true&characterEncoding=UTF-8`是导致乱码的罪魁祸首，在去除后可以正常使用**

如果想体验乱码可以在 `application.yaml` 中将jdbc链接替换为 `jdbc:mysql://host.docker.internal:3306/nativemysqlcharactertest?createDatabaseIfNotExist=true&useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2b8`，在次运行时将会出现乱码

### 分支 no-mysql

去除mysql 相关依赖，用于验证是否是编译的问题，项目直接运行 `gradlew nativeRun`

得出的结论是与编译无关