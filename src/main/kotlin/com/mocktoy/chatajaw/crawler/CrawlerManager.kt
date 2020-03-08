package com.mocktoy.chatajaw.crawler

import com.mocktoy.chatajaw.crawler.danawa.DanawaCrawler
import com.mocktoy.chatajaw.crawler.naver.NaverShoppingCrawler
import com.mocktoy.chatajaw.crawler.ppomppu.PpomppuCrawler

class CrawlerManager(vararg type: Type = arrayOf(Type.DANAWA, Type.PPOMPPU, Type.NAVER)) {

    private val crawlerManagers by lazy {
        type.map {
            println(it)
            it.cls.getConstructor().newInstance() as Crawler<*>
        }
    }

    fun search(keyword: Array<String>) = mutableListOf<Model>().apply {
        crawlerManagers.forEach {
            addAll(it.startCrawler(Request(keyword)))
        }
    }

    enum class Type(val cls: Class<out Crawler<out Model>>) {
        DANAWA(DanawaCrawler::class.java),
        PPOMPPU(PpomppuCrawler::class.java),
        NAVER(NaverShoppingCrawler::class.java),
    }
}

