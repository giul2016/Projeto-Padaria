package com.example.pizzaria.ui

/********************************************
 *     Created by Giul on 10/02/2024.  *
 ********************************************/

import android.content.SharedPreferences
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pizzaria.OnCartItemDeletedListener
import com.example.pizzaria.R
import com.example.pizzaria.adapter.CarrinhoAdapter
import com.example.pizzaria.model.PrefConfig
import com.example.pizzaria.model.Produto
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import java.text.NumberFormat
import java.util.Locale

class CarrinhoActivity : AppCompatActivity(), OnCartItemDeletedListener  {

    private var carrinho: MutableList<Produto> = mutableListOf()

    private lateinit var toolbar: Toolbar

    private lateinit var tvTotal: TextView

    private lateinit var adapter: CarrinhoAdapter

    private val carrinhoConte: MutableMap<Produto, Int> = mutableMapOf()

    private var totalInicial: Double = 0.0

    private lateinit var tvTotalItens: TextView

    private val sharedPreferences: SharedPreferences by lazy {
        PreferenceManager.getDefaultSharedPreferences(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carrinho)

        CarrinhoManager.init(this)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            title = "Carrinho"
            setDisplayHomeAsUpEnabled(true)
        }

        tvTotal = findViewById(R.id.tv_total)
        tvTotalItens = findViewById(R.id.tv_total_itens)

        carrinho = PrefConfig.readListFromPref(this) ?: mutableListOf()

        if (carrinho.isEmpty()) {
            carrinho = mutableListOf()
        } else {
            val listaSemDuplicatas = carrinho.distinct() // Remove itens duplicados
            carrinho.clear() // Limpa a lista atual
            carrinho.addAll(listaSemDuplicatas) // Adiciona a lista sem duplicatas
        }

        // Se a lista for nula, inicialize com uma lista vazia
        if (carrinho == null) {
            carrinho = mutableListOf()
        }

        val extras = intent.extras
        val listaDoIntent = extras?.getParcelableArrayList<Produto>("carrinho")
        if (listaDoIntent != null) {
            for (item in listaDoIntent) {
                if (!carrinho.contains(item)) {
                    carrinho.add(item)

                }
            }
            // Salva a lista atualizada no SharedPreferences
            PrefConfig.writeListInPref(this, carrinho)
        }

        // Configura o RecyclerView para exibir a lista de produtos
        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewCarrinho)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = CarrinhoAdapter(this, carrinho, this)
        recyclerView.adapter = adapter

        getPrecoTotalItens()

        notifyUpdate()

    }

    fun getPrecoTotalItens() {

        val totalItens = carrinho.size
        totalInicial = 0.0
        for (produto in carrinho) {
            totalInicial += produto.price
        }
        // Formata o total para exibição
        val formattedTotal =
            NumberFormat.getCurrencyInstance(Locale("pt", "BR")).format(totalInicial)
        tvTotal.text = formattedTotal
        val itemText = if (totalItens > 1) "itens" else "item"
        tvTotalItens.text = "Total / $totalItens $itemText"
        notifyUpdate()
    }

    fun notifyUpdate() {

        var totalCarrinho = totalInicial
        val totalItens = carrinho.size

        for ((produto, quantidade) in carrinhoConte) {
            totalCarrinho += produto.price * quantidade
        }
        val formattedTotal =
            NumberFormat.getCurrencyInstance(Locale("pt", "BR")).format(totalCarrinho)
        tvTotal.text = formattedTotal
        val itemText = if (totalItens > 1) "itens" else "item"
        tvTotalItens.text = "Total / $totalItens $itemText"
    }

    fun adicionarQtdItemAoCarrinho(produto: Produto) {

        val quantidadeAtual = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            carrinhoConte.getOrDefault(produto, 0)

        } else {
            if (carrinhoConte.containsKey(produto)) {
                carrinhoConte[produto]!! // Se o produto já está no mapa, retorna sua quantidade atual
            } else {
                0 // Se o produto não está no mapa, retorna 0
            }
        }
        carrinhoConte[produto] = quantidadeAtual + 1

        notifyUpdate()
    }

    fun reduzirQtdItemAoCarrinho(produto: Produto) {

        val quantidadeAtual = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            carrinhoConte.getOrDefault(produto, 0)

        } else {
            if (carrinhoConte.containsKey(produto)) {
                carrinhoConte[produto]!! // Se o produto já está no mapa, retorna sua quantidade atual
            } else {
                0 // Se o produto não está no mapa, retorna 0
            }
        }

        // Verifica se a quantidade atual é maior que 0 antes de reduzir
        if (quantidadeAtual > 0) {

            carrinhoConte[produto] = quantidadeAtual - 1
            notifyUpdate()

        } else {
            // Caso a quantidade atual seja 0, não fazemos nada
            // ou podemos exibir uma mensagem informando que a quantidade já está zerada
            // ou ainda podemos remover o produto do carrinho, se for apropriado para o contexto
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        super.onBackPressed()
    }

    //https://stackoverflow.com/questions/31746300/how-to-show-snackbar-at-top-of-the-screen
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCartItemDeleted() {

        val rootView = window.decorView.rootView
        val snackBarView = Snackbar.make(rootView, "Item removido do Carrinho" , Snackbar.LENGTH_SHORT)
        val view = snackBarView.view
        val params = view.layoutParams as FrameLayout.LayoutParams
        params.gravity = Gravity.TOP
       // snackBarView.view.setPadding(0, 10, 0, 0)
        params.topMargin = 150 // Ajuste esse valor conforme necessário
        view.layoutParams = params
        view.layoutParams = params
        view.setBackgroundResource(R.drawable.snackbar_background)
        val textView = view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)//
        textView.textAlignment = View.TEXT_ALIGNMENT_CENTER
        textView.setTextSize(14f)// Defina a cor desejada aqui
        snackBarView.setActionTextColor(Color.WHITE)
        snackBarView.animationMode = BaseTransientBottomBar.ANIMATION_MODE_SLIDE
        snackBarView.show()
    }
}
