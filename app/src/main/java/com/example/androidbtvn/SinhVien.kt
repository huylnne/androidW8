package com.example.androidbtvn

data class SinhVien(
    var mssv: String,
    var hoTen: String
) {
    override fun toString(): String {
        return "$hoTen - $mssv"
    }
}
