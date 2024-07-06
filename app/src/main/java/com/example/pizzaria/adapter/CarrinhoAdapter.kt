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
    private val carrinhoConte: MutableMap<Produto, Int>,
    private val activity: CarrinhoActivity
) : RecyclerView.Adapter<CarrinhoAdapter.ViewHolder>(), OnItemAddedListener,
    OnCartItemDeletedListener {

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

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val produto = carrinhoConte.keys.elementAt(position)
        val quantidade = carrinhoConte.getValue(produto)

        holder.nomeProduto.text = produto.name
        holder.preco.text =
            NumberFormat.getCurrencyInstance(Locale("pt", "BR")).format(produto.price)
        holder.imagem.setBackgroundResource(produto.imgProduct)
        holder.descricao.text = produto.descricao
        holder.quantidade.text = quantidade.toString()

        holder.btnDeletar.setOnClickListener {
            carrinhoConte.remove(produto)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, itemCount)
            (context as? CarrinhoActivity)?.getPrecoTotalItens()
            PrefConfig.writeListInPref(context, carrinhoConte.keys.toList())
            onCartItemDeleted()
        }

        holder.adicionarItem.setOnClickListener {
            val novaQuantidade = quantidade + 1
            carrinhoConte[produto] = novaQuantidade
            notifyItemChanged(position)
            (context as? CarrinhoActivity)?.adicionarQtdItemAoCarrinho(produto)
            activity.getPrecoTotalItens()
        }


        holder.diminuirItem.setOnClickListener {
            val novaQuantidade = if (quantidade > 1) quantidade - 1 else 1
            carrinhoConte[produto] = novaQuantidade
            notifyItemChanged(position)
            (context as? CarrinhoActivity)?.reduzirQtdItemAoCarrinho(produto)
            activity.getPrecoTotalItens()
        }

        atualizarVisibilidadeLixeira(holder)
    }

    override fun getItemCount(): Int = carrinhoConte.size

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


    // Método para atualizar a lista
    fun atualizarLista(novaLista: MutableMap<Produto, Int>) {
        carrinhoConte.clear()
        carrinhoConte.putAll(novaLista)
        notifyDataSetChanged()
    }
}
