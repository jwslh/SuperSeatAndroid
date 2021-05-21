package indi.wkq.superseatandroid.utils

/**
 * @author  calesq
 * @date    2021/5/21
 */
object LibraryUtils {
    fun setStatusText(oldStatus : String) : String {
        return when(oldStatus) {
            "CANCEL" -> "取消"
            "STOP" -> "结束"
            "MISS" -> "失约"
            "AWAY" -> "暂停"
            "COMPLETE" -> "已完成"
            "RESERVE" -> "已预约"
            else -> "已签到"
        }
    }
}