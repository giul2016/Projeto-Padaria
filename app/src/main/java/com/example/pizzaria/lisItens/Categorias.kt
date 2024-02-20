package com.example.pizzaria.lisItens

import com.example.pizzaria.R
import com.example.pizzaria.model.Categoria
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class Categorias {
    private val _categoriaList = MutableStateFlow<MutableList<Categoria>>(mutableListOf())
    private val categoriatListFlow: StateFlow<MutableList<Categoria>> = _categoriaList

    fun getCategoria(): Flow<MutableList<Categoria>> {
        val categoriaList: MutableList<Categoria> = mutableListOf(

            Categoria(
                imgResource = R.drawable.pizzas3,
                name = "Pizza"

            ),
            Categoria(
                imgResource = R.drawable.esfihas2,
                name = "Esfiha"


            ),
            Categoria(
                imgResource = R.drawable.lanches9,
                name = "Lanches"

            ),
            Categoria(
                imgResource = R.drawable.salgados4,
                name = "Salgados"

            ),
            Categoria(
                imgResource = R.drawable.bebidas6,
                name = "Bebidas"

            ),
            Categoria(
                imgResource = R.drawable.doces8,
                name = "Doces"


            ),
            Categoria(
                imgResource = R.drawable.paes1,
                name = "Pães"

            ),
            Categoria(
                imgResource = R.drawable.assados7,
                name = "Assados"

            ),
            Categoria(
                imgResource = R.drawable.sorvetes10,
                name = "Sorvetes"

            ),
            Categoria(
                imgResource = R.drawable.frios5,
                name = "Frios"

            )


        )
        _categoriaList.value = categoriaList
        return categoriatListFlow
    }
}
