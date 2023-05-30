package com.rivskyinc.shoppingnote.domain

class DeleteShoppingNoteUseCase(private val shopReposiroty: ShopReposiroty) {

    fun deleteShoppingNote(shoppingNote: ShoppingNote) {
        shopReposiroty.deleteShoppingNote(shoppingNote)
    }
}