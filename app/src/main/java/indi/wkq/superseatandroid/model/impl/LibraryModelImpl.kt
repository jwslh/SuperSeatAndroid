package indi.wkq.superseatandroid.model.impl

import indi.wkq.superseatandroid.constant.MyURL
import indi.wkq.superseatandroid.fragment.HistoryFragment
import indi.wkq.superseatandroid.fragment.LibraryFragment
import indi.wkq.superseatandroid.model.ApiService
import indi.wkq.superseatandroid.model.ILibraryModel
import indi.wkq.superseatandroid.model.response.JsonData
import indi.wkq.superseatandroid.presenter.impl.LibraryPresenterImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author  calesq
 * @date    2021/4/29
 */
object LibraryModelImpl : ILibraryModel {
    private val apiService: ApiService =
        Retrofit.Builder().baseUrl(MyURL.BASE_URL).addConverterFactory(
            GsonConverterFactory.create()
        ).build().create(ApiService::class.java)

    /**
     * 获取预约记录
     */
    override fun getReservations(
        token: String,
        libraryPresenterImpl: LibraryPresenterImpl,
        historyFragment: HistoryFragment
    ) {
        val res = apiService.reservations(token)

        historyFragment.showLoading()
        res.enqueue(object : Callback<JsonData> {
            override fun onResponse(call: Call<JsonData>, response: Response<JsonData>) {
                historyFragment.hideLoading()
                response.body() ?: return libraryPresenterImpl.fail(response.raw().code())
                libraryPresenterImpl.getReservationsSuccess(response.body()!!)
            }

            override fun onFailure(call: Call<JsonData>, t: Throwable) {
                historyFragment.hideLoading()
                libraryPresenterImpl.netError(t.message)
            }

        })
    }

    override fun getHistoryRecords(
        token: String,
        page: Int,
        pageSize: Int,
        libraryPresenterImpl: LibraryPresenterImpl,
        historyFragment: HistoryFragment
    ) {
        val res = apiService.getHistory(page, pageSize, token)

        historyFragment.showLoading()
        res.enqueue(object : Callback<JsonData> {
            override fun onResponse(call: Call<JsonData>, response: Response<JsonData>) {
                historyFragment.hideLoading()
                response.body() ?: return libraryPresenterImpl.fail(response.raw().code())
                libraryPresenterImpl.getHistorySuccess(response.body()!!)
            }

            override fun onFailure(call: Call<JsonData>, t: Throwable) {
                historyFragment.hideLoading()
                libraryPresenterImpl.netError(t.message)
            }

        })
    }

    override fun getSeatsInfo(
        token: String,
        roomId: String,
        date: String,
        libraryPresenterImpl: LibraryPresenterImpl,
        libraryFragment: LibraryFragment
    ) {
        libraryFragment.showLoading()
        apiService.getSeatsInRoom(roomId, date, token).enqueue(object : Callback<JsonData> {
            override fun onResponse(call: Call<JsonData>, response: Response<JsonData>) {
                libraryFragment.hideLoading()
                response.body() ?: return libraryPresenterImpl.fail(response.raw().code())
                libraryPresenterImpl.getSeatsInRoomSuccess(response.body()!!)
            }

            override fun onFailure(call: Call<JsonData>, t: Throwable) {
                libraryFragment.hideLoading()
                libraryPresenterImpl.netError(t.message)
            }

        })
    }
}