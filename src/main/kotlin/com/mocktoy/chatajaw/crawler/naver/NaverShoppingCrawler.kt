package com.mocktoy.chatajaw.crawler.naver

import com.mocktoy.chatajaw.crawler.Crawler
import com.mocktoy.chatajaw.crawler.Request
import org.jsoup.Jsoup
import org.jsoup.select.Elements
import java.io.File
import java.nio.charset.Charset

class NaverShoppingCrawler : Crawler<NaverProduct> {
    override val baseUrl = "https://shopping.naver.com/"
    override fun startCrawler(request: Request): List<NaverProduct> {
//        val url = "https://search.shopping.naver.com/search/all.nhn?query=${keyword.query[0]}&cat_id=&frm=NVSHATC"
//        val doc: Document = Jsoup.connect(url)
//            .userAgent(Crawler.CommonHeader.userAgent)
//            .timeout(Crawler.CommonHeader.timeOut)
//            .post()

        return mockHtml.select("li[class*=_itemSection]").toNaverModelList()
    }

    private fun Elements.toNaverModelList() = map {
        NaverProduct(
            it.attr("data-expose-id"),
            it.select("a[class=link]").text(),
            it.select("span[class=num _price_reload]").text(),
            it.select("a[class=link]").attr("href")
        )
    }
}

private val mockHtml by lazy {
    Jsoup.parse(
        File("src/main/kotlin/com/mocktoy/chatajaw/crawler/naver/naver.html")
            .inputStream()
            .readBytes()
            .toString(Charset.forName("utf-8"))
    )
}
