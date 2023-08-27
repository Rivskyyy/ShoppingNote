package com.rivskyinc.shoppingnote.domain

class GetShoppingItemUseCase(private val shopRepository: ShopRepository) {

    fun getShoppingItem(shoppingId: Int): ShoppingNote {
       return  shopRepository.getShoppingItem(shoppingId)
    }
}