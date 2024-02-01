package cc.shacocloud.nativemysqlcharactertest

import cc.shacocloud.nativemysqlcharactertest.model.UserPo
import cc.shacocloud.nativemysqlcharactertest.repository.UserRepository
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

/**
 * 使用 docker  build -t nativemysqlcharactertest . 构建本地镜像
 */
@EnableJpaRepositories
@SpringBootApplication
class NativeMysqlCharacterTestApplication

fun main(args: Array<String>) {
    val application = runApplication<NativeMysqlCharacterTestApplication>(*args)

    val userRepository = application.getBean(UserRepository::class.java)


    println("打印中文：${"测试"}")
    println("测试")

    val str = (1..5).joinToString(separator = "") { "123456789".random().toString() }
    val addUserPo = UserPo(nickname = "测试${str}")

    println("添加用户信息：${addUserPo}")

    userRepository.saveAndFlush(addUserPo)

    val userPos = userRepository.findByNicknameLike("%测试%")
    for (userPo in userPos) {
        println("打印查询用户信息：${userPo}")
    }
}
