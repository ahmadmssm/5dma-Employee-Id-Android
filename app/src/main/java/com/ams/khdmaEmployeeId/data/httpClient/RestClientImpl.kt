package com.ams.khdmaEmployeeId.data.httpClient

import com.ams.khdmaEmployeeId.BuildConfig
import com.ams.khdmaEmployeeId.data.interceptors.HttpInterceptors
import de.jensklingenberg.ktorfit.Ktorfit
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.koin.core.annotation.Single

@Single
class RestClientImpl(httpInterceptors: HttpInterceptors): RestClient {

    private companion object {
        private const val REQUEST_TIMEOUT_INTERVAL = 5000   // Milliseconds
    }

    private val client by lazy {
        Ktorfit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .httpClient(ktorHttpClient)
            .build()
    }

    private val httpClientEngine by lazy {
        OkHttp.create {
            httpInterceptors.getInterceptors().forEach {
                this.addInterceptor(it)
            }
        }
    }

    private val ktorHttpClient by lazy {
        // https://ktor.io/docs/http-client-engines.html#default
        HttpClient(engine = this.httpClientEngine) {

            install(HttpTimeout) {
                requestTimeoutMillis = REQUEST_TIMEOUT_INTERVAL.toLong()
            }

            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }

            engine {
                // this: HttpClientEngineConfig
                threadsCount = 4
                pipelining = true
            }

            // Throws exceptions for non-2xx error responses.
            expectSuccess = true
        }
    }

    override fun <API> createAPI(): API = this.client.create()

    override fun createClient() = this.client
}