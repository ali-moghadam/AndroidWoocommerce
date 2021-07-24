package com.alirnp.androidwoocommerceapp.core.woocomere

import android.app.Application
import com.alirnp.androidwoocommerceapp.Config
import com.alirnp.androidwoocommerceapp.core.constant.ApiVersion
import com.alirnp.androidwoocommerceapp.repository.CategoryRepository
import com.alirnp.androidwoocommerceapp.repository.ProductRepository
import com.alirnp.androidwoocommerceapp.repository.UserRepository

class Woocommerce(application: Application) {
    companion object {
        private val apiVersion = ApiVersion.API_VERSION3
        private const val siteUrl = Config.URL
    }

    val productRepository: ProductRepository
    val categoryRepository : CategoryRepository
    val userRepository: UserRepository

    init {
        val woocommerceApiUrl = "$siteUrl/wp-json/wc/v$apiVersion/"
        val wordpressUserApiUrl = "$siteUrl/wp-json/custom-plugin/"

        val cartBaseUrl = "$siteUrl/wp-json/cocart/v1/"

        productRepository = ProductRepository(application, woocommerceApiUrl)
        categoryRepository = CategoryRepository(application, woocommerceApiUrl)
        userRepository = UserRepository(application, wordpressUserApiUrl)

    }
}