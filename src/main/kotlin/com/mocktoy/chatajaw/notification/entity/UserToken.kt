package com.mocktoy.chatajaw.notification.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

//TODO: 임시! 지니꺼 머지되면 잘 섞어보기
@Entity
data class UserToken(@Id
                     @GeneratedValue(strategy = GenerationType.IDENTITY)
                     var id: Long? = null,
                     var userId: String,
                     var token: String)
