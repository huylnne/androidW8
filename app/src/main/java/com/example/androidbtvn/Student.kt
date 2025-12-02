package com.example.androidbtvn

data class Student(
    var mssv: String,
    var hoTen: String,
    var sdt: String,
    var diaChi: String
) {
    override fun toString(): String {
        return "$hoTen - $mssv"
    }
}
