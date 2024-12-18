package com.teka.chamaa_finance.util.extensions

import com.teka.chamaa_finance.util.DateFormatHelper.getFormattedDate

fun Long?.toStringFormat(): String {
    if (this == null) return ""
    return getFormattedDate(this, "dd MMM yyyy, HH:mm")
}