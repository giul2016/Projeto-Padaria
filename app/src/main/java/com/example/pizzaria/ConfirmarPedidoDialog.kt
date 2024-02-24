package com.example.pizzaria

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment

class ConfirmarPedidoDialog : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())
        val inflater = requireActivity().layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_confirmar_pedido, null)
        builder.setView(dialogView)

        val editTextNome = dialogView.findViewById<EditText>(R.id.editTextNome)
        val editTextEndereco = dialogView.findViewById<EditText>(R.id.editTextEndereco)
        val editTextTelefone = dialogView.findViewById<EditText>(R.id.editTextTelefone)
        val btnEnviarPedido = dialogView.findViewById<Button>(R.id.btnEnviarPedido)

        btnEnviarPedido.setOnClickListener {
            val nome = editTextNome.text.toString().trim()
            val endereco = editTextEndereco.text.toString().trim()
            val telefone = editTextTelefone.text.toString().trim()

            if (nome.isEmpty() || endereco.isEmpty() || telefone.isEmpty()) {
                Toast.makeText(requireContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            } else {
                processarPedido(nome, endereco, telefone)
                dismiss()
            }
        }

        return builder.create()
    }

    private fun processarPedido(nome: String, endereco: String, telefone: String) {
        val pedido = "Pedido enviado: Nome: $nome, Endereço: $endereco, Telefone: $telefone"
        Toast.makeText(requireContext(), pedido, Toast.LENGTH_LONG).show()
    }
}
