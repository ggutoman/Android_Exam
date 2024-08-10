package com.pg.android_exam.android.Repositories

import com.pg.android_exam.android.Connection.KtorHttpClient
import com.pg.android_exam.android.Models.ApiConstants
import com.pg.android_exam.android.Models.UserMain
import io.ktor.client.call.body
import io.ktor.client.request.get

class HttpRepository {

    val httpClient = KtorHttpClient

    suspend fun getRequestUserMain(): List<UserMain> =
        httpClient.httpClient.get(ApiConstants.BASE_RANDOM_URL).body<List<UserMain>>()
}