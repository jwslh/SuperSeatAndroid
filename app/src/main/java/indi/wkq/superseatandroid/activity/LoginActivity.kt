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

    /**
     * 打开fragment
     *
     * @param clazz          页面类
     * @param addToBackStack 是否添加到栈中
     * @return 打开的fragment对象
     */
    private fun <T : XPageFragment?> openPage(clazz: Class<T>?, addToBackStack: Boolean): T {
        val page = CoreSwitchBean(clazz)
                .setAddToBackStack(addToBackStack)
        return openPage(page) as T
    }
}