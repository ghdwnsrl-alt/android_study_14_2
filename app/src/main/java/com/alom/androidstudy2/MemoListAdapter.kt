package com.alom.androidstudy2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alom.androidstudy2.databinding.ItemMemoBinding
import com.bumptech.glide.Glide


object MemoDiffUtil : DiffUtil.ItemCallback<Memo>() {
    override fun areItemsTheSame(oldItem: Memo, newItem: Memo): Boolean {

        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Memo, newItem: Memo): Boolean {

        return oldItem == newItem
    }
}


class MemoListAdapter : ListAdapter<Memo, MemoListAdapter.MemoViewHolder>(MemoDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMemoBinding.inflate(inflater, parent, false)
        return MemoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MemoViewHolder, position: Int) {
        val memoItem = getItem(position)
        holder.bind(memoItem)
    }

    inner class MemoViewHolder(private val binding: ItemMemoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(memo: Memo) {
            binding.tvTitle.text = memo.title
            binding.tvPrice.text = memo.price
            binding.tvTime.text=memo.time
            Glide.with(binding.imgItem).load(memo.imageUrl).into(binding.imgItem)
        }
    }
}
