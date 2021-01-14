package com.github.jntakpe.adventofcode

fun main() {
    readInputLines(1).map { it.toInt() }.asSequence()
        .run {
            mapNotNull { p1 ->
                filter { p1 + it < 2020 }.mapNotNull { p2 -> find { p1 + p2 + it == 2020 }?.let { p1 * p2 * it } }.firstOrNull()
            }
                .firstOrNull()
                .run { print(this) }
        }
}
