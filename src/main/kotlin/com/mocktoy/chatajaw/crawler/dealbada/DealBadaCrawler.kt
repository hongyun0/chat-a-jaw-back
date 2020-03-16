package com.mocktoy.chatajaw.crawler.dealbada

import com.mocktoy.chatajaw.crawler.Crawler
import com.mocktoy.chatajaw.crawler.Model
import com.mocktoy.chatajaw.crawler.Request
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import java.io.File
import java.nio.charset.Charset

class DealBadaCrawler : Crawler<DealBadaModel> {
    override val baseUrl: String = "http://www.dealbada.com/bbs/board.php?bo_table="

    override fun startCrawler(request: Request): List<DealBadaModel> {
        return mutableListOf<DealBadaModel>().apply {
            addAll(findInGukNae())
            addAll(findInHaeWei())
        }
    }

    private fun findInGukNae(): List<DealBadaModel> {
//        val url = "${baseUrl}deal_domestic"
//        val doc: Document = Jsoup.connect(url)
//            .userAgent(Crawler.CommonHeader.userAgent)
//            .timeout(Crawler.CommonHeader.timeOut)
//            .post()

        return mockHtml.select("tbody>tr").filter {
            !it.attr("class").contains("bo_notice")
        }.toDealBadaModelList()
    }

    private fun findInHaeWei(): List<DealBadaModel> {
//        val url = "${baseUrl}deal_oversea"

        return mockHtml.select("tbody>tr").filter {
            !it.attr("class").contains("bo_notice")
        }.toDealBadaModelList()
    }

    private fun List<Element>.toDealBadaModelList() = map {
        DealBadaModel(
            it.select("td[class=td_num]").text(), // number
            it.select("td[class=td_subject]>a").text(), // title
            "",
            it.select("td[class=td_subject]>a").attr("href") //url
        )
    }

}

private val mockHtml by lazy {
    Jsoup.parse(
        File("src/main/kotlin/com/mocktoy/chatajaw/crawler/dealbada/dealbada.html")
            .inputStream()
            .readBytes()
            .toString(Charset.forName("utf-8"))
    )
}

data class DealBadaModel(
    override val uid: String,
    override val title: String,
    override val price: String, // todo : it could be more than two so we should be handle it
    override val url: String
) : Model