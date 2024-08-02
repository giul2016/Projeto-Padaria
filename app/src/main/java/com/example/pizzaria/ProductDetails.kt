package com.example.pizzaria
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.pizzaria.databinding.ActivityProductDetailsBinding
import com.example.pizzaria.model.PrefConfig
import com.example.pizzaria.model.Produto

import com.example.pizzaria.ui.CarrinhoActivity

import java.text.DecimalFormat


class ProductDetails : AppCompatActivity(), OnItemAddedListener  {

    private lateinit var binding: ActivityProductDetailsBinding

    var carrinho: MutableList<Produto> = mutableListOf()

    private var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CarrinhoManager.init(this)

        carrinho = PrefConfig.readListFromPref(this) ?: mutableListOf()

        // Observar o LiveData e atualizar o contador do carrinho
        CarrinhoManager.itemCountInCart.observe(this, Observer { count ->
            // Atualizar o texto do contador do carrinho no ícone do menu
            updateCartItemCount(count)
        })

        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        window.statusBarColor = Color.parseColor("#E0E0E0")

        val imgProduto = intent.extras!!.getInt("imgProduct")
        val name = intent.extras!!.getString("name")
        val price = intent.extras!!.getDouble("price")
        val descricao = intent.extras?.getString("descricao")
        var newPrice = price

        val decimalFormat = DecimalFormat.getCurrencyInstance()

        supportActionBar?.apply {
            title = name  // Define o título da AppBar para o nome do produto
            setDisplayHomeAsUpEnabled(true)// Ativa o botão "Voltar" na AppBar
            setTitleColor(Color.WHITE)
        }

        binding.imgProduct.setBackgroundResource(imgProduto)
        binding.txtProductName.text = "$name"
        binding.txtPrice.text = decimalFormat.format(price)
        binding.txtDescription.toString()
        binding.contLetras.text.toString()
        intent.putExtra(descricao,"descriçao")
        binding.txtDescription.text = descricao?.let {//recupera texto da descrição
            Editable.Factory.getInstance().newEditable(it)
        } ?: Editable.Factory.getInstance().newEditable("")

       // setupContadorCaracteres()

        binding.btConfirm.setOnClickListener {
            if (carrinho.isEmpty()) {
                // Adicionar o produto diretamente ao carrinho se estiver vazio
                val produto = Produto(imgProduto, name ?: "", newPrice, "", descricao ?: "")
                carrinho.add(produto)

                // Salvar a lista atualizada no SharedPreferences
                PrefConfig.writeListInPref(this, carrinho)

                // Criar o fragmento do carrinho e passar a lista de produtos para ele
                val intent = Intent(this, CarrinhoActivity::class.java)
                val bundle = Bundle().apply {
                    putParcelableArrayList("carrinho", ArrayList(carrinho))
                    intent.putExtra("observacao", binding.editTextObs.text.toString())
                }
                intent.putExtras(bundle)
                startActivity(intent)

                onItemAdd()
                finish()
            } else {
                // Verificar se o produto já está presente no carrinho
                val isItemAlreadyInCart = carrinho.any { it.name == name && it.price == newPrice }

                if (!isItemAlreadyInCart) {
                    // Adicionar o produto ao carrinho apenas se ele não estiver presente
                    val produto = Produto(imgProduto, name ?: "", newPrice, "", descricao ?: "")
                    carrinho.add(produto)

                    // Salvar a lista atualizada no SharedPreferences
                    PrefConfig.writeListInPref(this, carrinho)

                    // Criar o fragmento do carrinho e passar a lista de produtos para ele
                    val intent = Intent(this, CarrinhoActivity::class.java)
                    val bundle = Bundle().apply {
                        putParcelableArrayList("carrinho", ArrayList(carrinho))
                        intent.putExtra("observacao", binding.editTextObs.text.toString())
                    }
                    intent.putExtras(bundle)
                    startActivity(intent)
                    finish()

                    onItemAdd()
                } else {
                    // Mostrar uma mensagem informando que o produto já está no carrinho
                    Toast.makeText(this, "Este produto já está no carrinho", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

  /*  private fun setupContadorCaracteres() {
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Antes de alterar o texto
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Durante a alteração do texto, atualize o contador de caracteres
                val numCaracteres = s?.length ?: 0
                val contadorTexto = "$numCaracteres/140"
                binding.contLetras.text = contadorTexto
            }

            override fun afterTextChanged(s: Editable?) {
                // Após a alteração do texto
            }
        }

        binding.editTextObs.addTextChangedListener(textWatcher)
    }*/

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu
        menuInflater.inflate(com.example.pizzaria.R.menu.menu, menu)
        this.menu = menu

        // Atualizar o texto do contador do carrinho no ícone do menu
        updateCartItemCount(CarrinhoManager.getItemCountInCart())

        val cartMenuItem = menu?.findItem(com.example.pizzaria.R.id.badge)

        // Definir o listener de clique para o ícone do carrinho
        cartMenuItem?.actionView?.setOnClickListener {
            // Abrir a CarrinhoActivity quando o ícone do carrinho for clicado
            val intent = Intent(this, CarrinhoActivity::class.java)
            startActivity(intent)
        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            com.example.pizzaria.R.id.badge -> {

                // Item de menu "badge" foi clicado, abrir a CarrinhoActivity
                val intent = Intent(this, CarrinhoActivity::class.java)
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

    // Incrementa o contador do carrinho e atualiza menu
    override fun onItemAdd() {
        // Incrementa o contador do carrinho
        CarrinhoManager.incrementItemCountInCart()
        // Atualiza o ícone do menu
        invalidateOptionsMenu()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}