package com.example.freecart.ApiRepo


import com.example.freecart.bean.BaseBean
import com.logidtic.blueaid.utility.CoroutineHandler
import com.logidtic.blueaid.utility.InterceptorManager
import okhttp3.RequestBody


class ApiRepo(private val coroutineHandler: CoroutineHandler, private val interceptorManager: InterceptorManager) {


suspend fun fetchproductlist() = coroutineHandler.handleWithIO {
        ApiModel(interceptorManager).
    fetchproductlist() } as BaseBean


}
