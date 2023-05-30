package com.rivskyinc.shoppingnote.domain

class GetShoppingItemUseCase(private val shopReposiroty: ShopReposiroty) {

    fun getShoppingItem(shoppingId: Int): ShoppingNote {
       return  shopReposiroty.getShoppingItem(shoppingId)
    }
}