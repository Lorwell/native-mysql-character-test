package cc.shacocloud.nativemysqlcharactertest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * 使用 docker  build -t nativemysqlcharactertest . 构建本地镜像
 */
@SpringBootApplication
class NativeMysqlCharacterTestApplication

fun main(args: Array<String>) {
    runApplication<NativeMysqlCharacterTestApplication>(*args)

    println("测试")

}
