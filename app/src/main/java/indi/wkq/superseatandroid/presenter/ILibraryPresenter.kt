package indi.wkq.superseatandroid.presenter

import indi.wkq.superseatandroid.fragment.HistoryFragment
import indi.wkq.superseatandroid.fragment.LibraryFragment

/**
 * @author  calesq
 * @date    2021/5/19
 */
interface ILibraryPresenter {
    fun getReservations(token : String, historyFragment: HistoryFragment)

    fun getHistory(token: String, page : Int, pageSize : Int, historyFragment: HistoryFragment)

    fun getSeatsInRoom(token: String, roomId: String, date: String, libraryFragment: LibraryFragment)
}