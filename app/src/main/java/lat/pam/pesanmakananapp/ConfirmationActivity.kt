package lat.pam.pesanmakananapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import lat.pam.pesanmakananapp.databinding.ActivityConfirmationBinding

class ConfirmationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConfirmationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfirmationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra("USER_NAME_EXTRA") ?: "Pengguna"

        binding.tvGreeting.text = "Halo $username,"

        binding.btnSelesai.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

            intent.putExtra("USER_NAME_EXTRA", username)

            startActivity(intent)
            finish()
        }
    }
}