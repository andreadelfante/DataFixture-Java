package org.andreadelfante.datafixture.resolvers

import org.andreadelfante.datafixture.builders.FixtureBuilder
import kotlin.reflect.KClass

/**
 * This interface defines a resolver to get a fixture.
 */
interface FixtureResolver {

    /**
     * Resolve a fixture related to the object class
     * @param clazz the clazz
     * @param name the name of the fixture
     * @return a fixture builder of the specified clazz
     */
    operator fun <T : Any> get(clazz: Class<T>, name: Any): FixtureBuilder<T>

    /**
     * Resolve a fixture related to the object class
     * @param clazz the clazz
     * @return a fixture builder of the specified clazz
     */
    operator fun <T : Any> get(clazz: Class<T>): FixtureBuilder<T> = get(clazz, DEFAULT_NAME)

    /**
     * Resolve a fixture related to the object class
     * @param clazz the clazz
     * @param name the name of the fixture
     * @return a fixture builder of the specified clazz
     */
    operator fun <T : Any> get(clazz: KClass<T>, name: Any): FixtureBuilder<T> = get(clazz.java, name)

    /**
     * Resolve a fixture related to the object class
     * @param clazz the clazz
     * @return a fixture builder of the specified clazz
     */
    operator fun <T : Any> get(clazz: KClass<T>): FixtureBuilder<T> = get(clazz.java, DEFAULT_NAME)

    companion object {
        const val DEFAULT_NAME = "default"
    }
}


