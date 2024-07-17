package com.example.xogame

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.xogame.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private var _binding: ActivityLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStartGame.setOnClickListener {
            isValid()
        }

    }

    private fun isValid(){
        val name1 = binding.etPlayer1Name.text.toString()
        val name2 = binding.etPlayer2Name.text.toString()
        if (name1.isNullOrEmpty()){
            binding.etPlayer1Name.error="required"
            return
        }
        if (name2.isNullOrEmpty()){
            binding.etPlayer2Name.error="required"
            return
        }

        startGameActivity(name1,name2)

    }
    private fun startGameActivity(name1:String,name2:String) {
        val intent = Intent(this,MainActivity::class.java)
        intent.putExtra(NAME1,name1)
        intent.putExtra(NAME2,name2)
        startActivity(intent)
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}