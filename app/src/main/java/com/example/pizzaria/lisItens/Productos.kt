//package com.example.pizzaria.lisItens
package com.example.pizzaria.lisItens

import com.example.pizzaria.R
import com.example.pizzaria.model.Produto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

//class Productos {
//
//    private val _productList= MutableStateFlow<MutableList<Produto>>(mutableListOf())
//    private val productListFlow:StateFlow<MutableList<Produto>> = _productList
//
//    fun getProducts():Flow<MutableList<Produto>>{
//        val productList:MutableList<Produto> = mutableListOf(
//            Produto(
//                imgProduct = R.drawable.piza1,
//                name = "Peperone",
//                price = 5.00,
//                category = "Pizza",
//
//            ),
//            Produto(
//                imgProduct = R.drawable.piza2,
//                name = "A Moda",
//                price = 90.00,
//                category = "Pizza"
//
//
//            ),
//            Produto(
//                imgProduct = R.drawable.piza3,
//                name = "Marguerita",
//                price = 50.00,
//                category = "Pizza"
//
//            ),
//            Produto(
//                imgProduct = R.drawable.piza4,
//                name = "4 Queijos",
//                price = 45.00,
//                category = "Pizza"
//
//
//            ),
//            Produto(
//                imgProduct = R.drawable.piza5,
//                name = "Mussarela",
//                price =56.00,
//                category = "Pizza"
//
//            ),
//            Produto(
//                imgProduct = R.drawable.piza6,
//                name = "Portuhuesa",
//                price = 56.00,
//                category = "Pizza"
//
//            ),
//            Produto(
//                imgProduct = R.drawable.piza7,
//                name = "Aliche",
//                price =56.00,
//                category = "Pizza"
//            ),
//            Produto(
//                imgProduct = R.drawable.piza8,
//                name = "Catufrango",
//                price =56.00,
//                category = "Pizza"
//
//            ),
//            Produto(
//                imgProduct = R.drawable.piza9,
//                name = "Calabresa",
//                price =56.00,
//                category = "Pizza"
//
//            ),
//            Produto(
//                imgProduct = R.drawable.piza10,
//                name = "Lombo",
//                price =56.00,
//                category = "Pizza"
//
//            )
//        )
//        _productList.value=productList
//        return productListFlow
//    }
//}


//
//import com.example.pizzaria.R
//import com.example.pizzaria.model.Produto
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.StateFlow
//
//class Productos {
//
//    private val _productList= MutableStateFlow<MutableList<Produto>>(mutableListOf())
//    private val productListFlow:StateFlow<MutableList<Produto>> = _productList
//
//    fun getProducts():Flow<MutableList<Produto>>{
//     val productList:MutableList<Produto> = mutableListOf(
//         Produto(
//             imgProduct = R.drawable.pizza1,
//             name = "Pizza a moda da casa",
//             price = 5.00
//         ),
//         Produto(
//             imgProduct = R.drawable.pizza2,
//             name = "Mussarela",
//             price = 90.00
//
//
//         ),
//         Produto(
//             imgProduct = R.drawable.pizza3,
//             name = "Calabresa",
//             price = 50.00
//
//         ),
//         Produto(
//             imgProduct = R.drawable.pizza4,
//             name = "Catupiry com frango",
//             price = 45.00
//
//
//         ),
//         Produto(
//             imgProduct = R.drawable.pizza1,
//             name = "Pizza a moda da casa",
//             price =56.00
//
//         ),
//         Produto(
//             imgProduct = R.drawable.pizza2,
//             name = "Mussarela",
//             price = 56.00
//
//         ),
//         Produto(
//             imgProduct = R.drawable.pizza3,
//             name = "Calabresa",
//             price =56.00
//         ),
//         Produto(
//             imgProduct = R.drawable.pizza4,
//             name = "Catupiry com frango",
//             price =56.00
//
//         )
//     )
//        _productList.value=productList
//        return productListFlow
//    }
//}