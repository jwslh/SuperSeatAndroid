package indi.wkq.superseatandroid.activity

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import com.google.android.material.tabs.TabLayout
import com.xuexiang.xui.utils.ResUtils
import com.xuexiang.xui.utils.WidgetUtils
import com.xuexiang.xutil.app.FragmentUtils
import com.xuexiang.xutil.common.ClickUtils
import com.xuexiang.xutil.common.ClickUtils.OnClick2ExitListener
import com.xuexiang.xutil.tip.ToastUtils
import indi.wkq.superseatandroid.R
import indi.wkq.superseatandroid.base.BaseActivity
import indi.wkq.superseatandroid.fragment.BookFragment
import indi.wkq.superseatandroid.fragment.HistoryFragment
import indi.wkq.superseatandroid.fragment.LibraryFragment
import indi.wkq.superseatandroid.fragment.MeFragment

class MainActivity : BaseActivity(), OnClick2ExitListener {

    var mTabLayout: TabLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        mTabLayout = findViewById(R.id.tabs)
        initTab()
    }

    private fun initTab() {
        val tabTextList: Array<String> = ResUtils.getStringArray(R.array.tab_text)
        val tabIconList: Array<Drawable> = ResUtils.getDrawableArray(this, R.array.tab_icon)
        var size: Int = tabIconList.size
        for (index: Int in 0 until size) {
            var newTab: TabLayout.Tab = mTabLayout!!.newTab()
            newTab.setText(tabTextList[index])
            newTab.setIcon(tabIconList[index])
            mTabLayout!!.addTab(newTab)
        }

        WidgetUtils.setTabLayoutTextFont(mTabLayout)

        openPage(MeFragment::class.java)
        mTabLayout?.selectTab(mTabLayout?.getTabAt(2))

        mTabLayout?.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                Log.w("Main",FragmentUtils.getAllFragments(supportFragmentManager).toString())
                when (tab!!.position) {
                    0 -> {
                        val topFragment = FragmentUtils.getTop(supportFragmentManager)
                        if (topFragment.javaClass == LibraryFragment::class.java) {
                            FragmentUtils.show(topFragment)
                        } else {
                            switchPage(BookFragment::class.java)
                        }
                    }
                    1 -> {
                        switchPage(HistoryFragment::class.java)
                    }
                    2 -> {
                        switchPage(MeFragment::class.java)
                    }
                }
                tab.select()
            }

        })
    }

    /**
     * 菜单、返回键响应
     */
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            ClickUtils.exitBy2Click(2000, this)
        }
        return true
    }

    /**
     * 再点击一次
     */
    override fun onRetry() {
        ToastUtils.toast("再按一次退出程序")
    }

    /**
     * 退出
     */
    override fun onExit() {
        moveTaskToBack(true)
    }
}