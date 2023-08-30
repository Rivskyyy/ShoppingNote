package com.rivskyinc.shoppingnote.presentation

import androidx.recyclerview.widget.DiffUtil
import com.rivskyinc.shoppingnote.domain.ShoppingNote

class ShopItemDiffCallback : DiffUtil.ItemCallback<ShoppingNote>() {
    override fun areItemsTheSame(oldItem: ShoppingNote, newItem: ShoppingNote): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ShoppingNote, newItem: ShoppingNote): Boolean {

        return oldItem == newItem

    }


}