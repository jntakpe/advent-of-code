package com.github.jntakpe.adventofcode

fun main() {
    readInputLines(17)
        .run { Puzzle17.Xmas(map { it.toLong() }, 25).faultyValue() }
        .run { println(this) }
}

private object Puzzle17 {

    class Xmas(private val numbers: List<Long>, private val preamble: Int) {

        fun faultyValue() = numbers[checkIndex(preamble)]

        private tailrec fun checkIndex(index: Int): Int {
            val previous = (index - preamble..index).map { numbers[it] }
            return if (previous.hasPairEqualsTo(numbers[index])) checkIndex(index + 1) else index
        }

        private fun List<Long>.hasPairEqualsTo(value: Long): Boolean {
            forEach { f -> forEach { s -> if (f + s == value) return true } }
            return false
        }
    }
}
