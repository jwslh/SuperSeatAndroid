package indi.wkq.superseatandroid.model

import indi.wkq.superseatandroid.fragment.HistoryFragment
import indi.wkq.superseatandroid.presenter.impl.LibraryPresenterImpl

/**
 * @author  calesq
 * @date    2021/4/29
 *
 * 图书馆数据层接口
 */
interface ILibraryModel {

    fun getReservations(token : String, libraryPresenterImpl : LibraryPresenterImpl, historyFragment: HistoryFragment)

    fun getHistoryRecords(token: String, page : Int, pageSize : Int, libraryPresenterImpl: LibraryPresenterImpl, historyFragment: HistoryFragment)
}