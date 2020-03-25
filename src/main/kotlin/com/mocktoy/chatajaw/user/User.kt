package com.mocktoy.chatajaw.user

import java.time.LocalDateTime
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class User(duid: UUID, createAt: LocalDateTime = LocalDateTime.now(), lastAccessAt: LocalDateTime = LocalDateTime.now()) {
    @Id
    @Column(columnDefinition = "BINARY(16)")
    val duid: UUID = duid
    val createAt: LocalDateTime = createAt
    val lastAccessAt: LocalDateTime = lastAccessAt
}
