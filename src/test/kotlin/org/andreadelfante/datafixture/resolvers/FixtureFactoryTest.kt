package org.andreadelfante.datafixture.resolvers

import org.andreadelfante.datafixture.exceptions.MissingFixtureAssociationException
import org.andreadelfante.datafixture.exceptions.MissingFixtureException
import org.andreadelfante.datafixture.fixtures.PersonFixture
import org.andreadelfante.datafixture.models.Person
import org.junit.Before
import org.junit.Test

class FixtureFactoryTest {
    companion object {
        const val NAME = "NAME"
    }

    lateinit var factory: FixtureFactory

    @Before
    fun setUp() {
        factory = FixtureFactory()
    }

    @Test
    fun testDefineAndGetCorrectFixtureFromDefinedClass() {
        factory.define(clazz = Person::class, fixture = PersonFixture())
        factory.define(clazz = Person::class, name = NAME, fixture = PersonFixture())

        factory[Person::class]
        factory[Person::class, NAME]
    }

    @Test(expected = MissingFixtureException::class)
    fun testGetWithUndefinedClass() {
        factory[String::class]
    }

    @Test(expected = MissingFixtureAssociationException::class)
    fun testGetWithDefinedClassButNoName() {
        factory.define(clazz = Person::class, fixture = PersonFixture())

        factory[Person::class, NAME]
    }
}