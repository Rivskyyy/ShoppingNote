package com.rivskyinc.shoppingnote.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rivskyinc.shoppingnote.R
import com.rivskyinc.shoppingnote.domain.ShoppingNote

class DetailShopItemActivity : AppCompatActivity() {
    private var screenMode = MODE_UNKNOWN
    private var shopItemId = ShoppingNote.UNDEFINED
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_shop_item)
        launchRightMode()
        parseIntent()
    }

    private fun parseIntent() {
        if (!intent.hasExtra(SCREEN_MODE)) {
            throw RuntimeException("Param screen mode is absent")
        }
        val mode = intent.getStringExtra(SCREEN_MODE)
        if (mode != MODE_EDIT && mode != MODE_ADD) {
            throw RuntimeException("unknown screen mode ")
        }
        screenMode = mode
        if (screenMode == MODE_EDIT) {
            if (!intent.hasExtra(MODE_ID)) {
                throw RuntimeException("id is absent ")
            }
            shopItemId = intent.getIntExtra(MODE_ID, ShoppingNote.UNDEFINED)
        }

    }

    private fun launchRightMode() {
        val fragment = when (screenMode) {
            MODE_EDIT -> FragmentShopItem.newInstanceEditItem(shopItemId)
            MODE_ADD -> FragmentShopItem.newInstanceAddItem()
            else -> throw RuntimeException("Unknown screen mode $screenMode")
        }
    }

    companion object {
        private const val SCREEN_MODE = "extra_mode"
        private const val MODE_EDIT = "mode_edit"
        private const val MODE_ADD = "mode_add"
        private const val MODE_ID = "mode_id"
        private const val MODE_UNKNOWN = ""

        fun newIntentAddItem(context: Context): Intent {
            val intent = Intent(context, DetailShopItemActivity::class.java)
            intent.putExtra(SCREEN_MODE, MODE_ADD)
            return intent
        }

        fun newIntentEditItem(context: Context, id: Int): Intent {
            val intent = Intent(context, DetailShopItemActivity::class.java)
            intent.putExtra(SCREEN_MODE, MODE_EDIT)
            intent.putExtra(MODE_ID, id)
            return intent
        }
    }


}