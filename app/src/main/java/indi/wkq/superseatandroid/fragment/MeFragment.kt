package indi.wkq.superseatandroid.fragment

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
class MeFragment : XPageFragment(){
    override fun initListeners() {
    }

    override fun initViews() {
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_me
    }

    override fun initTitleBar(): TitleBar {
        return TitleBar(context)
    }
}