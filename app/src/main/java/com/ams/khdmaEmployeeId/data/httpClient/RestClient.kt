package com.ams.khdmaEmployeeId.data.httpClient

import de.jensklingenberg.ktorfit.Ktorfit

interface RestClient {
    fun <API> createAPI(): API
    fun createClient(): Ktorfit
}