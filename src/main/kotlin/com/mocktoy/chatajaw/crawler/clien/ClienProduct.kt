package com.mocktoy.chatajaw.crawler.clien

import com.mocktoy.chatajaw.crawler.Crawler

data class ClienProduct(
    override val uid: String,
    override val title: String,
    override val price: String,
    override val url: String
) : Crawler.Product