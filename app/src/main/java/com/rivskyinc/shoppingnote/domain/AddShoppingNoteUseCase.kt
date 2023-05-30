package com.rivskyinc.shoppingnote.domain

class AddShoppingNoteUseCase(private val shopReposiroty: ShopReposiroty) {

    fun addShoppingNote(shoppingNote: ShoppingNote) {
        shopReposiroty.addShoppingNote(shoppingNote)
    }
}