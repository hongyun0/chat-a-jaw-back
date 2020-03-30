package com.mocktoy.chatajaw.notification.dto;

import com.mocktoy.chatajaw.notification.entity.UserToken

data class UserTokenDto(val userId: String, val token: String) {
    companion object {
        fun from(userToken: UserToken) = UserTokenDto(
                userId = userToken.userId,
                token = userToken.token
        )
    }
}
