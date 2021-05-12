package indi.wkq.superseatandroid.fragment

import android.graphics.drawable.Icon
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatImageView
import com.xuexiang.xpage.annotation.Page
import com.xuexiang.xpage.base.XPageFragment
import com.xuexiang.xpage.enums.CoreAnim
import com.xuexiang.xpage.utils.TitleBar
import indi.wkq.superseatandroid.R

/**
 * @author  calesq
 * @date    2021/4/25
 */
@Page(anim = CoreAnim.none)
class MeFragment : XPageFragment() {
    override fun initListeners() {
    }


    override fun getLayoutId(): Int {
        return R.layout.fragment_me
    }

    override fun initViews() {
        var name = mRootView.findViewById<View>(R.id.header).findViewById<TextView>(R.id.tv_avatar)

        mRootView.findViewById<View>(R.id.school_id_item).apply {
            findViewById<AppCompatImageView>(R.id.icon).setImageResource(R.drawable.icon_me_item_school_id)
            findViewById<TextView>(R.id.title).setText(R.string.tv_title_school_id)
            findViewById<TextView>(R.id.item_val).text = "2017302580014"
        }


        var status = mRootView.findViewById<View>(R.id.status_item).apply {
            findViewById<AppCompatImageView>(R.id.icon).setImageResource(R.drawable.icon_me_item_status)
            findViewById<TextView>(R.id.title).setText(R.string.tv_title_status)
            findViewById<TextView>(R.id.item_val).text = "正常"
        }

        var reserveStatus = mRootView.findViewById<View>(R.id.reserve_status_item).apply {
            findViewById<AppCompatImageView>(R.id.icon).setImageResource(R.drawable.icon_me_item_reserve_status)
            findViewById<TextView>(R.id.title).setText(R.string.tv_title_reserve_status)
            findViewById<TextView>(R.id.item_val).text = "无"
        }

        var lastIn = mRootView.findViewById<View>(R.id.last_in_item).apply {
            findViewById<AppCompatImageView>(R.id.icon).setImageResource(R.drawable.icon_me_item_last_in)
            findViewById<TextView>(R.id.title).setText(R.string.tv_title_last_in)
            findViewById<TextView>(R.id.item_val).text = "2017302580014"
        }

        var lastOut = mRootView.findViewById<View>(R.id.last_out_item).apply {
            findViewById<AppCompatImageView>(R.id.icon).setImageResource(R.drawable.icon_me_item_last_out)
            findViewById<TextView>(R.id.title).setText(R.string.tv_title_last_out)
            findViewById<TextView>(R.id.item_val).text = "2017302580014"
        }

    }

    override fun initTitleBar(): TitleBar {
        return TitleBar(context)
    }
}