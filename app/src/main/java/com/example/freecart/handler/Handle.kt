package com.logidtic.blueaid.utility

import com.example.freecart.bean.BaseBean


interface Handle {
    /**
     * Action on pre execution.
     */
    fun preExecution() {}

    /**
     * Action and Set data.
     *
     * @return instance of BaseBean.
     */
    suspend fun setData(): BaseBean?

    /**
     * Action on success.
     *
     * @param baseBean : Instance of BaseBean.
     */
    suspend fun onSuccess(baseBean: BaseBean)

    /**
     * Action on failure
     *
     * @param baseBean : Instance of BaseBean (Default = null).
     */
    fun onFail(baseBean: BaseBean? = null) {}

    /**
     * Action on post execution
     */
    fun postExecution() {}
}