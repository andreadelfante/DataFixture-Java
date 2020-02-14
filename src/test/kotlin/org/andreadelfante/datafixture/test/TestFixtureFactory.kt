package org.andreadelfante.datafixture.test

import org.andreadelfante.datafixture.test.fixtures.DogFixture
import org.andreadelfante.datafixture.test.fixtures.PersonFixture
import org.andreadelfante.datafixture.test.fixtures.TodoFixture
import org.andreadelfante.datafixture.test.models.Dog
import org.andreadelfante.datafixture.test.models.Person
import org.andreadelfante.datafixture.test.models.Todo
import org.andreadelfante.datafixture.resolvers.FixtureFactory

class TestFixtureFactory : FixtureFactory() {
    init {
        define(clazz = Person::class, fixture = PersonFixture())
        define(clazz = Dog::class, fixture = DogFixture())
        define(clazz = Todo::class, fixture = TodoFixture())
    }
}