package com.github.jntakpe.adventofcode

fun readInputLines(puzzleNumber: Int) = readInput(puzzleNumber).lines()

fun readInput(puzzleNumber: Int) = object {}::class.java.getResource("/p$puzzleNumber.txt").readText().trim()
