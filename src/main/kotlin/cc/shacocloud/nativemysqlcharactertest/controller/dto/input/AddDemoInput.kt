package cc.shacocloud.nativemysqlcharactertest.controller.dto.input

import jakarta.validation.constraints.NotBlank

/**
 * @author 思追(shaco)
 */
data class AddDemoInput(

    @NotBlank
    val nickname: String

) {

    override fun toString(): String {
        return "AddDemoInput(nickname='$nickname')"
    }
}
