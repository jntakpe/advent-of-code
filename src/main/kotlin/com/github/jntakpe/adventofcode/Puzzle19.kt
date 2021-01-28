package com.github.jntakpe.adventofcode

import com.github.jntakpe.adventofcode.Puzzle19.Bag

fun main() {
    readInputLines(19)
        .map { it.toInt() }
        .run { Bag(this).result() }
        .run { println(this) }
}

private object Puzzle19 {

    class Bag(adapters: List<Int>) {

        private val adapters = adapters.addOutletAndDeviceAdapters().sorted()

        fun result() = with(differences()) { this[1]!! * this[3]!! }

        private fun differences(): Map<Int?, Int> {
            return adapters
                .mapIndexed { i, v -> difference(v, adapters.getOrNull(i - 1)) }
                .groupingBy { it }
                .eachCount()
                .filterKeys { it != null }
        }

        private fun difference(current: Int, previous:Int?  = null) = previous?.let { current - it }

        private fun List<Int>.addOutletAndDeviceAdapters() = toMutableList().run {
            add(0, 0)
            add(maxOrNull()!! + 3)
            toList()
        }
    }
}
