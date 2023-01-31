package com.ams.khdmaEmployeeId.data.apis

import com.ams.khdmaEmployeeId.data.models.Country
import de.jensklingenberg.ktorfit.http.GET

interface CountriesAPIs {
    @GET("all/")
    suspend fun getCountries(): List<Country>
}