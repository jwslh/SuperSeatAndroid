package indi.wkq.superseatandroid.model

import indi.wkq.superseatandroid.model.impl.UserModelImpl
import indi.wkq.superseatandroid.presenter.Impl.UserPresenterImpl
import indi.wkq.superseatandroid.utils.ICallback

/**
 * @author  calesq
 * @date    2021/4/29
 *
 * 用户数据层接口
 */
interface IUserModel {
    fun login(username : String, password : String, callback : UserPresenterImpl)

    fun getUserInfo()

    fun getHistory()
}