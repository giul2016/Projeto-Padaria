package com.example.pizzaria

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.pizzaria.databinding.ActivityPaymentBinding
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.text.DecimalFormat

class Payment : AppCompatActivity() {

    private lateinit var binding: ActivityPaymentBinding

    @SuppressLint("SuspiciousIndentation", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        //color satatus bar
        window.statusBarColor = Color.parseColor("#E0E0E0")
        val name = intent.extras!!.getString("name")
        val amount = intent.extras!!.getInt("amount")
        val imgProduct = intent.extras!!.getInt("imgProduct")
        val total = intent.extras!!.getDouble("total")
        val saucesAndDrinks = intent.extras!!.getString("saucesAndDrinks")
        val decimalFormat = DecimalFormat.getCurrencyInstance()

        supportActionBar?.apply {
            title = "Finalizar Pedido"  // Define o título da AppBar para o nome do produto
            setDisplayHomeAsUpEnabled(true)// Ativa o botão "Voltar" na AppBar
            setTitleColor(Color.WHITE)
        }

        binding.txtTotal.text = " Produto: $name \n" +
                " Quantidade: $amount \n" +
                " Sauces and Drinks: $saucesAndDrinks \n " +
                "Total:${decimalFormat.format(total)}"

        val text: String = binding.txtTotal.text.toString()
        binding.btnPay.setOnClickListener {

            val bitmap: Bitmap? = BitmapFactory.decodeResource(resources, imgProduct)
            shareOrderDetails(text, bitmap)
        }
    }

    private fun shareOrderDetails(itemName: String, bitmap: Bitmap?) {
        // Criar uma Intent de compartilhamento
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "image/*"
        val obs = binding.editObservacao.text.toString()
        // Adicionar o texto ao compartilhamento
        intent.putExtra(
            Intent.EXTRA_TEXT,
            "Olá, Poderia me enviar $itemName.\n" +
                    "Observação: $obs \n" +
                    " No endereço:"
        )
        // Adicionar a imagem ao compartilhamento
        bitmap?.let {
            val uri = getUriToBitmap(it, contentResolver)
            intent.putExtra(Intent.EXTRA_STREAM, uri)
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
        // Iniciar a atividade de compartilhamento
        startActivity(Intent.createChooser(intent, "Compartilhar via"))
    }

    private fun getUriToBitmap(bitmap: Bitmap, contentResolver: ContentResolver): Uri? {
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}