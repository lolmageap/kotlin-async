package sample.cost

import java.util.concurrent.CountDownLatch
import java.util.concurrent.atomic.AtomicLong
import kotlin.concurrent.thread
import kotlin.system.measureTimeMillis

fun main() {
    val latcher = CountDownLatch(10_000)
    val count = AtomicLong()
    measureTimeMillis {
        repeat(10_000) {
            thread {
                repeat(100_000) {
                    count.addAndGet(1)
                }
                latcher.countDown()
            }
            latcher.await()
        }
    }.let {
        println("count = $count, latcher = $latcher")
    }
}