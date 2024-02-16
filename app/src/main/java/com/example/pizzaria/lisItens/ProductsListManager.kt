package com.example.pizzaria.lisItens


import com.example.pizzaria.R
import com.example.pizzaria.model.Categoria
import com.example.pizzaria.model.Produto

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ProductsListManager {

    private val _pizzaList = MutableStateFlow<MutableList<Produto>>(mutableListOf())
    private val _assadosList = MutableStateFlow<MutableList<Produto>>(mutableListOf())
    private val _kebabList = MutableStateFlow<MutableList<Produto>>(mutableListOf())
    private val _otherList = MutableStateFlow<MutableList<Produto>>(mutableListOf())
    private val _lancheList = MutableStateFlow<MutableList<Produto>>(mutableListOf())
    private val _paesList = MutableStateFlow<MutableList<Produto>>(mutableListOf())
    private val _esfihaList = MutableStateFlow<MutableList<Produto>>(mutableListOf())
    private val _salgadoList = MutableStateFlow<MutableList<Produto>>(mutableListOf())
    private val _friosList = MutableStateFlow<MutableList<Produto>>(mutableListOf())

    val pizzaListFlow: StateFlow<MutableList<Produto>> = _pizzaList
    val assadosListFlow: StateFlow<MutableList<Produto>> = _assadosList
    val kebabListFlow: StateFlow<MutableList<Produto>> = _kebabList
    val otherListFlow: StateFlow<MutableList<Produto>> = _otherList
    val lancheListFlow: StateFlow<MutableList<Produto>> = _lancheList
    val paesaListFlow: StateFlow<MutableList<Produto>> = _paesList
    val esfihaListFlow: StateFlow<MutableList<Produto>> = _esfihaList
    val salgadoListFlow: StateFlow<MutableList<Produto>> = _salgadoList
    val friosListFlow: StateFlow<MutableList<Produto>> =_friosList


    fun initializeLists() {
        _pizzaList.value = createPizzaList()
        _assadosList.value = createAssadosList()
        _kebabList.value = createKebabList()
        _otherList.value = createOtherList()
        _lancheList.value=createLancheList()
        _esfihaList.value=createEsfihaList()
        _paesList.value=createPaesList()
        _salgadoList.value=createSalgadoList()
        _friosList.value=createFriosList()
    }

    private fun createPizzaList(): MutableList<Produto> {
        // Adicione produtos Pizza conforme necessário
        return mutableListOf(
            Produto(R.drawable.piza1, "Peperone", 45.00, "Pizza","Pepperoni – mussarela, pepperoni e cebola"),
            Produto(R.drawable.piza2, "Moda casa", 40.00, "Pizza","Calabresa – mussarela, pepperoni e tomates"),
            Produto(R.drawable.piza3, "Marguerita", 55.00, "Pizza","mussarela, rodelas de tomate e manjericão"),
            Produto(R.drawable.piza4, "4 Queijod", 60.00, "Pizza","mussarela, provolone, parmesão e catupiry"),
            Produto(R.drawable.piza5, "Mussarela", 25.00, "Pizza","mussarela, rodelas de tomate e orégano"),
            Produto(R.drawable.piza6, "Portuguesa", 90.00, "Pizza","mussarela, ovos, palmito, pimentão, ervilha, presunto e cebola"),
            Produto(R.drawable.piza7, "Aliche", 45.00, "Pizza","mussarela, aliche e tomates)"),
            Produto(R.drawable.piza8, "Catufrango", 50.00, "Pizza","molho de tomate, mussarela, frango e catupiry"),
            Produto(R.drawable.piza9, "Calabresa", 35.00, "Pizza","Calabres e cebola"),
            Produto(R.drawable.piza10, "Lombo", 55.00, "Pizza","mussarela, lombo defumado e cebola")
            //
        )
    }


    private fun createAssadosList(): MutableList<Produto> {
        // Adicione produtos Chicken conforme necessário
        return mutableListOf(
            Produto(R.drawable.assarfrango, "Frango assado", 10.00, "Assados","Frango assado"),
            Produto(R.drawable.assarbatata, "Batata assada", 8.00, "Assados","Batata assada"),
            Produto(R.drawable.assarcupim, "Cupim assado", 10.00, "Assados ","Cupim assado"),
            Produto(R.drawable.assarcostela, "Costela assada", 8.00, "Assados","Costela assada"),
            Produto(R.drawable.assarlingui, "Linguiça assada", 10.00, "Assados","Linguiça assada"),
            Produto(R.drawable.assararroz, "Arroz a grega", 8.00, "Assados","Arroz a grega"),
            Produto(R.drawable.assarfarofa, "Farofa gourmeet", 10.00, "Assados","Farofa especialmente temperada"),
            Produto(R.drawable.assarvinagre, "Vinagrete", 8.00, "Assados","Salada de tomates, cebolas, salcinha e temperos"),
            Produto(R.drawable.assarpao, "Pão de alho", 8.00, "Assados","Pão com creme de alho assado"),
            Produto(R.drawable.assarmaionese, "Maionese gourmet", 8.00, "Assados","Maionese com especiarias"),
            // ...
        )
    }

    private fun createKebabList(): MutableList<Produto> {
        // Adicione produtos Kebab conforme necessário
        return mutableListOf(
            Produto(R.drawable.esfihaqueijo, "Esfiha de queijo", 10.00, "Esfihas","Esfiha com queijo"),
            Produto(R.drawable.esfihacatupiry, "Esfiha de catupiry", 8.00, "Esfihas","Esfiha com queijo catupiry"),
            Produto(R.drawable.esfihamilhocatu, "Milho catupiry", 10.00, "Esfiha ","Esfiha com queijo catupiry e milho"),
            Produto(R.drawable.esfihafrango, "Esfiha de frango", 8.00, "Esfihas","Esfiha com frango desfiado"),
            Produto(R.drawable.esfihamoda, "Esfiha a moda", 10.00, "Esfihas","Esfiha de mussarela com bacom"),
            Produto(R.drawable.esfihamoranchoco, "Choco Morango", 8.00, "Esfihas","Esfiha de chocolate com morango"),
            Produto(R.drawable.esfihalombo, "Esfiha de lombo", 10.00, "Esfihas","Esfiha de lombo"),
            Produto(R.drawable.esfihalomboqueijo, "Lombo com queijo", 8.00, "Esfihas","Esfiha de Lombo com queijo mussarela"),
            Produto(R.drawable.esfihapalmito, "Palmito", 8.00, "Esfihas","Esfiha de palmito com queijo mussarela"),
            Produto(R.drawable.esfihsvegetariana, "Vegetariana", 8.00, "Esfihas","Esfiha de brócolis com queijo ricota"),
            // ...
        )
    }
    private fun createLancheList(): MutableList<Produto> {
        // Adicione produtos Pizza conforme necessário
        return mutableListOf(
            Produto(R.drawable.lanche1, "Hamburguer", 5.00, "Lanche","Pão de hambuyguer e hamburguer de carne 200g"),
            Produto(R.drawable.lanche2, "X-Salada", 90.00, "Lanche","Pão de hambuyguer, hamburguer de carne 200g, queijo, alface, tomate, cebola"),
            Produto(R.drawable.lanche3, "X-Burguer", 5.00, "Lanche","Pão de hambuyguer, hamburguer de carne 200g, queijo prato"),
            Produto(R.drawable.lanche4, "X-Bacon", 90.00, "Lanche","Pão de hambuyguer, hamburguer de carne 200g, baycon e queijo mussarela"),
            Produto(R.drawable.lanche5, "X-Egg", 5.00, "Lanche","Pão de hambuyguer, hamburguer de carne 200g, ovo frito e queijo mussarela"),
            Produto(R.drawable.lanche6, "X-Tudo", 90.00, "Lanche","Pão de hambuyguer, hamburguer de carne 200g, queijo, alface, tomate, cebola, bacon e ovo"),
            Produto(R.drawable.lanche7, "X-Bife", 90.00, "Lanche","Bife acebolado no pão francês"),
            Produto(R.drawable.lanche8, "Misto", 90.00, "Lanche","Pão quente na chapa com presunto e queijo"),
            Produto(R.drawable.lanche9, "Bauru", 90.00, "Lanche","Presunto queijo e tomate no pão frances"),
            Produto(R.drawable.lanche10, "File frango", 90.00, "Lanche","Filé de frando com maionese no pão francês"),
            // ...
        )
    }
    private fun createSalgadoList(): MutableList<Produto> {
        // Adicione produtos Pizza conforme necessário
        return mutableListOf(
            Produto(R.drawable.salgacoxinha7, "Coxinha", 5.00, "Salgados","Coxinha de frango"),
            Produto(R.drawable.salgadopastel1, "Pastel", 90.00, "Salgados","Pastel de carne tradicional"),
            Produto(R.drawable.salgadosacicha3, "Bolinho de salcicha", 5.00, "Salgados","Salcicha em massa de coxinha"),
            Produto(R.drawable.salgacoxinha7, "Torta", 90.00, "Salgados","Torta de frango com massa de empada "),
            Produto(R.drawable.salgbolcarne6, "Bolinho de carne", 5.00, "Salgados","Bolinho de carne tradicional"),
            Produto(R.drawable.salgarisfrango9, "Salgadinho de frango", 90.00, "Salgados","Salgado de frango desfiado"),
            Produto(R.drawable.salgempada8, "Empada", 90.00, "Salgados","Tradicional empada de palmito"),
            Produto(R.drawable.salgpaoqueijo10, "Risoli de queijo", 90.00, "Salgados","Risoli recheado com queijo"),
            Produto(R.drawable.salgpasfor4, "Pastel de forno", 90.00, "Salgados","Pastel de forno com recheio especial"),
            Produto(R.drawable.salgbolsal5, "Empanado de calabresa", 90.00, "Salgados","Pedaços de calabresa empanados"),
            // ...
        )
    }

    private fun createPaesList(): MutableList<Produto> {
        // Adicione produtos Pizza conforme necessário
        return mutableListOf(
            Produto(R.drawable.paofrances, "Pão francês", 5.00, "Pães","Pão francês tradicional"),
            Produto(R.drawable.paovcarteira, "Baguete", 90.00, "Pães","Pão do tipo baguetinho"),
            Produto(R.drawable.paofrios, "Pão de frios", 5.00, "Pães","Pão recheado de frios"),
            Produto(R.drawable.paobengala, "Bengala", 90.00, "Pães","Pão de bengala"),
            Produto(R.drawable.paocaseiro, "Pão caseiro", 5.00, "Pães","Pão de forma do tipo caseiro"),
            Produto(R.drawable.paointegral, "Pão Integral", 90.00, "Pães","Pão integral de forma"),
            Produto(R.drawable.paobanha, "Pão de banha", 90.00, "Pães","Pão de banha macio"),
            Produto(R.drawable.paointegral, "Pão integral", 90.00, "Pães","Pão integral sem glútem"),
            Produto(R.drawable.paoitaliano, "Pão italiano", 90.00, "Pães","Pão do tipo italiano casca grossa"),
            Produto(R.drawable.paomilho, "Pão de milho", 90.00, "Pães","Pão de milho"),
            // ...


            //
            Produto(R.drawable.paofrances, "Pão francês", 5.00, "Pães","Pão francês tradicional"),
            Produto(R.drawable.paovcarteira, "Baguete", 90.00, "Pães","Pão do tipo baguetinho"),
            Produto(R.drawable.paofrios, "Pão de frios", 5.00, "Pães","Pão recheado de frios"),
            Produto(R.drawable.paobengala, "Bengala", 90.00, "Pães","Pão de bengala"),
            Produto(R.drawable.paocaseiro, "Pão caseiro", 5.00, "Pães","Pão de forma do tipo caseiro"),
            Produto(R.drawable.paointegral, "Pão Integral", 90.00, "Pães","Pão integral de forma"),
            Produto(R.drawable.paobanha, "Pão de banha", 90.00, "Pães","Pão de banha macio"),
            Produto(R.drawable.paointegral, "Pão integral", 90.00, "Pães","Pão integral sem glútem"),
            Produto(R.drawable.paoitaliano, "Pão italiano", 90.00, "Pães","Pão do tipo italiano casca grossa"),
            Produto(R.drawable.paomilho, "Pão de milho", 90.00, "Pães","Pão de milho"),
            //
        )
    }
    private fun createOtherList(): MutableList<Produto> {
        // Adicione produtos Other conforme necessário
        return mutableListOf(
            Produto(R.drawable.esf2, "Esfiha de queijo", 10.00, "Esfihas","Esfiha com queijo"),
            Produto(R.drawable.esf6, "Esfiha de catupiry", 8.00, "Esfihas","Esfiha com queijo catupiry"),
            Produto(R.drawable.esf5, "Milho catupiry", 10.00, "Esfiha ","Esfiha com queijo catupiry e milho"),
            Produto(R.drawable.esf7, "Esfiha de frango", 8.00, "Esfihas","Esfiha com frango desfiado"),
            Produto(R.drawable.esf3, "Esfiha de Lombo", 10.00, "Esfihas","Esfiha de lombo"),
            Produto(R.drawable.esf6, "Choco Morango", 8.00, "Esfihas","Esfiha de chocolate com morango"),
            Produto(R.drawable.esf4, "Esfiha de Calabresa", 10.00, "Esfihas","Esfiha de Calabresa"),
            Produto(R.drawable.esf8, "Atum", 8.00, "Esfihas","Esfiha de atum com cebola"),
            Produto(R.drawable.esf9, "BananaChoco", 8.00, "Esfihas","Esfiha de banana com chocolate"),
            Produto(R.drawable.esf1, "Queijo ricota", 8.00, "Esfihas","Esfiha de queijo ricota"),
            // ...
        )
    }
    private fun createEsfihaList(): MutableList<Produto> {
        // Adicione produtos Other conforme necessário
        return mutableListOf(
            Produto(R.drawable.esf2, "Esfiha de queijo", 10.00, "Esfihas","Esfiha com queijo"),
            Produto(R.drawable.esf6, "Esfiha de catupiry", 8.00, "Esfihas","Esfiha com queijo catupiry"),
            Produto(R.drawable.esf5, "Milho catupiry", 10.00, "Esfiha ","Esfiha com queijo catupiry e milho"),
            Produto(R.drawable.esf7, "Esfiha de frango", 8.00, "Esfihas","Esfiha com frango desfiado"),
            Produto(R.drawable.esf3, "Esfiha de Lombo", 10.00, "Esfihas","Esfiha de lombo"),
            Produto(R.drawable.esf6, "Choco Morango", 8.00, "Esfihas","Esfiha de chocolate com morango"),
            Produto(R.drawable.esf4, "Esfiha de Calabresa", 10.00, "Esfihas","Esfiha de Calabresa"),
            Produto(R.drawable.esf8, "Atum", 8.00, "Esfihas","Esfiha de atum com cebola"),
            Produto(R.drawable.esf9, "BananaChoco", 8.00, "Esfihas","Esfiha de banana com chocolate"),
            Produto(R.drawable.esf1, "Queijo ricota", 8.00, "Esfihas","Esfiha de queijo ricota"),
            // ...
        )
    }
    private fun createFriosList(): MutableList<Produto> {
        // Adicione produtos Pizza conforme necessário
        return mutableListOf(
            Produto(R.drawable.friosmrtadela, "Mortadela", 45.00, "Frios","Mortadela fatiada"),
            Produto(R.drawable.friosmussarela, "Mussarela", 40.00, "Frios","Mussarela fatiada"),
            Produto(R.drawable.friospresunto, "Presunto", 55.00, "Frios","Presunto fatiado"),
            Produto(R.drawable.friosapresuntado, "Apresuntado", 60.00, "Frios","Apresuntado fatiado"),
            Produto(R.drawable.frioslombo, "Lombo", 25.00, "Frios","Lombo fatiado"),
            Produto(R.drawable.fiosprato, "Queijo prato", 90.00, "Frios","Queijo prato fatiado"),
            Produto(R.drawable.friosrosbife, "Rosbife", 45.00, "Frios","Rosbife fatiado)"),
            Produto(R.drawable.friossalame, "Salame", 50.00, "Frios","Salame fatiado"),
            Produto(R.drawable.friostemperada, "Calabresa", 35.00, "Frios","Calabres e cebola"),
            Produto(R.drawable.friosprovolone, "Provolone", 55.00, "Frios","Queijo provolone fatiado")
            //
        )
    }

    fun getAllProducts(): MutableList<Categoria> {

        return mutableListOf(
            Categoria("Pizzas",R.drawable.categoria2),
            Categoria("Esfihas",R.drawable.categoria1),
            Categoria("Pães",R.drawable.categoria7),
            Categoria("Lanches",R.drawable.categoria3),
            Categoria("Salgados",R.drawable.categoria4),
            Categoria("Bebidas",R.drawable.categoria5),
            Categoria("Doces",R.drawable.categoria6),
            Categoria("Assados",R.drawable.categoria8),
            Categoria("Sorvetes",R.drawable.categoria9),
            Categoria("Frios",R.drawable.categoria10),
        )
    }
}
