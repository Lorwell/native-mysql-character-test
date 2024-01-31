package cc.shacocloud.nativemysqlcharactertest.model

import jakarta.persistence.*
import java.io.Serializable

/**
 * JPA 实体的基类
 * @author 思追(shaco)
 */
@Access(AccessType.FIELD)
@MappedSuperclass
abstract class BasePo : Serializable {

    /**
     * 自增id
     */
    @get:[Access(AccessType.PROPERTY) Id GeneratedValue(strategy = GenerationType.AUTO)]
    abstract var id: Long?

}
