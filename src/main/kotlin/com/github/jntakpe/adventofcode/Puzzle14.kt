package com.github.jntakpe.adventofcode

import com.github.jntakpe.adventofcode.Puzzle14.Bag

fun main() {
    readInputLines(13)
        .map { Bag(it) }
        .run { find { it.name == "shiny gold" }!!.capacity2(toSet()) }
        .run { print(this) }
}

private fun Bag.capacity2(bags: Set<Bag>): Int {
    return subBags(bags)
        .map { it.capacity(bags) }
        .sum()
}

private fun Bag.capacity(bags: Set<Bag>): Int {
    var flattenBags = subBags(bags)
    var count = 1
    while (flattenBags.any { it.content.isNotEmpty() }) {
        flattenBags = flattenBags
            .filter { it.content.isNotEmpty() }
            .onEach { count++ }
            .flatMap { it.subBags(bags) }
            .let { b -> b + flattenBags.filter { it.content.isEmpty() } }
    }
    return flattenBags.size + count
}

private fun Bag.subBags(bags: Set<Bag>) = content.flatMap { e -> bags.byName(e.key).let { b -> (0 until e.value).map { b } } }

private fun Iterable<Bag>.byName(name: String) = find { it.name == name } ?: throw IllegalStateException("Bag $name does not exists")

private object Puzzle14 {

    class Bag(line: String) {

        val name: String = line.substringBefore("bags").trim()
        var content: Map<String, Int> = resolveContent(line)

        private fun resolveContent(line: String): Map<String, Int> {
            return line
                .substringAfter("contain")
                .split(",")
                .map { it.substringBefore("bag") }
                .filter { s -> s.any { it.isDigit() } }
                .groupBy({ s -> s.filter { !it.isDigit() }.trim() }, { s -> s.filter { it.isDigit() }.toInt() })
                .mapValues { it.value.first() }
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Bag

            if (name != other.name) return false

            return true
        }

        override fun hashCode(): Int {
            return name.hashCode()
        }

        override fun toString(): String {
            return "Bag(name='$name', content=$content)"
        }
    }
}
