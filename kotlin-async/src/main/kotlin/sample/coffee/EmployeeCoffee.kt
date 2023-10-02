package sample.coffee

import mu.KotlinLogging
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.system.measureTimeMillis

private val logger = KotlinLogging.logger{}

fun main() {
    measureTimeMillis {
        repeat(10){
            makeCoffee()
        }
        employees.shutdown()
        employees.awaitTermination(100, TimeUnit.SECONDS)
    }.let { logger.debug { ">> $it ms" } }
}

private val employees = Executors.newFixedThreadPool(3)

private fun makeCoffee() {
    val taskA = employees.submit {
        grindCoffee()
        brewCoffee()
    }
    val taskB = employees.submit {
        boilMilk()
        formMilk()
    }
    employees.submit {
        while (!taskA.isDone || !taskB.isDone) {
            Thread.sleep(10)
        }
        mixCoffeeAndMilk()
    }
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