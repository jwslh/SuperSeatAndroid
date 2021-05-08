package indi.wkq.superseatandroid.activity

import android.view.KeyEvent
import com.xuexiang.xui.utils.KeyboardUtils
import com.xuexiang.xui.widget.activity.BaseSplashActivity
import com.xuexiang.xutil.app.ActivityUtils
import indi.wkq.superseatandroid.R
import indi.wkq.superseatandroid.constant.MMK
import indi.wkq.superseatandroid.utils.LocalStorageUtils
import indi.wkq.superseatandroid.utils.MMKVUtils
import indi.wkq.superseatandroid.utils.SettingSPUtils

/**
 * @author  calesq
 * @date    2021/3/28
 */

class SplashActivity : BaseSplashActivity() {
    val KEY_IS_DISPLAY = "ss_key_is_display"
    val KEY_ENABLE_ALPHA_ANIM = "ss_key_enable_alpha_anim"

    private var isDisplay = false

    override fun getSplashDurationMillis(): Long {
        return 1000
    }

    override fun onCreateActivity() {
        isDisplay = intent.getBooleanExtra(KEY_IS_DISPLAY, isDisplay)
        val enableAlphaAnim = intent.getBooleanExtra(KEY_ENABLE_ALPHA_ANIM, true)
        val spUtil: SettingSPUtils = SettingSPUtils.get()
        if (spUtil.isFirstOpen()) {
            spUtil.setIsFirstOpen(false)
            ActivityUtils.startActivity(UserGuideActivity::class.java)
            finish()
        } else {
            if (enableAlphaAnim) {
                initSplashView(R.drawable.bg_splash_ss)
            } else {
                initSplashView(R.drawable.ss_config_bg_splash)
            }
        }
        startSplash(enableAlphaAnim)
    }

    override fun onSplashFinished() {
        if (!isDisplay) {
            var token = LocalStorageUtils.getValueFromLocal(this, MMK.TOKEN)
            var tokenExpiration = LocalStorageUtils.getValueFromLocal(this, MMK.TOKEN_EXPIRATION)
            if (token.isEmpty() || tokenExpiration < System.currentTimeMillis().toString()) {
                ActivityUtils.startActivity(LoginActivity::class.java)
            } else {
                MMKVUtils.setMMV(MMK.TOKEN, token)
                MMKVUtils.setMMV(MMK.TOKEN_EXPIRATION, tokenExpiration)
                ActivityUtils.startActivity(MainActivity::class.java)
            }
        }
        finish()
    }

    /**
     * 菜单、返回键响应
     */
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return KeyboardUtils.onDisableBackKeyDown(keyCode) && super.onKeyDown(keyCode, event)
    }
}