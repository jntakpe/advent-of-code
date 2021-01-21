package com.github.jntakpe.adventofcode

fun main() {
    readInputSplitOnBlank(11)
        .map { g -> g.filter { it.isLetterOrDigit() }.toSet().size }
        .sum()
        .run { println(this) }
}


