package com.example.pizzaria.ui.home


import android.Manifest
import android.app.Application
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.pizzaria.ProductDetails
import com.example.pizzaria.R
import com.example.pizzaria.databinding.FragmentHomeBinding
import com.example.pizzaria.ui.CarrinhoActivity
import com.example.pizzaria.ui.ContatoFragment
import com.example.pizzaria.ui.ProdutosFragment
import com.example.pizzaria.ui.Promocoes
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    //private lateinit var fusedLocationClient: FusedLocationProviderClient

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val LOCATION_PERMISSION_REQUEST_CODE = 1


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Inicialize o FusedLocationProviderClient

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
        binding.btnMain.setOnClickListener {
            val intent = Intent(context, CarrinhoActivity::class.java)
            startActivity(intent)
            // Toast.makeText (getActivity (), "Mudar para Main", Toast.LENGTH_LONG).show ();
        }

        binding.cardContato.setOnClickListener {
            val fragment = ContatoFragment() // Crie uma instância do seu fragmento
            val fragmentManager =
                requireActivity().supportFragmentManager // Obtenha o FragmentManager
            val transaction =
                fragmentManager.beginTransaction() // Inicie uma transação de fragmento
            transaction.replace(
                R.id.nav_host_fragment_content_menu,
                fragment
            ) // Substitua o conteúdo atual pelo fragmento ProdutosFragment
            transaction.addToBackStack(null) // Adicione a transação à pilha de retrocesso, se necessário
            transaction.commit() // Execute a transação

        }

        binding.cardProd.setOnClickListener {
            val fragment = ProdutosFragment() // Crie uma instância do seu fragmento
            val fragmentManager = requireActivity().supportFragmentManager // Obtenha o FragmentManager
            val transaction = fragmentManager.beginTransaction() // Inicie uma transação de fragmento
            transaction.replace(R.id.nav_host_fragment_content_menu, fragment) // Substitua o conteúdo atual pelo fragmento ProdutosFragment
            transaction.addToBackStack(null) // Adicione a transação à pilha de retrocesso, se necessário
            transaction.commit() // Execute a transação


        // Toast.makeText (getActivity (), "testando...", Toast.LENGTH_LONG).show ();
    }

        binding.cardLocal.setOnClickListener{

            if (ContextCompat.checkSelfPermission(
                    requireActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    LOCATION_PERMISSION_REQUEST_CODE
                )
            } else {
                getCurrentLocationAndOpenMaps()
            }


        }

        binding.cardPromo.setOnClickListener {

            val intent = Intent(context, Promocoes::class.java)
            startActivity(intent)
            Toast.makeText(requireContext(),"Ainda falta implementar",Toast.LENGTH_LONG).show()
        }

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }


    private fun getCurrentLocationAndOpenMaps() {
        if (ActivityCompat.checkSelfPermission(
                requireActivity(),
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireActivity(),
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            location?.let {
                val currentLatitude = it.latitude
                val currentLongitude = it.longitude

                //https://www.mapcoordinates.net/pt    -> buscar coordenadas por endereço

                // Endereço de destino (por exemplo, uma padaria)
                val destinationLatitude = -19.9570073
                val destinationLongitude = -44.1374404

                // Abrir o Google Maps com a rota
                val gmmIntentUri = Uri.parse(
                    "https://www.google.com/maps/dir/?api=1&origin=$currentLatitude,$currentLongitude" +
                            "&destination=$destinationLatitude,$destinationLongitude&travelmode=driving"
                )
                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                mapIntent.setPackage("com.google.android.apps.maps")
                startActivity(mapIntent)
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                getCurrentLocationAndOpenMaps()
            } else {
                // Permissão foi negada
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}