package com.mocktoy.chatajaw.crawler.ppomppu

import com.mocktoy.chatajaw.crawler.Crawler
import com.mocktoy.chatajaw.crawler.Request
import org.jsoup.Jsoup
import org.jsoup.select.Elements
import java.io.File
import java.nio.charset.Charset

class PpomppuCrawler : Crawler<PpomppuProduct> {
    override val baseUrl: String = "https://www.ppomppu.co.kr/zboard"

    override fun startCrawler(request: Request): List<PpomppuProduct> {
        return mutableListOf<PpomppuProduct>().apply {
            addAll(findInGukNae())
            addAll(findInHaeWei())
        }
    }

    private fun findInGukNae(): List<PpomppuProduct> {
//        val url = "$baseUrl/zboard.php?id=ppomppu"
//        val doc: Document = Jsoup.connect(url)
//            .userAgent(Crawler.CommonHeader.userAgent)
//            .timeout(Crawler.CommonHeader.timeOut)
//            .post()

        return mockHtml.select("tr[class=list0]").apply {
            addAll(mockHtml.select("tr[class=list1]"))
        }.toPpomppuModelList()
    }

    private fun findInHaeWei(): List<PpomppuProduct> {
        //        val url = "$baseUrl/zboard.php?id=ppomppu4"
        return mockHtml.select("tr[class=list0]").apply {
            addAll(mockHtml.select("tr[class=list1]"))
        }.toPpomppuModelList()
    }

    private fun Elements.toPpomppuModelList() = map {
        PpomppuProduct(
            it.select("td[class=eng list_vspace]").first().text(), // number
            it.select("a>font").text(), // title
            "",
            it.select("td>a").attr("href") //url
        )
    }
}

private val mockHtml by lazy {
    Jsoup.parse(
        File("src/main/kotlin/com/mocktoy/chatajaw/crawler/ppomppu/ppomppu.html")
            .inputStream()
            .readBytes()
            .toString(Charset.forName("euc_kr"))
    )
}
