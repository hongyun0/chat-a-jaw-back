package com.mocktoy.chatajaw.notification.dto

data class NotificationResultDto(val successCount: Int,
                                 val failureCount: Int,
                                 val failureTokens: List<String> = listOf())
