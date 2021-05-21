package indi.wkq.superseatandroid.utils

import java.text.SimpleDateFormat
import java.util.*

/**
 * @author  calesq
 * @date    2021/5/19
 */
object TimeUtils {

    private const val TIME_TEXT_PATTERN = "yyyy/MM/dd HH:mm:ss"
    private const val DATE_PATTERN = "yyyy/MM/dd"
    private const val TIME_PATTERN = "HH:mm:ss"

    fun getTimeStampFromTimeText(timeText: String): Long =
        SimpleDateFormat(TIME_TEXT_PATTERN).parse(timeText).time

    fun getTomorrowDate() : String {
        val calendar = Calendar.getInstance()
        calendar.apply {
            time = Date()
            add(Calendar.DATE, 1)
        }

        return SimpleDateFormat(DATE_PATTERN).format(calendar.time)
    }

    fun getTodayDate() : String = SimpleDateFormat(DATE_PATTERN).format(Date().time)

    fun getNowTimeText() : String = SimpleDateFormat(TIME_PATTERN).format(Date().time)
}