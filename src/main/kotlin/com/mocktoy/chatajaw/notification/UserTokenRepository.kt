package com.mocktoy.chatajaw.notification

import com.mocktoy.chatajaw.notification.entity.UserToken
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserTokenRepository : JpaRepository<UserToken, Long> {
    fun findAllByUserIdEqualsOrTokenEquals(userId: String, token: String): List<UserToken>
}
