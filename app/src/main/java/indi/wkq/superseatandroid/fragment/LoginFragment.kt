package indi.wkq.superseatandroid.fragment

import android.view.View
import butterknife.OnClick
import com.xuexiang.xpage.annotation.Page
import com.xuexiang.xpage.base.XPageFragment
import com.xuexiang.xpage.enums.CoreAnim
import com.xuexiang.xpage.utils.TitleBar
import com.xuexiang.xui.utils.CountDownButtonHelper
import com.xuexiang.xui.utils.WidgetUtils
import com.xuexiang.xui.widget.dialog.LoadingDialog
import com.xuexiang.xui.widget.edittext.materialedittext.MaterialEditText
import indi.wkq.superseatandroid.R
import indi.wkq.superseatandroid.constant.MMK
import indi.wkq.superseatandroid.presenter.impl.UserPresenterImpl
import indi.wkq.superseatandroid.utils.LocalStorageUtils
import indi.wkq.superseatandroid.utils.ToastUtils

/**
 * @author  calesq
 * @date    2021/4/25
 */
@Page(anim = CoreAnim.none)
class LoginFragment : XPageFragment() {

    private var mCountDownHelper: CountDownButtonHelper? = null

    private val mLoadingDialog : LoadingDialog by lazy {
        WidgetUtils.getLoadingDialog(context!!)
            .setIconScale(0.4f)
            .setLoadingSpeed(8)
    }

    override fun initListeners() {
    }

    override fun initViews() {
        if (!LocalStorageUtils.getValueFromLocal(context!!, MMK.USER).isEmpty()) {
            findViewById<MaterialEditText>(R.id.et_school_id).apply {
                setText(LocalStorageUtils.getValueFromLocal(context!!, MMK.USER))
            }
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_login
    }

    @OnClick(
        R.id.btn_login,
        R.id.tv_forget_password,
        R.id.tv_user_protocol,
        R.id.tv_privacy_protocol
    )
    fun onViewClicked(view: View) {
        when (view.id) {
            R.id.btn_login -> {
                LocalStorageUtils.saveValueIntoLocal(context!!, MMK.USER, findViewById<MaterialEditText>(R.id.et_school_id).editValue)
                UserPresenterImpl.login(
                    findViewById<MaterialEditText>(R.id.et_school_id).editValue,
                    findViewById<MaterialEditText>(R.id.et_password).editValue,
                    this
                )
            }
            R.id.tv_forget_password -> ToastUtils.warning("开发中，敬请期待")
            R.id.tv_user_protocol -> ToastUtils.info("用户协议")
            R.id.tv_privacy_protocol -> ToastUtils.info("隐私政策")
        }
    }

    override fun onDestroyView() {
        if (mCountDownHelper != null) {
            mCountDownHelper!!.recycle()
        }
        super.onDestroyView()
    }

    fun showLoading() {
        mLoadingDialog.show()
    }

    fun hideLoading() {
        mLoadingDialog.hide()
    }

    override fun initTitleBar(): TitleBar {
        return TitleBar(context)
    }
}