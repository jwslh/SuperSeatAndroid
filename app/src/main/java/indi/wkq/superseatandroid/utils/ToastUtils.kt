package indi.wkq.superseatandroid.utils

import androidx.annotation.MainThread
import androidx.annotation.NonNull
import androidx.annotation.StringRes
import com.xuexiang.xui.XUI
import com.xuexiang.xui.widget.toast.XToast

/**
 * @author  calesq
 * @date    2021/4/24
 */
object ToastUtils {
    var s = XToast.Config.get()
            .setAlpha(200)
            .setToastTypeface(XUI.getDefaultTypeface())
            .allowQueue(false)

    @MainThread
    fun toast(@NonNull message : CharSequence) {
        XToast.normal(XUI.getContext(), message).show()
    }

    @MainThread
    fun toast(@StringRes message: Int) {
        XToast.normal(XUI.getContext(), message).show()
    }

    @MainThread
    fun toast(message: CharSequence, duration: Int) {
        XToast.normal(XUI.getContext(), message, duration).show()
    }

    @MainThread
    fun toast(@StringRes message: Int, duration: Int) {
        XToast.normal(XUI.getContext(), message, duration).show()
    }

    //=============//
    @MainThread
    fun error(message: CharSequence) {
        XToast.error(XUI.getContext(), message).show()
    }

    @MainThread
    fun error(error: Exception) {
        XToast.error(XUI.getContext(), error.message!!).show()
    }

    @MainThread
    fun error(@StringRes message: Int) {
        XToast.error(XUI.getContext(), message).show()
    }

    @MainThread
    fun error(message: CharSequence, duration: Int) {
        XToast.error(XUI.getContext(), message, duration).show()
    }

    @MainThread
    fun error(@StringRes message: Int, duration: Int) {
        XToast.error(XUI.getContext(), message, duration).show()
    }

    //=============//
    @MainThread
    fun success(message: CharSequence) {
        XToast.success(XUI.getContext(), message).show()
    }

    @MainThread
    fun success(@StringRes message: Int) {
        XToast.success(XUI.getContext(), message).show()
    }

    @MainThread
    fun success(message: CharSequence, duration: Int) {
        XToast.success(XUI.getContext(), message, duration).show()
    }

    @MainThread
    fun success(@StringRes message: Int, duration: Int) {
        XToast.success(XUI.getContext(), message, duration).show()
    }

    //=============//
    @MainThread
    fun info(message: CharSequence) {
        XToast.info(XUI.getContext(), message).show()
    }

    @MainThread
    fun info(@StringRes message: Int) {
        XToast.info(XUI.getContext(), message).show()
    }

    @MainThread
    fun info(message: CharSequence, duration: Int) {
        XToast.info(XUI.getContext(), message, duration).show()
    }

    @MainThread
    fun info(@StringRes message: Int, duration: Int) {
        XToast.info(XUI.getContext(), message, duration).show()
    }


    //=============//
    @MainThread
    fun warning(message: CharSequence) {
        XToast.warning(XUI.getContext(), message).show()
    }

    @MainThread
    fun warning(@StringRes message: Int) {
        XToast.warning(XUI.getContext(), message).show()
    }

    @MainThread
    fun warning(message: CharSequence, duration: Int) {
        XToast.warning(XUI.getContext(), message, duration).show()
    }

    @MainThread
    fun warning(@StringRes message: Int, duration: Int) {
        XToast.warning(XUI.getContext(), message, duration).show()
    }
}