package com.example.freecart.ApiRepo

import com.example.freecart.bean.ProductListResponse
import com.logidtic.blueaid.utility.InterceptorManager
import com.logidtic.blueaid.utility.UtilAction
import okhttp3.RequestBody

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiModel {

 /*   @POST("user-friends-group")
    suspend fun createChatGroup(@Body request: ChatRequestBean): Response<ChatGroupCreateResponseBean>
*/


@GET("products")
    suspend fun fetchproductlist(): Response<ProductListResponse>


    companion object {
        operator fun invoke(interceptorManager: InterceptorManager): ApiModel {
            return UtilAction.buildRetrofit(interceptorManager).create(ApiModel::class.java)
        }
    }
}