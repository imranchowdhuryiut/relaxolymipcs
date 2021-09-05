package com.imran.relaxolymipcs

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.imran.relaxolymipcs.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {



    private lateinit var _binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        val view = _binding.root
        setContentView(view)


        _binding.btnFind.setOnClickListener {
            calculate()
            hideKeyboard(it)
        }
    }

    private fun hideKeyboard(it: View?) {
        this.currentFocus?.let { it ->
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }

    private fun calculate() {
        val rangeText = _binding.tvRange.text.toString()
        if (rangeText.isNotEmpty()) {
            val textArray = rangeText.split(",")
            val year1 = textArray[0].trim().toIntOrNull()
            val year2 = textArray[1].trim().toIntOrNull()

            if (year1 != null && year2 != null) {
                if (year1 > year2) {
                    val difference = year1 - year2
                    val totalEvent = difference / 4
                    val relaxEvent = difference / 7
                    _binding.tvTotal.text = getString(R.string.total_event) + " " + totalEvent
                    _binding.tvRelax.text = getString(R.string.total_relax_start) + " " + relaxEvent
                } else {
                    val difference = year2 - year1
                    val totalEvent = difference / 4
                    val relaxEvent = difference / 7
                    _binding.tvTotal.text = getString(R.string.total_event) + " " + totalEvent
                    _binding.tvRelax.text = getString(R.string.total_relax_start) + " " + relaxEvent
                }

            }
        }
    }
}