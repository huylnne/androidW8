package com.example.androidbtvn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class AddActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        val edtMSSV = findViewById<EditText>(R.id.edtMSSV)
        val edtHoTen = findViewById<EditText>(R.id.edtHoTen)
        val edtSDT = findViewById<EditText>(R.id.edtSDT)
        val edtDiaChi = findViewById<EditText>(R.id.edtDiaChi)
        val btnAdd = findViewById<Button>(R.id.btnAdd)

        btnAdd.setOnClickListener {
            val mssv = edtMSSV.text.toString()
            val hoten = edtHoTen.text.toString()
            val sdt = edtSDT.text.toString()
            val diachi = edtDiaChi.text.toString()

            if (mssv.isEmpty() || hoten.isEmpty()) {
                Toast.makeText(this, "MSSV & họ tên bắt buộc", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            StudentData.dsSV.add(Student(mssv, hoten, sdt, diachi))
            finish()
        }
    }
}
