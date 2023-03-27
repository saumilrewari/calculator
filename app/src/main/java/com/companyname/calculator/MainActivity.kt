package com.companyname.calculator

import android.os.Bundle
import android.util.Log
import android.view.View.OnClickListener
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.companyname.calculator.databinding.ActivityMainBinding
import com.companyname.calculator.databinding.SahilLayoutBinding
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

data class Student(val rollNo: Int, val marks: Int, val name: String)

class MainActivity : AppCompatActivity(), CoroutineScope {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        launch {
            Log.v("Sahil","World1")

            val test = async {
                myFun()
            }
            Log.v("Sahil","World2")

            delay(7000L)
            Log.v("Sahil","World3")
            Log.v("Sahil","async:${test.await()}")
        }

    }

    // first commit
    // second commit
    // last commit
    suspend fun myFun() : String {
        Log.v("Sahil","World4")

        delay(3000L)
        return "Hello World"
    }


    private fun clickListener(type: Int): OnClickListener {
        return OnClickListener {
            val n1 = binding.num1.text.toString().toDoubleOrNull()
            val n2 = binding.num2.text.toString().toDoubleOrNull()

            if (n1 != null && n2 != null) {
                val test = performAction(n1, n2, type)
                Log.v("jjj", "$test")
                //binding.result.text = test.toString()
                binding.value = test.toString()
            }
        }
    }

    fun performAction(n1: Double, n2: Double, type: Int): Double {
        return when (type) {
            0 -> n1 + n2
            1 -> n1 - n2
            2 -> n1 * n2
            3 -> n1 / n2
            else -> 0.0
        }
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + Job()
}
