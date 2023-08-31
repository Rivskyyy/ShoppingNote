package com.rivskyinc.shoppingnote.presentation

import androidx.lifecycle.ViewModel
import com.rivskyinc.shoppingnote.data.ShopRepositoryImpl
import com.rivskyinc.shoppingnote.domain.AddShoppingNoteUseCase
import com.rivskyinc.shoppingnote.domain.EditShoppingNoteUseCase
import com.rivskyinc.shoppingnote.domain.GetShoppingItemUseCase
import com.rivskyinc.shoppingnote.domain.ShoppingNote

class DetailViewModel : ViewModel() {

    val repository = ShopRepositoryImpl

    val addShoppingNoteUseCase = AddShoppingNoteUseCase(repository)
    val editShoppingNoteUseCase = EditShoppingNoteUseCase(repository)
    val getItemUseCase = GetShoppingItemUseCase(repository)

    fun addItem(itemId : Int){
       val item =  addShoppingNoteUseCase.addShoppingNote(itemId)

    }

    fun editItem(shoppingNote: ShoppingNote){
        editShoppingNoteUseCase.editShoppingNote(shoppingNote)
    }

    fun getItem(shoppingNote: ShoppingNote){
        getItemUseCase.getShoppingItem(shoppingNote)
    }
}