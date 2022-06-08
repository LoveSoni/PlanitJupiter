# Planit Jupiter Assessment
This repository contains PlanIt Assessment Automation project which is capable of running on both v1 and v2 jupiter urls.                           
 **v1 url:** https://jupiter.cloud.planittesting.com/#/home     
**v2 url:** https://jupiter2.cloud.planittesting.com/#/

**Installation (pre-requisites)**
1. JDK 1.8+ (make sure Java class path is set)
2. Maven (make sure .m2 class path is set)

**Language and Libraries Used**
* Java8 -> Lamda & streams
* Testng
* Selenium
* WebDriverManager
* Lombok
* Log4j2
* Extent Report

**Browser Supproted** We just need to update the browser name in configuration file and it will automatically install the relevant drivers
* Chrome
* Firefox
* Safari

# Project Structure
```bash
├── pom.xml
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── base
│   │   │   │   └── BaseClass.java
│   │   │   ├── constants
│   │   │   │   ├── Defaults.java
│   │   │   │   ├── InventoryItems.java
│   │   │   │   ├── ItemAmountType.java
│   │   │   │   ├── Messages.java
│   │   │   │   ├── ProjectPath.java
│   │   │   │   └── Urls.java
│   │   │   ├── library
│   │   │   │   └── WebLibrary.java
│   │   │   ├── requests
│   │   │   │   └── JupiterUser.java
│   │   │   ├── sessions
│   │   │   │   └── uisessions
│   │   │   │       ├── SessionManager.java
│   │   │   │       └── WebSession.java
│   │   │   └── utilities
│   │   │       ├── commonutilities
│   │   │       │   └── JavaUtility.java
│   │   │       ├── loggerutilities
│   │   │       │   └── LogUtility.java
│   │   │       └── readerutilities
│   │   │           ├── EnvironmentReader.java
│   │   │           └── PropertyReader.java
│   │   └── resources
│   │       ├── defaultConfig.properties
│   │       ├── environments
│   │       │   └── prod.properties
│   │       └── log4j2.xml
│   └──test
│       └── java
│           ├── testClasses
│           │   └── JupiterClass.java
│           └── testSteps
│               ├── CartSteps.java
│               ├── ContactPageSteps.java
│               ├── HomePageSteps.java
│               └── ShopPageSteps.java
|
└── testng.xml
```
**Supported CI/CD:** We can run test cases from any CI/CD tools e.g. Jenkins/TeamCity/Gitlab/BitBucket.                                    
**Command:** mvn clean test 

# Execution Video

https://user-images.githubusercontent.com/33176624/172653436-fa239b34-e4bd-47d5-a0a3-4ef887ce89a0.mp4
