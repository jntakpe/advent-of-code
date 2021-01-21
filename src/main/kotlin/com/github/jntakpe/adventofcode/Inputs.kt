package com.github.jntakpe.adventofcode

fun readInputLines(puzzleNumber: Int) = readInput(puzzleNumber).lines()

fun readInputSplitOnBlank(puzzleNumber: Int) = readInput(puzzleNumber).split("\\n\\r".toRegex()).map { it.trim() }

fun readInput(puzzleNumber: Int) = object {}::class.java.getResource("/p$puzzleNumber.txt").readText().trim()
