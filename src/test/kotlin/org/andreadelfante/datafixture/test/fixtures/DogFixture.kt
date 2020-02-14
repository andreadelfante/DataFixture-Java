package org.andreadelfante.datafixture.test.fixtures

import com.github.javafaker.Faker
import org.andreadelfante.datafixture.kotlin.java.interfaces.Fixture
import org.andreadelfante.datafixture.test.misc.FixtureAttributes
import org.andreadelfante.datafixture.test.models.Dog
import org.andreadelfante.datafixture.test.resolvers.FixtureResolver

class DogFixture : Fixture<Dog> {
    data class Attributes(val name: String? = null, val age: Int? = null) : FixtureAttributes() {
        companion object {
            const val NAME = "name"
            const val AGE = "age"
        }

        init {
            this[NAME] = name
            this[AGE] = age
        }
    }

    override fun fixture(faker: Faker, attributes: FixtureAttributes, resolver: FixtureResolver): Dog = Dog(
            name = attributes[Attributes.NAME, faker.name().name()],
            age = attributes[Attributes.AGE, faker.number().randomDigit()]
    )
}