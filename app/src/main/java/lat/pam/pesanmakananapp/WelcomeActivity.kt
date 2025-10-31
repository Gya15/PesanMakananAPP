package lat.pam.pesanmakananapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import lat.pam.pesanmakananapp.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStartNow.setOnClickListener {
            val intent = Intent(this, AuthChoiceActivity::class.java)
            startActivity(intent)
        }
    }
}