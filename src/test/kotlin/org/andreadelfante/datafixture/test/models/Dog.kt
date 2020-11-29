package org.andreadelfante.datafixture.test.models

import org.andreadelfante.datafixture.definitions.FixtureDefinition
import org.andreadelfante.datafixture.definitions.JSONFixtureDefinition
import org.andreadelfante.datafixture.factories.JSONFixtureFactory

data class Dog(
        val name: String,
        val age: Int
) {
    companion object
}

fun Dog.Companion.factory() = DogFixtureFactory()

class DogFixtureFactory : JSONFixtureFactory<Dog>() {
    override fun definition(): FixtureDefinition<Dog> = define { faker ->
        Dog(
                name = faker.name().name(),
                age = faker.number().randomDigitNotZero()
        )
    }

    fun old(): JSONFixtureDefinition<Dog> = redefineJSON {
        Dog(name = it.name, age = 20)
    }

    override fun jsonDefinition(): JSONFixtureDefinition<Dog> = defineJSON { dog ->
        mapOf(
                "name" to dog.name,
                "age" to dog.age
        )
    }
}