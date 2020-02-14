package org.andreadelfante.datafixture.kotlin.java.exceptions

class MissingFixtureAssociationException(clazz: Class<*>, name: Any)
    : RuntimeException("This fixture '$name' for '${clazz.simpleName}' class is missing. Please define some in your FixtureFactory.")