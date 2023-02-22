package com.logidtic.blueaid.utility

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log

import okhttp3.Interceptor
import okhttp3.Response

class InterceptorManager(private val context: Context): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if(!isInternetAvailable()) throw NoNetworkException()
   //     val token = PreferenceHandler(context).getString(UtilData.TOKEN)
     //   Log.e("InterceptionManager", "token is: $token")

            val request = chain.request().newBuilder()
                .addHeader("Content-Type", "application/json")
               // .addHeader("authorization", "Bearer $token")
                .build()
        return    chain.proceed(request)
    }

    /**
     * Check for internet connectivity
     * @return "true" if internet connection available and "false" if not.
     */
    @Suppress("DEPRECATION")
    fun isInternetAvailable(): Boolean {
        var result = false
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            connectivityManager?.run {
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)?.run {
                    result = when {
                        hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                        else -> false
                    }
                }
            }
        } else {
            connectivityManager?.run {
                connectivityManager.activeNetworkInfo?.run {
                    if (type == ConnectivityManager.TYPE_WIFI) {
                        result = true
                    } else if (type == ConnectivityManager.TYPE_MOBILE) {
                        result = true
                    }
                }
            }
        }
        return result
    }
}