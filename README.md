# 🚀 API Test Automation Framework

![Java](https://img.shields.io/badge/Java-17-orange?style=flat&logo=java)
![Maven](https://img.shields.io/badge/Maven-3.x-red?style=flat&logo=apache-maven)
![RestAssured](https://img.shields.io/badge/RestAssured-5.3.0-green?style=flat)
![TestNG](https://img.shields.io/badge/TestNG-7.7.1-yellow?style=flat)
![Tests](https://img.shields.io/badge/tests-31%20passing-brightgreen?style=flat)
![Build](https://img.shields.io/badge/build-passing-brightgreen?style=flat)
![CI](https://github.com/InasChair/api-test-framework/actions/workflows/maven.yml/badge.svg)


A professional-grade REST API test automation framework with **31 comprehensive tests** covering complete CRUD operations across 5 API domains.

## 📋 Table of Contents
- [Overview](#overview)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Features](#features)
- [Setup Instructions](#setup-instructions)
- [Running Tests](#running-tests)
- [Test Coverage](#test-coverage)
- [Framework Architecture](#framework-architecture)
- [Key Learnings](#key-learnings)
- [Framework Highlights](#framework-highlights)
- [Framework Metrics](#framework-metrics)
- [Future Enhancements](#future-enhancements)
- [Author](#author)

## 🎯 Overview

This framework provides a scalable and maintainable approach to REST API testing. Built as part of a professional development journey from QA Engineer to Senior SDET, it showcases:

- Clean code architecture
- Separation of concerns (tests, helpers, utilities)
- Reusable components
- Professional test organization
- Industry-standard tools and practices

**API Under Test:** [JSONPlaceholder](https://jsonplaceholder.typicode.com/) - A free fake REST API for testing and prototyping

## 🛠️ Tech Stack

| Technology | Version | Purpose |
|------------|---------|---------|
| Java | 17 | Programming Language |
| Maven | 3.x | Build & Dependency Management |
| RestAssured | 5.3.0 | REST API Testing Library |
| TestNG | 7.7.1 | Testing Framework |
| Hamcrest | 2.1 | Assertion Library |
| Gson | 2.10.1 | JSON Processing |

## 📁 Project Structure
```
api-test-framework/
├── src/
│   └── test/
│       └── java/
│           ├── base/
│           │   └── BaseTest.java              # Common test setup
│           ├── config/
│           │   └── Endpoints.java             # All API endpoints
│           ├── helpers/
│           │   ├── UserHelper.java            # User API operations
│           │   ├── PostHelper.java            # Post API operations
│           │   ├── CommentHelper.java         # Comment API operations
│           │   ├── AlbumHelper.java           # Album API operations
│           │   └── PhotoHelper.java           # Photo API operations
│           ├── tests/
│           │   ├── UserTests.java             # User test cases (6)
│           │   ├── PostTests.java             # Post test cases (7)
│           │   ├── CommentTests.java          # Comment test cases (6)
│           │   ├── AlbumTests.java            # Album test cases (6)
│           │   └── PhotoTests.java            # Photo test cases (6)
│           └── utils/
│               └── TestDataBuilder.java       # Test data generation
├── testng.xml                                  # TestNG suite (5 suites)
├── pom.xml                                     # Maven configuration
└── README.md                                   # Documentation
```

## ✨ Features

### Framework Capabilities
- ✅ **Modular Architecture**: Separation of test logic, helpers, and utilities
- ✅ **Reusable Components**: Helper classes for common API operations
- ✅ **Dynamic Test Data**: Builders for generating test data
- ✅ **Centralized Configuration**: All endpoints defined in one place
- ✅ **Comprehensive Logging**: Detailed test execution logs
- ✅ **Clean Test Reports**: TestNG HTML reports

### Testing Capabilities
- ✅ **CRUD Operations**: Complete Create, Read, Update, Delete testing
- ✅ **Positive Testing**: Happy path scenarios
- ✅ **Negative Testing**: Error handling and edge cases
- ✅ **Data Validation**: Response body, status codes, headers
- ✅ **Multi-step Workflows**: Complex test scenarios
- ✅ **Query Parameters**: Filtering and search operations
- ✅ **Path Parameters**: Dynamic URL handling

## 🚀 Setup Instructions

### Prerequisites
- **Java JDK 17** or higher installed
- **Maven 3.6+** installed
- IDE (IntelliJ IDEA recommended)

### Installation

1. **Clone the repository**
```bash
git clone https://github.com/YOUR_USERNAME/api-test-framework.git
cd api-test-framework
```

2. **Install dependencies**
```bash
mvn clean install
```

3. **Verify setup**
```bash
mvn clean test
```

## 🎮 Running Tests

### Run all tests
```bash
mvn clean test
```

### Run specific test class
```bash
mvn test -Dtest=UserTests
mvn test -Dtest=PostTests
```

### Run with TestNG suite
```bash
mvn test -DsuiteXmlFile=testng.xml
```

### View test reports
After test execution, open:
```
target/surefire-reports/index.html
```
## 📊 Test Coverage

**Total: 31 automated test cases** covering complete CRUD operations across 5 API domains

### User Management Tests (6 tests) ✅
- Get All Users
- Get Single User by ID
- Get Non-Existent User (404 validation)
- Create New User
- Update Existing User
- Delete User

### Post Management Tests (7 tests) ✅
- Get All Posts
- Get Post by ID
- Get Posts by User ID (query parameter filtering)
- Create New Post
- Update Existing Post
- Delete Post
- Get Non-Existent Post (404 validation)

### Comment Management Tests (6 tests) ✅
- Get All Comments
- Get Comment by ID
- Get Comments by Post ID (query parameter filtering)
- Create New Comment
- Update Existing Comment
- Delete Comment

### Album Management Tests (6 tests) ✅
- Get All Albums
- Get Album by ID
- Get Albums by User ID (query parameter filtering)
- Create New Album
- Update Existing Album
- Delete Album

### Photo Management Tests (6 tests) ✅
- Get All Photos
- Get Photo by ID
- Get Photos by Album ID (query parameter filtering)
- Create New Photo
- Update Existing Photo
- Delete Photo

**Execution Time:** ~8 seconds for full suite  
**Success Rate:** 100% (31/31 passing)  
**API Coverage:** Complete JSONPlaceholder API
## 🏗️ Framework Architecture

### Design Patterns Used

1. **Page Object Model (Adapted for APIs)**
   - Helper classes encapsulate API operations
   - Tests interact with helpers, not direct API calls

2. **Builder Pattern**
   - TestDataBuilder creates test data objects
   - Promotes reusability and consistency

3. **DRY Principle**
   - BaseTest eliminates duplicate setup code
   - Endpoints class centralizes URLs

### Helper Methods Example
```java
// Simple, reusable method in UserHelper
public static Response getUserById(int userId) {
    return given()
        .pathParam("userId", userId)
    .when()
        .get(Endpoints.USER_BY_ID)
    .then()
        .statusCode(200)
        .extract()
        .response();
}
```

### Test Example
```java
@Test
public void testGetSingleUser() {
    logTestStart("Get Single User");
    
    Response response = UserHelper.getUserById(1);
    
    assertThat(response.path("id"), equalTo(1));
    assertThat(response.path("name"), notNullValue());
    
    logTestEnd("Get Single User");
}
```

## 🎓 Key Learnings

This project demonstrates proficiency in:

- **API Testing Fundamentals**: HTTP methods, status codes, request/response validation
- **RestAssured Framework**: Given-When-Then syntax, extractors, matchers
- **Test Design**: Positive/negative scenarios, edge cases, data-driven approaches
- **Code Organization**: Modular architecture, separation of concerns
- **Best Practices**: DRY principle, reusability, maintainability
- **Version Control**: Git workflow, meaningful commits

## ✨ Framework Highlights

### Architecture
- **Modular Design**: Separate helpers for each API domain
- **DRY Principle**: Zero code duplication across tests
- **Scalable Structure**: Easy to add new endpoints
- **Clean Separation**: Tests, helpers, config, and utils clearly separated

### Test Coverage
- **31 Comprehensive Tests** across 5 API domains
- **100% Pass Rate** with consistent execution
- **8-Second Execution Time** for full suite
- **Positive & Negative Scenarios** including 404 validations
- **Query Parameter Testing** for filtering operations
- **Complete CRUD Coverage** for all endpoints

### Code Quality
- **Allure Annotations**: Ready for visual reporting
- **Professional Structure**: Industry-standard patterns
- **Reusable Components**: Helper methods eliminate duplication
- **Dynamic Test Data**: Automated generation of test data
- **Clear Documentation**: JavaDoc and inline comments

### Professional Features
- **TestNG Suite Configuration**: Organized test execution
- **Centralized Endpoints**: Single source of truth for URLs
- **Base Test Pattern**: Common setup across all tests
- **Builder Pattern**: Clean test data creation
- **Consistent Naming**: Easy to navigate and maintain

## 💡 Why This Framework Stands Out

### For Recruiters & Hiring Managers

This isn't a tutorial project or a copy-paste exercise. Here's what makes it special:

**1. Comprehensive Coverage**
- Not just basic CRUD - includes filtering, error handling, and edge cases
- 31 tests covering 5 complete API domains
- Real-world test scenarios you'd see in production

**2. Professional Architecture**
- Demonstrates understanding of design patterns
- Scalable structure that grows with requirements
- Production-ready code quality

**3. Technical Depth**
- RESTful API testing best practices
- Proper use of RestAssured framework
- TestNG organization and configuration
- Maven dependency management

**4. Built from Scratch**
- Every line written with understanding
- Shows progression from basic to advanced
- Demonstrates problem-solving and learning ability

**5. Interview-Ready**
- Can explain architecture decisions
- Can walk through test design choices
- Can demonstrate extension capabilities
- Can discuss CI/CD integration plans

### What This Demonstrates

✅ **API Testing Expertise**: Complete understanding of REST APIs, HTTP methods, status codes  
✅ **Framework Design**: Ability to architect scalable automation solutions  
✅ **Code Quality**: Clean, maintainable, professional-level code  
✅ **Best Practices**: Industry-standard patterns and approaches  
✅ **Self-Learning**: Built through research, practice, and iteration  
✅ **Production Mindset**: Ready for real-world projects

## 📈 Framework Metrics

| Metric | Value |
|--------|-------|
| **Total Tests** | 31 |
| **Test Suites** | 5 (Users, Posts, Comments, Albums, Photos) |
| **Helper Classes** | 5 |
| **Pass Rate** | 100% |
| **Execution Time** | ~8 seconds |
| **Code Coverage** | 100% of JSONPlaceholder endpoints |
| **Lines of Code** | ~800+ (excluding comments) |
| **Time to Build** | 2 days (learning + implementation) |

## 🔮 Future Enhancements

- [ ] **Allure Reporting**: Rich, interactive test reports
- [ ] **CI/CD Integration**: GitHub Actions for automated test execution
- [ ] **Environment Configuration**: Support for dev/staging/prod environments
- [ ] **Data-Driven Testing**: TestNG DataProviders for parameterized tests
- [ ] **API Schema Validation**: JSON Schema validation
- [ ] **Performance Testing**: Response time assertions
- [ ] **Database Validation**: Verify data persistence (when applicable)
- [ ] **Authentication**: OAuth, JWT token handling
- [ ] **Parallel Execution**: Faster test execution
- [ ] **Docker Support**: Containerized test execution

## 👤 Author

**Inas**  
QA Engineer   

**Professional Goal**: Advancing from QA to Senior SDET through hands-on framework development and automation expertise.

**Skills Demonstrated**:
- Java Programming
- API Test Automation
- RestAssured Framework
- TestNG
- Maven
- Git & GitHub
- Software Testing Best Practices

---

## 📝 License

This project is created for educational and portfolio purposes.

---

## 🙏 Acknowledgments

- Built as part of a structured 6-month learning roadmap
- API provided by [JSONPlaceholder](https://jsonplaceholder.typicode.com/)
- Inspired by industry best practices and professional SDET workflows

---

**⭐ If you find this project helpful, please consider giving it a star!**
