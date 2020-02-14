package org.andreadelfante.datafixture.test.misc

import org.junit.Before
import org.junit.Test

class FixtureAttributesTest {
    companion object {
        const val KEY = "KEY"
        const val UNEXISTED_KEY = "UNEXISTED_KEY"
        const val EXISTED_KEY_WITH_NULL = "EXISTED_KEY_WITH_NULL"

        const val VALUE = 1
        const val DEFAULT_VALUE = -1
    }

    lateinit var attributes: FixtureAttributes

    @Before
    fun setUp() {
        attributes = FixtureAttributes()

        attributes[KEY] = VALUE
        attributes[EXISTED_KEY_WITH_NULL] = null
    }

    @Test
    fun testGetAttribute() {
        val keyValue: Int = attributes[KEY]!!
        val keyValueDefault: Int? = attributes[KEY, null]

        assert(keyValue == VALUE)
        assert(keyValueDefault == VALUE)

        var unexistedValue: String? = attributes[UNEXISTED_KEY]
        assert(unexistedValue == null)

        unexistedValue = attributes[UNEXISTED_KEY, null]
        assert(unexistedValue == null)

        unexistedValue = attributes[EXISTED_KEY_WITH_NULL]
        assert(unexistedValue == null)

        unexistedValue = attributes[EXISTED_KEY_WITH_NULL, null]
        assert(unexistedValue == null)
    }

    @Test
    fun testGetAttributeWithDefaultValue() {
        assert(attributes[KEY, DEFAULT_VALUE] == VALUE)
        assert(attributes[UNEXISTED_KEY, DEFAULT_VALUE] == DEFAULT_VALUE)
        assert(attributes[EXISTED_KEY_WITH_NULL, VALUE] == VALUE)

        val unexisted: String? = attributes[UNEXISTED_KEY]
        assert(unexisted == null)
    }
}