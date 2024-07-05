def fibo(n) {
    if (n < 2) {
        throw new IllegalArgumentException("value of n must be >= 2")
    }

    def result = []

    def a = 0
    def b = 1

    result << a << b
    for (def i in 2..<n) {
        def c = a + b
        result << c

        a = b
        b = c
    }

    result
}
