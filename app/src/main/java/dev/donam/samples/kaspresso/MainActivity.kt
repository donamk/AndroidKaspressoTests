package dev.donam.samples.kaspresso

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.donam.samples.kaspresso.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.centerButton.setOnClickListener {
            binding.centerText.text = "Hello Button!"
        }
    }
}