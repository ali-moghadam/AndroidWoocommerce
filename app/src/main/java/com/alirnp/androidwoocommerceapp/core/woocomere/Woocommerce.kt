package com.alirnp.androidwoocommerceapp.core.woocomere

import com.alirnp.androidwoocommerceapp.core.constant.ApiVersion
import com.alirnp.androidwoocommerceapp.repository.ProductRepository

class Woocommerce(siteUrl: String, apiVersion: ApiVersion, consumerKey: String, consumerSecret: String) {
    companion object {
        val API_V1 = ApiVersion.API_VERSION1
        val API_V2 = ApiVersion.API_VERSION2
        val API_V3 = ApiVersion.API_VERSION3
    }


    private val productRepository: ProductRepository


    init {
        val baseUrl = "$siteUrl/wp-json/wc/v$apiVersion/"
        val cartBaseUrl = "$siteUrl/wp-json/cocart/v1/"

        productRepository = ProductRepository(baseUrl, consumerKey, consumerSecret)

    }


    fun productRepository(): ProductRepository {
        return productRepository
    }



    class Builder {
        private lateinit var siteUrl: String
        private lateinit var apiVersion: ApiVersion
        private lateinit var consumerKey: String
        private lateinit var consumerSecret: String

        fun setSiteUrl(siteUrl: String): Builder {
            this.siteUrl = siteUrl
            return this
        }

        fun setApiVersion(amiVersion: ApiVersion): Builder {
            this.apiVersion = amiVersion
            return this
        }

        fun setConsumerKey(consumerKey: String): Builder {
            this.consumerKey = consumerKey
            return this
        }

        fun setConsumerSecret(consumerSecret: String): Builder {
            this.consumerSecret = consumerSecret
            return this
        }


        fun build(): Woocommerce {
            return Woocommerce(siteUrl, apiVersion, consumerKey, consumerSecret)
        }


    }



}