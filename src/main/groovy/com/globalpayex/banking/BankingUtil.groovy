package com.globalpayex.banking

import com.globalpayex.banking.entities.Account

class BankingUtil {

    static final Integer MIN_BAL_SAVINGS_ACCOUNT = 1000
    static final Integer MIN_BAL_CURRENT_ACCOUNT = 500

    Boolean canWithdraw(Account account, BigDecimal amt) {
        switch (account.accType) {
            case 'Savings': account.balance - amt >= MIN_BAL_SAVINGS_ACCOUNT
                break
            case 'Current': account.balance - amt >= MIN_BAL_CURRENT_ACCOUNT
                break
        }
    }
}
