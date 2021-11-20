package com.winter.testkotlin.datasource.mock

import com.winter.testkotlin.datasource.BankDataSource
import com.winter.testkotlin.model.Bank
import org.springframework.stereotype.Repository

@Repository
class MockBankDataSource : BankDataSource {

    val banks = mutableListOf(
        Bank("1234", 3.14, 17),
        Bank("10101", 17.14, 0),
        Bank("5678", 0.0, 100)
    )

    override fun retrieveBanks(): Collection<Bank> = banks

    override fun retrieveBank(accountNumber: String): Bank = banks.first{it.accountNumber == accountNumber}

    override fun createBank(bank: Bank): Bank {
        if(banks.any { it.accountNumber == bank.accountNumber }){
            throw IllegalArgumentException("Bank with account number already present")
        }

        banks.add(bank)
        return bank
    }
}