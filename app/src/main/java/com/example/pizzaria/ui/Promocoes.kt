package com.example.pizzaria.ui

import PromoAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pizzaria.databinding.ActivityPromocoesBinding
import com.example.pizzaria.lisItens.PromoList

class Promocoes : AppCompatActivity() {
    private lateinit var binding: ActivityPromocoesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPromocoesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView: RecyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)

        val items = PromoList.getPromoItems()
        val adapter = PromoAdapter(this, items)
        recyclerView.adapter = adapter
    }
}