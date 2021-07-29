package com.alirnp.androidwoocommerceapp

import com.alirnp.androidwoocommerceapp.core.constant.ApiVersion

class Config {
    companion object{
        // base setting
        private const val URL = "http://ali-moghadaam.ir/shop"
        const val CONSUMER_KEY = "ck_8b7fc5cbb1973b1e5bbc8bdb1bd8ac2e0ea5bea7"
        const val CONSUMER_SECRET = "cs_761489a843897494e4acb2930c72df2870825dc9"

        val apiVersion = ApiVersion.API_VERSION3

        val woocommerceApiUrl = "$URL/wp-json/wc/v$apiVersion/"
        val wordpressUserApiUrl = "$URL/wp-json/custom-plugin/"

        val cartBaseUrl = "$URL/wp-json/cocart/v1/"
    }

}