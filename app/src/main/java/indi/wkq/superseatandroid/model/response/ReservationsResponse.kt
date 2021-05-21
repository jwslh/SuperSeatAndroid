package indi.wkq.superseatandroid.model.response

/**
 * @author  calesq
 * @date    2021/5/19
 * 预约请求返回体
 */
data class ReservationsResponse(
    val id: Long,
    val onDate: String,
    val seatId: Long,
    val status: String,
    val location : String,
    val begin : String,
    val end : String,
    val actualBegin : String,
    val awayBegin : String,
    val awayEnd : String,
    val userEnded : Boolean,
    val message : String
)
