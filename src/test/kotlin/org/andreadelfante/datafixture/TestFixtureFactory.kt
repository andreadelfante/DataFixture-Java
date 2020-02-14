package org.andreadelfante.datafixture

import org.andreadelfante.datafixture.fixtures.DogFixture
import org.andreadelfante.datafixture.fixtures.PersonFixture
import org.andreadelfante.datafixture.models.Dog
import org.andreadelfante.datafixture.models.Person
import org.andreadelfante.datafixture.resolvers.FixtureFactory

class TestFixtureFactory : FixtureFactory() {
    init {
        define(clazz = Person::class, fixture = PersonFixture())
        define(clazz = Dog::class, fixture = DogFixture())
    }
}