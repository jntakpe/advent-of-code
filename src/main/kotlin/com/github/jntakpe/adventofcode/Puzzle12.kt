package com.github.jntakpe.adventofcode

fun main() {
    readInputSplitOnBlank(11)
        .map { g -> with(g.lines()) { g.filter { it.isLetterOrDigit() }.toSet().filter { c -> all { it.contains(c) } } }.count() }
        .sum()
        .run { println(this) }
}


