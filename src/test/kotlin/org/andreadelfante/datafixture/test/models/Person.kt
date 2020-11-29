package org.andreadelfante.datafixture.test.models

import org.andreadelfante.datafixture.definitions.FixtureDefinition
import org.andreadelfante.datafixture.definitions.JSONFixtureDefinition
import org.andreadelfante.datafixture.factories.JSONFixtureFactory
import java.util.*
import java.util.concurrent.TimeUnit

data class Person(
        val firstName: String,
        val lastName: String,
        val birthday: Date?,
        val dogs: List<Dog>
) {
    companion object
}

fun Person.Companion.factory() = PersonFixtureFactory()

class PersonFixtureFactory : JSONFixtureFactory<Person>() {
    override fun definition(): FixtureDefinition<Person> = define { faker ->
        Person(
                firstName = faker.name().firstName(),
                lastName = faker.name().lastName(),
                birthday = faker.date().past(10, TimeUnit.DAYS),
                dogs = Dog.factory().make(10)
        )
    }

    override fun jsonDefinition(): JSONFixtureDefinition<Person> = defineJSON { person ->
        mapOf(
                "firstName" to person.firstName,
                "lastName" to person.lastName,
                "birthday" to person.birthday?.time,
                "dogs" to Dog.factory().makeJSON(person.dogs)
        )
    }

    fun withFields(firstName: String = "", lastName: String = "", birthday: Date? = null): JSONFixtureDefinition<Person> = redefineJSON {
        Person(
                firstName = firstName,
                lastName = lastName,
                birthday = birthday,
                dogs = it.dogs
        )
    }
}
