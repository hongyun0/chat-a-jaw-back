package com.mocktoy.chatajaw.crawler.clien

import com.mocktoy.chatajaw.crawler.Crawler
import com.mocktoy.chatajaw.crawler.Model
import com.mocktoy.chatajaw.crawler.Request
import org.jsoup.Jsoup
import org.jsoup.select.Elements
import java.io.File
import java.nio.charset.Charset

class ClienCrawler : Crawler<ClienModel> {
    override val baseUrl = "https://www.clien.net"

    override fun startCrawler(request: Request): List<ClienModel> {
//        val url = "$baseUrl/service/board/jirum"
//        val doc: Document = Jsoup.connect(url)
//            .userAgent(Crawler.CommonHeader.userAgent)
//            .timeout(Crawler.CommonHeader.timeOut)
//            .post()

        return mockHtml.select("div[class^=list_item]").toClienModelList()
    }

    private fun Elements.toClienModelList() = map {
        ClienModel(
            it.attr("data-board-sn"),
            it.select("a[data-role=list-title-text]").text(),
            "",
            it.select("a[data-role=list-title-text]").attr("href")
        )
    }.filter {
        it.number.isNotEmpty() // Try to remove deleted post from model list
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

data class ClienModel(
    override val number: String,
    override val title: String,
    override val price: String,
    override val url: String
) : Model