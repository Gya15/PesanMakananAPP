package lat.pam.pesanmakananapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import lat.pam.pesanmakananapp.databinding.FragmentOrderBinding

class OrderFragment : Fragment() {

    private var _binding: FragmentOrderBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: OrderViewModel by activityViewModels()

    // Daftarkan Activity Result Launcher
    private val locationPickerLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data
            val city = data?.getStringExtra(LocationPickerActivity.EXTRA_SELECTED_CITY) ?: "Unknown"
            val province = data?.getStringExtra(LocationPickerActivity.EXTRA_SELECTED_PROVINCE) ?: "Unknown"

            sharedViewModel.setSelectedLocation(Location(city, province))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOrderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnKirim.setOnClickListener {
            val intent = Intent(activity, AddressActivity::class.java)
            val currentUsername = sharedViewModel.username.value ?: "Pengguna"
            intent.putExtra("USER_NAME_EXTRA", currentUsername)
            startActivity(intent)
        }

        binding.layoutLocationPicker.setOnClickListener {
            val intent = Intent(activity, LocationPickerActivity::class.java)
            locationPickerLauncher.launch(intent)
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        sharedViewModel.username.observe(viewLifecycleOwner) { name ->
            binding.tvGreeting.text = "Halo $name,"
        }

        sharedViewModel.orderList.observe(viewLifecycleOwner) { orderList ->
            if (orderList.isEmpty()) {
                binding.tvOrderList.text = "Anda belum memesan apapun."
            } else {
                binding.tvOrderList.text = orderList.joinToString(separator = "\n") {
                    "- ${it.name} (${it.price})"
                }
            }
        }

        sharedViewModel.selectedLocation.observe(viewLifecycleOwner) { location ->
            binding.tvOrderLocation.text = location.toString()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}