package sample.cost

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.atomic.AtomicLong
import kotlin.system.measureTimeMillis

fun main() {
    val count = AtomicLong()
    measureTimeMillis {
        runBlocking {
            repeat(10_000) {
                launch {
                    repeat(100_000) {
                        count.addAndGet(1)
                    }
                }
            }
        }
    }.let {
        println("count = $count, time = $it ms")
    }
}