package com.mocktoy.chatajaw.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.BAD_REQUEST)
class DuplicateUserTokenException(message: String? = null, cause: Throwable? = null) : CustomException(message, cause)
