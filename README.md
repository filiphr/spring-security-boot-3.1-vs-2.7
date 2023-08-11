# Security Differences between Spring Boot 2.7 and Spring Boot 3.1 

When using Spring Boot 2.7.13 the [`TestRestControllerTest`](src/test/java/com/example/demo/TestRestControllerTest.java) is green.
However, when using Spring Boot 3.1.2 the test fails, more specifically the following tests fails:

* `ignoredUnknownAuthenticated` - in 2.7 the status code is HTTP 403, with 3.1 it is HTTP 401
* `ignoredForbiddenAuthenticated` - in 2.7 the status code is HTTP 403, with 3.1 it is HTTP 401
* `ignoredOkAuthenticated` - in 2.7 the status code is HTTP 403, with 3.1 it is HTTP 401
* `allowedUnknownAuthenticated` - in 2.7 the status code is HTTP 404, with 3.1 it is HTTP 401


In order to run the tests with Spring Boot 2.7 the following needs to be done:

* Change the Spring Boot Version to `2.7.13` in [build.gradle](build.gradle)
* Comment out the `//.antMatcher("/ignored-api/*")` in the [`SecurityConfiguration`](src/main/java/com/example/demo/SecurityConfiguration.java) and comment the line below that `.securityMatcher(AntPathRequestMatcher.antMatcher("/ignored-api/*"))`
