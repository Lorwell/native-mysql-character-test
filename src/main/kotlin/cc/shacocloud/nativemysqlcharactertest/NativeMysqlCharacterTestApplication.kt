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

    val str = (1..5).joinToString(separator = "") { "123456789".random().toString() }
    userRepository.saveAndFlush(
        UserPo(nickname = "测试${str}")
    )

    val userPos = userRepository.findByNicknameLike("%测试%")
    for (userPo in userPos) {
        println(userPo)
    }
}
