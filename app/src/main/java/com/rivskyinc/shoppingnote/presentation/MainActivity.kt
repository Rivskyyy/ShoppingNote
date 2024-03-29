package com.rivskyinc.shoppingnote.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.rivskyinc.shoppingnote.R

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: ShopListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerVew()
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.shopList.observe(this) {
            adapter.submitList(it)
        }
        val button_add_item  = findViewById<FloatingActionButton>(R.id.button_add_item)
        button_add_item.setOnClickListener {
            if ( isOnePaneMode()) {
                val intent = DetailShopItemActivity.newIntentAddItem(this)
                startActivity(intent)
            } else {
                launchFragment(FragmentShopItem.newInstanceAddItem())
            }
        }
    }

    private fun isOnePaneMode() : Boolean{

    }

    private fun launchFragment (fragment: Fragment){
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_shop_item_container, fragment)
            .addToBackStack(null)
            .commit()
    }
    private fun setupRecyclerVew() {

        val recyclerView = findViewById<RecyclerView>(R.id.rv_shop_list)
        adapter = ShopListAdapter()
        recyclerView.adapter = adapter

        setupOnSwipeListener(recyclerView)
        setupOnLinClickListener()
        setupOnClickListener()
    }

    private fun setupOnSwipeListener(recyclerView: RecyclerView?) {
        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = adapter.currentList[viewHolder.adapterPosition]
                viewModel.deleteItem(item)
            }
        })
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    private fun setupOnClickListener() {
        adapter.onClickListener = {
            Log.d("MainActivityTest", it.toString())
           val intent =  DetailShopItemActivity.newIntentEditItem(this, it.id)
            startActivity(intent)
        }
    }

    private fun setupOnLinClickListener() {
        adapter.onLongClickListener = {
            viewModel.editItem(it)
        }
    }


}
