package com.mocktoy.chatajaw.notification

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.BatchResponse
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.MulticastMessage
import com.google.firebase.messaging.Notification
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Component

@Component
class FirebaseManager(@Value("\${google.firebase.url}") private val url: String) : InitializingBean {

    fun sendMessage(targetTokens: List<String>, title: String, body: String): BatchResponse {
        val message = createMessage(Notification(title, body), targetTokens)

        return FirebaseMessaging.getInstance().sendMulticast(message)
    }

    private fun createMessage(notification: Notification,
                              registrationTokens: List<String>,
                              data: Map<String, Any> = mapOf()): MulticastMessage {
        return MulticastMessage.builder()
                .apply {
                    this.setNotification(notification)
                    this.addAllTokens(registrationTokens)
                    data.forEach { (key, value) ->
                        this.putData(key, value.toString())
                    }
                }.build()
    }

    private fun initializeFirebaseSdk() {
        //TODO: 환경변수로 설정하기 (https://firebase.google.com/docs/admin/setup?hl=ko#initialize_the_sdk)
        val resource = ClassPathResource("chat-a-jaw-service-account-file.json")
        val serviceAccount = resource.inputStream

        val options = FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl(url)
                .build()

        FirebaseApp.initializeApp(options)
    }

    override fun afterPropertiesSet() {
        initializeFirebaseSdk()
    }
}
