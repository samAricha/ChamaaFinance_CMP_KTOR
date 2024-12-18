package com.teka.chamaa_finance

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform