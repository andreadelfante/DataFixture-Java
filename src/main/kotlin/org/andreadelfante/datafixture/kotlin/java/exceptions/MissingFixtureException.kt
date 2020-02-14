package org.andreadelfante.datafixture.kotlin.java.exceptions

class MissingFixtureException(clazz: Class<*>)
    : RuntimeException("Missing fixtures for '${clazz.simpleName}' class. Please define some in your FixtureFactory.")