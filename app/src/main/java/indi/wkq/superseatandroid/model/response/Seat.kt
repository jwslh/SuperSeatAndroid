package indi.wkq.superseatandroid.model.response

/**
 * @author  calesq
 * @date    2021/6/2
 */
data class Seat(
    var id: Long,
    var name: String,
    var type: String,
    var status: String,
    var window: Boolean,
    var power: Boolean,
    var computer: Boolean,
    var local: Boolean
)
