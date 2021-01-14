package com.github.jntakpe.adventofcode

fun main() {
    readInputLines(1)
        .map { it.toInt() }
        .run { mapNotNull { c -> firstOrNull { it + c == 2020 }?.let { it * c } } }
        .first()
        .run { println(this) }
}
