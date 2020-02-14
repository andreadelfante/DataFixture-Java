package org.andreadelfante.datafixture.builders

import com.github.javafaker.Faker
import org.andreadelfante.datafixture.interfaces.Fixture
import org.andreadelfante.datafixture.misc.FixtureAttributes
import org.andreadelfante.datafixture.resolvers.FixtureResolver
import java.util.*

internal class FixtureFactoryBuilder<T>(private val fixture: Fixture<T>,
                                        private val resolver: FixtureResolver)
    : FixtureBuilder<T> {
    private val faker: Faker = Faker(Locale.getDefault())

    override fun create(number: Int, attributes: FixtureAttributes): List<T> {
        return (1..number).map { fixture.fixture(faker, attributes, resolver) }
    }
}