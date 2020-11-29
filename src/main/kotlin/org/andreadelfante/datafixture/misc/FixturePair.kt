package org.andreadelfante.datafixture.misc

/**
 * A tuple of object and its json representation.
 * @param obj the object.
 * @param json the JSON.
 */
data class FixturePair<T>(val obj: T, val json: Map<String, Any?>)