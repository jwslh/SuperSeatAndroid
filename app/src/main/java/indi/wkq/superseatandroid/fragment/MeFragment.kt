package indi.wkq.superseatandroid.fragment

import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import com.xuexiang.xpage.annotation.Page
import com.xuexiang.xpage.base.XPageFragment
import com.xuexiang.xpage.enums.CoreAnim
import com.xuexiang.xpage.utils.TitleBar
import com.xuexiang.xui.utils.WidgetUtils
import com.xuexiang.xui.widget.dialog.LoadingDialog
import com.xuexiang.xutil.app.ActivityUtils
import indi.wkq.superseatandroid.R
import indi.wkq.superseatandroid.activity.LoginActivity
import indi.wkq.superseatandroid.constant.MMK
import indi.wkq.superseatandroid.presenter.impl.UserPresenterImpl
import indi.wkq.superseatandroid.model.response.GetUserInfoResponse
import indi.wkq.superseatandroid.utils.LocalStorageUtils
import indi.wkq.superseatandroid.utils.ToastUtils

/**
 * @author  calesq
 * @date    2021/4/25
 */
@Page(anim = CoreAnim.none)
class MeFragment : XPageFragment() {
    override fun initListeners() {
    }


    override fun getLayoutId(): Int {
        return R.layout.fragment_me
    }

    override fun initViews() {
        initData()
        mRootView.findViewById<View>(R.id.header).findViewById<TextView>(R.id.tv_avatar).text = "s"

        mRootView.findViewById<View>(R.id.school_id_item).apply {
            findViewById<AppCompatImageView>(R.id.icon).setImageResource(R.drawable.icon_me_item_school_id)
            findViewById<TextView>(R.id.title).setText(R.string.tv_title_school_id)
            findViewById<TextView>(R.id.item_val).text = ""
        }


        mRootView.findViewById<View>(R.id.status_item).apply {
            findViewById<AppCompatImageView>(R.id.icon).setImageResource(R.drawable.icon_me_item_status)
            findViewById<TextView>(R.id.title).setText(R.string.tv_title_status)
            findViewById<TextView>(R.id.item_val).text = "正常"
        }

        mRootView.findViewById<View>(R.id.reserve_status_item).apply {
            findViewById<AppCompatImageView>(R.id.icon).setImageResource(R.drawable.icon_me_item_reserve_status)
            findViewById<TextView>(R.id.title).setText(R.string.tv_title_reserve_status)
            findViewById<TextView>(R.id.item_val).text = "无"
        }

        mRootView.findViewById<View>(R.id.last_in_item).apply {
            findViewById<AppCompatImageView>(R.id.icon).setImageResource(R.drawable.icon_me_item_last_in)
            findViewById<TextView>(R.id.title).setText(R.string.tv_title_last_in)
            findViewById<TextView>(R.id.item_val).text = ""
        }

        mRootView.findViewById<View>(R.id.last_out_item).apply {
            findViewById<AppCompatImageView>(R.id.icon).setImageResource(R.drawable.icon_me_item_last_out)
            findViewById<TextView>(R.id.title).setText(R.string.tv_title_last_out)
            findViewById<TextView>(R.id.item_val).text = ""
        }

    }

    override fun initTitleBar(): TitleBar {
        return TitleBar(context)
    }

    private fun translateStatus(old : String) : String {
        return when(old) {
            "NORMAL" -> "正常"
            else -> "异常"
        }
    }

    private fun translateReserverStatus(old : String?) : String {
        return when(old) {
            "CHECK_IN"  -> "已签到"
            "RESERVE"   -> "未签到"
            "AWAY"      -> "离开"
            else -> "暂无"
        }
    }

    private fun initData() {
        var token = LocalStorageUtils.getValueFromLocal(context!!, MMK.TOKEN)
        if (token == "") {
            ToastUtils.warning("身份过期，请重新登录", 1500)
            ActivityUtils.startActivity(LoginActivity::class.java)
            return
        }
        UserPresenterImpl.getUserInfo(token, this)
    }

    fun updateView(getUserInfoResponse: GetUserInfoResponse) {
        mRootView.findViewById<View>(R.id.header).findViewById<TextView>(R.id.tv_avatar).text = getUserInfoResponse.name

        mRootView.findViewById<View>(R.id.school_id_item).apply {
            findViewById<TextView>(R.id.item_val).text = getUserInfoResponse.username
        }

        mRootView.findViewById<View>(R.id.status_item).apply {
            findViewById<TextView>(R.id.item_val).text = translateStatus(getUserInfoResponse.status)
        }

        mRootView.findViewById<View>(R.id.reserve_status_item).apply {
            findViewById<TextView>(R.id.item_val).text = translateReserverStatus(getUserInfoResponse.reservationStatus)
        }

        mRootView.findViewById<View>(R.id.last_in_item).apply {
            findViewById<TextView>(R.id.item_val).text = getUserInfoResponse.lastIn
        }

        mRootView.findViewById<View>(R.id.last_out_item).apply {
            findViewById<TextView>(R.id.item_val).text = getUserInfoResponse.lastOut
        }
    }

    private val mLoadingDialog : LoadingDialog by lazy {
        WidgetUtils.getLoadingDialog(context!!)
            .setIconScale(0.4f)
            .setLoadingSpeed(8)
    }

    fun showLoading() {
        mLoadingDialog.show()
    }

    fun hideLoading() {
        mLoadingDialog.hide()
        mLoadingDialog.dismiss()
    }
}