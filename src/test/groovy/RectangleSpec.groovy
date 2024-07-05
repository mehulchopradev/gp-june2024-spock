import spock.lang.Shared
import spock.lang.Specification

class RectangleSpec extends Specification{

    @Shared
    GroovyObject obj

    // lifecycle methods
    // method --> called before every test case runs ---> setup
    // method --> called only once before the test cases of a specification start running ---> setupSpec
    // method --> called after every test case runs ---> cleanup
    // method --> called only after all the test cases of a specification have finished running ---> cleanupSpec

    def setupSpec() {
        Class cls = new GroovyClassLoader(getClass().getClassLoader())
                .parseClass(new File("src/main/groovy/rectangle.groovy"))
        obj = cls.getDeclaredConstructor().newInstance()
    }

    /* def setup() {
        Class cls = new GroovyClassLoader(getClass().getClassLoader())
                .parseClass(new File("src/main/groovy/rectangle.groovy"))
        obj = cls.getDeclaredConstructor().newInstance()
    } */

    def "test the perimeter function of a rectangle"() {
        /* given:
        def length = 10
        def breadth = 5 */

        expect:
        obj.perimeter(length, breadth) == expected

        where:
        length | breadth || expected
        10     | 5       || 30
        4      | 2       || 12
    }

    def "test the area function of a rectangle"() {
        given:
        def length = 5
        def breadth = 3

        expect:
        obj.area(length, breadth) == 15
    }
}
