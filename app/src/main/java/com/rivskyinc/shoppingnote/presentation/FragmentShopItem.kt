package com.rivskyinc.shoppingnote.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import com.rivskyinc.shoppingnote.R
import com.rivskyinc.shoppingnote.domain.ShoppingNote

class FragmentShopItem : Fragment(){

    private lateinit var viewModel: DetailViewModel

    private lateinit var nameInput: TextInputEditText
    private lateinit var countInput: TextInputEditText
    private lateinit var editName: EditText
    private lateinit var editCount: EditText
    private lateinit var buttonSave: Button

    private var screenMode = MODE_UNKNOWN
    private var shopItemId = ShoppingNote.UNDEFINED

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_shop_item_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       // parseIntent()
        initViews(view)
        viewModel = ViewModelProvider(this)[DetailViewModel::class.java]
        addChangeTextListener()
        launchRightMode()
        observeViewModel()
    }
    private fun observeViewModel() {
        viewModel.errorInputCount.observe(viewLifecycleOwner) {
            val message = if (it) {
                getString(R.string.error_input_message)
            } else {
                null
            }
            countInput.error = message
        }
        viewModel.errorInputName.observe(viewLifecycleOwner) {
            val message = if (it) {
                getString(R.string.error_input_message)
            } else {
                null
            }
            nameInput.error = message
        }
        viewModel.closePermissionScreen.observe(viewLifecycleOwner) {
            activity?.onBackPressed()
        }
    }

    private fun launchRightMode() {
        when (screenMode) {
            MODE_EDIT -> launchEditMode()
            MODE_ADD -> launchAddMode()
        }
    }

    private fun addChangeTextListener() {
        editName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.resetErrorInputName()
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
        editCount.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.resetErrorInputCount()
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
    }

    private fun launchAddMode() {
        buttonSave.setOnClickListener {
            viewModel.addItem(editName.text?.toString(), editCount.text?.toString())
        }
    }

    private fun launchEditMode() {
        val itemId = viewModel.getItem(shopItemId)

        viewModel.shopItem.observe(viewLifecycleOwner) {
            editName.setText(it.name)
            editCount.setText(it.count.toString())
            buttonSave.setOnClickListener {
                viewModel.editItem(editName.text?.toString(), editCount.text?.toString())
            }
        }
    }

//    private fun parseIntent() {
//        if (!intent.hasExtra(SCREEN_MODE)) {
//            throw RuntimeException("screen mode is absent")
//        }
//        val mode = intent.getStringExtra(SCREEN_MODE)
//        if (mode != MODE_ADD && mode != MODE_EDIT) {
//            throw RuntimeException("Unknown screen mode $mode ")
//        }
//        screenMode = mode
//        if (screenMode == MODE_EDIT) {
//            if (!intent.hasExtra(MODE_ID)) {
//                throw RuntimeException("shop item id is absent")
//            }
//            shopItemId = intent.getIntExtra(MODE_ID, -1)
//        }
//    }

    private fun initViews(view : View) {
        nameInput = view.findViewById(R.id.til_name)
        countInput = view.findViewById(R.id.til_count)
        editName = view.findViewById(R.id.edit_name)
        editCount = view.findViewById(R.id.edit_count)
        buttonSave = view.findViewById(R.id.button_save)

    }
    companion object {
        private const val SCREEN_MODE = "extra_mode"
        private const val MODE_EDIT = "mode_edit"
        private const val MODE_ADD = "mode_add"
        private const val MODE_ID = "mode_id"
        private const val MODE_UNKNOWN = ""

        fun newInstanceAddItem() : FragmentShopItem {
            val args = Bundle().apply {
                putString(SCREEN_MODE, MODE_ADD)
            }
            val fragment = FragmentShopItem().apply {
                arguments = args
            }
            return fragment
        }

        fun newInstanceEditItem(shopId : Int ) : FragmentShopItem{
            val args = Bundle().apply {
                putString(SCREEN_MODE, MODE_EDIT)
                putInt( MODE_ID, shopId)
            }
            val fragment = FragmentShopItem().apply {
                arguments = args
            }
            return fragment
        }
    }
}