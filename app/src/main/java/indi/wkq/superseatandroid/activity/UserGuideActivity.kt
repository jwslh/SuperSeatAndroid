package indi.wkq.superseatandroid.activity

import android.app.Activity
import com.xuexiang.xui.widget.activity.BaseGuideActivity
import indi.wkq.superseatandroid.DataProvider

/**
 * @author  calesq
 * @date    2021/4/24
 */
class UserGuideActivity : BaseGuideActivity() {
    override fun getGuideResourceList(): MutableList<Any> {
        return DataProvider.getUserGuideData()!!
    }

    override fun getSkipClass(): Class<out Activity> {
        return LoginActivity::class.java
    }

}