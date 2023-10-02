package sample.cost

import reactor.core.publisher.Flux
import reactor.core.scheduler.Schedulers
import java.util.concurrent.atomic.AtomicLong
import kotlin.system.measureTimeMillis

fun main() {
    val count = AtomicLong()
    measureTimeMillis {
        Flux.range(1, 10_000)
            .publishOn(Schedulers.boundedElastic())
            .doOnNext {
            Flux.range(1, 100_000).doOnNext {
                count.addAndGet(1)
            }.subscribe()
        }.blockLast()
    }.let {
        println("count = $count, latcher = $it ms")
    }
}