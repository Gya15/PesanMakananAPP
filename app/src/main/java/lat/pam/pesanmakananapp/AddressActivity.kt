package lat.pam.pesanmakananapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import lat.pam.pesanmakananapp.databinding.ActivityAddressBinding

class AddressActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddressBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddressBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra("USER_NAME_EXTRA") ?: "Pengguna"

        binding.btnOrderKirim.setOnClickListener {
            val intent = Intent(this, ConfirmationActivity::class.java)

            intent.putExtra("USER_NAME_EXTRA", username)

            startActivity(intent)
        }
    }
}