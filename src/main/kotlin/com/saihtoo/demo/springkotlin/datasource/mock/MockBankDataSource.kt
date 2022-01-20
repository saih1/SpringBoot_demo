package com.saihtoo.demo.springkotlin.datasource.mock

import com.saihtoo.demo.springkotlin.datasource.BankDataSource
import com.saihtoo.demo.springkotlin.model.Bank
import org.springframework.stereotype.Repository
import java.lang.IllegalArgumentException

@Repository("mock")
class MockBankDataSource : BankDataSource {
    val banks = mutableListOf(
        Bank("abcdef", 0.123, 3),
        Bank("ghijkl", 0.1, 1),
        Bank("nmopqr", 0.0, 0),
    )

    override fun retrieveBanks(): Collection<Bank> = banks

    override fun retrieveBank(accountNumber: String): Bank {
        val errorMsg = "Could not find a bank with account number ($accountNumber)"

        return banks.firstOrNull {
            it.accountNumber == accountNumber
        } ?: throw NoSuchElementException(errorMsg)
    }

    override fun createBank(bank: Bank): Bank {
        val errorMsg = "Bank with account number ${bank.accountNumber} already exists"
        if (banks.any { it.accountNumber == bank.accountNumber })
            throw IllegalArgumentException(errorMsg)

        banks.add(bank)
        return banks.last()
    }

    override fun updateBank(bank: Bank): Bank {
        val errorMsg = "Bank with account number ${bank.accountNumber} does not exist"
        val index = banks.indexOfFirst { it.accountNumber == bank.accountNumber }
        if (index == -1)
            throw NoSuchElementException(errorMsg)
        banks[index] = bank
        return banks[index]
    }

    override fun deleteBank(accountNumber: String): Unit {
        val bank = retrieveBank(accountNumber)
        banks.remove(bank)
    }
}