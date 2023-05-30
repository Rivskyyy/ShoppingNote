package com.rivskyinc.shoppingnote.domain

data class ShoppingNote(
    val name : String,
    val count : Int,
    val enabled : Boolean,
    var  id : Int = UNDEFINED
){

    companion object{

        const val UNDEFINED = -1
    }
}
