package com.rivskyinc.shoppingnote.presentation

import androidx.lifecycle.ViewModel
import com.rivskyinc.shoppingnote.data.ShopRepositoryImpl
import com.rivskyinc.shoppingnote.domain.DeleteShoppingNoteUseCase
import com.rivskyinc.shoppingnote.domain.EditShoppingNoteUseCase
import com.rivskyinc.shoppingnote.domain.GetShoppingListUseCase
import com.rivskyinc.shoppingnote.domain.ShoppingNote

class MainViewModel : ViewModel() {

    private val repository = ShopRepositoryImpl

    private val getShoppingListUseCase = GetShoppingListUseCase(repository)
    private val deleteShoppingNoteUseCase = DeleteShoppingNoteUseCase(repository)
    private val editShoppingNoteUseCase = EditShoppingNoteUseCase(repository)

    val shopList = getShoppingListUseCase.getShoppingNoteList()

    fun deleteItem(shoppingNote: ShoppingNote){
         deleteShoppingNoteUseCase.deleteShoppingNote(shoppingNote)
    }

    fun editItem(shoppingNote: ShoppingNote){
        val newItem = shoppingNote.copy(enabled = !shoppingNote.enabled)
        editShoppingNoteUseCase.editShoppingNote(newItem)
    }

}