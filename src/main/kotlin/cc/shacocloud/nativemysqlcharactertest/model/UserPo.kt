package cc.shacocloud.nativemysqlcharactertest.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.validation.constraints.NotBlank
import org.hibernate.validator.constraints.Length

/**
 * 用户模型
 * @author 思追(shaco)
 */
@Entity
@Table(
    name = "def_user"
)
class UserPo(
    override var id: Long? = null,

    /**
     * 昵称
     */
    @Column(nullable = false, length = 20)
    @Length(max = 20)
    @NotBlank
    var nickname: String

) : BasePo() {
    override fun toString(): String {
        return "UserPo(id=$id, nickname='$nickname')"
    }
}
