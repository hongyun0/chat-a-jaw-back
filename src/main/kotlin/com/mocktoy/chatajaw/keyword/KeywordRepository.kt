package com.mocktoy.chatajaw.keyword

import com.mocktoy.chatajaw.user.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface KeywordRepository : JpaRepository<RegisteredKeyword, Long> {
    fun findAllByOwner(user: User): List<RegisteredKeyword>
    fun removeAllByOwner(user: User): List<RegisteredKeyword>
}
