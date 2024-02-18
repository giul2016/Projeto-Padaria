package com.example.pizzaria.adapter

import android.content.Context
import android.os.Build
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityCompat.invalidateOptionsMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.pizzaria.OnCartItemDeletedListener
import com.example.pizzaria.OnItemAddedListener
import com.example.pizzaria.R
import com.example.pizzaria.model.PrefConfig
import com.example.pizzaria.model.Produto
import com.example.pizzaria.ui.CarrinhoActivity
import com.google.gson.Gson
import java.text.NumberFormat
import java.util.Locale

class CarrinhoAdapter(
    private val context: Context,
    private var carrinho: MutableList<Produto> = mutableListOf(),
    private val activity: CarrinhoActivity
) : RecyclerView.Adapter<CarrinhoAdapter.ViewHolder>(), OnItemAddedListener,
    OnCartItemDeletedListener {

    private val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    private val gson = Gson()
    private val quantidadeKeyPrefix = "quantidade_"

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nomeProduto: TextView = itemView.findViewById(R.id.nomeProduto)
        val quantidade: TextView = itemView.findViewById(R.id.txtQtd)
        val descricao: TextView = itemView.findViewById(R.id.descricao)
        val preco: TextView = itemView.findViewById(R.id.preco)
        val imagem: ImageView = itemView.findViewById(R.id.imagemProduto)
        val adicionarItem: Button = itemView.findViewById(R.id.btnAcrescentar)
        val diminuirItem: Button = itemView.findViewById(R.id.btnToDecrescentar)
        val btnDeletar: ImageButton = itemView.findViewById(R.id.ic_delete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_produto_carrinho, parent, false)
        return ViewHolder(view)
    }

    private lateinit var holder: ViewHolder
    private lateinit var produto: Produto
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        this.holder = holder
        produto = carrinho[position]

        holder.nomeProduto.text = produto.name
        holder.preco.text =
            NumberFormat.getCurrencyInstance(Locale("pt", "BR")).format(produto.price)
        holder.imagem.setBackgroundResource(produto.imgProduct)
        holder.descricao.text = produto.descricao

        var quantidade = getQuantidadeProduto(produto)
        holder.quantidade.text = quantidade.toString()

        holder.btnDeletar.setOnClickListener {
            carrinho.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, carrinho.size)
            (context as? CarrinhoActivity)?.getPrecoTotalItens()
            PrefConfig.writeListInPref(context, carrinho)
            onCartItemDeleted()
        }

        holder.adicionarItem.setOnClickListener {
            var quantidadeAtual = holder.quantidade.text.toString().toIntOrNull() ?: 1
            produto = carrinho[position]
            val novaQuantidade = quantidadeAtual + 1
            holder.quantidade.text = novaQuantidade.toString()
            setQuantidadeProduto(produto, novaQuantidade)
            atualizarVisibilidadeLixeira(holder)
            atualizarQuantidade(holder)
            //val novaQuantidade = quantidade ++

            (context as? CarrinhoActivity)?.adicionarQtdItemAoCarrinho(produto)
            activity.getPrecoTotalItens()
        }

        holder.diminuirItem.setOnClickListener {
            val quantidadeAtual = holder.quantidade.text.toString().toIntOrNull() ?: 1

            if (quantidadeAtual > 1) {
                val novaQuantidade = quantidadeAtual - 1
                holder.quantidade.text = novaQuantidade.toString()
                setQuantidadeProduto(produto, novaQuantidade)
                atualizarVisibilidadeLixeira(holder)
                setQuantidadeProduto(produto, novaQuantidade)
            } else {
                atualizarVisibilidadeLixeira(holder)
                //Toast.makeText(context, "Se quiser remover o item use a lixaira ao lado.", Toast.LENGTH_SHORT).show()
            }
            val produto = carrinho[position]

            // atualizarQuantidade(holder)

            (context as? CarrinhoActivity)?.reduzirQtdItemAoCarrinho(produto)

            // itemCountInCart--
        }

        atualizarVisibilidadeLixeira(holder)

    }

    override fun getItemCount(): Int = carrinho.size

    private fun getQuantidadeProduto(produto: Produto): Int {
        val key = quantidadeKeyPrefix + produto.hashCode()
        return sharedPreferences.getInt(key, 1)
    }

    private fun setQuantidadeProduto(produto: Produto, quantidade: Int) {
        val key = quantidadeKeyPrefix + produto.hashCode()
        sharedPreferences.edit().putInt(key, quantidade).apply()
    }

    private fun removeQuantidadeProduto(produto: Produto) {
        val key = quantidadeKeyPrefix + produto.hashCode()
        sharedPreferences.edit().remove(key).apply()
    }

    private fun atualizarVisibilidadeLixeira(holder: ViewHolder) {
        val quantidade = holder.quantidade.text.toString().toIntOrNull() ?: 1
        if (quantidade == 1) {
            holder.btnDeletar.visibility = View.VISIBLE
            holder.diminuirItem.visibility = View.GONE
        } else {
            holder.btnDeletar.visibility = View.GONE
            holder.diminuirItem.visibility = View.VISIBLE
        }
    }

    fun atualizarQuantidade(holder: ViewHolder) {
        holder.quantidade.text.toString()
    }

    override fun onItemAdd() {
        // Incrementa o contador do carrinho e atualiza menu
        CarrinhoManager.incrementItemCountInCart()
        invalidateOptionsMenu(activity)
    }

    override fun onCartItemDeleted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            (context as? CarrinhoActivity)?.onCartItemDeleted()
        }
        CarrinhoManager.decrementItemCountInCart()
    }

}
