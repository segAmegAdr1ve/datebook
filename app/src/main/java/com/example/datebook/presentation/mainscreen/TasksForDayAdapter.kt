package com.example.datebook.presentation.mainscreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.datebook.Constants.HEADER_TYPE
import com.example.datebook.Constants.TASK_TYPE
import com.example.datebook.databinding.HeaderCardBinding
import com.example.datebook.databinding.TaskCardBinding
import com.example.datebook.domain.entity.ItemModel
import com.example.datebook.utils.toNormalTimeFormat

class TasksForDayAdapter : RecyclerView.Adapter<ViewHolder>() {

    private val taskList = mutableListOf<ItemModel>()
    var onTaskCardClicked: (entryId: Int) -> Unit = {}

    fun setList(list: List<ItemModel>) {
        taskList.clear()
        taskList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            0 -> TaskForDayViewHolder(
                TaskCardBinding.inflate(LayoutInflater.from(parent.context))
            )

            else -> HeaderViewHolder(
                HeaderCardBinding.inflate(LayoutInflater.from(parent.context))
            )
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            TASK_TYPE -> (holder as TaskForDayViewHolder).bind(position)
            else -> (holder as HeaderViewHolder).bind(position)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (taskList[position].compactEntryModel != null) TASK_TYPE else HEADER_TYPE
    }

    override fun getItemCount(): Int = taskList.size

    inner class TaskForDayViewHolder(private val binding: TaskCardBinding) :
        ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.taskName.text = taskList[position].compactEntryModel?.name
            binding.taskTime.text =
                taskList[position].compactEntryModel?.dateFinish?.toNormalTimeFormat()
            binding.taskCard.setOnClickListener {
                onTaskCardClicked(taskList[position].compactEntryModel!!.id)
            }
        }
    }

    inner class HeaderViewHolder(private val binding: HeaderCardBinding) :
        ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.headerText.text = taskList[position].header?.header
        }
    }
}
