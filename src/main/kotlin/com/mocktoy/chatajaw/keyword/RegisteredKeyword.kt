package com.mocktoy.chatajaw.keyword

import com.mocktoy.chatajaw.user.User
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
class RegisteredKeyword(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,
                        @ManyToOne val owner: User,
                        val keyword: String,
                        val createAt: LocalDateTime = LocalDateTime.now()) {
    companion object {
        fun ofKeywordAndOwner(keyword: String, owner: User): RegisteredKeyword {
            return RegisteredKeyword(0, owner, keyword)
        }
    }
}
