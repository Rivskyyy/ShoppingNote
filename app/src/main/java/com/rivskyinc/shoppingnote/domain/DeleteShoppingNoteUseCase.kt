package com.rivskyinc.shoppingnote.domain

class DeleteShoppingNoteUseCase(private val shopRepository: ShopRepository) {

    fun deleteShoppingNote(shoppingNote: ShoppingNote) {
        shopRepository.deleteShoppingNote(shoppingNote)
    }
}