package org.andreadelfante.datafixture.builders

import org.andreadelfante.datafixture.misc.FixtureAttributes
import org.andreadelfante.datafixture.misc.FixtureTuple

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

    /**
     * Create a JSON Object from fixture.
     * Please be sure to define `jsonFixtureClosure` or to implement `JSONFixture`, otherwise this function produces an empty JSON Object.
     * @param number the count of the objects to generate.
     * @param attributes a dictionary to override fields during generating the objects
     * @return a JSON array
     */
    fun createJSON(number: Int, attributes: FixtureAttributes): List<Map<String, Any>>

    /**
     * Create a JSON Object from fixture.
     * Please be sure to define `jsonFixtureClosure` or to implement `JSONFixture`, otherwise this function produces an empty JSON Object.
     * @param number the count of the objects to generate.
     * @return a JSON array
     */
    fun createJSON(number: Int): List<Map<String, Any>> = this.createJSON(number, FixtureAttributes())

    /**
     * Create a JSON Object from fixture.
     * Please be sure to define `jsonFixtureClosure` or to implement `JSONFixture`, otherwise this function produces an empty JSON Object.
     * @param attributes a dictionary to override fields during generating the objects
     * @return a JSON object
     */
    fun createJSON(attributes: FixtureAttributes): Map<String, Any> = this.createJSON(1, attributes).first()

    /**
     * Create a JSON Object from fixture.
     * Please be sure to define `jsonFixtureClosure` or to implement `JSONFixture`, otherwise this function produces an empty JSON Object.
     * @return a JSON object
     */
    fun createJSON(): Map<String, Any> = this.createJSON(1, FixtureAttributes()).first()

    /**
     * Create a JSON Object from fixture.
     * Please be sure to define `jsonFixtureClosure` or to implement `JSONFixture`, otherwise this function produces an empty JSON Object.
     * @param objs a list of objects to transform in JSON array.
     * @return a JSON array
     */
    fun createJSON(objs: List<T>): List<Map<String, Any>>

    /**
     * Create a JSON Object from fixture.
     * Please be sure to define `jsonFixtureClosure` or to implement `JSONFixture`, otherwise this function produces an empty JSON Object.
     * @param obj the object to transform in JSON object.
     * @param attributes a dictionary to override fields during generating the objects
     * @return a JSON object
     */
    fun createJSON(obj: T): Map<String, Any> = this.createJSON(listOf(obj)).first()

    /**
     * Create an array of tuples of object and its JSONObject.
     * Please be sure to define `jsonFixtureClosure` or to implement `JSONFixture`, otherwise this function produces an empty JSON Object.
     * @param number the count of the tuples to generate.
     * @param attributes a dictionary to override fields during generating the objects and its JSON object.
     * @return a list of tuples of object and its JSON object.
     */
    fun createWithJSON(number: Int, attributes: FixtureAttributes): List<FixtureTuple<T>>

    /**
     * Create an array of tuples of object and its JSONObject.
     * Please be sure to define `jsonFixtureClosure` or to implement `JSONFixture`, otherwise this function produces an empty JSON Object.
     * @param number the count of the tuples to generate.
     * @return a list of tuples of object and its JSON object.
     */
    fun createWithJSON(number: Int): List<FixtureTuple<T>> = this.createWithJSON(number, FixtureAttributes())

    /**
     * Create an array of tuples of object and its JSONObject.
     * Please be sure to define `jsonFixtureClosure` or to implement `JSONFixture`, otherwise this function produces an empty JSON Object.
     * @param attributes a dictionary to override fields during generating the objects and its JSON object.
     * @return a tuple of object and its JSON object.
     */
    fun createWithJSON(attributes: FixtureAttributes): FixtureTuple<T> = this.createWithJSON(1, attributes).first()

    /**
     * Create an array of tuples of object and its JSONObject.
     * Please be sure to define `jsonFixtureClosure` or to implement `JSONFixture`, otherwise this function produces an empty JSON Object.
     * @return a tuple of object and its JSON object.
     */
    fun createWithJSON(): FixtureTuple<T> = this.createWithJSON(1, FixtureAttributes()).first()
}
