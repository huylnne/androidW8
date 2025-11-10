package com.example.androidbtvn

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {

    private lateinit var inputNumber: EditText
    private lateinit var radioGroup: RadioGroup
    private lateinit var listView: ListView
    private lateinit var tvMessage: TextView
    private lateinit var adapter: ArrayAdapter<Int>
    private val numbers = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputNumber = findViewById(R.id.inputNumber)
        radioGroup = findViewById(R.id.radioGroup)
        listView = findViewById(R.id.listView)
        tvMessage = findViewById(R.id.tvMessage)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, numbers)
        listView.adapter = adapter

        // Tự động cập nhật khi thay đổi giá trị hoặc lựa chọn
        inputNumber.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                updateList()
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        radioGroup.setOnCheckedChangeListener { _, _ -> updateList() }
    }

    private fun updateList() {
        numbers.clear()
        val input = inputNumber.text.toString()
        if (input.isEmpty()) {
            adapter.notifyDataSetChanged()
            tvMessage.visibility = View.VISIBLE
            tvMessage.text = "Không có số nào thỏa mãn"
            return
        }

        val n = input.toIntOrNull() ?: 0
        for (i in 1 until n) {
            if (checkNumber(i)) numbers.add(i)
        }

        adapter.notifyDataSetChanged()
        tvMessage.visibility = if (numbers.isEmpty()) View.VISIBLE else View.GONE
    }

    private fun checkNumber(x: Int): Boolean {
        return when (radioGroup.checkedRadioButtonId) {
            R.id.odd -> x % 2 != 0
            R.id.even -> x % 2 == 0
            R.id.prime -> isPrime(x)
            R.id.perfect -> isPerfect(x)
            R.id.square -> sqrt(x.toDouble()) % 1 == 0.0
            R.id.fibo -> isFibo(x)
            else -> false
        }
    }

    private fun isPrime(n: Int): Boolean {
        if (n < 2) return false
        for (i in 2..sqrt(n.toDouble()).toInt()) {
            if (n % i == 0) return false
        }
        return true
    }

    private fun isPerfect(n: Int): Boolean {
        var sum = 0
        for (i in 1 until n) {
            if (n % i == 0) sum += i
        }
        return sum == n
    }

    private fun isFibo(n: Int): Boolean {
        var a = 0
        var b = 1
        while (b < n) {
            val c = a + b
            a = b
            b = c
        }
        return b == n || n == 0
    }
}
