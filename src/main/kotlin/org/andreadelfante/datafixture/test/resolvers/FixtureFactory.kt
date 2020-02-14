package org.andreadelfante.datafixture.test.resolvers

import org.andreadelfante.datafixture.kotlin.java.builders.FixtureBuilder
import org.andreadelfante.datafixture.kotlin.java.builders.FixtureFactoryBuilder
import org.andreadelfante.datafixture.kotlin.java.exceptions.MissingFixtureAssociationException
import org.andreadelfante.datafixture.kotlin.java.exceptions.MissingFixtureException
import org.andreadelfante.datafixture.kotlin.java.interfaces.Fixture
import org.andreadelfante.datafixture.test.resolvers.FixtureResolver.Companion.DEFAULT_NAME
import kotlin.reflect.KClass

/**
 * This class contains all the model fixture definitions.
 * Fixtures provide a convenient way to generate new model instances for testing / seeding your application's database.
 */
open class FixtureFactory : FixtureResolver {
    private var fixtures: MutableMap<Class<*>, MutableMap<Any, Fixture<*>>> = HashMap()

    /**
     * Define a new fixture for the specified object clazz.
     * @param clazz the clazz
     * @param name the name of the fixture
     * @param fixture the fixture to build a new object
     */
    fun <T> define(clazz: Class<T>, name: Any, fixture: Fixture<T>) {
        val associations = fixtures[clazz].orEmpty().toMutableMap()
        associations[name] = fixture
        fixtures[clazz] = associations
    }

    /**
     * Define a new fixture for the specified object clazz.
     * @param clazz the clazz
     * @param fixture the fixture to build a new object
     */
    fun <T> define(clazz: Class<T>, fixture: Fixture<T>) = this.define(clazz, DEFAULT_NAME, fixture);

    /**
     * Define a new fixture for the specified object clazz.
     * @param clazz the clazz
     * @param name the name of the fixture
     * @param fixture the fixture to build a new object
     */
    fun <T : Any> define(clazz: KClass<T>, name: Any = DEFAULT_NAME, fixture: Fixture<T>) =
            this.define(clazz.java, name, fixture)

    /**
     * Define a new fixture for the specified object clazz.
     * @param clazz the clazz
     * @param fixture the fixture to build a new object
     */
    fun <T : Any> define(clazz: KClass<T>, fixture: Fixture<T>) =
            this.define(clazz.java, DEFAULT_NAME, fixture)

    override fun <T : Any> get(clazz: Class<T>, name: Any): FixtureBuilder<T> {
        val associations = fixtures[clazz] ?: throw MissingFixtureException(clazz)
        val fixture = associations[name] ?: throw MissingFixtureAssociationException(clazz, name)
        return FixtureFactoryBuilder(fixture as Fixture<T>, this)
    }
}


