package org.andreadelfante.datafixture.test.misc

/**
 * A container of attributes, used to override default generation of value.
 */
open class FixtureAttributes {
    private var attributes: MutableMap<Any, Any> = HashMap()

    /**
     * Get an attribute with its key.
     * @param key the key associated with the value
     * @return a value or null if not presents
     */
    operator fun <T> get(key: Any): T? = attributes[key] as T?

    /**
     * Get an attribute with its key.
     * @param key the key associated with the value
     * @param defaultValue a default value
     * @return a value
     */
    operator fun <T> get(key: Any, defaultValue: T): T =
            attributes[key] as T? ?: defaultValue

    /**
     * Set an attribute with its key.
     * @param key the key associated with the value
     * @param value the value
     */
    operator fun <T> set(key: Any, value: T?) {
        if (value != null) {
            attributes[key] = value as Any
        }
    }

    override fun toString(): String = attributes.toString()
}