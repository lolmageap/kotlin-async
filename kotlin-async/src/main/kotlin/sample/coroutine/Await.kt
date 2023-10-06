package sample.coroutine

import kotlinx.coroutines.*
import mu.KotlinLogging
import kotlin.system.measureTimeMillis
import kotlin.time.Duration.Companion.seconds

private val logger = KotlinLogging.logger {}
private val worker = newSingleThreadContext("worker")

//// 기본방식
//fun main() {
//    measureTimeMillis {
//        runBlocking(worker) {
//            launch { downloadA() }
//            launch { downloadB() }
//            launch { downloadC() }
//        }
//        logger.debug { "end!!" }
//    }.let {
//        logger.debug { "$it ms" }
//    }
//}


//// list 방식
//fun main() {
//    measureTimeMillis {
//        runBlocking(worker) {
//            listOf(
//                launch { downloadA() },
//                launch { downloadB() },
//                launch { downloadC() },
//            ).joinAll()
//        }
//        logger.debug { "end!!" }
//    }.let {
//        logger.debug { "$it ms" }
//    }
//}

// 리턴 값을 받는 방식
fun main() {
    measureTimeMillis {
        runBlocking(worker) {
            val taskA = async { downloadA() }
            val taskB = async { downloadB() }
            val taskC = async { downloadC() }
            val close = taskA.await() + taskB.await() + taskC.await()
            logger.debug { "end!! $close" }
        }
    }.let {
        logger.debug { "$it ms" }
    }
}

private suspend fun downloadA(): Long {
    repeat(1) {
        logger.debug { "download A" }
        delay(1.seconds)
    }
    return 1
}

private suspend fun downloadB(): Long {
    repeat(3) {
        logger.debug { "download B" }
        delay(1.seconds)
    }
    return 3
}

private suspend fun downloadC(): Long {
    repeat(5) {
        logger.debug { "download C" }
        delay(1.seconds)
    }
    delay(3.seconds)
    return 5
}

