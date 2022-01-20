package com.saihtoo.demo.springkotlin.datasource.mock

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test

internal class MockBankDataSourceTest {

    private val mockDataSource = MockBankDataSource()

    @Test
    fun `should provide a collection of banks`() {
        val banks = mockDataSource.retrieveBanks()
        assertThat(banks).apply {
            isNotEmpty
            size().isGreaterThanOrEqualTo(3)
        }
    }

    @Test
    fun `should provide some mock data`() {
        val banks = mockDataSource.retrieveBanks()
        with(assertThat(banks)) {
            allMatch { it.accountNumber.isNotEmpty() }
            anyMatch { it.trust != 0.0 }
            anyMatch { it.transactionFee != 0 }
        }
    }
}