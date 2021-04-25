package indi.wkq.superseatandroid.utils

import com.tencent.mmkv.MMKV;

/**
 * @author  calesq
 * @date    2021/4/24
 *
 */
object MMKVUtils {

    /**
     * 初始化键值对
     */
    fun initKV(key : String) {
        MMKV.defaultMMKV().decodeString(key,"")
    }

    fun setMMV(key : String, value : String) {
        MMKV.defaultMMKV().decodeString(key, value)
    }

    fun getMMV(key: String) : String {
        if (!MMKV.defaultMMKV().containsKey(key)) {
            return ""
        }
        return MMKV.defaultMMKV().getString(key, "")!!
    }
}
