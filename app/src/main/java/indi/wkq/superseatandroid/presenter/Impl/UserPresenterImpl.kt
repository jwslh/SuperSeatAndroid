package indi.wkq.superseatandroid.presenter.Impl

import android.app.Activity
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.xuexiang.xutil.app.ActivityUtils
import indi.wkq.superseatandroid.R
import indi.wkq.superseatandroid.activity.LoginActivity
import indi.wkq.superseatandroid.activity.MainActivity
import indi.wkq.superseatandroid.constant.MMK
import indi.wkq.superseatandroid.fragment.LoginFragment
import indi.wkq.superseatandroid.model.impl.UserModelImpl
import indi.wkq.superseatandroid.model.response.JsonData
import indi.wkq.superseatandroid.model.response.LoginResponse
import indi.wkq.superseatandroid.presenter.IUserPresenter
import indi.wkq.superseatandroid.utils.ICallback
import indi.wkq.superseatandroid.utils.LocalStorageUtils
import indi.wkq.superseatandroid.utils.MMKVUtils
import indi.wkq.superseatandroid.utils.ToastUtils

/**
 * @author  calesq
 * @date    2021/5/8
 */
class UserPresenterImpl(rootView : Fragment) : IUserPresenter, ICallback{

    private val mActivity : LoginFragment by lazy {
        rootView as LoginFragment
    }

    private val mUserModel : UserModelImpl by lazy {
        UserModelImpl()
    }

    override fun login(username: String, password: String) {
        if (isEmpty(username) || isEmpty(password)) {
            ToastUtils.info(R.string.name_or_pwd_empty)
            return
        }
        mUserModel.login(username, password, this)
    }

    open fun success(jsonData: JsonData) {

        if (!jsonData.status.equals("success")) {
            ToastUtils.warning(jsonData.status + " : " + jsonData.message)
            return
        }

        ToastUtils.success("登录成功", 1500)

        var gson = Gson()
        var resData = gson.fromJson(gson.toJson(jsonData.data), LoginResponse::class.java)

        MMKVUtils.setMMV(MMK.TOKEN, resData.token)
        MMKVUtils.setMMV(MMK.TOKEN_EXPIRATION, System.currentTimeMillis() + MMK.TOKEN_EXPIRATION_TIME)

        LocalStorageUtils.saveValueIntoLocal(mActivity.context!!, MMK.TOKEN, resData.token)
        LocalStorageUtils.saveValueIntoLocal(mActivity.context!!, MMK.TOKEN_EXPIRATION, (System.currentTimeMillis() + MMK.TOKEN_EXPIRATION_TIME).toString())
        ActivityUtils.startActivity(MainActivity::class.java)
    }

    open fun fail(code : Int) {
        ToastUtils.error(code)
    }

    override fun showLoading() {
        mActivity.showLoading()
    }

    override fun hideLoading() {
        mActivity.hideLoading()
    }

    private fun isEmpty(s : String) : Boolean {
        return s == ""
    }
}