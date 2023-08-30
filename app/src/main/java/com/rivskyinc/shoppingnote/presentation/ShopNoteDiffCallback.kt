package com.rivskyinc.shoppingnote.presentation

import androidx.recyclerview.widget.DiffUtil
import com.rivskyinc.shoppingnote.domain.ShoppingNote

class ShopNoteDiffCallback(
    private val oldList: List<ShoppingNote>,
    private val newList: List<ShoppingNote>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {

        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem.id == newItem.id
    }
        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldItem = oldList[oldItemPosition]
            val newItem = newList[newItemPosition]

            return oldItem == newItem
        }

}