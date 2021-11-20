package com.winter.testkotlin.service

import com.winter.testkotlin.datasource.BankDataSource
import com.winter.testkotlin.model.Bank
import org.springframework.stereotype.Service

@Service
class BankService (private val dataSource: BankDataSource){
    fun getBanks(): Collection<Bank> = dataSource.retrieveBanks()
    fun getBank(accountNumber: String): Bank = dataSource.retrieveBank(accountNumber)
    fun addBank(bank: Bank): Bank = dataSource.createBank(bank)
}