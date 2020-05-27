package org.andreadelfante.datafixture.test.fixtures

import com.github.javafaker.Faker
import org.andreadelfante.datafixture.interfaces.Fixture
import org.andreadelfante.datafixture.interfaces.JSONFixture
import org.andreadelfante.datafixture.misc.FixtureAttributes
import org.andreadelfante.datafixture.test.models.Dog
import org.andreadelfante.datafixture.test.models.Person
import org.andreadelfante.datafixture.resolvers.FixtureResolver

class PersonFixture : JSONFixture<Person> {
    override fun fixture(faker: Faker, attributes: FixtureAttributes, resolver: FixtureResolver): Person = Person(
            firstName = attributes["firstName", faker.name().firstName()],
            lastName = attributes["lastName", faker.name().lastName()],
            birthday = attributes["birthday", faker.date().birthday()],
            dogs = resolver[Dog::class].create(3)
    )

    override fun jsonFixture(obj: Person, resolver: FixtureResolver): Map<String, Any?> {
        return mapOf(
                "firstName" to obj.firstName,
                "lastName" to obj.lastName,
                "birthday" to obj.birthday?.time,
                "dogs" to resolver[Dog::class].createJSON(obj.dogs)
        )
    }
}