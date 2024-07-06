package com.example.pizzaria

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.NavDestination
import com.example.pizzaria.databinding.ActivityMenuBinding
import com.example.pizzaria.ui.CarrinhoActivity

class Menu : AppCompatActivity(), OnItemAddedListener {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMenuBinding

    private var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CarrinhoManager.init(this)

        // Observar o LiveData e atualizar o contador do carrinho
        CarrinhoManager.itemCountInCart.observe(this, Observer { count ->
            // Atualizar o texto do contador do carrinho no ícone do menu
            updateCartItemCount(count)
        })

        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMenu.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_menu)

        binding.navView.setNavigationItemSelectedListener { menuItem ->
            // Obtenha o NavController
            val navController = findNavController(R.id.nav_host_fragment_content_menu)

            // Obtenha todos os destinos do gráfico de navegação
            val graph = navController.graph
            val startDestination = graph.findNode(graph.startDestinationId) as NavDestination

            // Limpe a pilha de retrocesso antes de abrir o novo fragmento
            navController.popBackStack(startDestination.id, false)

            // Navegue para o destino correspondente ao item do menu clicado
            navController.navigate(menuItem.itemId)

            // Feche o drawer (menu lateral) se estiver aberto
            binding.drawerLayout.closeDrawers()

            true
        }



        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home,
                R.id.nav_gallery,
                R.id.nav_pedido_confirmados,
                R.id.nav_slideshow,
                R.id.nav_produtos,
                R.id.nav_contato
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu
        menuInflater.inflate(com.example.pizzaria.R.menu.menu, menu)
        this.menu = menu

        // Atualizar o texto do contador do carrinho no ícone do menu
        updateCartItemCount(CarrinhoManager.getItemCountInCart())

        val cartMenuItem = menu?.findItem(R.id.badge)

        // Definir o listener de clique para o ícone do carrinho
        cartMenuItem?.actionView?.setOnClickListener {
            // Abrir a CarrinhoActivity quando o ícone do carrinho for clicado
            val intent = Intent(this@Menu, CarrinhoActivity::class.java)
            startActivity(intent)
        }

        return true
    }

    private fun updateCartItemCount(count: Int) {
        val badgeItem = menu?.findItem(com.example.pizzaria.R.id.badge)
        badgeItem?.actionView?.findViewById<TextView>(com.example.pizzaria.R.id.actionbar_notifcation_textview)?.text =
            count.toString()
    }

    override fun onItemAdd() {
        // Incrementa o contador do carrinho no icone
        CarrinhoManager.incrementItemCountInCart()

        // Atualiza o ícone do menu
        invalidateOptionsMenu()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_menu)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}