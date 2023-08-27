package com.rivskyinc.shoppingnote.domain

import androidx.lifecycle.LiveData

class GetShoppingListUseCase(private val shopRepository: ShopRepository) {

    fun getShoppingNoteList() : LiveData<List<ShoppingNote>>{
       return  shopRepository.getShoppingNoteList()
    }
}