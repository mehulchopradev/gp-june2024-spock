import groovy.json.JsonSlurper
import spock.lang.Shared
import spock.lang.Specification

class SeriesSpec extends Specification {

    @Shared
    GroovyObject obj

    @Shared
    def inputs

    @Shared
    def outputs

    def setupSpec() {
        Class cls = new GroovyClassLoader(getClass().getClassLoader())
                .parseClass(new File("src/main/groovy/series.groovy"))
        obj = cls.getDeclaredConstructor().newInstance()

        def jsonSlurper = new JsonSlurper()
        def obj = jsonSlurper.parse(new File("src/test/groovy/fiboseries.json"));
        inputs = obj['inputs']
        outputs = obj['outputs']
    }

    // data pipes
    def "test fibo series"() {
        expect:
        obj.fibo(n) == expected

        where:
        n << inputs
        expected << outputs
        // n << [8, 5]
        // expected << [[0,1,1,2,3,5,8,13], [0,1,1,2,3]]
    }

    def "test fibo series for throwing exception for n < 2"() {
        given:
        def n = 1

        // when (action) - then (assertion)
        when:
        obj.fibo(n)

        then:
        IllegalArgumentException e = thrown()
        e.message == 'value of n must be >= 2'
    }

    def "test fibo series for not throwing exception for n >= 2"() {
        given:
        def n = 3

        when:
        obj.fibo(n)

        then:
        notThrown(IllegalArgumentException)
    }

    /* def "test fibo series"() {
        expect:
        obj.fibo(n) == expected

        where:
        n || expected
        8 || [0,1,1,2,3,5,8,13]
        5 || [0,1,1,2,3]
    } */
}
