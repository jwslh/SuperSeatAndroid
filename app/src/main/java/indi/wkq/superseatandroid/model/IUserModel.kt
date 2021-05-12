package indi.wkq.superseatandroid.model

import indi.wkq.superseatandroid.fragment.LoadingLayout
import indi.wkq.superseatandroid.fragment.LoginFragment
import indi.wkq.superseatandroid.fragment.MeFragment
import indi.wkq.superseatandroid.presenter.Impl.UserPresenterImpl

/**
 * @author  calesq
 * @date    2021/4/29
 *
 * 用户数据层接口
 */
interface IUserModel {
    fun login(username : String, password : String, mCallback : UserPresenterImpl, l : LoginFragment)

    fun getUserInfo(token : String, mCallBack : UserPresenterImpl, l : MeFragment)

    fun getHistory()
}