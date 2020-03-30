package com.mocktoy.chatajaw.notification

import com.mocktoy.chatajaw.notification.dto.NotificationDto
import com.mocktoy.chatajaw.notification.dto.NotificationResultDto
import com.mocktoy.chatajaw.notification.dto.UserTokenDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/notifications")
class NotificationController(private val notificationService: NotificationService) {

    @PostMapping("/tokens")
    fun registerToken(dto: UserTokenDto): ResponseEntity<Any> {
        notificationService.register(dto)
        return ResponseEntity.ok().build()
    }

    @PostMapping("/messages")
    fun sendMessage(@RequestBody dto: NotificationDto): ResponseEntity<NotificationResultDto> {
        return ResponseEntity.ok(notificationService.send(dto))
    }
}
