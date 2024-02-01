package cc.shacocloud.nativemysqlcharactertest.controller

import cc.shacocloud.nativemysqlcharactertest.controller.dto.input.AddDemoInput
import cc.shacocloud.nativemysqlcharactertest.model.UserPo
import cc.shacocloud.nativemysqlcharactertest.repository.UserRepository
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

/**
 * @author 思追(shaco)
 */
@RestController
class DemoController(
    val userRepository: UserRepository
) {

    @GetMapping("demo")
    fun demo(nickname: String) {
        println("请求的字符：${nickname}")
        val userPos = userRepository.findByNicknameLike("%${nickname}%")
        for (userPo in userPos) {
            println("打印查询用户信息：${userPo}")
        }
    }

    @PostMapping("demo")
    fun addDemo(@RequestBody @Validated input: AddDemoInput) {
        println("请求的参数：${input}")
        val addUserPo = UserPo(nickname = input.nickname)

        println("添加用户信息：${addUserPo}")

        userRepository.saveAndFlush(addUserPo)
    }

}
