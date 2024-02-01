FROM ccr.ccs.tencentyun.com/shaco_work/graalvm21:ubuntu22.build

# 复制项目所有代码
ADD . /build
WORKDIR /build

# 执行编译
RUN bash -c "source /etc/profile && chmod 755 -R /build && dos2unix /build/gradlew && /build/gradlew nativeCompile"

# 为了查看本地编译的文件顾使用该命令直接运行
CMD [ "sh", "-c", "/build/build/native/nativeCompile/native-mysql-character-test -Dfile.encoding=UTF-8 -Dsun.jnu.encoding=UTF-8"]

# todo 暂时注释，不使用运行时镜像因为需要查看编译的文件
## 运行时镜像
#FROM ccr.ccs.tencentyun.com/shaco_work/graalvm21:ubuntu22.runtime
#
## 复制构建时镜像的构建结果
#WORKDIR /workspace
#COPY --from=0 "/build/build/native/nativeCompile/native-mysql-character-test" /workspace/app
#
#EXPOSE 8080
#
#CMD [ "sh", "-c", "/workspace/app -Dfile.encoding=UTF-8 -Dsun.jnu.encoding=UTF-8"]