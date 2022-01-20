package com.saihtoo.demo.springkotlin.datasource.network.dto

import com.saihtoo.demo.springkotlin.model.Bank

data class BankList(
    val results: Collection<Bank>
)
