package com.mateus.orders.utils

import java.math.BigDecimal
import java.text.NumberFormat

object CurrencyUtils {
    fun calcCurrencyFromBigDecimal(price: BigDecimal, quantity: Int) : String {
        val numberFormat = NumberFormat.getCurrencyInstance()
        return numberFormat.format(
            android.icu.math.BigDecimal(price)
                .multiply(android.icu.math.BigDecimal(quantity))
        )
    }
}