package com.ams.khdmaEmployeeId

import com.ams.khdmaEmployeeId.data.apis.CountriesAPIs
import com.ams.khdmaEmployeeId.data.apis.createCountriesAPIs
import com.ams.khdmaEmployeeId.data.httpClient.RestClient
import org.koin.dsl.module

// val restClient = KoinPlatformTools.defaultContext().get().get<RestClient>()

val apisModule = module {
    // Not working
    factory { get<RestClient>().createAPI<CountriesAPIs>() }
    // Working
    // factory { get<RestClient>().createClient().createCountriesAPIs() }
}