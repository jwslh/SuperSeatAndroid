package indi.wkq.superseatandroid.response

/**
 * @author  calesq
 * @date    2021/5/12
 */
data class GetUserInfoResponse(
    var name : String,
    var username : String,
    var status : String,
    var lastIn : String,
    var lastOut : String,
    var reservationStatus : String
)
