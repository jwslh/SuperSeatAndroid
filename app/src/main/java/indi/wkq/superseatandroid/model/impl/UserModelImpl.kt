package indi.wkq.superseatandroid.model.impl

import indi.wkq.superseatandroid.model.ApiService
import indi.wkq.superseatandroid.model.IUserModel
import indi.wkq.superseatandroid.model.response.JsonData
import indi.wkq.superseatandroid.presenter.Impl.UserPresenterImpl
import indi.wkq.superseatandroid.utils.ICallback
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author  calesq
 * @date    2021/4/29
 */
class UserModelImpl : IUserModel {
    override fun login(username: String, password: String, callBack : UserPresenterImpl) {
        val r = Retrofit.Builder().baseUrl("https://seat.lib.whu.edu.cn:8443/").addConverterFactory(
            GsonConverterFactory.create()
        ).build()

        val apiService = r.create(ApiService::class.java)

        var res = apiService.login(username, password)

        callBack.showLoading()
        res.enqueue(object : Callback<JsonData> {
            override fun onResponse(call: Call<JsonData>, response: Response<JsonData>) {
                callBack.hideLoading()
                response.body()?: callBack.fail(response.raw().code())
                var jsonData : JsonData = response.body()!!
                callBack.success(jsonData)
            }

            override fun onFailure(call: Call<JsonData>, t: Throwable) {
                println("failed: " + t.message)
                callBack.hideLoading()
            }
        })
    }

    override fun getUserInfo() {
        TODO("Not yet implemented")
    }

    override fun getHistory() {
        TODO("Not yet implemented")
    }

}
