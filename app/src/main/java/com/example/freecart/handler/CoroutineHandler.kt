package com.logidtic.blueaid.utility

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.freecart.bean.BaseBean
import com.google.gson.Gson
import com.google.gson.JsonObject

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException


class CoroutineHandler(private val context: Context) {
     var errorBodyObject: JSONObject ?= null
    /**
     * Launch the coroutine with Main scope.
     *
     * @param handler : Instance of the handler interface.
     */
    fun launchWithMain(handler: Handle) {
        CoroutineScope(Dispatchers.Main).launch {
            handler.preExecution()

            val data: BaseBean?
            try {
                data = handler.setData()

                if (data != null) {

                        handler.onSuccess(data)

                } else {
                    handler.onFail(data)
                    handleExpiredToken(errorBodyObject,context)
                    errorBodyObject = null
                }


            } catch (e: SocketTimeoutException) {
                Toast.makeText(context, UtilData.UNABLE_TO_CONNECT_MESSAGE, Toast.LENGTH_SHORT)
                    .show()
                handler.onFail()
            } catch (e: IOException) {
                Toast.makeText(context, e.message!!, Toast.LENGTH_SHORT).show()
                handler.onFail()
            } catch (e: Exception) {
                e.printStackTrace()
                handler.onFail()
                handleExpiredToken(errorBodyObject,context)
                errorBodyObject = null
            }

            handler.postExecution()
        }
    }

    private fun handleExpiredToken(errorBodyObject: JSONObject?, context: Context) {

    }

    /**
     * Run the current coroutine in IO scope.
     *
     * @param action : Action to perform.
     *
     * @return the data with the generic type specified.
     */
    suspend fun <T> handleWithIO(action: suspend () -> Response<T>): T? {

        var data: T? = null
        withContext(Dispatchers.IO) {
            val response = action()
            if (response.body() != null) {
                 if (response.isSuccessful) data = response.body()
            } else {
                if (response.errorBody() != null) {
                    try {
                        errorBodyObject = JSONObject(response.errorBody()!!.string())
                    } catch (e: Exception) {
                        Toast.makeText(context, UtilData.DEFAULT_ERROR_MESSAGE, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        return data
    }
}