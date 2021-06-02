package indi.wkq.superseatandroid.fragment

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import butterknife.OnClick
import com.xuexiang.xpage.annotation.Page
import com.xuexiang.xpage.base.XPageFragment
import com.xuexiang.xpage.utils.TitleBar
import com.xuexiang.xui.widget.edittext.materialedittext.MaterialEditText
import com.xuexiang.xui.widget.picker.widget.OptionsPickerView
import com.xuexiang.xui.widget.picker.widget.builder.OptionsPickerBuilder
import com.xuexiang.xui.widget.picker.widget.listener.OnOptionsSelectListener
import indi.wkq.superseatandroid.R
import indi.wkq.superseatandroid.constant.MMK
import indi.wkq.superseatandroid.presenter.impl.UserPresenterImpl
import indi.wkq.superseatandroid.utils.LocalStorageUtils
import indi.wkq.superseatandroid.utils.TimeUtils
import indi.wkq.superseatandroid.utils.ToastUtils
import java.util.*

/**
 * @author  calesq
 * @date    2021/6/2
 */
@Page(name="抢座页面")
class BookActionFragment : XPageFragment() {

    private var mTimeOption: List<String?>? = null

    private val timePickerTV : TextView by lazy {
        findViewById<TextView>(R.id.tv_time_picker)
    }

    override fun getLayoutId(): Int {
        return R.layout.fragemnt_book_action
    }

    override fun initViews() {
        showTimePeriodPicker()
    }

    override fun initListeners() {

    }

    override fun initTitleBar(): TitleBar {
        var titleBar = super.initTitleBar()
        titleBar.setTitle(pageTitle)
        titleBar.setLeftImageDrawable(null)
        return titleBar
    }

    @OnClick(
        R.id.tv_time_picker,
        R.id.book_btn
    )
    fun onViewClicked(view: View) {
        when (view.id) {
            R.id.tv_time_picker -> {
                showTimePeriodPicker()
            }
            R.id.book_btn -> ToastUtils.warning("抢座开发中，敬请期待")
        }
    }

    private fun showTimePeriodPicker() {
        if (mTimeOption == null) {
            mTimeOption = TimeUtils.getTimePeriod(22, 15)?.toList()
        }

        //8点
        val defaultOption = if (TimeUtils.getNowTimeText() > "22:40:00") {
            8 * 60 / 15
        } else {
            Calendar.getInstance().get(Calendar.HOUR_OF_DAY) * 60 / 15
        }

        var onOptionsSelectListener = OnOptionsSelectListener{ v: View?, options1: Int, options2: Int, options3: Int ->
            if (options1 > options2) {
                ToastUtils.error("结束时间不能早于开始时间")
                //返回为true意为拦截，选择框不消失。
                return@OnOptionsSelectListener true
            } else {
                var startTimeText = mTimeOption!![options1].toString()
                var endTimeText = mTimeOption!![options2]
                timePickerTV.text ="$startTimeText ~ $endTimeText"
                return@OnOptionsSelectListener false
            }
        }

        val pvOptions: OptionsPickerView<Any> = OptionsPickerBuilder(
            context,
            onOptionsSelectListener)
            .setTitleText("时间段选择")
            .setSelectOptions(defaultOption, defaultOption)
            .build<Any>()
        pvOptions.setNPicker(mTimeOption, mTimeOption)
        pvOptions.show()
    }
}