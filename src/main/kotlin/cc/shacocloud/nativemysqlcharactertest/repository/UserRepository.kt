package cc.shacocloud.nativemysqlcharactertest.repository

import cc.shacocloud.nativemysqlcharactertest.model.UserPo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

/**
 * @author 思追(shaco)
 */
@Repository
@Transactional
interface UserRepository : JpaRepository<UserPo, Long> {

    /**
     * 模糊查询用户
     */
    @Query("select u from UserPo u where u.nickname like ?1")
    fun findByNicknameLike(nickname: String): List<UserPo>

}