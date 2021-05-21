package indi.wkq.superseatandroid.model.response

/**
 * @author  calesq
 * @date    2021/4/29
 */
data class JsonData (
    var status : String,
    var data : Any?,
    var code : Int,
    var message : String
)