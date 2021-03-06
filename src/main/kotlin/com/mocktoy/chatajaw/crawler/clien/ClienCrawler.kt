package com.mocktoy.chatajaw.crawler.clien

import com.mocktoy.chatajaw.crawler.Crawler
import com.mocktoy.chatajaw.crawler.Request
import org.jsoup.Jsoup
import org.jsoup.select.Elements
import java.io.File
import java.nio.charset.Charset

class ClienCrawler : Crawler<ClienProduct> {
    override val baseUrl = "https://www.clien.net"

    override fun startCrawler(request: Request): List<ClienProduct> {
//        val url = "$baseUrl/service/board/jirum"
//        val doc: Document = Jsoup.connect(url)
//            .userAgent(Crawler.CommonHeader.userAgent)
//            .timeout(Crawler.CommonHeader.timeOut)
//            .post()

        return mockHtml.select("div[class^=list_item]").toClienModelList()
    }

    private fun Elements.toClienModelList() = map {
        ClienProduct(
            it.attr("data-board-sn"),
            it.select("a[data-role=list-title-text]").text(),
            "",
            it.select("a[data-role=list-title-text]").attr("href")
        )
    }.filter {
        it.uid.isNotEmpty() // Try to remove deleted post from model list
    }
}

private val mockHtml by lazy {
    Jsoup.parse(
        File("src/main/kotlin/com/mocktoy/chatajaw/crawler/clien/clien.html")
            .inputStream()
            .readBytes()
            .toString(Charset.forName("utf-8"))
    )
}
