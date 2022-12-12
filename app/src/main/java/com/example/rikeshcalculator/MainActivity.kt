package com.example.rikeshcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import org.mariuszgromada.math.mxparser.Expression
import java.lang.Exception
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    var check = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_clear.setOnClickListener {
            input_num.text = ""
            output_num.text = ""
        }

        back_btn.setOnClickListener {
            var backSpace : String? = null
            if (input_num.text.length > 0) {
                val stringBuilder : StringBuilder = java.lang.StringBuilder(input_num.text)
                val find = input_num.text.toString()

                stringBuilder.deleteCharAt(input_num.text.length-1)
                backSpace = stringBuilder.toString()
                input_num.setText(backSpace)
                showresult()
            }
        }

        btn_bracket_left.setOnClickListener {
            input_num.text = addtoInputText("(")
        }

        btn_bracket_right.setOnClickListener {
            input_num.text = addtoInputText(")")
        }

        btn_0.setOnClickListener {
            input_num.text = addtoInputText("0")
        }

        btn_1.setOnClickListener {
            input_num.text = addtoInputText("1")
        }

        btn_2.setOnClickListener {
            input_num.text = addtoInputText("2")
        }

        btn_3.setOnClickListener {
            input_num.text = addtoInputText("3")
        }

        btn_4.setOnClickListener {
            input_num.text = addtoInputText("4")
        }

        btn_5.setOnClickListener {
            input_num.text = addtoInputText("5")
        }

        btn_6.setOnClickListener {
            input_num.text = addtoInputText("6")
        }

        btn_7.setOnClickListener {
            input_num.text = addtoInputText("7")
        }

        btn_8.setOnClickListener {
            input_num.text = addtoInputText("8")
        }

        btn_9.setOnClickListener {
            input_num.text = addtoInputText("9")
        }

        btn_dot.setOnClickListener {
            input_num.text = addtoInputText(".")
        }

        btn_division.setOnClickListener {
            input_num.text = addtoInputText("÷")
        }

        btn_multiply.setOnClickListener {
            input_num.text = addtoInputText("×")
        }

        btn_substraction.setOnClickListener {
            input_num.text = addtoInputText("-")
        }

        btn_addition.setOnClickListener {
            input_num.text = addtoInputText("+")
        }

        btn_percentage.setOnClickListener {
            input_num.text = addtoInputText("%")
        }

        btn_equal.setOnClickListener {
            showresult()
        }

    }

    private fun showresult() {
        try {
            val expression = getInputExpression()
            val result = Expression(expression).calculate()

            if (result.isNaN()){
                output_num.text = "Error"
            } else{
                output_num.text = DecimalFormat("0.#######").format(result).toString()
            }
        } catch (e : Exception){
            output_num.text = "Error"
        }
    }

    private fun getInputExpression():String {
        var expression = input_num.text.replace(Regex("÷") ,"/")
        expression = expression.replace(Regex("×"), "*")
        return  expression
    }

    private fun addtoInputText(butttonValue : String) : String {
        return "${input_num.text}$butttonValue"
    }
}