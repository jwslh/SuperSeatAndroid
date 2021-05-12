package indi.wkq.superseatandroid.presenter

import indi.wkq.superseatandroid.fragment.LoginFragment
import indi.wkq.superseatandroid.fragment.MeFragment

/**
 * @author  calesq
 * @date    2021/5/8
 */
interface IUserPresenter {
    fun login(username : String, password : String, loginFragment: LoginFragment)

    fun getUserInfo(token : String, meFragment: MeFragment)
}