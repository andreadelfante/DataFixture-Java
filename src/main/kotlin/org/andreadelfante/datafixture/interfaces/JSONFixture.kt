package org.andreadelfante.datafixture.interfaces

import org.andreadelfante.datafixture.resolvers.FixtureResolver

/**
 * This interface defines the rules to create a JSON Object from an object.
 */
interface JSONFixture<T> : Fixture<T> {

    /**
     *  This function transforms an object in a JSON Object.
     *  @param obj the object to transform, previously created by this fixture.
     *  @param resolver it resolves a specific fixture for nested fixture creations.
     *  @return the JSON object.
     */
    fun jsonFixture(obj: T, resolver: FixtureResolver): Map<String, Any?>
}