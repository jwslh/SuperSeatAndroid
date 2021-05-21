package indi.wkq.superseatandroid.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.xuexiang.xui.adapter.simple.ViewHolder
import com.xuexiang.xui.utils.ResUtils.getString
import indi.wkq.superseatandroid.R
import indi.wkq.superseatandroid.model.response.ReservationsResponse
import indi.wkq.superseatandroid.utils.LibraryUtils

/**
 * @author  calesq
 * @date    2021/5/19
 */
class ReservationsAdapter(private val mData : List<ReservationsResponse>) : RecyclerView.Adapter<ReservationsAdapter.HistoryViewHolder>(){

    inner class HistoryViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val date: View = view.findViewById<View>(R.id.date)
        val time: View = view.findViewById<View>(R.id.time)
        val location: View = view.findViewById<View>(R.id.location)
        val status: View = view.findViewById<View>(R.id.status)
        val action: View = view.findViewById<View>(R.id.action)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.block_history, parent, false)
        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val reservation = mData[position]
        holder.date.findViewById<TextView>(R.id.title).text = getString(R.string.tv_title_date)
        holder.date.findViewById<TextView>(R.id.item_val).text = reservation.onDate

        holder.time.findViewById<TextView>(R.id.title).text = getString(R.string.tv_title_time)
        holder.time.findViewById<TextView>(R.id.item_val).text = reservation.begin + " ~ " + reservation.end

        holder.location.findViewById<TextView>(R.id.title).text = getString(R.string.tv_title_location)
        holder.location.findViewById<TextView>(R.id.item_val).text = reservation.location

        holder.status.findViewById<TextView>(R.id.title).text = getString(R.string.tv_title_history_status)
        holder.status.findViewById<TextView>(R.id.item_val).text = LibraryUtils.setStatusText(reservation.status)

        holder.action.isVisible  = false
    }

    override fun getItemCount(): Int {
        return mData.size
    }
}