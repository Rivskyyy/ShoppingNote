package com.rivskyinc.shoppingnote.domain

class EditShoppingNoteUseCase(private val shopRepository: ShopRepository) {

    fun editShoppingNote(shoppingNote: ShoppingNote){
        shopRepository.editShoppingNote(shoppingNote)
    }
}