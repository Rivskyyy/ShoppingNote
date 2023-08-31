package com.rivskyinc.shoppingnote.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    private var _errorInputName = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean>
        get() = _errorInputName
    private var _errorInputCount = MutableLiveData<Boolean>()
    val errorInputCount: LiveData<Boolean>
        get() = _errorInputCount
    private var _shopItem = MutableLiveData<ShoppingNote>()
    val shopItem: LiveData<ShoppingNote>
        get() = _shopItem
    private var _closePermission = MutableLiveData<Unit>()
    val closePermissionScreen : LiveData<Unit>
        get() = _closePermission

    fun addItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val inputValid = validateInput(name, count)
        if (inputValid) {
            val shoppingNote = ShoppingNote(name, count, true)
            addShoppingNoteUseCase.addShoppingNote(shoppingNote)
            shouldCloseScreen()
        }
    }

    fun editItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val inputValid = validateInput(name, count)
        if (inputValid) {
            val shoppingNote = ShoppingNote(name, count, true)
            editShoppingNoteUseCase.editShoppingNote(shoppingNote)
            shouldCloseScreen()
        }
    }

    fun getItem(itemId: Int) {
        val item = getItemUseCase.getShoppingItem(itemId)
        _shopItem.value = item
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

    private fun validateInput(name: String, count: Int): Boolean {
        var result = true
        if (name.isBlank()) {
            _errorInputName.value = true
            result = false
        }
        if (count <= 0) {
            _errorInputCount.value = true
            //TODO: show error input count
            result = false
        }
        return result
    }

    fun resetErrorInputName() {
        _errorInputName.value = false
    }

    fun resetErrorInputCount() {
        _errorInputCount.value = false
    }
    private fun shouldCloseScreen(){
        _closePermission.value = Unit
    }

}

