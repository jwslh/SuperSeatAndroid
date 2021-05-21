package indi.wkq.superseatandroid.presenter.impl

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import indi.wkq.superseatandroid.fragment.HistoryFragment
import indi.wkq.superseatandroid.model.impl.LibraryModelImpl
import indi.wkq.superseatandroid.model.response.GetUserInfoResponse
import indi.wkq.superseatandroid.model.response.HistoryResponse
import indi.wkq.superseatandroid.model.response.JsonData
import indi.wkq.superseatandroid.model.response.ReservationsResponse
import indi.wkq.superseatandroid.presenter.ILibraryPresenter
import indi.wkq.superseatandroid.utils.ToastUtils

/**
 * @author  calesq
 * @date    2021/5/19
 */
object LibraryPresenterImpl : ILibraryPresenter {

    private lateinit var mHistoryFragment: HistoryFragment

    override fun getReservations(token: String, historyFragment: HistoryFragment) {
        mHistoryFragment = historyFragment
        LibraryModelImpl.getReservations(token, this, historyFragment)
    }

    fun getReservationsSuccess(jsonData: JsonData) {
        if (!jsonData.status.equals("success")) {
            ToastUtils.warning(jsonData.status + " : " + jsonData.message)
            return
        }

        var gson = Gson()
        var resData: ArrayList<ReservationsResponse>? =
            gson.fromJson(gson.toJson(jsonData.data), object : TypeToken<ArrayList<ReservationsResponse>>(){}.type)
                ?: return
        mHistoryFragment.updatePage(resData!!.get(0))
    }

    override fun getHistory(
        token: String,
        page: Int,
        pageSize: Int,
        historyFragment: HistoryFragment
    ) {
        mHistoryFragment = historyFragment
        LibraryModelImpl.getHistoryRecords(token, page, pageSize, this, historyFragment)
    }

    fun getHistorySuccess(jsonData: JsonData) {
        if (!jsonData.status.equals("success")) {
            ToastUtils.warning(jsonData.status + " : " + jsonData.message)
            return
        }

        var gson = Gson()
        var resData: HistoryResponse? =
            gson.fromJson(gson.toJson(jsonData.data), HistoryResponse::class.java)
                ?: return
        mHistoryFragment.updatePage(resData!!)
    }

    fun fail(code : Int) {
        ToastUtils.error(code)
    }

    fun netError(msg : String?) {
        ToastUtils.error(msg?: "网络错误")
    }

}