package org.andreadelfante.datafixture.test.builders

import com.github.javafaker.Faker
import org.andreadelfante.datafixture.test.TestFixtureFactory
import org.andreadelfante.datafixture.test.fixtures.DogFixture
import org.andreadelfante.datafixture.test.models.Dog
import org.andreadelfante.datafixture.test.models.Person
import org.andreadelfante.datafixture.test.resolvers.FixtureFactory
import org.junit.Before
import org.junit.Test

class FixtureBuilderTest {
    lateinit var faker: Faker
    lateinit var factory: FixtureFactory
    lateinit var fixedAttributes: DogFixture.Attributes

    @Before
    fun setUp() {
        faker = Faker()
        factory = TestFixtureFactory()
        fixedAttributes = DogFixture.Attributes("fixedName", 1)
    }

    @Test
    fun testCreateOneObject() {
        factory[Person::class].create()
    }

    @Test
    fun testCreateOneObjectWithAttributes() {
        val result = factory[Dog::class].create(fixedAttributes)

        assert(result.name == fixedAttributes.name)
        assert(result.age == fixedAttributes.age)
    }

    @Test
    fun testCreateManyObjects() {
        val results = factory[Person::class].create(3)

        assert(results.size == 3)
    }

    @Test
    fun testCreateManyObjectsWithAttributes() {
        val results = factory[Dog::class].create(3, fixedAttributes)

        assert(results.size == 3)
        for (result in results) {
            assert(result.name == fixedAttributes.name)
            assert(result.age == fixedAttributes.age)
        }
    }
}