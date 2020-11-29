package org.andreadelfante.datafixture.test

import com.github.javafaker.Faker
import org.andreadelfante.datafixture.test.models.Company
import org.andreadelfante.datafixture.test.models.Dog
import org.andreadelfante.datafixture.test.models.Person
import org.andreadelfante.datafixture.test.models.factory
import org.junit.Before
import org.junit.Test
import java.util.*

class FixtureFactoryTest {
    private lateinit var faker: Faker
    private lateinit var expectedString1: String
    private lateinit var expectedString2: String
    private lateinit var expectedDate1: Date

    @Before
    fun setUp() {
        faker = Faker()
        expectedString1 = faker.lorem().character().toString()
        expectedString2 = faker.lorem().character().toString()
        expectedDate1 = faker.date().birthday()
    }

    @Test
    fun makeOneObject() {
        val dog = Dog.factory().make()

        assert(dog.name.isNotEmpty())
        assert(dog.age > 0)
    }

    @Test
    fun makeOneObjectWithRedefinition() {
        val dog = Dog.factory().old().make()

        assert(dog.name.isNotEmpty())
        assert(dog.age == 20)
    }

    @Test
    fun makeOneJsonObject() {
        val result = Person.factory().makeJSON()

        arrayOf("firstName", "lastName", "dogs", "birthday").forEach {
            assert(result[it] != null)
        }
    }

    @Test
    fun makeOneJsonObjectWithRedefinition() {
        val result = Person.factory()
                .withFields(firstName = expectedString1, lastName = expectedString2)
                .makeJSON()

        assert(result["firstName"] == expectedString1)
        assert(result["lastName"] == expectedString2)
        assert(result["birthday"] == null)
    }

    @Test
    fun makeOneObjectWithAssociatedJsonObjectWithRedefinition() {
        val result = Person.factory()
                .withFields(firstName = expectedString2, lastName = expectedString1)
                .makeWithJSON()

        assert(result.obj.firstName == expectedString2)
        assert(result.obj.lastName == expectedString1)
        assert(result.obj.birthday == null)

        assert(result.json["firstName"] == expectedString2)
        assert(result.json["lastName"] == expectedString1)
        assert(result.json["birthday"] == null)
    }

    @Test
    fun makeManyObjects() {
        val results = Person.factory().make(3)

        assert(results.count() == 3)
    }

    @Test
    fun makeManyObjectsWithRedefinition() {
        val results = Person.factory()
                .withFields(firstName = expectedString1, lastName = expectedString2, birthday = expectedDate1)
                .make(3)

        assert(results.count() == 3)
        results.forEach {
            assert(it.firstName == expectedString1)
            assert(it.lastName == expectedString2)
            assert(it.birthday == expectedDate1)
        }
    }

    @Test
    fun makeOneObjectWithAssociatedJsonObject() {
        val result = Dog.factory().makeWithJSON()

        assert(result.json["name"] == result.obj.name)
        assert(result.json["age"] == result.obj.age)
    }

    @Test
    fun makeOneJsonArray() {
        val results = Person.factory().makeJSON(3)

        assert(results.count() == 3)
        results.forEach { result ->
            arrayOf("firstName", "lastName", "birthday", "dogs").forEach { key ->
                assert(result[key] != null)
            }
        }
    }

    @Test
    fun makeOneJsonArrayWithRedefinition() {
        val results = Person.factory()
                .withFields(
                        firstName = expectedString1,
                        lastName = expectedString2,
                        birthday = expectedDate1
                )
                .makeJSON(3)

        assert(results.count() == 3)
        results.forEach {
            assert(it["firstName"] == expectedString1)
            assert(it["lastName"] == expectedString2)
            assert(it["birthday"] == expectedDate1.time)
        }
    }

    @Test
    fun makeManyObjectsWithAssociatedJsonArray() {
        val results = Dog.factory()
                .old()
                .makeWithJSON(3)

        assert(results.count() == 3)
        results.forEach {
            assert(it.json["name"] == it.obj.name)
            assert(it.json["age"] == it.obj.age)
        }
    }

    @Test
    fun makeManyObjectsWithAssociatedJsonArrayWithRedefinition() {
        val results = Person.factory()
                .withFields(
                        firstName = expectedString1,
                        lastName = expectedString2,
                        birthday = expectedDate1
                )
                .makeWithJSON(3)

        assert(results.count() == 3)
        results.forEach {
            assert(it.obj.firstName == expectedString1)
            assert(it.obj.lastName == expectedString2)
            assert(it.obj.birthday == expectedDate1)

            assert(it.json["firstName"] == expectedString1)
            assert(it.json["lastName"] == expectedString2)
            assert(it.json["birthday"] == expectedDate1.time)
        }
    }

    @Test
    fun makeObjectsFromJson() {
        val companies = Company.factory().make(3)
        val results = Company.factory().makeJSON(companies)

        assert(results.count() == 3)
        results.forEachIndexed { index, result ->
            val company = companies[index]

            assert(result["name"] == company.name)

            val employeesResult = result["employees"] as List<Map<String, Any?>>
            employeesResult.forEachIndexed { index, result ->
                val employee = company.employees[index]

                assert(result["firstName"] == employee.firstName)
                assert(result["lastName"] == employee.lastName)
            }
        }
    }

    @Test
    fun makeObjectFromJson() {
        val dog = Dog.factory().make()
        val result = Dog.factory().makeJSON(dog)

        assert(result["name"] == dog.name)
        assert(result["age"] == dog.age)
    }
}