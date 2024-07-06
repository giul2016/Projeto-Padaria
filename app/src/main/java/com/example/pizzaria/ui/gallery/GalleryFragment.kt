package com.example.pizzaria.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment


//class GalleryFragment : Fragment() {
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val view = inflater.inflate(R.layout.fragment_gallery, container, false)
//
//        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView_pedido)
//        recyclerView.layoutManager = LinearLayoutManager(context)
//
//        val bundle = arguments
//        if (bundle != null) {
//            val carrinhoConteList = bundle.getSerializable("carrinhoConte") as ArrayList<Pair<Produto, Int>>?
//            val nome = bundle.getString("nome")
//            val endereco = bundle.getString("endereco")
//            val telefone = bundle.getString("telefone")
//
//            if (carrinhoConteList != null && nome != null && endereco != null && telefone != null) {
//                val adapter = PedidoAdapter(nome, endereco, telefone,carrinhoConteList )
//                recyclerView.adapter = adapter
//            }
//        }
//
//        return view
//    }
//}


import androidx.lifecycle.ViewModelProvider
import com.example.pizzaria.databinding.FragmentGalleryBinding

class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galleryViewModel =
            ViewModelProvider(this).get(GalleryViewModel::class.java)

        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root

       // val textView: TextView = binding.textGallery
        galleryViewModel.text.observe(viewLifecycleOwner) {
          //  textView.text = it
        }
        return root
    }

    override fun onResume() {
        super.onResume()
       // startActivity(Intent(requireContext(), PedidoConfirmado::class.java))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}