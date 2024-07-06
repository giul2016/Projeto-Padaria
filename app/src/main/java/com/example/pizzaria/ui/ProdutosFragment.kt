package com.example.pizzaria.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.app.ActivityCompat.invalidateOptionsMenu
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pizzaria.CategoriaClickListener
import com.example.pizzaria.OnItemAddedListener
import com.example.pizzaria.R
import com.example.pizzaria.adapter.ProdutosAdapter
import com.example.pizzaria.databinding.FragmentProdutosBinding
import com.example.pizzaria.lisItens.Categorias
import com.example.pizzaria.lisItens.ProductsListManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch


class ProdutosFragment : Fragment() , CategoriaClickListener, OnItemAddedListener {

    private lateinit var binding: FragmentProdutosBinding
    private lateinit var produtosAdapter: ProdutosAdapter
    private val productsListManager = ProductsListManager()
    private val categorias = Categorias()
    private var clicked: String? = null

    private var menu: Menu? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProdutosBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.title = "Produtos"

        CarrinhoManager.init(requireContext())

        // Observar o LiveData e atualizar o contador do carrinho
        CarrinhoManager.itemCountInCart.observe(viewLifecycleOwner, Observer { count ->
            // Atualizar o texto do contador do carrinho onde quer que seja necessário
            updateCartItemCount(count)
        })

//        val callback = object : OnBackPressedCallback(true) {
//            override fun handleOnBackPressed() {
//                // Deixe o corpo do método vazio para manter a fragmento quando o botão voltar é pressionado
//            }
//        }
//
//        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)

        val recyclerViewProductos = binding.recyclerViewProdutos
        recyclerViewProductos.layoutManager = GridLayoutManager(context, 2)
        recyclerViewProductos.setHasFixedSize(true)

        produtosAdapter = ProdutosAdapter(requireContext(), mutableListOf(), this@ProdutosFragment)
        recyclerViewProductos.adapter = produtosAdapter

        // Inicializar RecyclerView para a grade de categorias e lista de produtos
        val categories = listOf("Lanches", "Pizzas","Kebab","Esfiha", "Assados","Paes","Salgados","Frios")
        val categoryItems = categories.map { ProdutosAdapter.Item(ProdutosAdapter.ItemType.CATEGORIA, it) }

        produtosAdapter.itemList.addAll(categoryItems)
        produtosAdapter.notifyDataSetChanged()

        recyclerViewProductos.adapter = produtosAdapter

        val categorias = Categorias()

        lifecycleScope.launch {
            val categoryItems = categorias.getCategoria().collectData().map {
                ProdutosAdapter.Item(ProdutosAdapter.ItemType.CATEGORIA, it)
            }

            requireActivity().runOnUiThread {
                produtosAdapter.itemList.clear()
                produtosAdapter.itemList.addAll(categoryItems)
                produtosAdapter.notifyDataSetChanged()
            }
        }

        productsListManager.initializeLists()

        binding.btnAll.setOnClickListener {
            handleButtonClick("All")
            binding.btnAll.setBackgroundResource(R.drawable.bg_button_enable)
            loadCategorias()
        }
        binding.btnPizza.setOnClickListener {
            handleButtonClick("Pizza")
            binding.btnPizza.setBackgroundResource(R.drawable.bg_button_enable)
            updateRecyclerView("Pizza")
        }

        binding.btnLanches.setOnClickListener {
            handleButtonClick("Lanches")
            binding.btnLanches.setBackgroundResource(R.drawable.bg_button_enable)
            updateRecyclerView("Lanches")
        }
        binding.btnEsfiha.setOnClickListener {
            handleButtonClick("Esfiha")
            binding.btnEsfiha.setBackgroundResource(R.drawable.bg_button_enable)
            updateRecyclerView("Esfiha")
        }

        binding.btnAssados.setOnClickListener {
            handleButtonClick("Assados")
            binding.btnAssados.setBackgroundResource(R.drawable.bg_button_enable)
            updateRecyclerView("Assados")
        }


        binding.btnPaes.setOnClickListener {
            handleButtonClick("Pães")
            binding.btnPaes.setBackgroundResource(R.drawable.bg_button_enable)
            updateRecyclerView("Pães")
        }

        binding.btnSalgados.setOnClickListener {
            handleButtonClick("Salgados")
            binding.btnSalgados.setBackgroundResource(R.drawable.bg_button_enable)
            updateRecyclerView("Salgados")
        }
        binding.btnFrios.setOnClickListener {
            handleButtonClick("Frios")
            binding.btnFrios.setBackgroundResource(R.drawable.bg_button_enable)
            updateRecyclerView("Frios")
        }

