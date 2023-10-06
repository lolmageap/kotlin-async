package sample.coffee

import kotlinx.coroutines.delay
import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

suspend fun main() {
    logger.debug { "start" }
    delay(1000)
    logger.debug { "done" }
}