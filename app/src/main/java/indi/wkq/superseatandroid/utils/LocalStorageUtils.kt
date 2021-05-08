package indi.wkq.superseatandroid.utils

import android.content.Context
import android.content.SharedPreferences
import indi.wkq.superseatandroid.constant.MMK

/**
 * @author  calesq
 * @date    2021/5/8
 */
object LocalStorageUtils {

    fun getValueFromLocal(context : Context, key : String) : String {
        var sp : SharedPreferences = context.getSharedPreferences(MMK.LOCAL_STORAGE, 0)
        return sp.getString(key, "")!!
    }

    fun saveValueIntoLocal(context : Context, key : String, value : String) {
        var edit = context.getSharedPreferences(MMK.LOCAL_STORAGE, 0).edit()
        edit.putString(key, value)
        edit.commit()
    }
}
