package indi.wkq.superseatandroid.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import indi.wkq.superseatandroid.R
import indi.wkq.superseatandroid.model.response.Seat

/**
 * @author  calesq
 * @date    2021/5/31
 */
class RoomsAdapter(
    private val seatCode: List<String>,
    private val seats: List<Seat>,
    private var mItemListener: View.OnClickListener
) : RecyclerView.Adapter<RoomsAdapter.RoomItemViewHolder>() {

    inner class RoomItemViewHolder(view: View, itemClickListenner: View.OnClickListener) : RecyclerView.ViewHolder(view) {
        var tvSeatNumber: TextView = view.findViewById(R.id.seat_number)
        var tvSeatCode: TextView = view.findViewById(R.id.seat_code)
        var iconPower: AppCompatImageView = view.findViewById(R.id.power)
        var iconWindow: AppCompatImageView = view.findViewById(R.id.window)
        var iconComputer: AppCompatImageView = view.findViewById(R.id.computer)

        init {
            view.setOnClickListener(itemClickListenner)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_library_item, parent, false)
        return RoomItemViewHolder(view, mItemListener)
    }

    override fun onBindViewHolder(holder: RoomItemViewHolder, position: Int) {
        val seat = seats[position]
        holder.tvSeatNumber.text = seat.name
        holder.tvSeatCode.text = seat.id.toString()
        when (true) {
            seat.computer -> holder.iconComputer.setImageResource(R.drawable.icon_has_computer)
            seat.window -> holder.iconWindow.setImageResource(R.drawable.icon_has_window)
            seat.power -> holder.iconPower.setImageResource(R.drawable.icon_has_power)
            else -> return
        }
    }

    override fun getItemCount(): Int = seats.size
}