package com.rivskyinc.shoppingnote.domain

import androidx.lifecycle.LiveData

interface ShopRepository {

    fun addShoppingNote(shoppingNote: ShoppingNote)

    fun deleteShoppingNote(shoppingNote: ShoppingNote)

    fun editShoppingNote(shoppingNote: ShoppingNote)

    fun getShoppingItem(shoppingId: Int): ShoppingNote

    fun getShoppingNoteList() : LiveData<List<ShoppingNote>>
}