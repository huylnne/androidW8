package com.example.androidbtvn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.androidbtvn.R

class MainActivity : AppCompatActivity() {

    private lateinit var edtMSSV: EditText
    private lateinit var edtHoTen: EditText
    private lateinit var btnAdd: Button
    private lateinit var btnUpdate: Button
    private lateinit var listView: ListView

    private lateinit var adapter: ArrayAdapter<SinhVien>
    private val dsSV = ArrayList<SinhVien>()

    private var indexSelected = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Ánh xạ view — KHÔNG ĐƯỢC truyền id=
        edtMSSV = findViewById(R.id.edtMSSV)
        edtHoTen = findViewById(R.id.edtHoTen)
        btnAdd = findViewById(R.id.btnAdd)
        btnUpdate = findViewById(R.id.btnUpdate)
        listView = findViewById(R.id.lvSinhVien)

        // Adapter
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, dsSV)
        listView.adapter = adapter

        // ADD
        btnAdd.setOnClickListener {
            val mssv = edtMSSV.text.toString()
            val hoten = edtHoTen.text.toString()

            if (mssv.isEmpty() || hoten.isEmpty()) {
                Toast.makeText(this, "Nhập đủ MSSV và họ tên", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            dsSV.add(SinhVien(mssv, hoten))
            adapter.notifyDataSetChanged()

            edtMSSV.setText("")
            edtHoTen.setText("")
        }

        // CLICK ITEM (hiển thị lại lên ô input)
        listView.setOnItemClickListener { _, _, position, _ ->
            indexSelected = position
            val sv = dsSV[position]

            edtMSSV.setText(sv.mssv)
            edtHoTen.setText(sv.hoTen)
        }

        // LONG PRESS DELETE
        listView.setOnItemLongClickListener { _, _, position, _ ->
            dsSV.removeAt(position)
            adapter.notifyDataSetChanged()
            true
        }

        // UPDATE
        btnUpdate.setOnClickListener {
            if (indexSelected == -1) {
                Toast.makeText(this, "Chọn sinh viên để update", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val newMssv = edtMSSV.text.toString()
            val newHoten = edtHoTen.text.toString()

            if (newMssv.isEmpty() || newHoten.isEmpty()) {
                Toast.makeText(this, "Dữ liệu không hợp lệ", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            dsSV[indexSelected].mssv = newMssv
            dsSV[indexSelected].hoTen = newHoten
            adapter.notifyDataSetChanged()

            indexSelected = -1
            edtMSSV.setText("")
            edtHoTen.setText("")
        }
    }
}
