package sample.coffee

import mu.KotlinLogging
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.scheduler.Scheduler
import reactor.core.scheduler.Schedulers
import java.time.Duration
import kotlin.system.measureTimeMillis

private val logger = KotlinLogging.logger{}

val singleThread: Scheduler = Schedulers.newSingle("employee")

fun main() {
    measureTimeMillis {
        Flux.range(1, 10).flatMap {
            makeCoffee()
        }.subscribeOn(singleThread).blockLast()
    }.let { logger.debug { ">> $it ms" } }
}

private fun makeCoffee(): Mono<Unit> {
    return Mono.zip(
        grindCoffee().then(brewCoffee()),
        boilMilk().then(formMilk()),
    ).then(mixCoffeeAndMilk())
}

private fun grindCoffee(): Mono<Unit> {
    return Mono.fromCallable { logger.debug { "커피 갈기" } }
        .delayElement(Duration.ofSeconds(1))
        .publishOn(singleThread)
        .doOnNext { logger.debug { "커피 가루 추출" } }
}
private fun brewCoffee(): Mono<Unit> {
    return Mono.fromCallable { logger.debug { "커피 내리기" } }
        .delayElement(Duration.ofSeconds(1))
        .publishOn(singleThread)
        .doOnNext { logger.debug { "커피 원액 추출" } }
}
private fun boilMilk(): Mono<Unit> {
    return Mono.fromCallable { logger.debug { "우유 끓이기" } }
        .delayElement(Duration.ofSeconds(1))
        .publishOn(singleThread)
        .doOnNext { logger.debug { "데운 우유 추출" } }
}
private fun formMilk(): Mono<Unit> {
    return Mono.fromCallable { logger.debug { "우유 거품내기" } }
        .delayElement(Duration.ofSeconds(1))
        .publishOn(singleThread)
        .doOnNext { logger.debug { "거품 추출" } }
}

private fun mixCoffeeAndMilk(): Mono<Unit> {
    return Mono.fromCallable { logger.debug { "커피와 우유 섞기" } }
        .delayElement(Duration.ofSeconds(1))
        .publishOn(singleThread)
        .doOnNext { logger.debug { "커피 완성" } }
}