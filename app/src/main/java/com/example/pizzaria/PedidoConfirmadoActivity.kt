package com.example.pizzaria

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pizzaria.adapter.PedidoAdapter
import com.example.pizzaria.model.PedidoItem
import com.example.pizzaria.model.Produto
import com.example.pizzaria.utils.MySharedPreferencesManager
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class PedidoConfirmadoActivity : AppCompatActivity() {

    private lateinit var viewModel: PedidoViewModel
    private lateinit var sharedPreferencesManager: MySharedPreferencesManager
    private lateinit var recyclerView: RecyclerView
    private lateinit var btnLimpaLista: Button
    private var adapter: PedidoAdapter? = null
    private var updatedPedidoItemList: MutableList<PedidoItem> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pedido_confirmado)

        val toolbar: Toolbar = findViewById(R.id.toolbaPedidoConfirm)
        setSupportActionBar(toolbar)

        supportActionBar?.apply {
            title = "Pedidos confirmados"
            setDisplayHomeAsUpEnabled(true)
            setTitleColor(Color.WHITE)
        }

        viewModel = ViewModelProvider(this).get(PedidoViewModel::class.java)

        recyclerView = findViewById(R.id.recyclerView_pedido1)
        recyclerView.layoutManager = LinearLayoutManager(this)

        btnLimpaLista = findViewById<Button>(R.id.btn_limpar)

        // Inicializa o sharedPreferencesManager
        sharedPreferencesManager = MySharedPreferencesManager(this)

        // Carrega a lista de pedidos do SharedPreferences
        val savedPedidoItemList = sharedPreferencesManager.getPedidoItemList("pedidoItemList")
        if (savedPedidoItemList.isNotEmpty()) {
            adapter = PedidoAdapter(savedPedidoItemList)
            recyclerView.adapter = adapter
            adapter?.notifyDataSetChanged()
        }

        // Recebe os dados do Intent
        val carrinhoConteList = intent.getSerializableExtra("carrinhoContePedido") as HashMap<Produto, Int>?
        val nome = intent.getStringExtra("nome")
        val endereco = intent.getStringExtra("endereco")
        val telefone = intent.getStringExtra("telefone")
        val dataPedido = getCurrentDate()

        if (carrinhoConteList != null && nome != null && endereco != null && telefone != null) {
            val produtosComQuantidade = carrinhoConteList.map { it.key to it.value } // Converte HashMap para List<Pair<Produto, Int>>
            val pedidoItem = PedidoItem(nome, endereco, telefone, produtosComQuantidade,dataPedido)

            // Adiciona o novo PedidoItem à lista salva e atualiza o SharedPreferences
            updatedPedidoItemList = savedPedidoItemList.toMutableList()
            updatedPedidoItemList.add(pedidoItem)
            sharedPreferencesManager.savePedidoItemList("pedidoItemList", updatedPedidoItemList)

            // Atualiza o RecyclerView
            adapter?.updateData(updatedPedidoItemList)
        }

        // Configura o clique do botão "Limpar"
        setupLimparButton()
    }

    private fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("EEEE, dd/MM/yyyy", Locale.getDefault())
        return dateFormat.format(Date())
    }


    private fun setupLimparButton() {
        btnLimpaLista.setOnClickListener {
            Toast.makeText(this, "Limpando lista", Toast.LENGTH_LONG).show()
            // Limpa a lista de itens
            updatedPedidoItemList.clear()

            // Salva a lista atualizada no SharedPreferences
            sharedPreferencesManager.savePedidoItemList("pedidoItemList", updatedPedidoItemList)

            // Notifica o RecyclerView para atualizar a exibição
            adapter?.notifyDataSetChanged()

            recreate()
        }
    }
}


