package com.pg.android_exam

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform