package indi.wkq.superseatandroid

import java.util.*

/**
 * @author  calesq
 * @date    2021/4/25
 */
object DataProvider {
    fun getUserGuideData() : MutableList<Any> {
        var list: MutableList<Any> = ArrayList()
        list.add(R.drawable.ss_bg_splash_1)
        list.add(R.drawable.ss_bg_splash_2)
        list.add(R.drawable.ss_bg_splash_3)
        list.add(R.drawable.ss_bg_splash_4)
        return list
    }
}