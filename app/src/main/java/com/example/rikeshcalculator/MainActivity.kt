package com.example.rikeshcalculator

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.Double4
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import org.mariuszgromada.math.mxparser.Expression
import org.mariuszgromada.math.mxparser.mathcollection.MathFunctions.factorial
import java.lang.Exception
import java.text.DecimalFormat

@Suppress("UNSAFE_CALL_ON_PARTIALLY_DEFINED_RESOURCE")
class MainActivity : AppCompatActivity() {
    var isPotrait = true

    @SuppressLint("SetTextI18n")
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

        btn_x_power_2?.setOnClickListener {
            val value:Double= input_num.text.toString().toDouble()
            val r = value * value
            output_num.text = r.toString()
        }

        btn_1_divide_x?.setOnClickListener {
            input_num.text = addtoInputText("×" + "1" + "÷")
        }

        btn_pie?.setOnClickListener {
            val value:Double= input_num.text.toString().toDouble()
            val pie = 3.14159265359
            val r = value * pie
            input_num.text = addtoInputText("×" + "π")
            output_num.text = r.toString()
        }

        btn_fact_btn?.setOnClickListener {
            if (input_num.text.toString().isEmpty()){
                Toast.makeText(this , "Please enter a valid number" , Toast.LENGTH_SHORT).show()
            }else{
                val value : Int = input_num.text.toString().toInt()
                val fact : Int = factorial(value)
                input_num.text = input_num.text.toString()
                output_num.text = fact.toString()
            }
        }

        btn_root?.setOnClickListener {
           val str : String = input_num.text.toString()
           val r = Math.sqrt(str.toDouble())
           val result = r.toString()
           output_num.text = result.toString()
        }

       



        landscape_btn.setOnClickListener {
            requestedOrientation = if (isPotrait){
                ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE
            }else{
                ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT
            }
            isPotrait = !isPotrait
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

    private fun factorial(n:Int):Int{
        return if (n==1 || n==0)1 else n* factorial(n-1)
    }

}