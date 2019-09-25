package com.beatrizfelix.tictactoe

import android.graphics.Color
import android.media.AsyncPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock.sleep
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.core.view.marginLeft
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadTurn()
    }

    var player1 = ArrayList<Int>()
    var player2 = ArrayList<Int>()
    var activePlayer = 1
    var plays = 0

    private fun loadTurn(){
        if(activePlayer==1){
            showActivePlayer.text = "Time turn: ❌"
        } else if(activePlayer==2){
            showActivePlayer.text = "Time turn: ⭕"
        }
    }

    fun btnOnClick(view: View) {
        val btnSelected = view as Button
        var btnPosition = 0

        when(btnSelected.id) {
            R.id.btn1 -> btnPosition = 1
            R.id.btn2 -> btnPosition = 2
            R.id.btn3 -> btnPosition = 3
            R.id.btn4 -> btnPosition = 4
            R.id.btn5 -> btnPosition = 5
            R.id.btn6 -> btnPosition = 6
            R.id.btn7 -> btnPosition = 7
            R.id.btn8 -> btnPosition = 8
            R.id.btn9 -> btnPosition = 9
        }

        playTurn(btnPosition, btnSelected)
    }

    private fun playTurn(btnPosition: Int, btnSelected: Button) {
        plays ++

        if(activePlayer == 1) {
            btnSelected.text = "❌"
            btnSelected.setBackgroundColor(Color.TRANSPARENT)
            player1.add(btnPosition)
            activePlayer = 2

        } else if (activePlayer == 2){
            btnSelected.text = "⭕"
            btnSelected.setBackgroundColor(Color.TRANSPARENT)
            player2.add(btnPosition)
            activePlayer = 1
        }

        btnSelected.isEnabled = false

        checkWinner()

        loadTurn()
    }

    private fun checkWinner(){
        var winner = 0

        if((player1.contains(1) && player1.contains(2) && player1.contains(3))
            or (player1.contains(4) && player1.contains(5) && player1.contains(6))
            or (player1.contains(7) && player1.contains(8) && player1.contains(9))
            or (player1.contains(1) && player1.contains(4) && player1.contains(7))
            or (player1.contains(2) && player1.contains(5) && player1.contains(8))
            or (player1.contains(3) && player1.contains(6) && player1.contains(9))
            or (player1.contains(1) && player1.contains(5) && player1.contains(9))
            or (player1.contains(3) && player1.contains(5) && player1.contains(7))){
                winner = 1
        }

        if((player2.contains(1) && player2.contains(2) && player2.contains(3))
            or (player2.contains(4) && player2.contains(5) && player2.contains(6))
            or (player2.contains(7) && player2.contains(8) && player2.contains(9))
            or (player2.contains(1) && player2.contains(4) && player2.contains(7))
            or (player2.contains(2) && player2.contains(5) && player2.contains(8))
            or (player2.contains(3) && player2.contains(6) && player2.contains(9))
            or (player2.contains(1) && player2.contains(5) && player2.contains(9))
            or (player2.contains(3) && player2.contains(5) && player2.contains(7))){
            winner = 2
        }

        if(winner != 0){
            if(winner == 1){
                Toast.makeText(this, "Player ❌ win \uD83C\uDFC6", Toast.LENGTH_LONG).show()

            } else if(winner == 2){
                Toast.makeText(this, "Player ⭕ win \uD83C\uDFC6", Toast.LENGTH_LONG).show()
            }

            endGame()

        } else if(plays > 8){
            Toast.makeText(this, "A tie! \uD83D\uDC75", Toast.LENGTH_LONG).show()
            endGame()
        }
    }

    private fun endGame(){
        player1.clear()
        player2.clear()
        plays = 0
        cleanButton(btn1)
        cleanButton(btn2)
        cleanButton(btn3)
        cleanButton(btn4)
        cleanButton(btn5)
        cleanButton(btn6)
        cleanButton(btn7)
        cleanButton(btn8)
        cleanButton(btn9)
    }

    private fun cleanButton(btn: Button){
        btn.isEnabled = true
        btn.setBackgroundColor(Color.LTGRAY)
        btn.text = ""
    }
}
