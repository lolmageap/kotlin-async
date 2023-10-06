package sample.coffee

import kotlinx.coroutines.*
import mu.KotlinLogging
import kotlin.system.measureTimeMillis

private val logger = KotlinLogging.logger {}
private val worker = newSingleThreadContext("worker")

fun main() {
    measureTimeMillis {
        runBlocking {
            repeat(10) {
                launch(worker) {
                    makeCoffee()
                }
            }
        }
    }.let {
        logger.debug { "$it ms" }
    }
}

private suspend fun makeCoffee() {
    coroutineScope {
        launch {
            grindCoffee()
            brewCoffee()
        }
        launch {
            boilMilk()
            formMilk()
        }
    }
    mixCoffeeAndMilk()
}

private suspend fun grindCoffee() {
    logger.debug { "커피 갈기" }
    delay(1000)
    logger.debug { "커피 가루 추출" }
}
private suspend fun brewCoffee() {
    logger.debug { "커피 내리기" }
    delay(1000)
    logger.debug { "커피 원액 추출" }
}
private suspend fun boilMilk() {
    logger.debug { "우유 끓이기" }
    delay(1000)
    logger.debug { "데운 우유 추출" }
}
private suspend fun formMilk() {
    logger.debug { "우유 거품내기" }
    delay(1000)
    logger.debug { "거품 추출" }
}

private suspend fun mixCoffeeAndMilk() {
    logger.debug { "커피와 우유 섞기" }
    delay(1000)
    logger.debug { "커피 완성" }
}