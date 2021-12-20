package com.example.samplemovieapp.util.extension

fun appendZeroBeforeNumber(num: Int): String {
    return if (num < 10) "0$num" else num.toString()
}