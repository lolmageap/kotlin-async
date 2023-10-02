package sample.coffee

import mu.KotlinLogging
import kotlin.system.measureTimeMillis

private val logger = KotlinLogging.logger{}

fun main() {
    measureTimeMillis {
        repeat(10){
            makeCoffee()
        }
    }.let { logger.debug { ">> $it ms" } }
}

private fun makeCoffee() {
    grindCoffee()
    brewCoffee()
    boilMilk()
    formMilk()
    mixCoffeeAndMilk()
}

private fun grindCoffee() {
    logger.debug { "커피 갈기" }
    Thread.sleep(1000)
    logger.debug { "커피 가루 추출" }
}
private fun brewCoffee() {
    logger.debug { "커피 내리기" }
    Thread.sleep(1000)
    logger.debug { "커피 원액 추출" }
}
private fun boilMilk() {
    logger.debug { "우유 끓이기" }
    Thread.sleep(1000)
    logger.debug { "데운 우유 추출" }
}
private fun formMilk() {
    logger.debug { "우유 거품내기" }
    Thread.sleep(1000)
    logger.debug { "거품 추출" }
}

private fun mixCoffeeAndMilk() {
    logger.debug { "커피와 우유 섞기" }
    Thread.sleep(1000)
    logger.debug { "커피 완성" }
}