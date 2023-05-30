package com.rivskyinc.shoppingnote.domain

interface ShopReposiroty {

    fun addShoppingNote(shoppingNote: ShoppingNote)

    fun deleteShoppingNote(shoppingNote: ShoppingNote)

    fun editShoppingNote(shoppingNote: ShoppingNote)

    fun getShoppingItem(shoppingId: Int): ShoppingNote

    fun getShoppingNoteList() : List<ShoppingNote>
}