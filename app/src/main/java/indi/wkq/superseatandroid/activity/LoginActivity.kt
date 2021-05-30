package indi.wkq.superseatandroid.activity

import android.os.Bundle
import android.view.KeyEvent
import com.xuexiang.xpage.base.XPageActivity
import com.xuexiang.xpage.base.XPageFragment
import com.xuexiang.xpage.core.CoreSwitchBean
import com.xuexiang.xui.utils.KeyboardUtils
import com.xuexiang.xui.utils.StatusBarUtils
import com.xuexiang.xutil.display.Colors
import indi.wkq.superseatandroid.fragment.LoginFragment

/**
 * @author  calesq
 * @date    2021/4/24
 */
class LoginActivity : XPageActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        openPage(LoginFragment::class.java, intent.extras)
    }

    protected fun initStatusBarStyle() {
        StatusBarUtils.initStatusBarStyle(this, false, Colors.TRANSPARENT)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return KeyboardUtils.onDisableBackKeyDown(keyCode) && super.onKeyDown(keyCode, event)
    }

}