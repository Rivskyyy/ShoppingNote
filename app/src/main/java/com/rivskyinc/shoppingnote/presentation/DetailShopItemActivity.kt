package com.rivskyinc.shoppingnote.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rivskyinc.shoppingnote.R

class DetailShopItemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_shop_item)
    }


    companion object{
        const val EXTRA_SCREEN_MODE = "extra_mode"
        const val MODE_EDIT = "mode_edit"
        const val MODE_ADD = "mode_add"

        fun newIntentAddItem(context: Context) : Intent {
            val intent = Intent(context, DetailShopItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_ADD)
            return intent
        }
    }
}