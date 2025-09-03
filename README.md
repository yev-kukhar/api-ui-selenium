### Test Automation Framework
This project is a demonstration of automation testing using Java, Selenium, and JUnit 5. It includes reporting with Allure Report and Maven Wrapper for seamless build management.

#### Key Technologies:
* **Java 17**
* **Maven Wrapper (`mvnw`) 3.6.3**
* **Selenium WebDriver 4.34.0**
* **JUnit 5.13.1**
* **Allure Report 2.29.0**
* **Lombok 1.18.38**
* **Logback 1.5.18**

#### Project Structure (tree):
```
├── LICENSE
├── README.md
├── mvnw
├── mvnw.cmd
├── pom.xml
└── src
    ├── main
    │ ├── java
    │ │ └── org
    │ │     └── yevhenii
    │ │         ├── ConfigReader.java
    │ │         └── api
    │ │             ├── ApiEngine.java
    │ │             └── models
    │ │                 ├── privatbank
    │ │                 │ ├── ExchangeArchiveResponse.java
    │ │                 │ ├── ExchangeRate.java
    │ │                 │ └── MockUserPrivatbank.java
    │ │                 └── typicode
    │ │                     └── Post.java
    │ └── resources
    │     └── config.properties
    └── test
        ├── java
        │ └── org
        │     └── yevhenii
        │         ├── Utils.java
        │         ├── api_test
        │         │ ├── JsonServerTest.java
        │         │ ├── PrivatbankTest.java
        │         │ ├── TypicodeTest.java
        │         │ ├── models
        │         │ │ ├── jsonserver
        │         │ │ │ └── Book.java
        │         │ │ ├── privatbank
        │         │ │ └── typicode
        │         │ │     ├── AddressModel.java
        │         │ │     ├── OrderModel.java
        │         │ │     ├── PostRecordModel.java
        │         │ │     └── User.java
        │         │ └── services
        │         │     ├── ApiServicePrivatbank.java
        │         │     ├── ApiServiceTypicode.java
        │         │     └── JsonServer.java
        │         └── ui_tests
        │             ├── BaseUiTest.java
        │             ├── SearchTest.java
        │             └── pages
        │                 ├── BasePage.java
        │                 └── GooglePage.java
        └── resources
            ├── allure.properties
            └── logback-test.xml

```

```bash
  ./mvnw clean install
  ```


#### Essential Commands:

* **Build the project:**

  ```bash
  ./mvnw clean install
  ```

* **Run all tests:**

  ```bash
  ./mvnw clean test
  ```

* **Run specific tag:**

  ```bash
  ./mvnw clean test -Dgroups=critical
  ```
    ```bash
  ./mvnw clean test -Dgroups=api-tests
  ```    
  ```bash
  ./mvnw clean test -Dgroups=ui-tests
  ```

* **Generate Allure Report:**
  
  ```bash
  allure generate target/allure-results --clean -o target/allure-report
  allure open target/allure-report
  ```

* **Run a specific test class:**

  ```bash
  ./mvnw test -Dtest=MyTestClass
  ```

* **Clean the build directory:**

  ```bash
  ./mvnw clean
  ```

* **Verify dependencies:**

  ```bash
  ./mvnw dependency:tree
  ```

* **Compile the project:**

  ```bash
  ./mvnw compile
  ```

* **Package the project into a JAR file:**

  ```bash
  ./mvnw package
  ```

#### A full fake REST API from a single JSON file:
to install locally:
1. node -v <br>
   npm -v  <br>
2. npm install -g json-server
3. Create a new file named db.json and put data inside, like:
`{
"posts": [
{ "id": 1, "title": "json-server", "author": "typicode" }
],
"comments": [
{ "id": 1, "body": "some comment", "postId": 1 }
]
}`
4. Run server: 
  ```bash
  json-server --watch db.json
  ```
5. By default, it runs on http://localhost:3000
6. Available requests: <br>
**GET**: Go to http://localhost:3000/posts in your browser to see your data. <br>
**POST**: request to http://localhost:3000/posts with a new post object in the body. The new post will be added to your db.json file.<br>
payload example:
`{
   "title": "My new post",
   "author": "UserYK"
}`<br>

id value will be assigned automatically <br>
   **PUT**: Send a PUT request to http://localhost:3000/posts/1 to update the post with ID 1. The changes will be saved to your file
