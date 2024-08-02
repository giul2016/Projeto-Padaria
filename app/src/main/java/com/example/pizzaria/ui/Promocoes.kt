package com.example.pizzaria.ui

import PromoAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pizzaria.databinding.ActivityPromocoesBinding
import com.example.pizzaria.lisItens.PromoList
import com.example.pizzaria.model.PromoItem

class Promocoes : AppCompatActivity(),  PromoAdapter.OnItemClickListener {
    private lateinit var binding: ActivityPromocoesBinding


    private lateinit var adapter: PromoAdapter
    private var items: List<PromoItem> = PromoList.getPromoItems()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPromocoesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchView.setBackgroundColor(ContextCompat.getColor(this, android.R.color.white))

        val recyclerView: RecyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = PromoAdapter(this, items,this)
        recyclerView.adapter = adapter

        setupSearchView()
    }

    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filter(newText)
                return true
            }
        })
    }

    private fun filter(text: String?) {
        val filteredList = items.filter {
            it.name.contains(text ?: "", ignoreCase = true) ||
                    it.descricao.contains(text ?: "", ignoreCase = true)
        }
        adapter.updateList(filteredList)
    }

    override fun onItemClick() {
        binding.searchView.setQuery("", false)
        binding.searchView.clearFocus()
    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityPromocoesBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        val recyclerView: RecyclerView = binding.recyclerView
//        recyclerView.layoutManager = LinearLayoutManager(this)
//
//        val items = PromoList.getPromoItems()
//        val adapter = PromoAdapter(this, items)
//        recyclerView.adapter = adapter
//    }
}