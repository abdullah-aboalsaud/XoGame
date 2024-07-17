package com.example.xogame

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.xogame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name1 = intent.getStringExtra(NAME1)
        val name2 = intent.getStringExtra(NAME2)

        binding.tvPlayer1.text = name1
        binding.tvPlayer2.text = name2

    }

    private var boardState = arrayOf(
        "", "", "",
        "", "", "",
        "", "", ""
    )

    var playCount = 0
    var player1Count=0
    var player2Count=0

    fun onPlayerClick(view: View) {

        var clickedButton = view as Button

        if (clickedButton.text.toString().isNotEmpty()) {
            return
        }
        val indexOfButton = Integer.parseInt(clickedButton.tag as String)

        if (playCount % 2 == 0) {
            clickedButton.text = "x"
            boardState[indexOfButton] = "x"
        } else {
            clickedButton.text = "o"
            boardState[indexOfButton] = "o"
        }
        playCount++

        if (checkWinner("x")){
            player1Count++
            binding.tvPlayer1Score.text="score ($player1Count)"
            resetBoard()
        }
        else if (checkWinner("o")){
            player2Count++
            binding.tvPlayer2Score.text="score ($player2Count)"
            resetBoard()
        }
        else if (playCount==9){
            resetBoard()
        }

    }

    private fun resetBoard() {
        boardState = arrayOf("","","","","","","","","")
        playCount = 0
        for (i in 0..8){
            var view = binding.main.findViewWithTag<Button>("$i")
            view.text=""
        }
    }

    private fun checkWinner(playerSymbol: String): Boolean {
        for (i in 0..8 step 3) {
            if (
                boardState[i].equals(playerSymbol)&&
                boardState[i+1].equals(playerSymbol)&&
                boardState[i+2].equals(playerSymbol)
            ) {
                return true
            }
        }
        for(i in 0..3 step 1){
            if (
                boardState[i].equals(playerSymbol)&&
                boardState[i+3].equals(playerSymbol)&&
                boardState[i+6].equals(playerSymbol)
            ){
              return true
            }
        }
        if (
            boardState[0].equals(playerSymbol)&&
            boardState[4].equals(playerSymbol)&&
            boardState[8].equals(playerSymbol)
        ){
            return true
        }
        if (
            boardState[2].equals(playerSymbol)&&
            boardState[4].equals(playerSymbol)&&
            boardState[6].equals(playerSymbol)
        ){
            return true
        }

        return false
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}