package indi.wkq.superseatandroid.presenter.impl

import com.google.gson.Gson
import com.xuexiang.xutil.app.ActivityUtils
import indi.wkq.superseatandroid.R
import indi.wkq.superseatandroid.activity.MainActivity
import indi.wkq.superseatandroid.constant.MMK
import indi.wkq.superseatandroid.fragment.LoginFragment
import indi.wkq.superseatandroid.fragment.MeFragment
import indi.wkq.superseatandroid.model.impl.UserModelImpl
import indi.wkq.superseatandroid.response.JsonData
import indi.wkq.superseatandroid.response.LoginResponse
import indi.wkq.superseatandroid.presenter.IUserPresenter
import indi.wkq.superseatandroid.response.GetUserInfoResponse
import indi.wkq.superseatandroid.utils.LocalStorageUtils
import indi.wkq.superseatandroid.utils.MMKVUtils
import indi.wkq.superseatandroid.utils.ToastUtils

/**
 * @author  calesq
 * @date    2021/5/8
 */
object UserPresenterImpl : IUserPresenter {

    private var mLoginFragment : LoginFragment? = null
    private var mmeFragment : MeFragment? = null

    override fun login(username: String, password: String, loginFragment: LoginFragment) {
        mLoginFragment = loginFragment
        if (isEmpty(username) || isEmpty(password)) {
            ToastUtils.info(R.string.name_or_pwd_empty)
            return
        }
        UserModelImpl.login(username, password, this, loginFragment)
    }

    fun loginSuccess(jsonData: JsonData) {

        if (!jsonData.status.equals("success")) {
            ToastUtils.warning(jsonData.status + " : " + jsonData.message)
            return
        }

        ToastUtils.success("登录成功", 1500)

        var gson = Gson()
        var resData = gson.fromJson(gson.toJson(jsonData.data), LoginResponse::class.java)

        MMKVUtils.setMMV(MMK.TOKEN, resData.token)
        MMKVUtils.setMMV(MMK.TOKEN_EXPIRATION, System.currentTimeMillis() + MMK.TOKEN_EXPIRATION_TIME)

        LocalStorageUtils.saveValueIntoLocal(mLoginFragment!!.context!!, MMK.TOKEN, resData.token)
        LocalStorageUtils.saveValueIntoLocal(mLoginFragment!!.context!!, MMK.TOKEN_EXPIRATION, (System.currentTimeMillis() + MMK.TOKEN_EXPIRATION_TIME).toString())
        ActivityUtils.startActivity(MainActivity::class.java)
    }

    override fun getUserInfo(token: String, meFragment: MeFragment) {
        mmeFragment = meFragment
        UserModelImpl.getUserInfo(token, this, meFragment)
    }

    fun getUserInfoSuccess(jsonData: JsonData) {
        if (!jsonData.status.equals("success")) {
            ToastUtils.warning(jsonData.status + " : " + jsonData.message)
            return
        }

        var gson = Gson()
        var resData = gson.fromJson(gson.toJson(jsonData.data), GetUserInfoResponse::class.java)
        mmeFragment!!.updateView(resData!!)
    }

    fun fail(code : Int) {
        ToastUtils.error(code)
    }

    private fun isEmpty(s : String) : Boolean {
        return s == ""
    }
}