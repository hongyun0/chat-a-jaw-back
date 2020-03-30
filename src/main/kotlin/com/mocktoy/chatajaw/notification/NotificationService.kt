package com.mocktoy.chatajaw.notification


import com.google.firebase.messaging.BatchResponse
import com.mocktoy.chatajaw.exception.DuplicateUserTokenException
import com.mocktoy.chatajaw.logger.Log
import com.mocktoy.chatajaw.notification.dto.NotificationDto
import com.mocktoy.chatajaw.notification.dto.NotificationResultDto
import com.mocktoy.chatajaw.notification.dto.UserTokenDto
import com.mocktoy.chatajaw.notification.entity.UserToken
import org.springframework.stereotype.Service
import java.util.*

@Service
class NotificationService(private val firebaseManager: FirebaseManager,
                          private val userTokenRepository: UserTokenRepository) {
    companion object : Log

    fun register(dto: UserTokenDto) {
        log.info("Token register start: {}", dto)

        val duplicates = userTokenRepository.findAllByUserIdEqualsOrTokenEquals(dto.userId, dto.token)
        if (duplicates.isNotEmpty()) {
            throw DuplicateUserTokenException("중복된 값이 있습니다.")
        }

        val notificationUser = UserToken(userId = dto.userId, token = dto.token)
        userTokenRepository.save(notificationUser)
    }

    fun send(dto: NotificationDto): NotificationResultDto {
        log.info("Notification send start: {}", dto)

        val targetTokens = if (dto.targetTokens.isNotEmpty()) dto.targetTokens
        else userTokenRepository.findAll().map { it.token }

        val response = firebaseManager.sendMessage(targetTokens, dto.title, dto.content)

        if (response.hasFailure()) {
            val failureTokens = response.getFailureTokens(targetTokens)
            log.warn("Notification send failed.(${failureTokens.size}/${targetTokens.size})")
            return NotificationResultDto(response.successCount, response.failureCount, failureTokens)
        }
        return NotificationResultDto(response.successCount, response.failureCount)
    }

    private fun BatchResponse.hasFailure() = this.failureCount > 0

    private fun <T> BatchResponse.getFailureTokens(targets: List<T>): List<T> {
        val responses = this.responses

        return responses.indices
                .asSequence()
                .filterNot { responses[it].isSuccessful }
                .mapTo(ArrayList()) { targets[it] }
    }
}
