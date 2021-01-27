package com.github.jntakpe.adventofcode

import com.github.jntakpe.adventofcode.Puzzle18.Xmas

fun main() {
    readInputLines(17)
        .run { Xmas(map { it.toLong() }, 25).findWeakness() }
        .run { println(this) }
}

private object Puzzle18 {

    class Xmas(private val numbers: List<Long>, private val preamble: Int) {

        fun findWeakness(): Long {
            val contiguous = findContiguousEqualsToForGivenIndex(faultyValue())
            return contiguous.minOrNull()!! + contiguous.maxOrNull()!!
        }

        private tailrec fun findContiguousEqualsToForGivenIndex(value: Long, fromIndex: Int = 0): List<Long> {
            val contiguous = findContiguousEqualsToForGivenRange(value, fromIndex)
            return if (contiguous.isEmpty()) findContiguousEqualsToForGivenIndex(value, fromIndex + 1) else contiguous
        }

        private tailrec fun findContiguousEqualsToForGivenRange(value: Long, fromIndex: Int, toIndex: Int = fromIndex + 1): List<Long> {
            if (toIndex == numbers.size - 1) return emptyList()
            val contiguous = (fromIndex..toIndex).map { numbers[it] }
            return if (contiguous.sum() == value) contiguous else findContiguousEqualsToForGivenRange(value, fromIndex, toIndex + 1)
        }

        private fun faultyValue() = numbers[checkIndex(preamble)]

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
