package indi.wkq.superseatandroid.fragment

import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.kunminx.linkage.bean.DefaultGroupedItem
import com.xuexiang.xpage.annotation.Page
import com.xuexiang.xpage.base.XPageFragment
import com.xuexiang.xpage.utils.TitleBar
import com.xuexiang.xui.utils.ResUtils
import indi.wkq.superseatandroid.R
import indi.wkq.superseatandroid.adapter.LibraryRecyclerViewAdapter
import indi.wkq.superseatandroid.constant.LibraryConst

/**
 * @author  calesq
 * @date    2021/5/30
 */
@Page(name = "图书馆房间信息")
class LibraryFragment : XPageFragment() {
    override fun initListeners() {
    }

    private val mPagerWrap: FrameLayout by lazy {
        findViewById<FrameLayout>(R.id.roomWrap)
    }

    override fun initViews() {
        var mPagerLayoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        mRecycleView?.layoutManager = mPagerLayoutManager
        mRecycleView?.adapter = LibraryRecyclerViewAdapter(
            this, ResUtils.getStringArray(R.array.pages).asList(), initData(
                LibraryConst.LIBRARIES
            )
        )
        mPagerWrap.addView(mRecycleView)
        PagerSnapHelper().apply {
            attachToRecyclerView((mRecycleView))
        }
    }

    private fun initData(headers: Map<String, MutableMap<String, String>>): List<DefaultGroupedItem> {
        val items: MutableList<DefaultGroupedItem> = ArrayList()
        val k = headers.keys.toList()
        for (i in k) {
            items.add(DefaultGroupedItem(true, i))
            val itemsLibrary = headers[i]!!
            val roomsName = itemsLibrary.keys.toList()
            val roomsCode = itemsLibrary.values.toList()

            for (j in roomsName) {
                items.add(DefaultGroupedItem(DefaultGroupedItem.ItemInfo(j, i)))
            }
        }

        return items
    }

    private val mRecycleView by lazy {
        context?.let { RecyclerView(it) }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_library
    }

    override fun initTitleBar(): TitleBar {
        var titleBar = super.initTitleBar()
        titleBar.setTitle(arguments?.getString("library"))
        titleBar.setLeftImageDrawable(null)
        return titleBar
    }
}