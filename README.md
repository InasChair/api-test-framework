# API Test Automation Framework with RestAssured

## 📋 Project Overview
Comprehensive API test automation framework built with RestAssured for testing RESTful APIs. This project demonstrates end-to-end API testing capabilities including CRUD operations, data validation, and multi-step workflows.

## 🛠️ Technology Stack
- **Language:** Java 17
- **Build Tool:** Maven
- **Testing Framework:** TestNG 7.7.1
- **API Testing:** RestAssured 5.3.0
- **API Under Test:** JSONPlaceholder (https://jsonplaceholder.typicode.com)

## ✨ Features
- Complete CRUD operation testing (Create, Read, Update, Delete)
- Positive and negative test scenarios
- Dynamic data extraction and reuse
- Multi-step workflow testing
- Query parameter and path parameter handling
- Comprehensive response validation
- Detailed logging for debugging

## 📦 Test Coverage

### User Management
- Get all users
- Get single user by ID
- Get non-existent user (negative test)
- Create new user
- Update existing user
- Delete user

### Posts & Comments
- Multi-step workflow (Create → Read → Delete)
- Extract and validate user posts
- Verify comment ownership

### Albums & Photos
- Get albums
- Get specific album
- Retrieve photos by album ID

## 🚀 How to Run

### Prerequisites
- Java 17 or higher
- Maven 3.6+

### Execution
```bash
# Run all tests
mvn clean test

# Run specific test class
mvn test -Dtest=FirstApiTest
```

## 📁 Project Structure
```
api-test-framework/
├── src/
│   └── test/
│       └── java/
│           ├── tests/
│           │   └── FirstApiTest.java
│           ├── utils/
│           └── config/
├── pom.xml
└── README.md
```

## 🎯 Learning Objectives
This framework was built as part of a 6-month roadmap to advance from QA Engineer to Senior SDET, focusing on:
- API test automation best practices
- RestAssured framework mastery
- Test design patterns
- CI/CD integration readiness

## 👤 Author
**Inas** - QA Engineer at Deezer

## 📈 Next Steps
- Refactor into BaseTest and utility classes
- Add configuration management for multiple environments
- Implement data-driven testing
- Integrate Allure reporting
- Add CI/CD pipeline integration

## 📝 License
This project is for educational and portfolio purposes.
```

### **3. Update LinkedIn**

Post something like:
```
🚀 Day 1 of my API Test Automation journey!

Just built my first RestAssured framework from scratch with:
✅ 11 comprehensive test cases
✅ Full CRUD operation coverage
✅ Multi-step workflow testing
✅ Dynamic data extraction

Excited to continue building this into a production-ready framework!

#APITesting #TestAutomation #QA #RestAssured #Java #SoftwareTesting
```

---

## **🎓 Reflection Questions:**

Before our next session, think about:
1. What part of today's session was most challenging?
2. What clicked the easiest for you?
3. What are you most excited to learn next?

---

## **📅 Next Session Preview - Framework Organization**

We'll transform your tests from a single file into a professional framework:
```
api-test-framework/
├── src/test/java/
│   ├── base/
│   │   └── BaseTest.java          // Common setup
│   ├── tests/
│   │   ├── UserTests.java         // User-specific tests
│   │   ├── PostTests.java         // Post-specific tests
│   │   └── AlbumTests.java        // Album-specific tests
│   ├── utils/
│   │   ├── ApiHelper.java         // Reusable API methods
│   │   └── TestDataBuilder.java  // Test data creation
│   └── config/
│       └── Configuration.java     // Environment config
└── testng.xml                      // Test suite configuration