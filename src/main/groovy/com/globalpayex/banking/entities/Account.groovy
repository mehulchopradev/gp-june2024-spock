package com.globalpayex.banking.entities

import com.globalpayex.banking.BankingUtil

class Account {

    String accNumber
    String accType
    BigDecimal balance
    BankingUtil bankingUtil // null

    BigDecimal withdraw(BigDecimal amt) {
        if(!this.bankingUtil.canWithdraw(this, amt)) {
            throw new IllegalStateException("withdrawl not allowed as balance going below threshold")
        }

        this.balance -= amt
    }

    BigDecimal deposit(BigDecimal amt) {
        this.balance += amt
    }
}
