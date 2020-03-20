package com.mocktoy.chatajaw.crawler.ppomppu

import com.mocktoy.chatajaw.crawler.Crawler

data class PpomppuProduct(
    override val uid: String,
    override val title: String,
    override val price: String,
    override val url: String
) : Crawler.Product