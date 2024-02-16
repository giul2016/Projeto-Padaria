package com.example.pizzaria.adapter

/********************************************
 *     Created by Giul on 10/02/2024.  *
 ********************************************/
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat.invalidateOptionsMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.pizzaria.OnCartItemDeletedListener
import com.example.pizzaria.OnItemAddedListener
import com.example.pizzaria.R
import com.example.pizzaria.model.PrefConfig
import com.example.pizzaria.model.Produto
import com.example.pizzaria.ui.CarrinhoActivity
import java.text.NumberFormat
import java.util.Locale

class CarrinhoAdapter(private val context: Context,
                      private var carrinho: MutableList<Produto> = mutableListOf(),
                      private val activity: CarrinhoActivity
) : RecyclerView.Adapter<CarrinhoAdapter.ViewHolder>(), OnItemAddedListener,OnCartItemDeletedListener {

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
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_produto_carrinho, parent, false)
        return ViewHolder(view)
    }

    private lateinit var holder: ViewHolder

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        this.holder = holder
        val produto = carrinho[position]

        val formattedPrice = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
            .format(produto.price)
        holder.nomeProduto.text = produto.name
        holder.quantidade.text = "1"
        holder.preco.text = formattedPrice.toString()
        holder.imagem.setBackgroundResource(produto.imgProduct)
        holder.descricao.text = produto.descricao


       // val decimalFormat = DecimalFormat.getCurrencyInstance()
        atualizarQuantidade(holder)

        holder.btnDeletar.setOnClickListener {
            carrinho.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, carrinho.size)

            (context as? CarrinhoActivity)?.getPrecoTotalItens()

            PrefConfig.writeListInPref(context, carrinho)
            // Exibir uma mensagem informando que o item foi removido
            //Toast.makeText(context, "Item removido do carrinho", Toast.LENGTH_SHORT).show()
            onCartItemDeleted()
           // onItemAdd()
        }

        holder.adicionarItem.setOnClickListener {
            val quantidadeAtual = holder.quantidade.text.toString().toIntOrNull() ?: 1
            val produto = carrinho[position]
            holder.quantidade.text = (quantidadeAtual + 1).toString()
            atualizarVisibilidadeLixeira(holder)
            atualizarQuantidade(holder)
            (context as? CarrinhoActivity)?.adicionarQtdItemAoCarrinho(produto)

             //onItemAdd()
            //itemCountInCart++
        }

        holder.diminuirItem.setOnClickListener {
            val quantidadeAtual = holder.quantidade.text.toString().toIntOrNull() ?: 1

            if (quantidadeAtual > 1) {
                holder.quantidade.text = (quantidadeAtual - 1).toString()
                atualizarVisibilidadeLixeira(holder)
            } else{
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

    override fun getItemCount(): Int {
        return carrinho.size
    }

    private fun atualizarQuantidade(holder: ViewHolder) {
        holder.quantidade.text.toString()
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
    // Incrementa o contador do carrinho e atualiza menu
    override fun onItemAdd() {
        // Incrementa o contador do carrinho
        CarrinhoManager.incrementItemCountInCart()
        //  atualizar o ícone do carrinho na barra de ação da CarrinhoActivity
        invalidateOptionsMenu(activity)
    }

    override fun onCartItemDeleted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            (context as? CarrinhoActivity)?.onCartItemDeleted()
        }

        CarrinhoManager.decrementItemCountInCart()
    }
}