        binding.btnKebab.setOnClickListener {
            handleButtonClick("Kebab")
            binding.btnKebab.setBackgroundResource(R.drawable.bg_button_enable)
            updateRecyclerView("Kebab")
        }

    }

    //var imageListener = ImageListener { position, imageView -> imageView.setImageResource(imageArray[position]) }

    private fun loadCategorias() {
        lifecycleScope.launch {
            val categoryItems = categorias.getCategoria().collectData().map {
                ProdutosAdapter.Item(ProdutosAdapter.ItemType.CATEGORIA, it)
            }

            requireActivity().runOnUiThread {
                produtosAdapter.itemList.clear()
                produtosAdapter.itemList.addAll(categoryItems)
                produtosAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun updateRecyclerView(category: String) {
        lifecycleScope.launch {
            val currentList = when (category) {
                "All" -> productsListManager.getAllProducts()
                "Pizza" -> productsListManager.pizzaListFlow.collectData()
                "Assados" -> productsListManager.assadosListFlow.collectData()
                "Kebab" -> productsListManager.kebabListFlow.collectData()
                "Lanches" -> productsListManager.lancheListFlow.collectData()
                "Esfiha" -> productsListManager.esfihaListFlow.collectData()
                "Pães" -> productsListManager.paesaListFlow.collectData()
                "Salgados" -> productsListManager.salgadoListFlow.collectData()
                "Frios" -> productsListManager.friosListFlow.collectData()
                else -> emptyList()
            }

            val items = currentList.map { ProdutosAdapter.Item(ProdutosAdapter.ItemType.PRODUTO, it) }

            requireActivity().runOnUiThread {
                produtosAdapter.updateDataP(items)
            }
        }
    }

    private suspend inline fun <reified T> Flow<T>.collectData(): T =
        this.first()


    private fun handleButtonClick(category: String) {
        clicked = true.toString()
        if (clicked != null) {
            setButtonStyle(binding.btnAll, Color.TRANSPARENT)
            setButtonStyle(binding.btnAssados, Color.TRANSPARENT)
            setButtonStyle(binding.btnKebab, Color.TRANSPARENT)
            setButtonStyle(binding.btnPizza, Color.TRANSPARENT)
            setButtonStyle(binding.btnLanches, Color.TRANSPARENT)
            setButtonStyle(binding.btnEsfiha, Color.TRANSPARENT)
            setButtonStyle(binding.btnPaes, Color.TRANSPARENT)
            setButtonStyle(binding.btnSalgados, Color.TRANSPARENT)
            setButtonStyle(binding.btnFrios, Color.TRANSPARENT)

            // Atualiza a visibilidade do RecyclerView
            binding.recyclerViewProdutos.visibility = View.VISIBLE

            // Atualiza o texto do título
          //  binding.txtlistTitle.text = category
        }
    }

    private fun setButtonStyle(button: View, backgroundColor: Int) {
        button.setBackgroundResource(R.drawable.bg_button_disabled)
        button.setBackgroundColor(backgroundColor)
        if (button is androidx.appcompat.widget.AppCompatButton) {
            button.setTextColor(Color.WHITE)
        }
    }
    override fun onCategoriaClick(categoria: String) {
        updateRecyclerView(categoria)
    }

        override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
        getActivity()?.menuInflater?.inflate(com.example.pizzaria.R.menu.menu, menu)

        // Encontra o item de menu "badge"
        val badgeItem = menu.findItem(com.example.pizzaria.R.id.badge)

        // Atualiza o texto do contador no ícone do menu
        badgeItem?.actionView?.findViewById<TextView>(com.example.pizzaria.R.id.actionbar_notifcation_textview)?.text = CarrinhoManager.getItemCountInCart().toString()

    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            com.example.pizzaria.R.id.badge -> {
                Toast.makeText(requireContext(),"Estou aqui", Toast.LENGTH_LONG).show()
                // Item de menu "badge" foi clicado, abrir a CarrinhoActivity
                val intent = Intent(requireContext(), CarrinhoActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun updateCartItemCount(count: Int) {
        val badgeItem = menu?.findItem(R.id.badge)
        badgeItem?.actionView?.findViewById<TextView>(R.id.actionbar_notifcation_textview)?.text = count.toString()
    }

    override fun onItemAdd() {
        // Incrementa o contador do carrinho
        CarrinhoManager.incrementItemCountInCart()

        // Atualiza o ícone do menu
        requireActivity().invalidateOptionsMenu()
    }

}
