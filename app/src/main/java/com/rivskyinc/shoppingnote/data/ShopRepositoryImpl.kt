package com.rivskyinc.shoppingnote.data

import com.rivskyinc.shoppingnote.domain.ShopReposiroty
import com.rivskyinc.shoppingnote.domain.ShoppingNote
import java.lang.RuntimeException

object ShopRepositoryImpl : ShopReposiroty {

    private var shopList = mutableListOf<ShoppingNote>()

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
    }

    override fun deleteShoppingNote(shoppingNote: ShoppingNote) {
        shopList.remove(shoppingNote)
    }

    override fun editShoppingNote(shoppingNote: ShoppingNote) {
        val oldElement = getShoppingItem(shoppingNote.id)
        shopList.remove(oldElement)
        shopList.add(shoppingNote)
    }

    override fun getShoppingItem(shoppingId: Int): ShoppingNote {
        return shopList.find { it.id == shoppingId }
            ?: throw RuntimeException("Element with id $shoppingId is not found ")
    }

    override fun getShoppingNoteList(): List<ShoppingNote> {
        return shopList.toList()
    }


}