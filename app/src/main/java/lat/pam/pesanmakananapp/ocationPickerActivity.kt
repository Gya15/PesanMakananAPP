package lat.pam.pesanmakananapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import lat.pam.pesanmakananapp.databinding.ActivityLocationPickerBinding

class LocationPickerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLocationPickerBinding

    companion object {
        const val EXTRA_SELECTED_CITY = "EXTRA_SELECTED_CITY"
        const val EXTRA_SELECTED_PROVINCE = "EXTRA_SELECTED_PROVINCE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLocationPickerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val locationList = createDummyLocationData()

        val adapter = LocationAdapter(locationList) { selectedLocation ->
            val resultIntent = Intent()
            resultIntent.putExtra(EXTRA_SELECTED_CITY, selectedLocation.city)
            resultIntent.putExtra(EXTRA_SELECTED_PROVINCE, selectedLocation.province)

            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }

        binding.recyclerViewLocations.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewLocations.adapter = adapter
    }

    private fun createDummyLocationData(): List<Location> {
        return listOf(
            Location("Jakarta Pusat", "DKI Jakarta"),
            Location("Bandung", "Jawa Barat"),
            Location("Cimahi", "Jawa Barat"),
            Location("Surabaya", "Jawa Timur"),
            Location("Semarang", "Jawa Tengah"),
            Location("Yogyakarta", "DI Yogyakarta"),
            Location("Medan", "Sumatra Utara"),
            Location("Makassar", "Sulawesi Selatan"),
            Location("Denpasar", "Bali"),
            Location("Balikpapan", "Kalimantan Timur")
        )
    }
}