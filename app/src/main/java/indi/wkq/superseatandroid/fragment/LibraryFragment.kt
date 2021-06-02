package indi.wkq.superseatandroid.fragment

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xuexiang.xpage.annotation.Page
import com.xuexiang.xpage.base.XPageFragment
import com.xuexiang.xpage.utils.TitleBar
import com.xuexiang.xui.utils.WidgetUtils
import com.xuexiang.xui.widget.dialog.LoadingDialog
import com.xuexiang.xui.widget.picker.widget.OptionsPickerView
import com.xuexiang.xui.widget.picker.widget.builder.OptionsPickerBuilder
import com.xuexiang.xui.widget.picker.widget.listener.OnOptionsSelectListener
import com.xuexiang.xutil.app.ActivityUtils
import indi.wkq.superseatandroid.R
import indi.wkq.superseatandroid.activity.LoginActivity
import indi.wkq.superseatandroid.adapter.RoomsAdapter
import indi.wkq.superseatandroid.constant.LibraryConst
import indi.wkq.superseatandroid.constant.MMK
import indi.wkq.superseatandroid.model.response.GetRoomsResponse
import indi.wkq.superseatandroid.model.response.Seat
import indi.wkq.superseatandroid.presenter.impl.LibraryPresenterImpl
import indi.wkq.superseatandroid.utils.LocalStorageUtils
import indi.wkq.superseatandroid.utils.TimeUtils
import indi.wkq.superseatandroid.utils.ToastUtils
import java.util.*

/**
 * @author  calesq
 * @date    2021/5/30
 */
@Page(name = "图书馆房间信息")
class LibraryFragment : XPageFragment() {

    private var roomName: String = ""
    private var library: String = ""
    private var mTimeOption: List<String?>? = null

    override fun getLayoutId(): Int {
        return R.layout.fragment_library
    }

    override fun initViews() {
        initData()
    }

    override fun initListeners() {
    }

    override fun initTitleBar(): TitleBar {
        var titleBar = super.initTitleBar()
        roomName = arguments?.getString("room").toString()
        library = arguments?.getString("library").toString()
        titleBar.setTitle(library + " " + roomName)
        titleBar.setLeftImageDrawable(null)
        return titleBar
    }

    private fun initData() {
        var token = LocalStorageUtils.getValueFromLocal(context!!, MMK.TOKEN)
        if (token == "") {
            ToastUtils.warning("身份过期，请重新登录", 1500)
            ActivityUtils.startActivity(LoginActivity::class.java)
            return
        }

        var date = ""
        date = if (TimeUtils.getNowTimeText() > "22:30:00") {
            TimeUtils.getTomorrowDate()
        } else {
            TimeUtils.getTodayDate()
        }

        LibraryPresenterImpl.getSeatsInRoom(
            token,
            LibraryConst.getRoomIdFromName(roomName),
            date,
            this
        )
    }

    fun updatePage(getRoomsResponse: GetRoomsResponse) {

        var seatsCode: List<String> = getRoomsResponse.layout.keys.toList()
        var seatsInfo: List<Seat> = getRoomsResponse.layout.values.toList()

        for (seat in seatsInfo) {
            if (seat.id == 0L) {
                seatsInfo -= seat
            }
        }

        seatsInfo = seatsInfo.sortedBy { seat -> seat.name}

        findViewById<RecyclerView>(R.id.rooms).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = RoomsAdapter(seatsCode, seatsInfo, View.OnClickListener {
                ToastUtils.warning("点击成功")
                showTimePeriodPicker()
            })
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
                ToastUtils.toast(
                    mTimeOption!![options1].toString() + "~" + mTimeOption!![options2]
                )
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

    private val mLoadingDialog: LoadingDialog by lazy {
        WidgetUtils.getLoadingDialog(context!!)
            .setIconScale(0.4f)
            .setLoadingSpeed(8)
    }

    fun showLoading() {
        mLoadingDialog.show()
    }

    fun hideLoading() {
        mLoadingDialog.hide()
        mLoadingDialog.dismiss()
    }
}