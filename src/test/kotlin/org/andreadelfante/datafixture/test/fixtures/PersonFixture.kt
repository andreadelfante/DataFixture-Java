package org.andreadelfante.datafixture.test.fixtures

import com.github.javafaker.Faker
import org.andreadelfante.datafixture.kotlin.java.interfaces.Fixture
import org.andreadelfante.datafixture.test.misc.FixtureAttributes
import org.andreadelfante.datafixture.test.models.Dog
import org.andreadelfante.datafixture.test.models.Person
import org.andreadelfante.datafixture.test.resolvers.FixtureResolver

class PersonFixture : Fixture<Person> {
    override fun fixture(faker: Faker, attributes: FixtureAttributes, resolver: FixtureResolver): Person = Person(
            firstName = attributes["firstName", faker.name().firstName()],
            lastName = attributes["lastName", faker.name().lastName()],
            birthday = attributes["birthday", faker.date().birthday()],
            dogs = resolver[Dog::class].create(3)
    )
}