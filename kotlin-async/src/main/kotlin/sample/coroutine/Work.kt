package sample.coroutine

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import mu.KotlinLogging
import kotlin.system.measureTimeMillis

private val logger = KotlinLogging.logger {}
private val worker = newSingleThreadContext("worker")

fun main() {
    measureTimeMillis {
        runBlocking(worker) {
            val taskHard = launch {
                workHard()
            }
            val taskEasy = launch {
                workEasy()
            }
            delay(3000)
            taskHard.cancel()
            logger.debug { "end!!" }
        }
    }.let {
        logger.debug { "$it ms" }
    }
}

private suspend fun workEasy() {
    logger.debug { "start easy work" }
    delay(1000)
    logger.debug { "end easy work" }
}

private suspend fun workHard() {
    logger.debug { "start hard work" }
    try {
        while (true) {
            delay(100)
        }
    } finally {
        logger.debug { "end hard work" }
    }

}