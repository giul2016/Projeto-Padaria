package com.example.pizzaria.adapter

import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pizzaria.R
import com.example.pizzaria.model.PedidoItem
import com.example.pizzaria.model.Produto
import java.util.Calendar
import java.text.NumberFormat
import java.util.*



class PedidoAdapter(
    private var pedidos: List<PedidoItem>
) : RecyclerView.Adapter<PedidoAdapter.PedidoViewHolder>() {

    // Método para atualizar a lista de pedidos
    fun atualizarListaPedidos(novaLista: List<PedidoItem>) {
        pedidos = novaLista
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PedidoViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.pedido_item, parent, false)
        return PedidoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PedidoViewHolder, position: Int) {
        val pedidoItem = pedidos[position]
        holder.bind(pedidoItem)
    }

    override fun getItemCount(): Int = pedidos.size

    inner class PedidoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textNome: TextView = itemView.findViewById(R.id.textNome)
        private val textEndereco: TextView = itemView.findViewById(R.id.textEndereco)
        private val textTelefone: TextView = itemView.findViewById(R.id.textTelefone)
        private val textProdutos: TextView = itemView.findViewById(R.id.textProduto)
        private val textData: TextView = itemView.findViewById(R.id.textData)
        private val textTotal: TextView = itemView.findViewById(R.id.textTotal)
        private val textPrecoPorProduto: TextView = itemView.findViewById(R.id.txtPrecoPorProduto)

        fun bind(pedidoItem: PedidoItem) {
            val nome = pedidoItem.nome
            val endereco = pedidoItem.endereco
            val telefone = pedidoItem.telefone
            val pedidos = pedidoItem.pedidos
            val dataPedido = pedidoItem.dataPedido

            textNome.text = nome
            textEndereco.text = endereco
            textTelefone.text = "Telefone: $telefone"

            val produtosTexto = StringBuilder()
            val precoxProduto = StringBuilder()
            var precoTotal = 0.0

            for ((produto, quantidade) in pedidos) {

                val precoProduto = produto.price
                // Calcula o preço total do produto multiplicado pela quantidade
                val precoxqtde = produto.price * quantidade
                // Adiciona o preço do produto ao preço total
                precoTotal += precoxqtde

                // Concatena o preço total por produto à string produtosTexto
                val produtoTexto = "${produto.name} - Quantidade: $quantidade \n"
                produtosTexto.append(produtoTexto)

                val precoxProdutos = "${formatarMoeda(precoProduto)} \n"
                precoxProduto.append(precoxProdutos)
            }

            textProdutos.text = produtosTexto.toString().trim()

            // Formatando o preço total para exibir corretamente
            val precoTotalFormatado = formatarMoeda(precoTotal)
            textTotal.text = "Total: $precoTotalFormatado"

           // val dataAtual = Calendar.getInstance().time
            //val dataFormatada = DateFormat.format("EEEE, dd/MM/yyyy", dataPedido).toString()
            textData.text = "Data do Pedido: $dataPedido"
           // textData.text = "Data do Pedido: $dataFormatada"
            val obs = textNome.text
            textNome.text = "Observação: $obs"

            textPrecoPorProduto.text = precoxProduto.toString()
        }

        private fun formatarMoeda(valor: Double): String {
            val format = NumberFormat.getCurrencyInstance(Locale.getDefault())
            return format.format(valor)
        }


    }

    fun updateData(newPedidoItemList: List<PedidoItem>) {
        pedidos = newPedidoItemList
        notifyDataSetChanged()
    }
}




