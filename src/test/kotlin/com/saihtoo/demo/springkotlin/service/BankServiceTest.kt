package com.saihtoo.demo.springkotlin.service

import com.saihtoo.demo.springkotlin.datasource.BankDataSource
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class BankServiceTest {
    //Pure Unit Test
    private val dataSource: BankDataSource = mockk(relaxed = true )
    private val bankService = BankService(dataSource)

    @Test
    fun `should call its data source to retrieve banks`() {
        bankService.getBanks()
        verify(exactly = 1) { dataSource.retrieveBanks() }
    }
}