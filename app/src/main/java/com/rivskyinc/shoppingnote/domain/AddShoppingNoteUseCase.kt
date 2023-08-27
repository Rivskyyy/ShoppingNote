package com.rivskyinc.shoppingnote.domain

class AddShoppingNoteUseCase(private val shopRepository: ShopRepository) {

    fun addShoppingNote(shoppingNote: ShoppingNote) {
        shopRepository.addShoppingNote(shoppingNote)
    }
}