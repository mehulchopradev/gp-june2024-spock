import spock.lang.Specification

class StringOpsSpec extends Specification{

    // test cases
    // Specification methods
    def "test the subscript operator in string data"() {
        // test data
        // blocks
        // defines the test data --> given:
        given:
        def str = 'good morning'
        // some more test data

        // action + assertions only --> expect:
        expect:
        str[0] == 'g'
        str[-1] == 'g'
        str[-2] == 'n'
        str[0..3] == 'good'
    }
}
