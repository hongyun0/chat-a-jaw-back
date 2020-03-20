package com.mocktoy.chatajaw.crawler.naver

import com.mocktoy.chatajaw.crawler.Crawler

data class NaverProduct(
    override val uid: String,
    override val title: String,
    override val price: String, // todo : it could be more than two so we should be handle it
    override val url: String
) : Crawler.Product