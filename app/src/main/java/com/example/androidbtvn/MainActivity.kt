package com.example.androidbtvn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var adapter: ArrayAdapter<Student>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.lvSinhVien)

        adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            StudentData.dsSV
        )
        listView.adapter = adapter

        // Click → mở DetailActivity
        listView.setOnItemClickListener { _, _, position, _ ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("index", position)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }

    // Tạo menu thêm
    override fun onCreateOptionsMenu(menu: android.view.Menu): Boolean {
        menu.add("Thêm sinh viên")
        return true
    }

    override fun onOptionsItemSelected(item: android.view.MenuItem): Boolean {
        if (item.title == "Thêm sinh viên") {
            startActivity(Intent(this, AddActivity::class.java))
        }
        return true
    }
}
