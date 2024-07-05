package com.globalpayex

class Execute {

    def static executeAsync(Runnable job) {
        println "async task about to be scheduled"
        new Thread(job).start() // async flow
        println "async task scheduled successfully"
    }
}
