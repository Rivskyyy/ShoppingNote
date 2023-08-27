package com.rivskyinc.shoppingnote.domain

class GetShoppingListUseCase(private val shopReposiroty: ShopReposiroty) {

    fun getShoppingNoteList() : List<ShoppingNote>{
       return  shopReposiroty.getShoppingNoteList()
    }
}