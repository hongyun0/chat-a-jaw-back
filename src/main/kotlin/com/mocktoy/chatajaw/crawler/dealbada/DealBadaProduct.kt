package com.mocktoy.chatajaw.crawler.dealbada

import com.mocktoy.chatajaw.crawler.Crawler

data class DealBadaProduct(
    override val uid: String,
    override val title: String,
    override val price: String, // todo : it could be more than two so we should be handle it
    override val url: String
) : Crawler.Product