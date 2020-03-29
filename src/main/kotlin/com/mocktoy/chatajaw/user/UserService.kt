package com.mocktoy.chatajaw.user

import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(private val userRepository: UserRepository) {

    fun joinUser(user: User): User {
        return userRepository.findById(user.duid).orElseGet {userRepository.save(user)}
    }

    fun getUser(duid: UUID): Optional<User> {
        return userRepository.findById(duid)
    }
}
