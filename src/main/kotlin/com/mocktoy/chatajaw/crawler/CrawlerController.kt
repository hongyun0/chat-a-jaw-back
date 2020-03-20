package com.mocktoy.chatajaw.crawler

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/search")
class CrawlerController {
    val manager: CrawlerManager by lazy {
        CrawlerManager()
    }

    @RequestMapping(value = ["/testSearch"], method = [RequestMethod.GET])
    fun search(@RequestParam("keyword") arg: String): List<Crawler.Product> {
        return manager.search(arrayOf(arg))
    }
}