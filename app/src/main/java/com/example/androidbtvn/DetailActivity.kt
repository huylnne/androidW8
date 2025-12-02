package com.example.androidbtvn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val index = intent.getIntExtra("index", -1)
        if (index == -1) {
            finish()
            return
        }

        val sv = StudentData.dsSV[index]

        val edtMSSV = findViewById<EditText>(R.id.edtMSSV)
        val edtHoTen = findViewById<EditText>(R.id.edtHoTen)
        val edtSDT = findViewById<EditText>(R.id.edtSDT)
        val edtDiaChi = findViewById<EditText>(R.id.edtDiaChi)

        val btnUpdate = findViewById<Button>(R.id.btnUpdate)
        val btnDelete = findViewById<Button>(R.id.btnDelete)

        // Set data
        edtMSSV.setText(sv.mssv)
        edtHoTen.setText(sv.hoTen)
        edtSDT.setText(sv.sdt)
        edtDiaChi.setText(sv.diaChi)

        // Update
        btnUpdate.setOnClickListener {
            sv.mssv = edtMSSV.text.toString()
            sv.hoTen = edtHoTen.text.toString()
            sv.sdt = edtSDT.text.toString()
            sv.diaChi = edtDiaChi.text.toString()

            finish()
        }

        // Delete
        btnDelete.setOnClickListener {
            StudentData.dsSV.removeAt(index)
            finish()
        }
    }
}
