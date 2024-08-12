package com.pg.android_exam.android.Model

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.accept
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object KtorHttpClient {

    val httpClient: HttpClient = HttpClient(Android){

        //todo: this block -> install properties for this http object

        //http logging
        install(Logging){
            level = LogLevel.ALL
        }

        //http timeout property
        install(HttpTimeout){
            connectTimeoutMillis = 150000L
            requestTimeoutMillis = 15000L
            socketTimeoutMillis = 15000L
        }

        //http response property
        install(ContentNegotiation){
            json(
                Json {
                    isLenient = true //allow unspecified json inputs (string, boolean, int, etc)
                    ignoreUnknownKeys = true //ignore unspecified JSON input properties
                    prettyPrint = true //pretty print JSON result
                    explicitNulls = true //allow encoding null properties w/o default values to be presented on result
                }
            )
        }

        //http request property. POST, PUT, GET
        install(DefaultRequest){
            //header
            header(HttpHeaders.ContentType, ContentType.Application.Json)

            //accept json as request body
            accept(ContentType.Application.Json)
        }
    }
}