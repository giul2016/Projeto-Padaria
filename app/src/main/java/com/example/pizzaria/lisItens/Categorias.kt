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
                imgResource = R.drawable.categoria2,
                name = "Pizza"

            ),
            Categoria(
                imgResource = R.drawable.categoria1,
                name = "Esfiha"


            ),
            Categoria(
                imgResource = R.drawable.categoria3,
                name = "Lanches"

            ),
            Categoria(
                imgResource = R.drawable.categoria4,
                name = "Salgados"

            ),
            Categoria(
                imgResource = R.drawable.categoria5,
                name = "Bebidas"

            ),
            Categoria(
                imgResource = R.drawable.categoria6,
                name = "Doces"


            ),
            Categoria(
                imgResource = R.drawable.categoria7,
                name = "Pães"

            ),
            Categoria(
                imgResource = R.drawable.categoria8,
                name = "Assados"

            ),
            Categoria(
                imgResource = R.drawable.categoria9,
                name = "Sorvetes"

            ),
            Categoria(
                imgResource = R.drawable.categoria10,
                name = "Frios"

            )


        )
        _categoriaList.value = categoriaList
        return categoriatListFlow
    }
}
