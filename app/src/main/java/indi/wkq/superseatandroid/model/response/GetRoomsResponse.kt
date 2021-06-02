package indi.wkq.superseatandroid.model.response

/**
 * @author  calesq
 * @date    2021/5/31
 */
data class GetRoomsResponse(
    var id: Long,
    var name: String,
    var col: Int,
    var row: Int,
    var layout: Map<String, Seat>
)