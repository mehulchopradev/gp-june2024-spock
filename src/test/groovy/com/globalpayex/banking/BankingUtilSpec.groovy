package com.globalpayex.banking

import com.globalpayex.banking.entities.Account
import spock.lang.Specification

class BankingUtilSpec extends Specification {

    static final acc = [
            new Account(accNumber: 'ABC123', accType: 'Savings', balance: 10000),
            new Account(accNumber: 'XYZ123', accType: 'Current', balance: 5000)
    ]

    def "CanWithdraw"() {
        given:
        def bankingUtil = new BankingUtil()

        expect:
        bankingUtil.canWithdraw(account, amt) == canWithdrawExpected

        where:
        account | amt  || canWithdrawExpected
        acc[0]  | 5000 || true
        acc[0]  | 9500 || false
        acc[1]  | 4500 || true
        acc[1]  | 4900 || false
    }
}
