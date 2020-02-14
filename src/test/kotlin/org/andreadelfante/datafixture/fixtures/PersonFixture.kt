package org.andreadelfante.datafixture.fixtures

import com.github.javafaker.Faker
import org.andreadelfante.datafixture.interfaces.Fixture
import org.andreadelfante.datafixture.misc.FixtureAttributes
import org.andreadelfante.datafixture.models.Dog
import org.andreadelfante.datafixture.models.Person
import org.andreadelfante.datafixture.resolvers.FixtureResolver

class PersonFixture : Fixture<Person> {
    override fun fixture(faker: Faker, attributes: FixtureAttributes, resolver: FixtureResolver): Person = Person(
            firstName = attributes["firstName", faker.name().firstName()],
            lastName = attributes["lastName", faker.name().lastName()],
            birthday = attributes["birthday", faker.date().birthday()],
            dogs = resolver[Dog::class].create(3)
    )
}