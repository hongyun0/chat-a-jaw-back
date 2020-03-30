package com.mocktoy.chatajaw.notification.dto

data class NotificationDto(val targetTokens: List<String> = listOf(),
                           val title: String = "(제목 없음)",
                           val content: String = "(내용 없음)")
