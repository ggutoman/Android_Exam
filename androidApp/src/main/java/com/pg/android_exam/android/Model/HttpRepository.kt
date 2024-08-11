package com.pg.android_exam.android.Model

import com.pg.android_exam.android.Connection.KtorHttpClient
import com.pg.android_exam.android.View.ApiConstants
import com.pg.android_exam.android.View.User
import io.ktor.client.call.body
import io.ktor.client.request.get

object HttpRepository {

    val httpClient = KtorHttpClient

    suspend fun getRequestUserMain(): User =
        httpClient.httpClient.get(ApiConstants.BASE_RANDOM_URL).body()
}