package indi.wkq.superseatandroid.presenter

import indi.wkq.superseatandroid.fragment.HistoryFragment

/**
 * @author  calesq
 * @date    2021/5/19
 */
interface ILibraryPresenter {
    fun getReservations(token : String, historyFragment: HistoryFragment)

    fun getHistory(token: String, page : Int, pageSize : Int, historyFragment: HistoryFragment)
}