package com.mocktoy.chatajaw.user

import java.time.LocalDateTime
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class User(@Id @Column(columnDefinition = "BINARY(16)") val duid: UUID,
           val createAt: LocalDateTime = LocalDateTime.now(),
           val lastAccessAt: LocalDateTime = LocalDateTime.now()) {
}
