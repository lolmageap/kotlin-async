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
            launch {
                workHard()
            }
            launch {
                workEasy()
            }
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
    while (true) { }
    logger.debug { "end hard work" }
}