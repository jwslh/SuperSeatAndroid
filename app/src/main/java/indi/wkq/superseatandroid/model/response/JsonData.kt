package indi.wkq.superseatandroid.model.response

import java.util.*

/**
 * @author  calesq
 * @date    2021/4/29
 */
class JsonData {
    lateinit var status : String
    var data : Object? = null
    var code : Int = 0
    lateinit var message : String
}