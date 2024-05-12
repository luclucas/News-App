package com.lulu.newsapp.utils

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter


fun getCleanedTitle(title: String): String {
    val s = title.split(" - ")
    var aux = ""

    if(!title.contains(" - ")){
        return title
    }

    s.forEach {
        if (it != s.last()) {
            aux = aux.plus(it)
        }
    }
    return aux
}

fun getFormattedDate(date: String): String {
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    val zdt = ZonedDateTime.parse(date)
    return zdt.format(formatter)


}