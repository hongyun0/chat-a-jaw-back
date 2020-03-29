package com.mocktoy.chatajaw.user

import com.mocktoy.chatajaw.api.ApiResponse
import com.mocktoy.chatajaw.keyword.KeywordService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class UserController(private val userService: UserService, private val keywordService: KeywordService) {

    private val logger = LoggerFactory.getLogger(UserController::class.java)

    @PostMapping("/users")
    @ResponseBody
    fun joinUser(@RequestBody user: User): ApiResponse<Map<String, Any>> {
        val savedUser = userService.joinUser(user)
        return ApiResponse(mapOf("user" to savedUser, "registeredKeywords" to keywordService.getKeywords(savedUser)));
    }
}
