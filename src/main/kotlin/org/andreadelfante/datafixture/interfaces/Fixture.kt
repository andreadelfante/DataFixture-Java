package org.andreadelfante.datafixture.interfaces

import com.github.javafaker.Faker
import org.andreadelfante.datafixture.misc.FixtureAttributes
import org.andreadelfante.datafixture.resolvers.FixtureResolver

/**
 * This interface defines the rules to create an object.
 */
interface Fixture<T> {

    /**
     * This function creates a fixture object.
     * @param faker a faker object to create some random values.
     * @param attributes it contains attributes to override object fields.
     * @param resolver it resolves a specific fixture for nested fixture creations.
     * @return a new object.
     */
    fun fixture(faker: Faker, attributes: FixtureAttributes, resolver: FixtureResolver): T
}