package com.example.pizzaria

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pizzaria.databinding.ActivityEnderecoBinding

class EnderecoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEnderecoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityEnderecoBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnCadastrarEndereO.setOnClickListener {

            val rua = binding.editTextRua.text.toString()
            val numero = binding.editNumer.text.toString()
            val bairro = binding.editTextBairro.text.toString()
            val complemento = binding.editTextComplemento.text.toString()
            val cidade = binding.editTextCidade.text.toString()
            val uF = binding.editTextUf.text.toString()

            when {
                rua.isEmpty() -> {
                    binding.editTextRua.error = "Preencha o mome da rua!"
                }

                numero.isEmpty() -> {
                    binding.editNumer.error = "Informe o número de casa!"
                }

                bairro.isEmpty() -> {
                    binding.editTextBairro.error = "Preencha o bairro!"
                }

                cidade.isEmpty() -> {
                    binding.editTextCidade.error = "Informe a cidade!"
                }

                uF.isEmpty() -> {
                    binding.editTextUf.error = "Preencha qual Estado!"
                }


            }

        }

    }
}