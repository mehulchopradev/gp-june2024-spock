package com.globalpayex

import com.globalpayex.banking.entities.Account
import spock.lang.Specification
import spock.util.concurrent.AsyncConditions
import spock.util.concurrent.BlockingVariable

class ExecuteSpec extends Specification {
    // synchronous test to test asynchronous code
    /* def "test asynchronous account withdrawl"() {
        given:
        def account = new Account(accNumber: 'ABC123', accType: 'Savings', balance: 10000)

        when:
        Execute.executeAsync {
            account.withdraw(6000)
        }

        then:
        account.balance == 4000
    } */

    // asynchronous testing
    // AsyncConditions
    /* def "test asynchronous account withdrawl"() {
        given:
        def account = new Account(accNumber: 'ABC123', accType: 'Savings', balance: 10000)
        def asyncConditions = new AsyncConditions(1)

        when:
        Execute.executeAsync {
            account.withdraw(6000)
            asyncConditions.evaluate {
                assert account.balance == 4000
            }
        }

        then:
        asyncConditions.await()
    } */

    // BlockingVariable
    def "test asynchronous account withdrawl"() {
        given:
        def account = new Account(accNumber: 'ABC123', accType: 'Savings', balance: 10000)
        def actualBalance = new BlockingVariable<BigDecimal>()

        when:
        Execute.executeAsync {
            account.withdraw(6000)
            actualBalance.set(account.balance)
        }

        then:
        actualBalance.get() == 4000
    }

    def "test asynchronous addition(thread 1) and multiplication (thread 2)"() {
        given:
        def a = 10
        def b = 4
        def actualAddition = new BlockingVariable<Integer>()
        def actualMultiplication = new BlockingVariable<Integer>()

        when:
        Execute.executeAsync {
            def c = a + b
            actualAddition.set(c)
        }

        Execute.executeAsync {
            def c = a * b
            actualMultiplication.set(c)
        }

        then:
        actualAddition.get() == 14
        actualMultiplication.get() == 40
    }












}

