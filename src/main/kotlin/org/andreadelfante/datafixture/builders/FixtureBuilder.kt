package org.andreadelfante.datafixture.builders

import org.andreadelfante.datafixture.misc.FixtureAttributes

/**
 * This interface provides functions to create fixtures.
 */
interface FixtureBuilder<T> {

    /**
     * Create a collection of object from fixture.
     * @param number the count of the objects to generate.
     * @param attributes a dictionary to override fields during generating the objects
     * @return a collection of objects
     */
    fun create(number: Int, attributes: FixtureAttributes): List<T>

    /**
     * Create a collection of object from fixture.
     * @param number the count of the objects to generate.
     * @return a collection of objects
     */
    fun create(number: Int): List<T> = this.create(number, FixtureAttributes())

    /**
     * Create a single object from fixture.
     * @param attributes a dictionary to override fields during generating the objects
     * @return the object
     */
    fun create(attributes: FixtureAttributes): T = this.create(1, attributes).first()

    /**
     * Create a single object from fixture.
     * @return the object
     */
    fun create(): T = this.create(1).first()
}
