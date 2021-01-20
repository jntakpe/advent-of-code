package com.github.jntakpe.adventofcode

fun main() {
    readInputLines(9)
        .map { Puzzle10.Position(it).seat() }
        .sorted()
        .run { (minOrNull()!!..maxOrNull()!!).find { !contains(it) } }
        .run { println(this) }
}

object Puzzle10 {
    enum class Direction(private val char: Char) {
        FRONT('F'),
        BACK('B'),
        RIGHT('R'),
        LEFT('L');

        companion object {

            fun valueOf(char: Char): Direction {
                return values().find { it.char == char } ?: throw IllegalStateException("Missing enum value for char $char")
            }
        }
    }

    class Position(directions: String) {

        private val frontal = directions.substring(0, 7).map { Direction.valueOf(it) }
        private val lateral = directions.substring(7).map { Direction.valueOf(it) }

        fun seat() = row() * 8 + seatRow()

        private fun row(): Int {
            return frontal
                .fold(0..127, rowRange())
                .let { if (it.first == it.last) it.first else throw IllegalStateException("Unable to find row") }
        }

        private fun seatRow(): Int {
            return lateral
                .fold(0..7, seatRange())
                .let { if (it.first == it.last) it.first else throw IllegalStateException("Unable to find seat position for a given row") }
        }

        private fun rowRange() = { r: IntRange, d: Direction ->
            when (d) {
                Direction.FRONT -> r.first..(r.last - r.count() / 2)
                Direction.BACK -> (r.first + r.count() / 2)..r.last
                else -> throw IllegalStateException("Expecting frontal position instead received: $d")
            }
        }

        private fun seatRange() = { r: IntRange, d: Direction ->
            when (d) {
                Direction.LEFT -> r.first..(r.last - r.count() / 2)
                Direction.RIGHT -> (r.first + r.count() / 2)..r.last
                else -> throw IllegalStateException("Expecting lateral position instead received: $d")
            }
        }
    }
}
