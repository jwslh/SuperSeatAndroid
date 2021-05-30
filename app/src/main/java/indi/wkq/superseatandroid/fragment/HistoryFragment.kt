package indi.wkq.superseatandroid.fragment

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xuexiang.xpage.annotation.Page
import com.xuexiang.xpage.base.XPageFragment
import com.xuexiang.xpage.enums.CoreAnim
import com.xuexiang.xpage.utils.TitleBar
import com.xuexiang.xpage.utils.TitleUtils
import com.xuexiang.xui.utils.DensityUtils
import com.xuexiang.xui.utils.WidgetUtils
import com.xuexiang.xui.widget.dialog.LoadingDialog
import com.xuexiang.xutil.app.ActivityUtils
import indi.wkq.superseatandroid.R
import indi.wkq.superseatandroid.activity.LoginActivity
import indi.wkq.superseatandroid.adapter.HistoryAdapter
import indi.wkq.superseatandroid.adapter.ReservationsAdapter
import indi.wkq.superseatandroid.constant.MMK
import indi.wkq.superseatandroid.model.response.HistoryResponse
import indi.wkq.superseatandroid.model.response.ReservationsResponse
import indi.wkq.superseatandroid.presenter.impl.LibraryPresenterImpl
import indi.wkq.superseatandroid.utils.LocalStorageUtils
import indi.wkq.superseatandroid.utils.TimeUtils
import indi.wkq.superseatandroid.utils.ToastUtils

/**
 * @author  calesq
 * @date    2021/4/25
 */
@Page(name = "历史记录", anim = CoreAnim.fade)
class HistoryFragment : XPageFragment() {
    override fun initListeners() {
    }

    override fun initViews() {
        initData()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_history
    }

    override fun initTitleBar(): TitleBar {
        var titleBar = super.initTitleBar()
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

        // 判断当前时间，大于每天晚上的22：30，请求历史记录接口，反之请求预约记录
        if (TimeUtils.getTimeStampFromTimeText(TimeUtils.getTodayDate() + " " + TimeUtils.getNowTimeText()) > TimeUtils.getTimeStampFromTimeText(
                TimeUtils.getTodayDate() + " " + getString(R.string.const_div_time)
            )
        ) {
            LibraryPresenterImpl.getHistory(token, 1, 10, this)
        } else {
            LibraryPresenterImpl.getReservations(token, this)
        }
    }

    fun updatePage(reservationsResponse : ReservationsResponse) {
        val data = ArrayList<ReservationsResponse>()
        data.add(reservationsResponse)
        findViewById<RecyclerView>(R.id.history_list).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ReservationsAdapter(data)
        }
    }

    fun updatePage(historyResponse: HistoryResponse) {
        findViewById<RecyclerView>(R.id.history_list).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = HistoryAdapter(historyResponse.reservations)
        }
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
    }
}