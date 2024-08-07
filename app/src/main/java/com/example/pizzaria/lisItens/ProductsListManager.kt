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
    private val _sorveteList = MutableStateFlow<MutableList<Produto>>(mutableListOf())
    private val _docesList = MutableStateFlow<MutableList<Produto>>(mutableListOf())

    val pizzaListFlow: StateFlow<MutableList<Produto>> = _pizzaList
    val assadosListFlow: StateFlow<MutableList<Produto>> = _assadosList
    val kebabListFlow: StateFlow<MutableList<Produto>> = _kebabList
    val otherListFlow: StateFlow<MutableList<Produto>> = _otherList
    val lancheListFlow: StateFlow<MutableList<Produto>> = _lancheList
    val paesaListFlow: StateFlow<MutableList<Produto>> = _paesList
    val esfihaListFlow: StateFlow<MutableList<Produto>> = _esfihaList
    val salgadoListFlow: StateFlow<MutableList<Produto>> = _salgadoList
    val friosListFlow: StateFlow<MutableList<Produto>> =_friosList
    val sorvetesListFlow: StateFlow<MutableList<Produto>> =_sorveteList
    val docesListFlow: StateFlow<MutableList<Produto>> =_docesList


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
        _sorveteList.value=createSorveteList()
        _docesList.value=createDocesList()
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
            Produto(R.drawable.reccoca, "Coca-Cola", 10.00, "Bebidas","Refrigerante coca cola 2 litros"),
            Produto(R.drawable.recsprit, "Sprit", 8.00, "Bebidas","Refrigerante sprit 2 litros"),
            Produto(R.drawable.recskol, "Cerveja Skoll", 10.00, "Bebidas ","Cerveja Skoll litrão"),
            Produto(R.drawable.recbud, "Cervela Bud", 8.00, "Bebidas","Cerveja bud long neck"),
            Produto(R.drawable.recabs, "Vodka Absolut", 10.00, "Bebidas","Vodka Absolut"),
            Produto(R.drawable.recsmir, "Vodka Smirnoff", 8.00, "Bebidas","Vodka Smirnoff"),
            Produto(R.drawable.recgato, "Gatorade", 10.00, "Bebidas","Energético gatorade"),
            Produto(R.drawable.recred, "Red Bull", 8.00, "Bebidas","Energético red bull"),
            Produto(R.drawable.reclabelred, "Whisky Red", 8.00, "Bebidas","Whisky Red Label"),
            Produto(R.drawable.reclabelblack, "Whisky Black", 8.00, "Bebidas","Whisky Black Label"),
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
            Produto(R.drawable.saldadocoxinha, "Coxinha", 5.00, "Salgados","Coxinha de frango"),
            Produto(R.drawable.saldadopastel, "Pastel", 90.00, "Salgados","Pastel de carne tradicional"),
            Produto(R.drawable.saldadobolinhosalcicha, "Bolinho de salcicha", 5.00, "Salgados","Salcicha em massa de coxinha"),
            Produto(R.drawable.saldadobolinhocarne, "Torta", 90.00, "Salgados","Torta de frango com massa de empada "),
            Produto(R.drawable.saldadobolinhocarne, "Bolinho de carne", 5.00, "Salgados","Bolinho de carne tradicional"),
            Produto(R.drawable.saldadokibe, "Salgadinho de Kibe", 90.00, "Salgados","Salgado de kibe com carne"),
            Produto(R.drawable.saldadoempada, "Empada", 90.00, "Salgados","Tradicional empada de palmito"),
            Produto(R.drawable.saldadorisoli, "Risoli de queijo", 90.00, "Salgados","Risoli recheado com queijo"),
            Produto(R.drawable.saldadopastelforno, "Pastel de forno", 90.00, "Salgados","Pastel de forno com recheio especial"),
            Produto(R.drawable.saldadocalabresa, "Empanado de calabresa", 90.00, "Salgados","Pedaços de calabresa empanados"),
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
            Produto(R.drawable.fatiarmortasimples, "Mortadela", 45.00, "Frios","Mortadela fatiada"),
            Produto(R.drawable.fatiarmussarela, "Mussarela", 40.00, "Frios","Mussarela fatiada"),
            Produto(R.drawable.fatiarpresunto, "Presunto", 55.00, "Frios","Presunto fatiado"),
            Produto(R.drawable.fatiarapresuntado, "Apresuntado", 60.00, "Frios","Apresuntado fatiado"),
            Produto(R.drawable.fatiarbranco, "Queijo Branco", 25.00, "Frios","Queijo branco fatiado"),
            Produto(R.drawable.fatiarqueijo, "Queijo prato", 90.00, "Frios","Queijo prato fatiado"),
            Produto(R.drawable.fatiarrosbife, "Rosbife", 45.00, "Frios","Rosbife fatiado)"),
            Produto(R.drawable.fatiarsalame, "Salame", 50.00, "Frios","Salame fatiado"),
            Produto(R.drawable.fatiarmortadela, "Mortadela especial", 35.00, "Frios","Mortadela especial temperada"),
            Produto(R.drawable.fatiarprovolone, "Provolone", 55.00, "Frios","Queijo provolone fatiado")
            //
        )
    }
    private fun createSorveteList(): MutableList<Produto> {
        // Adicione produtos Pizza conforme necessário
        return mutableListOf(
            Produto(R.drawable.sorv_pal_morango_1, "Morango", 5.00, "Sorvetes","Sorvete no palito sabor morango"),
            Produto(R.drawable.sorv_pal_especial_2, "Especial", 10.00, "Sorvetes","Sorvete de sabores especiais no palito"),
            Produto(R.drawable.sorv_pal_maracuja_3, "Maracujá", 5.00, "Sorvetes","Sorvete no palito sabor maracujá"),
            Produto(R.drawable.sorv_pal_limao_4, "Limão", 5.00, "Sorvetes","Sorvete no palito sabor limão"),
            Produto(R.drawable.sorv_pal_abacaxi_5, "Abacaxí", 5.00, "Sorvetes","Sorvete no palito sabor abacaxi"),
            Produto(R.drawable.sorv_pot_morango_6, "Massa de Morango", 20.00, "Sorvetes","Sorvete de massa sabor morango"),
            Produto(R.drawable.sorv_pot_napolitano_7, "Massa Napolitano", 25.00, "Sorvetes","Sorvete de massa sabor napolitano"),
            Produto(R.drawable.sorv_copo_passas_8, "Passas ao Rum", 20.00, "Sorvetes","Sorvete de massa sabor passas ao rum"),
            Produto(R.drawable.sorv_pot_choco_9, "Chocolate Massa", 25.00, "Sorvetes","Sorvete de massa sabor chocolate"),
            Produto(R.drawable.sorv_pot_coco_10, "Côco Massa", 25.00, "Sorvetes","Sorvete de massa sabor côco")
            //
        )
    }
    private fun createDocesList(): MutableList<Produto> {
        // Adicione produtos Pizza conforme necessário
        return mutableListOf(
            Produto(R.drawable.doce_donut_1, "Donut", 8.00, "Doces","Donut coberto de chocolate com recheios especiais"),
            Produto(R.drawable.doce_torta_morango_2, "Torta de Morango", 18.00, "Doces","Deliciosa torta com recheio de geléia de morangos"),
            Produto(R.drawable.doce_sonho_3, "Sonho", 5.00, "Doces","Delicioso somho açucarado com recheio de doce de leite ou creme"),
            Produto(R.drawable.doce_cupcake_4, "CupCake", 9.00, "Doces","CapCake com chamtily e recheio de morango"),
            Produto(R.drawable.doce_bomba_5, "Bomba de Chocolate", 10.00, "Doces","Delicioso doce trufado de chocolate"),
            Produto(R.drawable.doce_bolo_aniver_6, "Bolo  De Aniversário", 20.00, "Doces","Bolo de diversos sabores recheado e com cobertura"),
            Produto(R.drawable.doce_pudim_7, "Pudim", 25.00, "Doces","Pudim de leite condensado"),
            Produto(R.drawable.doce_brigadeiro_8, "Brigadeiro", 3.00, "Doces","Tradicional brigadeiro de chocolate"),
            Produto(R.drawable.doce_mousse_9, "Mousse de Maracujá", 25.00, "Doces","Tradicional Mousse de Maracujá"),
            Produto(R.drawable.doce_rocambole_10, "Rocambole", 15.00, "Doces","Delicioso rocambole recheado com doce de leite")
            //
        )
    }

    fun getAllProducts(): MutableList<Categoria> {

        return mutableListOf(
            Categoria("Esfihas",R.drawable.categoria1),
            Categoria("Pizzas",R.drawable.categoria2),
            Categoria("Lanches",R.drawable.categoria3),
            Categoria("Salgados",R.drawable.categoria4),
            Categoria("Bebidas",R.drawable.categoria5),
            Categoria("Doces",R.drawable.categoria6),
            Categoria("Pães",R.drawable.categoria7),
            Categoria("Assados",R.drawable.categoria8),
            Categoria("Sorvetes",R.drawable.categoria9),
            Categoria("Frios",R.drawable.categoria10)
        )
    }
}
