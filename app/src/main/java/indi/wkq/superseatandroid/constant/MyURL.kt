package indi.wkq.superseatandroid.constant

/**
 * @author  calesq
 * @date    2021/5/12
 */
object MyURL {
    const val BASE_URL = "https://seat.lib.whu.edu.cn:8443/"

    const val PATH_LOGIN = "/rest/auth"

    const val PATH_USR_INFO = "/rest/v2/user"

    const val PATH_RESERVATIONS = "/rest/v2/user/reservations"

    const val PATH_HISTORY = "/rest/v2/history/{page}/{pageSize}"

    const val PATH_BOOK = "/rest/v2/freeBook"

    const val PATH_CANCEL = "/rest/v2/cancel/{reserveId}"

    const val PATH_STOP = "/rest/v2/stop"

    const val PATH_SEARCH = "/rest/v2/searchSeats/{date}/{start}/{endTime}"
}