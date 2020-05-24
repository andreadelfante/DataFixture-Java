package org.andreadelfante.datafixture.exceptions

import java.lang.RuntimeException

class MissingJSONFixtureDefinitionException(clazz: Class<*>) :
        RuntimeException("This fixture '${clazz.name}' is not a subtype of JSONFixture. Please implement this interface to enable JSON fixture generation.")