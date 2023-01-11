package dev.coodie.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CoodieDevApplication

fun main(args: Array<String>) {
    runApplication<CoodieDevApplication>(*args)
}