//class PedidoConfirmadoActivity : AppCompatActivity() {
//
//    private lateinit var viewModel: PedidoViewModel
//    private lateinit var sharedPreferencesManager: MySharedPreferencesManager
//    private lateinit var recyclerView: RecyclerView
//    private lateinit var btnLimpaLista: Button
//    private var adapter: PedidoAdapter? = null
//    private var updatedPedidoItemList: MutableList<PedidoItem> = mutableListOf()
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_pedido_confirmado)
//
//        val toolbar: Toolbar = findViewById(R.id.toolbaPedidoConfirm)
//        setSupportActionBar(toolbar)
//
//        supportActionBar?.apply {
//            title = "Pedidos confirmados"
//            setDisplayHomeAsUpEnabled(true)
//            setTitleColor(Color.WHITE)
//        }
//
//        viewModel = ViewModelProvider(this).get(PedidoViewModel::class.java)
//
//        recyclerView = findViewById(R.id.recyclerView_pedido1)
//        recyclerView.layoutManager = LinearLayoutManager(this)
//
//        btnLimpaLista = findViewById<Button>(R.id.btn_limpar)
//
//        // Inicializa o sharedPreferencesManager
//        sharedPreferencesManager = MySharedPreferencesManager(this)
//
//        // Carrega a lista de pedidos do SharedPreferences
//        val savedPedidoItemList = sharedPreferencesManager.getPedidoItemList("pedidoItemList")
//        if (savedPedidoItemList.isNotEmpty()) {
//            adapter = PedidoAdapter(savedPedidoItemList)
//            recyclerView.adapter = adapter
//            adapter?.notifyDataSetChanged()
//        }
//
//        val carrinhoConteList = intent.getParcelableArrayListExtra<Produto>("carrinhoContePedido")
//        if (carrinhoConteList != null) {
//            viewModel.setCarrinhoConteList(carrinhoConteList)
//        }
//
//        viewModel.carrinhoConteList.observe(this, Observer { carrinhoConteList ->
//            val nome = intent.getStringExtra("nome")
//            val endereco = intent.getStringExtra("endereco")
//            val telefone = intent.getStringExtra("telefone")
//
//            if (nome != null && endereco != null && telefone != null && carrinhoConteList != null) {
//                val produtosComQuantidade = carrinhoConteList.map { it to 1 }
//                val pedidoItem = PedidoItem(nome, endereco, telefone, produtosComQuantidade)
//
//                // Adiciona o novo PedidoItem à lista salva e atualiza o SharedPreferences
//                updatedPedidoItemList = savedPedidoItemList.toMutableList()
//                updatedPedidoItemList.add(pedidoItem)
//                sharedPreferencesManager.savePedidoItemList("pedidoItemList", updatedPedidoItemList)
//
////                // Atualiza o RecyclerView
////                val adapter = PedidoAdapter(updatedPedidoItemList)
////                recyclerView.adapter = adapter
////                adapter.notifyDataSetChanged()
//
//                // Atualiza o RecyclerView
//                adapter?.let {
//                    it.updateData(updatedPedidoItemList)
//                    //it.setData(updatedPedidoItemList)
//                    it.notifyDataSetChanged()
//                }
//
//
//            }
//        })
//
//        // Configura o clique do botão "Limpar"
//        setupLimparButton()
//    }
//
//    private fun setupLimparButton() {
//        btnLimpaLista.setOnClickListener {
//            Toast.makeText(this, "Limpando lista", Toast.LENGTH_LONG).show()
//            // Limpa a lista de itens
//            updatedPedidoItemList.clear()
//
//            // Salva a lista atualizada no SharedPreferences
//            sharedPreferencesManager.savePedidoItemList("pedidoItemList", updatedPedidoItemList)
//
//            // Notifica o RecyclerView para atualizar a exibição
//            adapter?.notifyDataSetChanged()
//
//            recreate()
//        }
//    }
//}


//Este foi o penultimo codigo com limpa lista

//class PedidoConfirmadoActivity : AppCompatActivity() {
//
//    private lateinit var viewModel: PedidoViewModel
//    private lateinit var sharedPreferencesManager: MySharedPreferencesManager
//
//    private lateinit var btnLimpaLista: Button
//    private lateinit var adapter: PedidoAdapter
//    private lateinit var updatedPedidoItemList: MutableList<PedidoItem>
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_pedido_confirmado)
//
//        val toolbar: Toolbar = findViewById(R.id.toolbaPedidoConfirm)
//        setSupportActionBar(toolbar)
//
//        supportActionBar?.apply {
//            title = "Pedidos confirmados"
//            setDisplayHomeAsUpEnabled(true)
//            setTitleColor(Color.WHITE)
//        }
//
//        viewModel = ViewModelProvider(this).get(PedidoViewModel::class.java)
//
//        val recyclerView: RecyclerView = findViewById(R.id.recyclerView_pedido1)
//        recyclerView.layoutManager = LinearLayoutManager(this)
//
//       btnLimpaLista = findViewById<Button>(R.id.btn_limpar)
//
//
//        // Inicializa o sharedPreferencesManager
//        sharedPreferencesManager = MySharedPreferencesManager(this)
//
//        // Carrega a lista de pedidos do SharedPreferences
//        val savedPedidoItemList = sharedPreferencesManager.getPedidoItemList("pedidoItemList")
//
//        if (savedPedidoItemList.isNotEmpty()) {
//             adapter = PedidoAdapter(savedPedidoItemList)
//            recyclerView.adapter = adapter
//            adapter.notifyDataSetChanged()
//        }
//
//        val carrinhoConteList = intent.getParcelableArrayListExtra<Produto>("carrinhoContePedido")
//        if (carrinhoConteList != null) {
//            viewModel.setCarrinhoConteList(carrinhoConteList)
//        }
//
//        viewModel.carrinhoConteList.observe(this, Observer { carrinhoConteList ->
//            val nome = intent.getStringExtra("nome")
//            val endereco = intent.getStringExtra("endereco")
//            val telefone = intent.getStringExtra("telefone")
//
//            if (nome != null && endereco != null && telefone != null && carrinhoConteList != null) {
//                val produtosComQuantidade = carrinhoConteList.map { it to 1 }
//                val pedidoItem = PedidoItem(nome, endereco, telefone, produtosComQuantidade)
//
//                // Adiciona o novo PedidoItem à lista salva e atualiza o SharedPreferences
//                updatedPedidoItemList = savedPedidoItemList.toMutableList()
//                updatedPedidoItemList.add(pedidoItem)
//                sharedPreferencesManager.savePedidoItemList("pedidoItemList", updatedPedidoItemList)
//
//                // Atualiza o RecyclerView
//                val adapter = PedidoAdapter(updatedPedidoItemList)
//                recyclerView.adapter = adapter
//                adapter.notifyDataSetChanged()
//
//            }
//
//        })
//
//        btnLimpaLista.setOnClickListener {
//            Toast.makeText(this,"limpando lista",Toast.LENGTH_LONG).show()
//            // Limpa a lista de itens
//
//            updatedPedidoItemList.clear()
//
//            // Salva a lista atualizada no SharedPreferences
//            sharedPreferencesManager.savePedidoItemList("pedidoItemList", updatedPedidoItemList)
//
//            // Notifica o RecyclerView para atualizar a exibição
//            adapter.notifyDataSetChanged()
//        }
//
//    }
//}