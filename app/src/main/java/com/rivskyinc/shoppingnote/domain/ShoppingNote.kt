package com.rivskyinc.shoppingnote.domain

data class ShoppingNote(
    val id : Int,
    val name : String,
    val count : Int,
    val enabled : Boolean
)
