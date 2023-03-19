package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.tictactoe.R.layout.activity_main
import org.w3c.dom.Text


class MainActivity : AppCompatActivity() ,View.OnClickListener{
    var PLAYER = true
    var TURN_COUNT =0

   var boardStatus = Array(3){IntArray(3)}

    lateinit var board: Array<Array<Button>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_main)
        var btn1:Button = findViewById(R.id.button1)
        var btn2:Button = findViewById(R.id.button2)
        var btn3:Button = findViewById(R.id.button3)
        var btn4:Button = findViewById(R.id.button4)
        var btn5:Button = findViewById(R.id.button5)
        var btn6:Button = findViewById(R.id.button6)
        var btn7:Button = findViewById(R.id.button7)
        var btn8:Button = findViewById(R.id.button8)
        var btn9:Button = findViewById(R.id.button9)
        var btnReset:Button = findViewById(R.id.resetBtn)
        var displayTv = findViewById<TextView>(R.id.displyTv)



        board = arrayOf(
            arrayOf(btn1,btn2,btn3),
            arrayOf(btn4,btn5,btn6),
            arrayOf(btn7,btn8,btn9)
        )
        for (i: Array<Button> in board){
            for (button:Button in i)
            {
                button.setOnClickListener(this)
            }
        }
        intializeBoardStatus()

        btnReset.setOnClickListener{
            PLAYER  = true
            TURN_COUNT = 0
            intializeBoardStatus()
        }
    }

    private fun intializeBoardStatus() {
       for (i in 0..2)
       {
           for (j in 0..2)
           {
               boardStatus[i][j] = -1

           }
       }
        for (i :Array<Button>in board)
        {
            for (button:Button in i)
            {
               button.isEnabled = true
                button.text = ""
            }
        }
    }

    override fun onClick(view: View) {
    when(view.id){

        R.id.button1 ->{
            updateValue(row =0,col =0,player = PLAYER)

        }
        R.id.button2 ->{
            updateValue(row =0,col =1,player = PLAYER)
        }
        R.id.button3 ->{
            updateValue(row =0,col =2,player = PLAYER)
        }
        R.id.button4 ->{
            updateValue(row =1,col =0,player = PLAYER)

        }
        R.id.button5 ->{
            updateValue(row =1,col =1,player = PLAYER)

        }
        R.id.button6 ->{
            updateValue(row =1,col =2,player = PLAYER)

        }
        R.id.button7 ->{
            updateValue(row =2,col =0,player = PLAYER)

        }
        R.id.button8 ->{
            updateValue(row =2,col =1,player = PLAYER)

        }
        R.id.button9 ->{
            updateValue(row =2,col =2,player = PLAYER)

        }

        }
        TURN_COUNT ++
        PLAYER = !PLAYER

        if(PLAYER){
            updateDisplay("Player X Turn")
        } else{
            updateDisplay("Player 0 Turn")
        }

        if(TURN_COUNT == 9)
        {
            updateDisplay("Game Draw")
        }
     checkWinner()
    }

    private fun checkWinner() {
        //Horigantal me
       for(i:Int in 0..2)
       {
           if(boardStatus[i][0] == boardStatus[i][1] && boardStatus[i][0] == boardStatus[i][2] )
               if(boardStatus[i][0] == 1){
                   updateDisplay("Player X Winner")
                   break
               }else if(boardStatus[i][0] == 0)
               {
                   updateDisplay("Player 0 Winner")
                   break
               }

       }
        //Vertical me
        for(i:Int in 0..2)
        {
            if(boardStatus[0][i] == boardStatus[1][i] && boardStatus[0][i] == boardStatus[2][i] )
                if(boardStatus[0][i] == 1){
                    updateDisplay("Player X Winner")
                    break
                }else if(boardStatus[0][i] == 0)
                {
                    updateDisplay("Player 0 Winner")
                    break
                }

        }
        // First Digonal
          if(boardStatus[0][0] == boardStatus[1][1] && boardStatus[0][0] == boardStatus[2][2] ) {
              if (boardStatus[0][0] == 1) {
                  updateDisplay("Player X Winner")

              } else if (boardStatus[0][0] == 0) {
                  updateDisplay("Player 0 Winner")

              }

          }
        //Second Digonal
        if(boardStatus[0][2] == boardStatus[1][1] && boardStatus[0][2] == boardStatus[2][0] ) {
            if (boardStatus[0][2] == 1) {
                updateDisplay("Player X Winner")

            } else if (boardStatus[0][2] == 0) {
                updateDisplay("Player 0 Winner")

            }

        }
    }

    private fun updateDisplay(text: String) {

        var displayTv = findViewById<TextView>(R.id.displyTv)

        displayTv.text= text

        if(text.contains("Winner"))
        {
         disaleButton()
        }
    }
    private fun disaleButton(){
        for (i:Array<Button> in board)
        {
            for (button:Button in i){
                button .isEnabled = false
            }
        }
    }

    private fun updateValue(row: Int, col: Int, player: Boolean) {

        val text = if(player) "x" else "0"
        val value:Int = if(player) 1 else 0
        board[row][col].apply {
            isEnabled = false
            setText(text)
        }
        boardStatus[row][col] =value
    }
}