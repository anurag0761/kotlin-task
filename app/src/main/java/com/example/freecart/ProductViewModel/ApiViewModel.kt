package com.example.freecart.ProductViewModel

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.freecart.ApiRepo.ApiRepo
import com.example.freecart.bean.BaseBean
import com.example.freecart.bean.ImageItem
import com.example.freecart.bean.ImageListResponse
import com.example.freecart.bean.ProductListResponse

import com.logidtic.blueaid.utility.CoroutineHandler
import com.logidtic.blueaid.utility.Handle
import com.logidtic.blueaid.utility.PreferenceHandler

import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.log

class ApiViewModel(

    private val preferenceHandler: PreferenceHandler,
    private val coroutineHandler: CoroutineHandler,
    private val apiRepo: ApiRepo
) : ViewModel() {
  private val TAG = ApiViewModel::class.java.simpleName
    private val taskStarted: MutableLiveData<Boolean> = MutableLiveData()
    private val taskSuccess: MutableLiveData<Boolean> = MutableLiveData()
    private val taskFinished: MutableLiveData<Boolean> = MutableLiveData()
    private val productList: MutableLiveData<ProductListResponse> = MutableLiveData()
    private val imgList: MutableLiveData<ImageListResponse> = MutableLiveData()

    fun getproductList(): LiveData<ProductListResponse> = productList
    fun getimgList(): LiveData<ImageListResponse> = imgList
    fun isTaskStarted(): LiveData<Boolean> = taskStarted

    fun isTaskFinished(): LiveData<Boolean> = taskFinished

    fun fetchproductlist() {
        Log.e(TAG, "feedbackList()")
        coroutineHandler.launchWithMain(object : Handle {
            override fun preExecution() {
                taskStarted.value = true
            }

            override fun postExecution() {
                taskFinished.value = true
            }

            override suspend fun setData() = run {

                apiRepo.fetchproductlist()
            }

            override suspend fun onSuccess(baseBean: BaseBean) {
                val response = baseBean as ProductListResponse
                if (response.products != null) {
                    productList.value = response
                }
            }

            override fun onFail(baseBean: BaseBean?) {
                taskSuccess.value = false
            }
        })
    }

    fun setImage(imageListResponse: ImageListResponse) {

          imgList.value = imageListResponse


    }








}