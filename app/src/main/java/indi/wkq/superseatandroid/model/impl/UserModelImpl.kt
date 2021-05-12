package indi.wkq.superseatandroid.model.impl

import indi.wkq.superseatandroid.constant.URL
import indi.wkq.superseatandroid.fragment.LoadingLayout
import indi.wkq.superseatandroid.fragment.LoginFragment
import indi.wkq.superseatandroid.fragment.MeFragment
import indi.wkq.superseatandroid.model.ApiService
import indi.wkq.superseatandroid.model.IUserModel
import indi.wkq.superseatandroid.model.response.JsonData
import indi.wkq.superseatandroid.presenter.Impl.UserPresenterImpl
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author  calesq
 * @date    2021/4/29
 */
object UserModelImpl : IUserModel {

    private val apiService: ApiService = Retrofit.Builder().baseUrl(URL.BASE_URL).addConverterFactory(
        GsonConverterFactory.create()
    ).build().create(ApiService::class.java)

    override fun login(username: String, password: String, mCallBack : UserPresenterImpl, l : LoginFragment) {

        val res = apiService.login(username, password)

        l.showLoading()
        res.enqueue(object : Callback<JsonData> {
            override fun onResponse(call: Call<JsonData>, response: Response<JsonData>) {
                l.hideLoading()
                response.body()?: mCallBack.fail(response.raw().code())
                var jsonData : JsonData = response.body()!!
                mCallBack.loginSuccess(jsonData)
            }

            override fun onFailure(call: Call<JsonData>, t: Throwable) {
                println("failed: " + t.message)
                l.hideLoading()
            }
        })
    }

    override fun getUserInfo(token : String, mCallBack : UserPresenterImpl, l : MeFragment) {
        val res = apiService.getUserInfo(token)
        l.showLoading()
        res.enqueue(object : Callback<JsonData> {
            override fun onResponse(call: Call<JsonData>, response: Response<JsonData>) {
                l.hideLoading()
                response.body()?: mCallBack.fail(response.raw().code())
                var jsonData : JsonData = response.body()!!
                mCallBack.usrInfoSuccess(jsonData)
            }

            override fun onFailure(call: Call<JsonData>, t: Throwable) {
                println("failed: " + t.message)
                l.hideLoading()
            }
        })
    }

    override fun getHistory() {
        TODO("Not yet implemented")
    }

}
