package com.example.pizzaria.ui

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.pizzaria.R
import com.example.pizzaria.databinding.FragmentContatoBinding


class ContatoFragment : Fragment() {

    private lateinit var binding: FragmentContatoBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentContatoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity).supportActionBar?.show()
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)


        // Defina o listener para a seta de voltar
        (requireActivity() as AppCompatActivity).supportActionBar?.setHomeButtonEnabled(true)
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)



        binding.call.setOnClickListener{
            ligar(view)
        }
        binding.email.setOnClickListener{
            enviarEmail(view)
        }
        binding.zap.setOnClickListener{
            enviarZap(view)
        }

        // val textView: TextView = binding.textNotifications


    }

    fun enviarZap(view: View) {
        openWhatsApp()
    }

    fun enviarEmail(view: View) {
        openGmail()
    }

    fun ligar(view: View) {
        call()
    }

    private fun openGmail() {
        try {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("mailto:" + getString(R.string.emailId) + "?subject=" + getString(R.string.app_name) + " feedback")
            startActivity(Intent.createChooser(intent, "E_mail"))
        } catch (e: ActivityNotFoundException) {
            // Tratar exceção, se necessário
        }
    }

    private fun call() {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:" + getString(R.string.contactNo))
        startActivity(intent)
    }

    private fun openWhatsApp() {
        try {
            val toNumber = getString(R.string.contactNo)
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("http://api.whatsapp.com/send?phone=$toNumber&text=")
            startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


}