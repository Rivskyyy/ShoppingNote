package com.rivskyinc.shoppingnote.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.android.material.textfield.TextInputEditText
import com.rivskyinc.shoppingnote.R

class DetailShopItemActivity : AppCompatActivity() {

    private lateinit var nameInput : TextInputEditText
    private lateinit var countInput: TextInputEditText
    private lateinit var editName : EditText
    private lateinit var editCount : EditText
    private lateinit var buttonSave : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_shop_item)
        initViews()
        val getIntentString = intent.getStringExtra(EXTRA_SCREEN_MODE)
        val getIntentInt =  intent.getIntExtra(MODE_ID, 0 )
    }

    private fun initViews() {
        nameInput = findViewById(R.id.til_name)
        countInput = findViewById(R.id.til_count)
        editName = findViewById(R.id.edit_name)
        editCount = findViewById(R.id.edit_count)
        buttonSave = findViewById(R.id.button_save)

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