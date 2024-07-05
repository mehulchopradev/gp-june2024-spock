import spock.lang.Shared
import spock.lang.Specification

class EvenOddSpec extends Specification {

    @Shared
    GroovyObject obj

    def setupSpec() {
        Class cls = new GroovyClassLoader(getClass().getClassLoader())
                .parseClass(new File("src/main/groovy/even_odd.groovy"))
        obj = cls.getDeclaredConstructor().newInstance()
    }

    // data driven test case
    def "test even - odd numbers"() {
        // there is no given block

        expect:
        obj.evenOdd(n) == expected

        where:
        // data tables
        n | expected
        9 | "Odd"
        6 | "Even"
        0 | "Even"
    }

    /* def "test even numbers"() {
        given:
        def n = 8

        expect:
        obj.evenOdd(n) == 'Even'
    }

    def "test odd numbers"() {
        given:
        def n = 9

        expect:
        obj.evenOdd(n) == 'Odd'
    } */
}
