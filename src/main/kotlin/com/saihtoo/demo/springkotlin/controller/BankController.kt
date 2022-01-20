package com.saihtoo.demo.springkotlin.controller

import com.saihtoo.demo.springkotlin.model.Bank
import com.saihtoo.demo.springkotlin.service.BankService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/banks")
class BankController(
    private val service: BankService
) {
    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(e: NoSuchElementException): ResponseEntity<String> {
        return ResponseEntity(e.message, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleBadRequest(e: java.lang.IllegalArgumentException): ResponseEntity<String> {
        return ResponseEntity(e.message, HttpStatus.BAD_REQUEST)
    }

    @GetMapping
    fun getBanks(): Collection<Bank> {
        return service.getBanks()
    }

    @GetMapping("/{accountNumber}")
    fun getBank(@PathVariable accountNumber: String): Bank {
       return service.getBank(accountNumber)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addBank(@RequestBody bank: Bank): Bank {
        service.addBank(bank)
        return service.getBank(bank.accountNumber)
    }

    @PatchMapping
    fun updateBank(@RequestBody bank: Bank, ): Bank {
        service.updateBank(bank)
        return service.getBank(bank.accountNumber)
    }

    @DeleteMapping("/{accountNumber}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteBank(
        @PathVariable accountNumber: String
    ): Unit {
        service.deleteBank(accountNumber)
    }
}