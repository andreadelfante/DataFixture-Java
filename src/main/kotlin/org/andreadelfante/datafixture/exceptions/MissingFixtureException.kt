package org.andreadelfante.datafixture.exceptions

class MissingFixtureException(clazz: Class<*>)
    : RuntimeException("Missing fixtures for '${clazz.simpleName}' class. Please define some in your FixtureFactory.")