package com.rivskyinc.shoppingnote.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import com.rivskyinc.shoppingnote.R
import com.rivskyinc.shoppingnote.domain.ShoppingNote
import java.lang.RuntimeException

class DetailShopItemActivity : AppCompatActivity() {

    private lateinit var viewModel: DetailViewModel

    private lateinit var nameInput : TextInputEditText
    private lateinit var countInput: TextInputEditText
    private lateinit var editName : EditText
    private lateinit var editCount : EditText
    private lateinit var buttonSave : Button

    private var screenMode = SCREEN_MODE
    private var shopItemId = ShoppingNote.UNDEFINED

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_shop_item)
        parseIntent()
        initViews()
        viewModel = ViewModelProvider(this)[DetailViewModel::class.java]
        editName.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                TODO("Not yet implemented")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
        when(screenMode){
            MODE_EDIT -> launchEditMode()
            MODE_ADD -> launchAddMode()
        }
        viewModel.errorInputCount.observe(this){
            val message = if ( it){
                getString(R.string.error_input_message)
            } else {
                null
            }
            countInput.error = message
        }
        viewModel.errorInputName.observe(this){
            val message = if ( it){
                getString(R.string.error_input_message)
            } else {
                null
            }
            nameInput.error = message
        }
        viewModel.closePermissionScreen.observe(this){
            finish()
        }
    }

    private fun launchAddMode() {
        buttonSave.setOnClickListener {
            viewModel.addItem(editName.text?.toString(), editCount.text?.toString())
        }
    }

    private fun launchEditMode() {
       val itemId =  viewModel.getItem(shopItemId)

        viewModel.shopItem.observe(this){
            editName.setText(it.name)
            editCount.setText(it.count.toString())
            buttonSave.setOnClickListener {
                viewModel.editItem(editName.text?.toString(), editCount.text?.toString())
            }
        }
    }

    private fun parseIntent() {
        if ( !intent.hasExtra(EXTRA_SCREEN_MODE)){
            throw RuntimeException("screen mode is absent")
        }
        val mode = intent.getStringExtra(EXTRA_SCREEN_MODE)
        if ( mode != MODE_ADD && mode != MODE_EDIT ){
            throw RuntimeException("Unknown screen mode $mode ")
        }
        screenMode = mode
        if ( screenMode == MODE_EDIT){
            if(!intent.hasExtra(MODE_ID)){
                throw RuntimeException("shop item id is absent")
            }
            shopItemId = intent.getIntExtra(MODE_ID, -1 )
        }
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
        private const val SCREEN_MODE = ""

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