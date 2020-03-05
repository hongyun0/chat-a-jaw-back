package com.mocktoy.chatajaw.sample

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SampleController {
    @GetMapping
    fun getSample(): ResponseEntity<String> = ResponseEntity.ok("sample")
}
