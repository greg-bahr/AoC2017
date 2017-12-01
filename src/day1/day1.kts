package day1

import java.io.File

var input = File("./input").readText().trim()

println(input.filterIndexed { index, c -> c == input[(index+1)%input.length] }.sumBy { c -> c - '0' })
println(input.filterIndexed { index, c -> c == input[(index+(input.length/2))%input.length] }.sumBy { c -> c - '0' })
