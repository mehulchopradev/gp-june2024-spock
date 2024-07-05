package com.globalpayex.banking.entities

import com.globalpayex.banking.BankingUtil
import spock.lang.Specification

class AccountSpec extends Specification {
    def "withdraw method throws an exception when the user cannot withdraw from account" () {
        given:
        // def bankingUtilStub = Stub(BankingUtil)
        // bankingUtilStub.canWithdraw(_,_) >> false
        def bankingUtilMock = Mock(BankingUtil)

        def account = new Account(accNumber: 'ABC123', accType: 'Savings', balance: 10000, bankingUtil: bankingUtilMock)

        when:
        account.withdraw(6000)

        then:
        IllegalStateException e = thrown()
        e.message == 'withdrawl not allowed as balance going below threshold'
    }

    def "Withdraw"() {
        given:
        // Mock object of BankingUtil
        // def bankingUtilMock = Mock(BankingUtil)

        // Stub object of BankingUtil
        def bankingUtilStub = Stub(BankingUtil)
        bankingUtilStub.canWithdraw(_, _) >> true

        def account = new Account(accNumber: 'ABC123', accType: 'Savings', balance: 10000, bankingUtil: bankingUtilStub)

        when:
        def actual = account.withdraw(6000)

        then:
        actual == 4000

        /* account.balance == 4000
        account.accType == 'Savings'
        account.accNumber == 'ABC123' */
        /* with(account) {
            balance == 4000
            accType == 'Savings'
            accNumber == 'ABC123'
        } */
        /* verifyAll(account) {
            balance == 4000
            accType == 'Savings'
            accNumber == 'ABC123'
        } */
        verifyAccount(account, 4000, 'Savings', 'ABC123')
    }

    def "Deposit"() {
        given:
        def account = new Account(accNumber: 'ABC456', accType: 'Savings', balance: 10000)

        when:
        def actual = account.deposit(6000)

        then:
        actual == 16000

        /* verifyAll(account) {
            balance == 16000
            accType == 'Savings'
            accNumber == 'ABC456'
        } */
        verifyAccount(account, 16000, 'Savings', 'ABC456')
    }

    // helper method
    // must explicitly have a void return type
    void verifyAccount(account, pbalance, paccType, paccNumber) {
        assert account.balance == pbalance
        assert account.accType == paccType
        assert account.accNumber == paccNumber
    }
}
