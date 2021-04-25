package indi.wkq.superseatandroid.activity

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import butterknife.BindView
import com.google.android.material.tabs.TabLayout
import com.jpeng.jptabbar.OnTabSelectListener
import com.xuexiang.xui.utils.ResUtils
import com.xuexiang.xui.utils.WidgetUtils
import indi.wkq.superseatandroid.R
import indi.wkq.superseatandroid.base.BaseActivity
import indi.wkq.superseatandroid.fragment.BookFragment
import indi.wkq.superseatandroid.fragment.HistoryFragment
import indi.wkq.superseatandroid.fragment.MeFragment

class MainActivity : BaseActivity() {

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
        val tabTextList : Array<String> = ResUtils.getStringArray(R.array.tab_text)
        val tabIconList : Array<Drawable> = ResUtils.getDrawableArray(this, R.array.tab_icon)
        var size : Int = tabIconList.size
        for (index : Int in 0 until size) {
            var newTab : TabLayout.Tab = mTabLayout!!.newTab()
            newTab.setText(tabTextList[index])
            newTab.setIcon(tabIconList[index])
            mTabLayout!!.addTab(newTab)
        }

        WidgetUtils.setTabLayoutTextFont(mTabLayout)

        openPage(MeFragment::class.java)
        mTabLayout?.selectTab(mTabLayout?.getTabAt(2))

        mTabLayout?.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab!!.position) {
                    0 -> switchPage(BookFragment::class.java)
                    1 -> switchPage(HistoryFragment::class.java)
                    2 -> switchPage(MeFragment::class.java)
                }
                tab.select()
            }

        })
    }
}