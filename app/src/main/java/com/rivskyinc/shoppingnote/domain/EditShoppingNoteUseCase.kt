package com.rivskyinc.shoppingnote.domain

class EditShoppingNoteUseCase(private val shopReposiroty: ShopReposiroty) {

    fun editShoppingNote(shoppingNote: ShoppingNote){
        shopReposiroty.editShoppingNote(shoppingNote)
    }
}