package org.andreadelfante.datafixture.test.builders

import com.github.javafaker.Faker
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken
import org.andreadelfante.datafixture.test.TestFixtureFactory
import org.andreadelfante.datafixture.test.fixtures.DogFixture
import org.andreadelfante.datafixture.test.models.Dog
import org.andreadelfante.datafixture.test.models.Person
import org.andreadelfante.datafixture.resolvers.FixtureFactory
import org.junit.Before
import org.junit.Test

class FixtureBuilderTest {
    lateinit var faker: Faker
    lateinit var factory: FixtureFactory
    lateinit var fixedAttributes: DogFixture.Attributes
    lateinit var gson: Gson

    @Before
    fun setUp() {
        faker = Faker()
        factory = TestFixtureFactory()
        fixedAttributes = DogFixture.Attributes("fixedName", 1)
        gson = Gson()
    }

    @Test
    fun testCreateOneObject() {
        factory[Person::class].create()
    }

    @Test
    fun testCreateOneObjectWithAttributes() {
        val result = factory[Dog::class].create(fixedAttributes)

        assert(result.name == fixedAttributes.name)
        assert(result.age == fixedAttributes.age)
    }

    @Test
    fun testCreateOneJSONObject() {
        val result = factory[Person::class].createJSON()

        listOf("firstName", "lastName", "dogs", "birthday").forEach {
            assert(result[it] != null)
        }
    }

    @Test
    fun testCreateOneJSONObjectWithAttributes() {
        val result = factory[Dog::class].createJSON(fixedAttributes)

        assert(result["name"] == fixedAttributes.name)
        assert(result["age"] == fixedAttributes.age)
    }

    @Test
    fun testCreateOneObjectWithAssociatedJSONObjectWithAttributes() {
        val result = factory[Dog::class].createWithJSON(fixedAttributes)

        assert(result.obj.name == fixedAttributes.name)
        assert(result.obj.age == fixedAttributes.age)

        assert(result.JSON["name"] == fixedAttributes.name)
        assert(result.JSON["age"] == fixedAttributes.age)
    }

    @Test
    fun testCreateManyObjects() {
        val results = factory[Person::class].create(3)

        assert(results.size == 3)
    }

    @Test
    fun testCreateManyObjectsWithAttributes() {
        val results = factory[Dog::class].create(3, fixedAttributes)

        assert(results.size == 3)
        for (result in results) {
            assert(result.name == fixedAttributes.name)
            assert(result.age == fixedAttributes.age)
        }
    }

    @Test
    fun testCreateOneObjectWithAssociatedJSONObject() {
        val result = factory[Dog::class].createWithJSON()

        assert(result.JSON["name"] == result.obj.name)
        assert(result.JSON["age"] == result.obj.age)
    }

    @Test
    fun testCreateOneJSONArray() {
        val results = factory[Person::class].createJSON(3)

        assert(results.count() == 3)
        results.forEach { result ->
            listOf("firstName", "lastName", "dogs", "birthday").forEach {
                assert(result[it] != null)
            }
        }
    }

    @Test
    fun testCreateOneJSONArrayWithAttributes() {
        val results = factory[Dog::class].createJSON(3, fixedAttributes)

        assert(results.count() == 3)
        results.forEach { result ->
            assert(result["name"] == fixedAttributes.name)
            assert(result["age"] == fixedAttributes.age)
        }
    }

    @Test
    fun testCreateManyObjectsWithAssociatedJSONArray() {
        val results = factory[Dog::class].createWithJSON(3)

        assert(results.count() == 3)
        results.forEach { result ->
            assert(result.JSON["name"] == result.obj.name)
            assert(result.JSON["age"] == result.obj.age)
        }
    }

    @Test
    fun testCreateManyObjectsWithAssociatedJSONArrayWithAttributes() {
        val results = factory[Dog::class].createWithJSON(3, fixedAttributes)

        assert(results.count() == 3)
        results.forEach { result ->
            assert(result.obj.name == fixedAttributes.name)
            assert(result.obj.age == fixedAttributes.age)

            assert(result.JSON["name"] == fixedAttributes.name)
            assert(result.JSON["age"] == fixedAttributes.age)
        }
    }

    @Test
    fun testMappingWithGson() {
        val expected = factory[Dog::class].createWithJSON(3)
        val JSON = gson.toJson(expected.map { it.JSON })
        val results = gson.fromJson<Array<Dog>>(JSON, object: TypeToken<Array<Dog>>() {}.type)

        assert(results.size == expected.size)
        results.withIndex().forEach {
            assert(it.value.name == expected[it.index].obj.name)
            assert(it.value.age == expected[it.index].obj.age)
        }
    }
}