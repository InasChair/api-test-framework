# Contributing to API Test Framework

## How to Extend

### Adding a New Endpoint

1. **Add endpoint to `config/Endpoints.java`**
```java
public static final String NEW_ENDPOINT = "/new";
public static final String NEW_BY_ID = "/new/{id}";
```

2. **Create Helper class** in `helpers/` package
3. **Create Test class** in `tests/` package extending `BaseTest`
4. **Add to `testng.xml`** suite configuration
5. **Update README.md** with new test count

## Running Tests
```bash
# All tests
mvn clean test

# Specific suite
mvn test -Dtest=UserTests
```

## Code Style

- Use camelCase for methods
- Use PascalCase for classes
- Add JavaDoc for public methods
- Keep methods focused and single-purpose
