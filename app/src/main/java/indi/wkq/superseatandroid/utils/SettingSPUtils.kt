package indi.wkq.superseatandroid.utils

import android.content.Context
import com.xuexiang.xutil.XUtil
import com.xuexiang.xutil.data.BaseSPUtil

/**
 * @author  calesq
 * @date    2021/3/28
 */

class SettingSPUtils(context: Context) : BaseSPUtil(context) {

    // kotlin 线程安全的懒汉单例
    companion object {
        private var instance: SettingSPUtils? = null
            get() {
                if (field == null) {
                    field = SettingSPUtils(XUtil.getContext())
                }
                return field
            }

        @Synchronized
        fun get(): SettingSPUtils {
            return instance!!
        }
    }

    private val IS_FIRST_OPEN_KEY : String = "is_first_open_key"

    private val IS_AGREE_PRIVACY_KEY = "is_agree_privacy_key"

    private val IS_USE_CUSTOM_THEME_KEY = "is_use_custom_theme_key"

    private val IS_USE_CUSTOM_FONT_KEY = "is_use_custom_font_key"

    fun isFirstOpen() : Boolean {
        return getBoolean(IS_FIRST_OPEN_KEY, true)
    }

    /**
     * 设置是否是第一次启动
     */
    fun setIsFirstOpen(isFirstOpen : Boolean) {
        putBoolean(IS_FIRST_OPEN_KEY, isFirstOpen)
    }

    /**
     * @return 是否同意隐私政策
     */
    fun isAgreePrivacy(): Boolean {
        return getBoolean(IS_AGREE_PRIVACY_KEY, false)
    }

    fun setIsAgreePrivacy(isAgreePrivacy: Boolean) {
        putBoolean(IS_AGREE_PRIVACY_KEY, isAgreePrivacy)
    }

    fun isUseCustomTheme(): Boolean {
        return getBoolean(IS_USE_CUSTOM_THEME_KEY, false)
    }

    fun setIsUseCustomTheme(isUseCustomTheme: Boolean) {
        putBoolean(IS_USE_CUSTOM_THEME_KEY, isUseCustomTheme)
    }

    fun isUseCustomFont(): Boolean {
        return getBoolean(IS_USE_CUSTOM_FONT_KEY, false)
    }

    fun setIsUseCustomFont(isUseCustomFont: Boolean) {
        putBoolean(IS_USE_CUSTOM_FONT_KEY, isUseCustomFont)
    }
}