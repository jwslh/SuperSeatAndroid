package indi.wkq.superseatandroid.adapter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.kunminx.linkage.LinkageRecyclerView
import com.kunminx.linkage.adapter.viewholder.LinkagePrimaryViewHolder
import com.kunminx.linkage.adapter.viewholder.LinkageSecondaryFooterViewHolder
import com.kunminx.linkage.adapter.viewholder.LinkageSecondaryHeaderViewHolder
import com.kunminx.linkage.adapter.viewholder.LinkageSecondaryViewHolder
import com.kunminx.linkage.bean.BaseGroupedItem
import com.kunminx.linkage.bean.DefaultGroupedItem
import com.xuexiang.xpage.base.XPageFragment
import com.xuexiang.xpage.enums.CoreAnim
import com.xuexiang.xui.utils.SnackbarUtils
import com.xuexiang.xutil.app.FragmentUtils
import indi.wkq.superseatandroid.R
import indi.wkq.superseatandroid.fragment.LibraryFragment

/**
 * @author  calesq
 * @date    2021/5/28
 */
class LibraryRecyclerViewAdapter(private val mFragment: XPageFragment, private val libraries : List<String>, private val mData : List<DefaultGroupedItem>) : RecyclerView.Adapter<LibraryRecyclerViewAdapter.LibraryViewHolder>() {
    inner class LibraryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val fragment: LinkageRecyclerView<DefaultGroupedItem.ItemInfo> = view.findViewById(R.id.linkage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibraryViewHolder {
        var root = LayoutInflater.from(parent.context).inflate(R.layout.fragment_book_page, parent, false)
        return LibraryViewHolder(root)
    }

    override fun onBindViewHolder(holder: LibraryViewHolder, position: Int) {
        holder.fragment.apply {
            // 设置子 RecycleView
            init(mData)
            isScrollSmoothly = true
            setDefaultOnItemBindListener(
                { _: LinkagePrimaryViewHolder?, primaryClickView: View?, title: String? ->
                    //一级列表点击
                    SnackbarUtils.Short(primaryClickView, title).show()
                },
                { _: LinkagePrimaryViewHolder?, _: String? -> },
                { secondaryHolder: LinkageSecondaryViewHolder, item: BaseGroupedItem<DefaultGroupedItem.ItemInfo> ->
                    //二级列表点击
                    secondaryHolder.getView<View>(R.id.level_2_item)
                        .setOnClickListener {
                            var bundle = Bundle()
                            bundle.putString("library", item.info.group + item.info.title)
                            mFragment.openPage("图书馆房间信息", bundle, CoreAnim.slide, true)
                        }
                },
                { _: LinkageSecondaryHeaderViewHolder?, item: BaseGroupedItem<DefaultGroupedItem.ItemInfo?>? -> },
                { _: LinkageSecondaryFooterViewHolder?, item: BaseGroupedItem<DefaultGroupedItem.ItemInfo?>? -> }
            )
        }
    }

    override fun getItemCount(): Int {
        return libraries.size
    }

    override fun onViewAttachedToWindow(holder: LibraryViewHolder) {
        var position : Int = holder.layoutPosition
        Log.w("POSITION", position.toString())
    }
}