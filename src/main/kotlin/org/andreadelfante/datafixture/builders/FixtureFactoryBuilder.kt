package org.andreadelfante.datafixture.builders

import com.github.javafaker.Faker
import org.andreadelfante.datafixture.exceptions.MissingJSONFixtureDefinitionException
import org.andreadelfante.datafixture.interfaces.Fixture
import org.andreadelfante.datafixture.interfaces.JSONFixture
import org.andreadelfante.datafixture.misc.FixtureAttributes
import org.andreadelfante.datafixture.misc.FixtureTuple
import org.andreadelfante.datafixture.resolvers.FixtureResolver
import java.util.*

internal class FixtureFactoryBuilder<T>(private val fixture: Fixture<T>,
                                        private val resolver: FixtureResolver)
    : FixtureBuilder<T> {
    private val faker: Faker = Faker(Locale.getDefault())

    override fun create(number: Int, attributes: FixtureAttributes): List<T> {
        return (1..number).map { fixture.fixture(faker, attributes, resolver) }
    }

    override fun createJSON(number: Int, attributes: FixtureAttributes): List<Map<String, Any>> {
        return (1..number).map {
            val obj = fixture.fixture(faker, attributes, resolver)
            jsonFixture().jsonFixture(obj, resolver)
        }
    }

    override fun createJSON(objs: List<T>): List<Map<String, Any>> {
        return objs.map { jsonFixture().jsonFixture(it, resolver) }
    }

    override fun createWithJSON(number: Int, attributes: FixtureAttributes): List<FixtureTuple<T>> {
        return (1..number).map {
            val obj = fixture.fixture(faker, attributes, resolver)
            val json = jsonFixture().jsonFixture(obj, resolver)
            FixtureTuple(obj, json)
        }
    }

    private fun jsonFixture(): JSONFixture<T> {
        if (fixture is JSONFixture<T>) {
            return fixture
        }
        throw MissingJSONFixtureDefinitionException(fixture.javaClass)
    }
}