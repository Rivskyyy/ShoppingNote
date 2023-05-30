package com.rivskyinc.shoppingnote.domain

class GetShoppingNoteUseCase(private val shopReposiroty: ShopReposiroty) {

    fun getShoppingNoteList() : List<ShoppingNote>{
       return  shopReposiroty.getShoppingNoteList()
    }
}