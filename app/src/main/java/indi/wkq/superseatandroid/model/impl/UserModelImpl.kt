package indi.wkq.superseatandroid.model.impl

import indi.wkq.superseatandroid.constant.MyURL
import indi.wkq.superseatandroid.fragment.LoginFragment
import indi.wkq.superseatandroid.fragment.MeFragment
import indi.wkq.superseatandroid.model.ApiService
import indi.wkq.superseatandroid.model.IUserModel
import indi.wkq.superseatandroid.model.response.JsonData
import indi.wkq.superseatandroid.presenter.impl.UserPresenterImpl
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author  calesq
 * @date    2021/4/29
 */
object UserModelImpl : IUserModel {

    private val apiService: ApiService = Retrofit.Builder().baseUrl(MyURL.BASE_URL).addConverterFactory(
        GsonConverterFactory.create()
    ).build().create(ApiService::class.java)

    override fun login(username: String, password: String, upi : UserPresenterImpl, l : LoginFragment) {

        val res = apiService.login(username, password)

        l.showLoading()
        res.enqueue(object : Callback<JsonData> {
            override fun onResponse(call: Call<JsonData>, response: Response<JsonData>) {
                l.hideLoading()
                response.body()?: upi.fail(response.raw().code())
                var jsonData : JsonData = response.body()!!
                upi.loginSuccess(jsonData)
            }

            override fun onFailure(call: Call<JsonData>, t: Throwable) {
                l.hideLoading()
                upi.netError(t.message)
            }
        })
    }

    override fun getUserInfo(token: String, upi: UserPresenterImpl, m: MeFragment) {
        val res = apiService.getUserInfo(token)

        m.showLoading()
        res.enqueue(object : Callback<JsonData> {
            override fun onResponse(call: Call<JsonData>, response: Response<JsonData>) {
                m.hideLoading()
                response.body()?: upi.fail(response.raw().code())
                var jsonData : JsonData = response.body()!!
                upi.getUserInfoSuccess(jsonData)
            }

            override fun onFailure(call: Call<JsonData>, t: Throwable) {
                m.hideLoading()
                upi.netError(t.message)
            }
        })
    }
}
