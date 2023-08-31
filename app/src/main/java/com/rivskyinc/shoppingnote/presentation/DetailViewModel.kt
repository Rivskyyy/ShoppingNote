package com.rivskyinc.shoppingnote.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import com.rivskyinc.shoppingnote.data.ShopRepositoryImpl
import com.rivskyinc.shoppingnote.domain.AddShoppingNoteUseCase
import com.rivskyinc.shoppingnote.domain.EditShoppingNoteUseCase
import com.rivskyinc.shoppingnote.domain.GetShoppingItemUseCase
import com.rivskyinc.shoppingnote.domain.ShoppingNote
import java.lang.Exception

class DetailViewModel : ViewModel() {

    val repository = ShopRepositoryImpl

    private val addShoppingNoteUseCase = AddShoppingNoteUseCase(repository)
    private val editShoppingNoteUseCase = EditShoppingNoteUseCase(repository)
    private val getItemUseCase = GetShoppingItemUseCase(repository)

    fun addItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val inputValid = validateInput(name,count)
        if ( inputValid){
            val shoppingNote = ShoppingNote(name, count, true)
            addShoppingNoteUseCase.addShoppingNote(shoppingNote)
        }
    }

    fun editItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val inputValid = validateInput(name,count)
        if ( inputValid){
            val shoppingNote = ShoppingNote(name, count, true)
            editShoppingNoteUseCase.editShoppingNote(shoppingNote)
        }
    }

    fun getItem(itemId: Int) {
        val item = getItemUseCase.getShoppingItem(itemId)
    }

    private fun parseName(inputName: String?): String {
        return inputName?.trim() ?: ""
    }

    private fun parseCount(inputCount: String?): Int {
        return try {
             inputCount?.trim()?.toInt() ?: 0

        } catch (e: Exception) {
                0
        }
    }
    private fun validateInput(name: String, count : Int) : Boolean {
        var result = true
        if ( name.isBlank()){
            //TODO: show error input name
            result = false
        }
        if ( count <= 0 ){
            //TODO: show error input count
            result = false
        }
        return result
    }
}

