package com.example.pizzaria

/********************************************
 *     Created by Giul on 10/02/2024.  *
 ********************************************/

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pizzaria.model.Produto


class CarrinhoViewModel : ViewModel() {
    private val _carrinho = MutableLiveData<MutableList<Produto>>()
    val carrinho: LiveData<MutableList<Produto>> = _carrinho

    private val _total = MutableLiveData<Double>()
    val total: LiveData<Double> = _total

    init {
        _carrinho.value = mutableListOf()
        _total.value = 0.0
    }

    fun setCarrinho(lista: MutableList<Produto>) {
        _carrinho.value = lista
    }

    fun adicionarProduto(produto: Produto) {
        val listaAtual = _carrinho.value ?: mutableListOf()
        listaAtual.add(produto)
        _carrinho.value = listaAtual
        calcularTotal()
    }

    fun removerProduto(posicao: Int) {
        val listaAtual = _carrinho.value ?: mutableListOf()
        if (posicao in listaAtual.indices) {
            listaAtual.removeAt(posicao)
            _carrinho.value = listaAtual
            calcularTotal()
        }
    }

    fun calcularTotal() {
        var total = 0.0
        _carrinho.value?.forEach { produto ->
            total += produto.price
        }
        _total.value = total
    }
}
