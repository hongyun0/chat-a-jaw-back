package com.mocktoy.chatajaw.keyword

import com.mocktoy.chatajaw.user.User
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class KeywordService(private val keywordRepository: KeywordRepository) {
    fun registerKeyword(keyword: RegisteredKeyword): RegisteredKeyword {
        return keywordRepository.save(keyword);
    }

    fun getKeywords(user: User): List<RegisteredKeyword> {
        return keywordRepository.findAllByOwner(user)
    }

    @Transactional
    fun clearAllKeywords(user: User): List<RegisteredKeyword> {
        return keywordRepository.removeAllByOwner(user)

    }
}
