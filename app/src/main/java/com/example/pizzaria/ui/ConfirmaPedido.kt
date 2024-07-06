package com.example.pizzaria.ui

import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.example.pizzaria.PedidoConfirmadoActivity
import com.example.pizzaria.R
import com.example.pizzaria.adapter.CarrinhoAdapter
import com.example.pizzaria.model.PrefConfig
import com.example.pizzaria.model.Produto
import com.example.pizzaria.ui.gallery.GalleryFragment

import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.Serializable


class ConfirmaPedido : AppCompatActivity() {
    private lateinit var toolbar: Toolbar
    private lateinit var carrinhoConte: MutableMap<Produto, Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_confirmar_pedido)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            title = "Pedido"
            setDisplayHomeAsUpEnabled(false)
        }
        //Aqui recebo lista da classe CarrinhoActivity
        carrinhoConte = intent.getSerializableExtra("carrinhoConte") as MutableMap<Produto, Int>



        val editTextNome = findViewById<EditText>(R.id.editTextNome)
        val editTextEndereco = findViewById<EditText>(R.id.editTextEndereco)
        val editTextTelefone = findViewById<EditText>(R.id.editTextTelefone)
        val btnEnviarPedido = findViewById<Button>(R.id.btnEnviarPedido)

//        val btnChamarTela = findViewById<Button>(R.id.btnChamarPedido)
//        btnChamarTela.setOnClickListener {
//
//            startActivity(Intent(this, PedidoConfirmadoActivity::class.java))
////            val intent = Intent(this, PedidoConfirmado::class.java)
////            startActivity(intent)
//        }
        btnEnviarPedido.setOnClickListener {
            val nome = editTextNome.text.toString().trim()
            val endereco = editTextEndereco.text.toString().trim()
            val telefone = editTextTelefone.text.toString().trim()

            if (nome.isNotEmpty() && endereco.isNotEmpty() && telefone.isNotEmpty()) {
                val bundle = Bundle().apply {
                    putSerializable("carrinhoContePedido", HashMap(carrinhoConte))
                    putString("nome", nome)
                    putString("endereco", endereco)
                    putString("telefone", telefone)
                }

                // Inicializar o Fragment
                 PedidoConfirmadoFragment().apply {
                    arguments = bundle // Definir os argumentos do Fragment com o Bundle
                }

                val intent = Intent(this, PedidoConfirmadoActivity::class.java).apply {
                    putExtras(bundle)
                }
                startActivity(intent)

                enviarListaViaWhatsApp(carrinhoConte,nome,endereco,telefone)

                // Limpar a lista do carrinho
                limparCarrinho()

                Toast.makeText(this,"Seu pedido foi enviado com sucesso!",Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show()
            }
            finish()
        }


//        btnEnviarPedido.setOnClickListener {
//            val nome = editTextNome.text.toString().trim()
//            val endereco = editTextEndereco.text.toString().trim()
//            val telefone = editTextTelefone.text.toString().trim()
//
//            if (nome.isNotEmpty() && endereco.isNotEmpty() && telefone.isNotEmpty()) {
//                //enviarListaViaWhatsApp(carrinhoConte, nome, endereco, telefone)
//              // enviarListaViaWhatsApp(carrinhoConte, nome, endereco, telefone)
//               // enviarDadosParaGallery(carrinhoConte, nome, endereco, telefone)
//
//                val bundle = Bundle().apply {
//                    putSerializable("carrinhoContePedido", ArrayList(carrinhoConte.toList()))
//                    putString("nome", nome)
//                    putString("endereco", endereco)
//                    putString("telefone", telefone)
//                }
//
//                // Inicializar o Fragment
//                 PedidoConfirmadoFragment().apply {
//                    arguments = bundle // Definir os argumentos do Fragment com o Bundle
//                }
//
//
//                // Substituir o Fragment atual pelo novo Fragment com os argumentos
//               // supportFragmentManager.beginTransaction()
//                  //  .replace(R.id.fragment_container, fragment)
//                  //  .commit()
//
//                val intent = Intent(this, PedidoConfirmadoActivity::class.java)
//                val produtos = ArrayList<Produto>()
//                for ((produto, _) in carrinhoConte) {
//                    produtos.add(produto)
//                }
//                intent.putParcelableArrayListExtra("carrinhoContePedido", produtos)
//                intent.putExtra("nome", nome)
//                intent.putExtra("endereco", endereco)
//                intent.putExtra("telefone", telefone)
////
//                startActivity(intent)
//
//                // Limpar a lista do carrinho
//                limparCarrinho()
//
//                Toast.makeText(this,"Seu pedido foi enviado com sucesso!",Toast.LENGTH_LONG).show()
//
//
//                // Iniciar a GalleryFragment
////                supportFragmentManager.beginTransaction()
////                    .replace(R.id.fragment_container, galleryFragment) // R.id.fragment_container é o ID do layout onde você deseja exibir a GalleryFragment
////                    .addToBackStack(null)
////                    .commit()
//
//
//
//            } else {
//                // Mostrar uma mensagem de erro ao usuário informando que os campos não podem estar vazios
//                // Por exemplo:
//                Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show()
//            }
//
//
//
//
//            //enviarListaViaWhatsApp(carrinhoConte,nome,endereco,telefone)
//        }
    }

    private fun limparCarrinho() {
        // Limpar a lista do carrinho
        carrinhoConte.clear()

        // Atualizar o armazenamento persistente, se necessário
        PrefConfig.writeListInPref(this, carrinhoConte.keys.toList())

        // Notificar a atividade CarrinhoActivity para atualizar a RecyclerView
        val intent = Intent("ATUALIZAR_CARRINHO")
        sendBroadcast(intent)
    }

    private fun enviarListaViaWhatsApp(carrinhoConte: MutableMap<Produto, Int>, nome: String, endereco: String, telefone: String) {
        // Verifique se os campos Nome, Endereço e Telefone não estão vazios antes de prosseguir
        if (nome.isNotEmpty() && endereco.isNotEmpty() && telefone.isNotEmpty()) {
            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND_MULTIPLE
            sendIntent.type = "image/*"

            val uris = arrayListOf<Uri>()

            // Criar uma lista de partes da mensagem, cada uma contendo foto, nome e quantidade do produto
            val messageParts = mutableListOf<String>()

            // Adicionar um título para a lista de produtos
            messageParts.add(buildPedidoMessage(nome, endereco, telefone))

            // Adicionar os detalhes do pedido à mensagem
            carrinhoConte.forEach { (produto, quantidade) ->
                val produtoMessage = "${produto.name} - Quantidade: $quantidade"
                messageParts.add(produtoMessage)

                // Carregar a imagem do produto como Bitmap
                val imagemProduto: Bitmap? = BitmapFactory.decodeResource(resources, produto.imgProduct)

                // Adicionar a imagem ao compartilhamento
                imagemProduto?.let {
                    val uri = getUriToBitmap(it)
                    uri?.let { uris.add(it) }
                }
            }

            // Combinar todas as partes da mensagem em uma única mensagem, separando-as com quebras de linha
            val mensagem = messageParts.joinToString("\n")

            // Adicionar a mensagem ao intent de compartilhamento
            sendIntent.putExtra(Intent.EXTRA_TEXT, mensagem)

            // Adicionar os URIs das imagens ao intent de compartilhamento
            sendIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris)
            sendIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)

            // Criar um Intent de escolha do compartilhamento
            val shareIntent = Intent.createChooser(sendIntent, null)
            if (sendIntent.resolveActivity(packageManager) != null) {
                startActivity(shareIntent)
            } else {
                // Caso nenhum aplicativo de compartilhamento esteja disponível
                // Você pode lidar com isso de forma adequada, por exemplo, exibindo uma mensagem de erro
            }
        } else {
            // Mostrar uma mensagem de erro ao usuário informando que os campos não podem estar vazios
            // Por exemplo:
            Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getUriToBitmap(bitmap: Bitmap): Uri? {
        try {
            val bytes = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes)

            val values = ContentValues().apply {
                put(MediaStore.Images.Media.TITLE, "temp_image")
                put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
            }

            val uri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
            uri?.let {
                contentResolver.openOutputStream(it)?.use { outputStream ->
                    outputStream.write(bytes.toByteArray())
                }
            }
            return uri
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }

    private fun buildPedidoMessage(obs: String, endereco: String, telefone: String): String {
        val pedidoMessage = StringBuilder()
        pedidoMessage.append("Pedido:\n\n")
        pedidoMessage.append("Obs: $obs\n")
        pedidoMessage.append("Endereço: $endereco\n")
        pedidoMessage.append("Telefone: $telefone\n\n")
        pedidoMessage.append("Produtos:\n")
        return pedidoMessage.toString()
    }

    // Função para enviar os dados para a GalleryFragment
    private fun enviarDadosParaGallery(carrinhoConte: MutableMap<Produto, Int>, nome: String, endereco: String, telefone: String) {
        val carrinhoConteList = carrinhoConte.toList()
        val bundle = Bundle().apply {
            putSerializable("carrinhoConte", ArrayList(carrinhoConteList))
            putString("nome", nome)
            putString("endereco", endereco)
            putString("telefone", telefone)
        }

        val intent = Intent(this, GalleryFragment::class.java).apply {
            putExtras(bundle)
        }

        startActivity(intent)
    }

}