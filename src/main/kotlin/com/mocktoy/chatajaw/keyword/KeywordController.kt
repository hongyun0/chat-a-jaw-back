package com.mocktoy.chatajaw.keyword

import com.mocktoy.chatajaw.api.ApiResponse
import com.mocktoy.chatajaw.user.UserService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class KeywordController(private val userService: UserService, private val keywordService: KeywordService) {
    private val logger = LoggerFactory.getLogger(KeywordController::class.java)

    @PostMapping("/keywords")
    @ResponseBody
    fun registerKeyword(@RequestBody req:KeywordRequest): ApiResponse<Map<String, Any>> {
        val keyword = RegisteredKeyword.ofKeywordAndOwner(req.keyword, userService.getUser(req.duid).get())
        return ApiResponse(mapOf("registeredKeyword" to keywordService.registerKeyword(keyword)))
    }

    @DeleteMapping("/keywords")
    @ResponseBody
    fun clearAllKeywords(@RequestBody req:KeywordRequest): ApiResponse<Map<String, Any>> {
        return ApiResponse(mapOf("removedKeyword" to keywordService.clearAllKeywords(userService.getUser(req.duid).get())))
    }

}
