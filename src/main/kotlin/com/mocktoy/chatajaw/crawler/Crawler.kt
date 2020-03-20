package com.mocktoy.chatajaw.crawler

interface Crawler<PRODUCT: Crawler.Product> {
    val baseUrl: String
    fun startCrawler(request: Request): List<PRODUCT>

    object CommonHeader {
        const val userAgent = "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:40.0) Gecko/20100101 Firefox/40.0"
        const val timeOut = 5000
    }

    interface Product {
        val uid: String
        val title: String
        val price: String
        val url: String
    }
}
