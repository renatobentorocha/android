package com.example.tipapp.util

fun getTipValue(totalBill: Double, percentage: Double): Double {
    return totalBill * percentage;
}

fun getTotalPerPerson(totalBill: Double, tip: Double, splitBy: Int): Double {
    return (totalBill * tip + totalBill) / splitBy;
}