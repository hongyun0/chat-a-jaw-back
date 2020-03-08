package com.mocktoy.chatajaw.crawler.danawa

import com.mocktoy.chatajaw.crawler.Crawler
import com.mocktoy.chatajaw.crawler.Model
import com.mocktoy.chatajaw.crawler.Request
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import java.io.File
import java.nio.charset.Charset

class DanawaCrawler : Crawler<DanawaModel> {
    override val baseUrl = "http://www.danawa.com/"

    override fun startCrawler(request: Request): List<DanawaModel> {
//        val url = "http://search.danawa.com/ajax/getProductList.ajax.php"
//        val doc: Document = Jsoup.connect(url)
//            .userAgent(Crawler.CommonHeader.userAgent)
//            .referrer("http://search.danawa.com/dsearch.php?")
//            .data(mapOf(Pair("query", keyword.query[0])))
//            .timeout(Crawler.CommonHeader.timeOut)
//            .post()

        return mockHtml.select("li[class^=prod_item]").toDanawaModelList()
    }

    private fun Elements.toDanawaModelList() = map {
        DanawaModel(
            it.attr("id"),
            it.select("p[class=prod_name]>a").text(),
            it.getPrice().text() ?: "",
            it.select("p[class=prod_name]>a").attr("href")
        )
    }

    private fun Element.getPrice(): Elements {
        val element = select("p[class=price_sect]>strong")
        if (element.text().isNullOrEmpty()) {
            return select("p[class=price_sect]>a>strong")
        } else {
            return element
        }
    }
}

private val mockHtml by lazy {
    Jsoup.parse(
        File("src/main/kotlin/com/mocktoy/chatajaw/crawler/danawa/danawa.html")
            .inputStream()
            .readBytes()
            .toString(Charset.forName("euc_kr"))
    )
}

data class DanawaModel(
    override val number: String,
    override val title: String,
    override val price: String, // todo : it could be more than two so we should be handle it
    override val url: String
) : Model