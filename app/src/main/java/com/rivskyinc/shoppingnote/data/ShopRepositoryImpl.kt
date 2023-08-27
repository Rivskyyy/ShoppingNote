package com.rivskyinc.shoppingnote.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rivskyinc.shoppingnote.domain.ShopRepository
import com.rivskyinc.shoppingnote.domain.ShoppingNote
import java.lang.RuntimeException

object ShopRepositoryImpl : ShopRepository {

    private var shopList = mutableListOf<ShoppingNote>()
    private val shopListLD = MutableLiveData<List<ShoppingNote>>()
    private var autoIncrementId = 0

    init {
        for ( i in 0 until 10 ){
                val item = ShoppingNote("Name $i", i, true  )
                addShoppingNote(item)
        }
    }

    override fun addShoppingNote(shoppingNote: ShoppingNote) {
        if ( shoppingNote.id == ShoppingNote.UNDEFINED){
            shoppingNote.id = autoIncrementId++
        }

        shopList.add(shoppingNote)
        updateList()
    }

    override fun deleteShoppingNote(shoppingNote: ShoppingNote) {
        shopList.remove(shoppingNote)
        updateList()
    }

    override fun editShoppingNote(shoppingNote: ShoppingNote) {
        val oldElement = getShoppingItem(shoppingNote.id)
        shopList.remove(oldElement)
        addShoppingNote(shoppingNote)
    }

    override fun getShoppingItem(shoppingId: Int): ShoppingNote {
        return shopList.find { it.id == shoppingId }
            ?: throw RuntimeException("Element with id $shoppingId is not found ")
    }

    override fun getShoppingNoteList(): LiveData<List<ShoppingNote>> {

        return shopListLD
    }

    private fun updateList(){
        shopListLD.value = shopList.toList()
    }


}