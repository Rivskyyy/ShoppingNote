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

        val getIntentString = intent.getStringExtra(EXTRA_SCREEN_MODE)
        val getIntentInt =  intent.getIntExtra(MODE_ID, 0 )
    }


    companion object{
        private const val EXTRA_SCREEN_MODE = "extra_mode"
        private const val MODE_EDIT = "mode_edit"
        private const val MODE_ADD = "mode_add"
        private const val MODE_ID = "mode_id"

        fun newIntentAddItem(context: Context ) : Intent {
            val intent = Intent(context, DetailShopItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_ADD)
            return intent
        }

        fun newIntentEditItem(context: Context, id : Int) : Intent {
            val intent = Intent(context, DetailShopItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_EDIT)
            intent.putExtra(MODE_ID, id)
            return intent

        }
    }
}