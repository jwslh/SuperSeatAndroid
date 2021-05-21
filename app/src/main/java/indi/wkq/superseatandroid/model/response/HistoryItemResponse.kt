package indi.wkq.superseatandroid.model.response

/**
 * @author  calesq
 * @date    2021/5/19
 */
data class HistoryItemResponse(
    val id: Long, val date: String, val begin: String, val end: String, val awayBegin: String,
    val awayEnd: String, val loc: String, val stat: String
)