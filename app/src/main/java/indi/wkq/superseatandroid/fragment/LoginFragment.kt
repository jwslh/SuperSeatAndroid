package indi.wkq.superseatandroid.fragment

import android.view.View
import butterknife.BindView
import butterknife.OnClick
import com.xuexiang.xpage.annotation.Page
import com.xuexiang.xpage.base.XPageFragment
import com.xuexiang.xpage.enums.CoreAnim
import com.xuexiang.xui.utils.CountDownButtonHelper
import com.xuexiang.xui.widget.button.roundbutton.RoundButton
import com.xuexiang.xui.widget.edittext.materialedittext.MaterialEditText
import indi.wkq.superseatandroid.R
import indi.wkq.superseatandroid.utils.ToastUtils

/**
 * @author  calesq
 * @date    2021/4/25
 */
@Page(anim = CoreAnim.none)
class LoginFragment : XPageFragment() {

    private var mCountDownHelper : CountDownButtonHelper? = null

    override fun initListeners() {
    }

    override fun initViews() {
        mCountDownHelper
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_login
    }

    //@OnClick({R.id.btn_get_verify_code, R.id.btn_login, R.id.tv_forget_password, R.id.tv_user_protocol, R.id.tv_privacy_protocol})
    fun onViewClicked(view : View) {
        when(view.id) {
            R.id.btn_get_verify_code -> ToastUtils.info("暂时无法使用")
            R.id.btn_login -> ToastUtils.info("登录成功")
            R.id.tv_forget_password -> ToastUtils.info("忘记密码")
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
}