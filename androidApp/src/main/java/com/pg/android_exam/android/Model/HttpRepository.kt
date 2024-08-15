package com.pg.android_exam.android.Model

import com.pg.android_exam.android.View.ApiConstants
import com.pg.android_exam.android.View.User
import io.ktor.client.call.body
import io.ktor.client.request.get
import java.lang.Exception

object HttpRepository {

    val httpClient = KtorHttpClient

    suspend fun getRequestUserMain(): Response<User> {
        return try {
            Response.Success(httpClient.httpClient.get(ApiConstants.BASE_RANDOM_URL).body())
        }catch (e: Exception){
            Response.Error(e.message.toString())
        }
    }


    interface Response<out T>{
        data class Success<T>(val data: T): Response<T>
        data class Error(val exception: String): Response<Nothing>
    }
}