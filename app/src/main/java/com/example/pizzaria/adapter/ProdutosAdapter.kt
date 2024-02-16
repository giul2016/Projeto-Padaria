package com.example.pizzaria.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pizzaria.CategoriaClickListener
import com.example.pizzaria.ProductDetails
import com.example.pizzaria.databinding.CategoriaItemBinding
import com.example.pizzaria.databinding.ProductItemBinding
import com.example.pizzaria.model.Categoria
import com.example.pizzaria.model.Produto
import java.text.NumberFormat
import java.util.Locale

class ProdutosAdapter(private val context: Context,
                      val itemList: MutableList<Item>,
                      private val categoriaClickListener: CategoriaClickListener
):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    enum class ItemType {
        CATEGORIA,
        PRODUTO
    }

    data class Item(val type: ItemType, val data: Any)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ItemType.CATEGORIA.ordinal -> {
                // Inflar o layout para a categoria
                val categoryItemBinding = CategoriaItemBinding.inflate(LayoutInflater.from(context), parent, false)
                return CategoryViewHolder(categoryItemBinding)
            }
            ItemType.PRODUTO.ordinal -> {
                // Inflar o layout para o produto
                // Retornar o ViewHolder para o produto
                val listItem = ProductItemBinding.inflate(LayoutInflater.from(context), parent, false)
                return ProductViewHolder(listItem)
            }
            else -> throw IllegalArgumentException("Tipo de item desconhecido")
        }
    }

    fun updateData(newList: MutableList<Produto>) {
        val items = newList.map { Item(ItemType.PRODUTO, it) }
        updateDataWithItems(items)
    }


    fun updateDataP(newList: List<Item>) {
        itemList.clear()
        itemList.addAll(newList)
        notifyDataSetChanged() }



    private fun updateDataWithItems(newList: List<Item>) {
        itemList.clear()
        itemList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun getItemCount() = itemList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = itemList[position]) {
            is Item -> {
                when (item.type) {
                    ItemType.CATEGORIA -> {
                        val categoryViewHolder = holder as? CategoryViewHolder
                        categoryViewHolder?.bind(item.data)
                    }
                    ItemType.PRODUTO -> {
                        val productViewHolder = holder as? ProductViewHolder
                        productViewHolder?.bind(item.data as Produto)
                    }
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return itemList[position].type.ordinal
    }

    inner class ProductViewHolder(binding: ProductItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val imgProduto = binding.imgProduto
        val nome = binding.txtName
        val price = binding.txtPrice
        val btAdd = binding.btnAdd


        fun bind(produto: Produto) {
            imgProduto.setBackgroundResource(produto.imgProduct)
            nome.text = produto.name
            //price.text = produto.price.toString()

            val formattedPrice = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
                .format(produto.price)

            price.text = formattedPrice

            imgProduto.setOnClickListener {
                val intent = Intent(context, ProductDetails::class.java)
                intent.putExtra("imgProduct", produto.imgProduct)
                intent.putExtra("name", produto.name)
                intent.putExtra("price", produto.price)
                intent.putExtra("descricao", produto.descricao)
                context.startActivity(intent)
            }
            //Evento CLICK
            btAdd.setOnClickListener {
                val intent = Intent(context, ProductDetails::class.java)
                intent.putExtra("imgProduct", produto.imgProduct)
                intent.putExtra("name", produto.name)
                intent.putExtra("price", produto.price)
                intent.putExtra("descricao", produto.descricao)
                context.startActivity(intent)
            }
        }
    }

    inner class CategoryViewHolder(binding: CategoriaItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val imgProdutoCategory: ImageView = binding.imgProdutoCategory
        val txtNameCategory: TextView = binding.txtNameCategory

        fun bind(data: Any) {
            when (data) {
                is Categoria -> {
                    imgProdutoCategory.setBackgroundResource(data.imgResource)
                    txtNameCategory.text = data.name
                    itemView.setOnClickListener {
                        categoriaClickListener.onCategoriaClick(data.name)
                    }
                }
                is String -> {
                    // Se for uma String, atualize o texto diretamente
                    txtNameCategory.text = data
                    itemView.setOnClickListener {
                        categoriaClickListener.onCategoriaClick(data)
                    }
                }
            }
        }
    }
}

//
//package com.example.pizzaria.adapter
//
//import android.content.Context
//import android.content.Intent
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import com.example.pizzaria.ProductDetails
//import com.example.pizzaria.databinding.CategoriaItemBinding
//import com.example.pizzaria.databinding.ProductItemBinding
//import com.example.pizzaria.model.Categoria
//import com.example.pizzaria.model.Produto
//
//class ProdutosAdapter(private val context: Context, val itemList: MutableList<Item>):
//    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
//
//    enum class ItemType {
//        CATEGORIA,
//        PRODUTO
//    }
//
//    data class Item(val type: ItemType, val data: Any)
//
//
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        return when (viewType) {
//            ItemType.CATEGORIA.ordinal -> {
//                // Inflar o layout para a categoria
//                val categoryItemBinding = CategoriaItemBinding.inflate(LayoutInflater.from(context), parent, false)
//                return CategoryViewHolder(categoryItemBinding)
//
//            }
//            ItemType.PRODUTO.ordinal -> {
//                // Inflar o layout para o produto
//                // Retornar o ViewHolder para o produto
//                val listItem = ProductItemBinding.inflate(LayoutInflater.from(context), parent, false)
//                return ProductViewHolder(listItem)
//            }
//            else -> throw IllegalArgumentException("Tipo de item desconhecido")
//        }
//    }
//
//    fun updateData(newList: MutableList<Produto>) {
//        val items = newList.map { Item(ItemType.PRODUTO, it) }
//        updateDataWithItems(items)
//    }
//
//    private fun updateDataWithItems(newList: List<Item>) {
//        itemList.clear()
//        itemList.addAll(newList)
//        notifyDataSetChanged()
//    }
////    fun updateData(newList: MutableList<Produto>) {
////        itemList.clear()
////        itemList.addAll(newList)
////        notifyDataSetChanged()
////    }
//
//    fun updateDataP(newList: List<Item>) {
//        itemList.clear()
//        itemList.addAll(newList)
//        notifyDataSetChanged()
//    }
//
//
//
//    override fun getItemCount() = itemList.size
//
//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//
//            when (val item = itemList[position]) {
//                is Item -> {
//                    when (item.type) {
//                        ItemType.CATEGORIA -> {
//                            val categoryViewHolder = holder as? CategoryViewHolder
//                            categoryViewHolder?.bind(item.data as Categoria)
//                        }
//                        ItemType.PRODUTO -> {
//                            val productViewHolder = holder as? ProductViewHolder
//                            productViewHolder?.bind(item.data as Produto)
//                        }
//                    }
//                }
//            }
//    }
//
//    override fun getItemViewType(position: Int): Int {
//        return itemList[position].type.ordinal
//    }
//
//    inner class ProductViewHolder(binding: ProductItemBinding) : RecyclerView.ViewHolder(binding.root) {
//        val imgProduto = binding.imgProduto
//        val nome = binding.txtName
//        val price = binding.txtPrice
//        val btAdd = binding.btnAdd
//
//        fun bind(produto: Produto) {
//            imgProduto.setBackgroundResource(produto.imgProduct)
//            nome.text = produto.name
//            price.text = produto.price.toString()
//            //Evento CLICK
//            btAdd.setOnClickListener {
//                val intent = Intent(context, ProductDetails::class.java)
//                intent.putExtra("imgProduct", produto.imgProduct)
//                intent.putExtra("name", produto.name)
//                intent.putExtra("price", produto.price)
//                context.startActivity(intent)
//            }
//        }
//    }
//
//    inner class CategoryViewHolder(binding: CategoriaItemBinding) : RecyclerView.ViewHolder(binding.root) {
//        val imgProdutoCategory: ImageView = binding.imgProdutoCategory
//        val txtNameCategory: TextView = binding.txtNameCategory
//
//        fun bind(category: Categoria) {
//            imgProdutoCategory.setBackgroundResource(category.imgResource)
//            txtNameCategory.text = category.name
//            // Adicione mais lógica, se necessário
//        }
//    }
//}
//
//
//
////
////import android.content.Context
////import android.content.Intent
////import android.view.LayoutInflater
////import android.view.ViewGroup
////import androidx.recyclerview.widget.RecyclerView
////import com.example.pizzaria.ProductDetails
////import com.example.pizzaria.databinding.ProductItemBinding
////import com.example.pizzaria.model.Produto
////
////class ProdutosAdapter(private val context: Context, private val produtList: MutableList<Produto>):
////    RecyclerView.Adapter<ProdutosAdapter.ProductViewHolder>() {
////
////    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
////        val listItem = ProductItemBinding.inflate(LayoutInflater.from(context),parent,false)
////        return ProductViewHolder(listItem)
////    }
////
////    fun updateData(newList: List<Produto>) {
////        produtList.clear()
////        produtList.addAll(newList)
////        notifyDataSetChanged()
////    }
////
////
////    override fun getItemCount()= produtList.size
////
////    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
////        holder.imgProduto.setBackgroundResource(produtList[position].imgProduct)
////        holder.nome.text=produtList[position].name
////        holder.price.text=produtList[position].price.toString()
////        //Evento CLICK
////        holder.btAdd.setOnClickListener {
////             val intent = Intent(context,ProductDetails::class.java )
////            intent.putExtra("imgProduct",produtList[position].imgProduct)
////            intent.putExtra("name",produtList[position].name)
////            intent.putExtra("price",produtList[position].price)
////            context.startActivity(intent)
////        }
////    }
////    inner class ProductViewHolder(binding: ProductItemBinding): RecyclerView.ViewHolder(binding.root){
////    val imgProduto=binding.imgProduto
////    val nome=binding.txtName
////    val price=binding.txtPrice
////    val btAdd=binding.btnAdd
////    }
////
////
////}