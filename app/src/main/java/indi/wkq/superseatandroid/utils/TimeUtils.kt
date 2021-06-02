package indi.wkq.superseatandroid.utils

import java.text.SimpleDateFormat
import java.util.*

/**
 * @author  calesq
 * @date    2021/5/19
 */
object TimeUtils {

    private const val TIME_TEXT_PATTERN = "yyyy-MM-dd HH:mm:ss"
    private const val DATE_PATTERN = "yyyy-MM-dd"
    private const val TIME_PATTERN = "HH:mm:ss"

    fun getTimeStampFromTimeText(timeText: String): Long =
        SimpleDateFormat(TIME_TEXT_PATTERN).parse(timeText).time

    fun getTomorrowDate(): String {
        val calendar = Calendar.getInstance()
        calendar.apply {
            time = Date()
            add(Calendar.DATE, 1)
        }

        return SimpleDateFormat(DATE_PATTERN).format(calendar.time)
    }

    fun getTodayDate(): String = SimpleDateFormat(DATE_PATTERN).format(Date().time)

    fun getNowTimeText(): String = SimpleDateFormat(TIME_PATTERN).format(Date().time)

    /**
     * 时分格式 转为图书馆需要的 整数格式
     */
    fun getTimeFromTimeText(timeText: String): String {
        var arrs = timeText.split(":")
        if (arrs.size == 2) {
            return (arrs[0].toInt() * 60 + arrs[1].toInt()).toString()
        }
        return ""
    }

    /**
     *  图书馆整数时间格式 转为 日常时分格式
     */
    fun getTimeTextFromTime(time: String): String {
        if (time != "" && time != "0") {
            var h = time.toInt() / 60
            var m = time.toInt() % 60
            return (if (h >= 10) h.toString() else "0$h") + ":" + if (m >= 10) m.toString() else "0$m"
        }
        return ""
    }

    /**
     * 获取时间段
     *
     * @param interval 时间间隔（分钟）
     * @return
     */
    fun getTimePeriod(interval: Int): Array<String?>? {
        return getTimePeriod(24, interval)
    }

    /**
     * 获取时间段
     *
     * @param interval 时间间隔（分钟）
     * @return
     */
    fun getTimePeriod(totalHour: Int, interval: Int): Array<String?>? {
        var time = arrayOfNulls<String>(totalHour * 60 / interval)
        var point: Int
        var hour: Int
        var min: Int
        for (i in time.indices) {
            point = i * interval
            hour = point / 60
            min = point - hour * 60
            time[i] =
                (if (hour < 9) "0$hour" else "" + hour) + ":" + if (min < 9) "0$min" else "" + min
        }
        return time
    }


    /**
     * 获取时间段
     *
     * @param interval 时间间隔（分钟）
     * @return
     */
    fun getTimePeriod(startHour: Int, totalHour: Int, interval: Int): Array<String?>? {
        val time = arrayOfNulls<String>(totalHour * 60 / interval)
        var point: Int
        var hour: Int
        var min: Int
        for (i in time.indices) {
            point = i * interval + startHour * 60
            hour = point / 60
            min = point - hour * 60
            time[i] =
                (if (hour < 9) "0$hour" else "" + hour) + ":" + if (min < 9) "0$min" else "" + min
        }
        return time
    }

}