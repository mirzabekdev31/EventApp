package uz.gita.mirzabek.example.eventapp.presenter.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.mirzabek.example.eventapp.R
import uz.gita.mirzabek.example.eventapp.data.constants.Constants
import uz.gita.mirzabek.example.eventapp.data.room.entities.EventModel
import uz.gita.mirzabek.example.eventapp.databinding.ItemBinding

class EventAdapter : ListAdapter<EventModel, EventAdapter.ViewHolder>(ItemDiffUtil) {

    private var switchListener: ((EventModel) -> Unit)? = null

    fun setSwitchListener(block: (EventModel) -> Unit) {
        switchListener = block
    }

    object ItemDiffUtil : DiffUtil.ItemCallback<EventModel>() {
        override fun areItemsTheSame(oldItem: EventModel, newItem: EventModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: EventModel, newItem: EventModel): Boolean {
            return oldItem == newItem
        }

    }

    inner class ViewHolder(private val binding: ItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.SwitchBtn.setOnClickListener {
                val data = getItem(absoluteAdapterPosition)
                switchListener?.invoke(data.copy(isSwitch = 1 - data.isSwitch))
            }
        }

        fun bind() {
            getItem(absoluteAdapterPosition).apply {
                binding.eventImg.setImageResource(Constants.lis[absoluteAdapterPosition])
                binding.NAme.text = name
                binding.SwitchBtn.isChecked = isSwitch == 1
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemBinding.bind(
                LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

}