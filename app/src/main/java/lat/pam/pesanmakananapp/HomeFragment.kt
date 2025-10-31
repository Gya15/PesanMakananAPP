package lat.pam.pesanmakananapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import lat.pam.pesanmakananapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel: OrderViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        val menuList = createDummyData()

        val menuAdapter = MenuAdapter(menuList) { menuItem ->
            sharedViewModel.addItemToOrder(menuItem)
            Toast.makeText(requireContext(), "${menuItem.name} ditambahkan ke pesanan", Toast.LENGTH_SHORT).show()
        }

        binding.recyclerViewMenu.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = menuAdapter
        }
    }

    private fun observeViewModel() {
        sharedViewModel.username.observe(viewLifecycleOwner) { name ->
            binding.tvGreeting.text = "Halo $name,"
        }
    }

    private fun createDummyData(): List<MenuItem> {
        return listOf(
            MenuItem("Nasi Goreng Spesial", "Nasi goreng dengan telur, ayam, dan bakso.", "Rp 25.000"),
            MenuItem("Mie Ayam Bakso", "Mie ayam dengan topping bakso sapi asli.", "Rp 20.000"),
            MenuItem("Soto Ayam Lamongan", "Soto ayam kuning dengan koya gurih.", "Rp 18.000"),
            MenuItem("Ayam Bakar Madu", "Ayam bakar bumbu madu, lengkap dengan lalapan.", "Rp 28.000"),
            MenuItem("Gado-Gado Siram", "Sayuran segar dengan bumbu kacang medok.", "Rp 22.000"),
            MenuItem("Cendol", "Cendol dan Bandrek adalah minuman...", "Rp 12.000"),
            MenuItem("Bandrek", "Manajer Bandrek adalah minuman...", "Rp 10.000"),
            MenuItem("Es Teh Manis", "Minuman teh segar dingin.", "Rp 5.000"),
            MenuItem("Jus Alpukat", "Jus alpukat kental dengan susu coklat.", "Rp 15.000"),
            MenuItem("Kopi Susu Gula Aren", "Kopi susu kekinian dengan gula aren.", "Rp 18.000")
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}