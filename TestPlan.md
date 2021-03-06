# Test plan

- [Test plan](#Test-plan)
  - [1. Introduction](#1-Introduction)
    - [1.1 Purpose](#11-Purpose)
    - [1.2 Scope](#12-Scope)
    - [1.3 Intended Audience](#13-Intended-Audience)
    - [1.4 Document Terminology and Acronyms](#14-Document-Terminology-and-Acronyms)
    - [1.5 References](#15-References)
  - [2. Evaluation Mission and Test Motivation](#2-Evaluation-Mission-and-Test-Motivation)
    - [2.1 Background](#21-Background)
    - [2.2 Evaluation Mission](#22-Evaluation-Mission)
    - [2.3 Test Motivators](#23-Test-Motivators)
  - [3. Target Test Items](#3-Target-Test-Items)
  - [4. Outline of Planned Tests](#4-Outline-of-Planned-Tests)
    - [4.1 Outline of Test Inclusions](#41-Outline-of-Test-Inclusions)
    - [4.2 Outline of Other Candidates for Potential Inclusion](#42-Outline-of-Other-Candidates-for-Potential-Inclusion)
    - [4.3 Outline of Test Exclusions](#43-Outline-of-Test-Exclusions)
  - [5. Test Approach](#5-Test-Approach)
    - [5.1 Testing Techniques and Types](#51-Testing-Techniques-and-Types)
      - [5.1.1 Business Logic Testing](#511-Business-Logic-Testing)
      - [5.1.2 User Interface Testing](#512-User-Interface-Testing)
      - [5.1.3 API Testing](#513-API-Testing)
  - [6. Entry and Exit Criteria](#6-Entry-and-Exit-Criteria)
    - [6.1 Test Plan](#61-Test-Plan)
      - [6.1.1 Test Plan Entry Criteria](#611-Test-Plan-Entry-Criteria)
      - [6.1.2 Test Plan Exit Criteria](#612-Test-Plan-Exit-Criteria)
  - [7. Deliverables](#7-Deliverables)
  - [7.1 Test Evaluation Summaries](#71-Test-Evaluation-Summaries)
  - [7.2 Reporting on Test Coverage](#72-Reporting-on-Test-Coverage)
  - [7.3 Perceived Quality Reports](#73-Perceived-Quality-Reports)
  - [7.4 Incident Logs and Change Requests](#74-Incident-Logs-and-Change-Requests)
  - [7.5 Smoke Test Suite and Supporting Test Scripts](#75-Smoke-Test-Suite-and-Supporting-Test-Scripts)
  - [8. Testing Workflow](#8-Testing-Workflow)
  - [9. Environmental Needs](#9-Environmental-Needs)
    - [9.1 Base System Hardware](#91-Base-System-Hardware)
    - [9.2 Base Software Elements in the Test Environment](#92-Base-Software-Elements-in-the-Test-Environment)
    - [9.3 Productivity and Support Tools](#93-Productivity-and-Support-Tools)
  - [10. Responsibilities, Staffing, and Training Needs](#10-Responsibilities-Staffing-and-Training-Needs)
    - [10.1 People and Roles](#101-People-and-Roles)
    - [10.2 Staffing and Training Needs](#102-Staffing-and-Training-Needs)
  - [11. Iteration Milestones](#11-Iteration-Milestones)
  - [12. Risks, Dependencies, Assumptions, and Constraints](#12-Risks-Dependencies-Assumptions-and-Constraints)
  - [13. Management Process and Procedures](#13-Management-Process-and-Procedures)
    - [13.1 Reporting and Escalation](#131-Reporting-and-Escalation)

## 1. Introduction

### 1.1 Purpose

The purpose of the Iteration Test Plan is to gather all of the information necessary to plan and control the test effort for a given iteration. It describes the approach to testing the software.
The Test Plan for FlashCardCommunity supports the following objectives:

-   Identifying the items that should be targeted by the tests.
-   Identifying the motivation for and ideas behind the test areas to be covered.
-   Outlining the testing approach that will be used.
-   Identifying the required resources and provides an estimate of the test efforts.

### 1.2 Scope

This test plan will assure the functionality of the application's front end, back end and the communication between the two.
This document shows the following types of testing: UI-, API and Unit Testing.

### 1.3 Intended Audience

The intended audience of this test plan are the developers themself. This document is on a technical level so it is for more advanced readers who need the necessary background knowledge.

### 1.4 Document Terminology and Acronyms

| Abbr | Abbreviation                        |
| ---- | ----------------------------------- |
| API  | Application Programmable Interface  |
| CI   | Continuous Integration              |
| CD   | Continuous Delivery/Deployment      |
| n/a  | not applicable                      |
| SRS  | Software Requirements Specification |
| TBD  | to be determined                    |
| UI   | User Interface                      |

### 1.5 References

| Title                                                                                                                |    Date    | Publishing organization |
| -------------------------------------------------------------------------------------------------------------------- | :--------: | ----------------------- |
| [FlashCardCommunity Blog](https://flashcardcommunity.wordpress.com/)                                                 | 2018-10-XX | Team FlashCardCommunity |
| [Repository on GitHub](https://github.com/phoenixfeder/fc-com)                                                       | 2018-10-XX | Team FlashCardCommunity |
| [SRS](./SRS.md)                                                                                                      | 2018-10-XX | Team FlashCardCommunity |
| [UC Register](https://github.com/phoenixfeder/fc-com/blob/master/UseCases/Register/Register.md)                      | 2018-11-18 | Team FlashCardCommunity |
| [UC Login](https://github.com/phoenixfeder/fc-com/blob/master/UseCases/Login/Login.md)                               | 2018-11-18 | Team FlashCardCommunity |
| [UC Logout](https://github.com/phoenixfeder/fc-com/blob/master/UseCases/Logout/Logout.md)                            | 2018-11-18 | Team FlashCardCommunity |
| [UC Edit Profile](https://github.com/phoenixfeder/fc-com/blob/master/UseCases/EditProfile/EditProfile.md)            | 2018-11-18 | Team FlashCardCommunity |
| [UC Close Account](https://github.com/phoenixfeder/fc-com/blob/master/UseCases/CloseAccount/CloseAccount.md)         | 2018-11-18 | Team FlashCardCommunity |
| [UC Flashcards](https://github.com/phoenixfeder/fc-com/blob/master/UseCases/FlashCard/FlashCard.md)                  | 2019-04-07 | Team FlashCardCommunity |
| [UC Flashcardboxes](https://github.com/phoenixfeder/fc-com/blob/master/UseCases/FlashcardBox/FlashcardBox.md)        | 2019-04-07 | Team FlashCardCommunity |
| [UC Sharing](https://github.com/phoenixfeder/fc-com/blob/master/UseCases/ShareFlashcardBoxes/ShareFlashcardBoxes.md) | 2019-04-07 | Team FlashCardCommunity |
| [UC Learning](https://github.com/phoenixfeder/fc-com/blob/master/UseCases/Learning/Learning.md)                      | 2019-04-07 | Team FlashCardCommunity |
| [UC FAQ](https://github.com/phoenixfeder/fc-com/blob/master/UseCases/FAQ/FAQ.md)                                     | 2019-04-07 | Team FlashCardCommunity |

## 2. Evaluation Mission and Test Motivation

### 2.1 Background

Testing serves to ensure that the written code does what it is intended to do. It also prevents future code changes to break existing functionality unnoticed. Code coverage can give us information about how well our application is covered with tests.
In the context of integration testing can also prevent broken software states to be merged into secured VC branches and only stable versions of our project will be deployed.

### 2.2 Evaluation Mission

Testing is a crucial phase in the development cycle. It is necessary in order to fix technical bugs and important functional problems. With TDD tests are written before the functional code is implemented. This helps preventing bugs before they even occur.

### 2.3 Test Motivators

The tests are done to ensure quality and mitigate risks and fulfill functional requirements. Their purpose is to provide stability for our application and to detect bugs faster than by manual testing.

## 3. Target Test Items

The following lists represent the tested parts of this application:

-   ReactJS frontend / Webapp (UI and Business Logic)
-   Java Spring Server backend (APIs)

## 4. Outline of Planned Tests

### 4.1 Outline of Test Inclusions

_Frontend: ReactJS_:

-   UI testing
-   Unit testing

_Backend: Spring Boot Application_:

-   API testing

### 4.2 Outline of Other Candidates for Potential Inclusion

n/a

### 4.3 Outline of Test Exclusions

Due missing time and ressources we won't focus on the following:

-   Stress test
-   Load/performance tests
-   Usability tests

Not testing stress, performance etc. does not mean that we will not think about such things when developing!

## 5. Test Approach

### 5.1 Testing Techniques and Types

#### 5.1.1 Business Logic Testing

Business logic testing in our frontend ensures that the transition from one state to another is correct. This means that reducers (the part of the code that is in charge for changing the state. Therefore small parts of the sourcecode are tested independently.

|                        | Description                                                                                                              |
| ---------------------- | ------------------------------------------------------------------------------------------------------------------------ |
| Technique Objective    | Test application automated from the perspective of the user through Business Logic Test                                  |
| Technique              | Writing Jest test files that are similar to JUnit tests [Furhter information](https://jestjs.io/docs/en/getting-started) |
| Oracles                | Expect that the steps of the tests are logged to the command line and be marked as successful.                           |
| Required Tools         | Dependencies of [Jest](https://jestjs.io/)                                                                               |
| Success Criteria       | All business logic tests pass.                                                                                           |
| Special Considerations | -                                                                                                                        |

![Jest results](graphics/JestTestResult.PNG)

#### 5.1.2 User Interface Testing

By UI testing the application is tested from the perspective of the user. The goal of UI testing is to ensure that the UI behaves as expected.

|                        | Description                                                                                                                                                                                                                                                                       |
| ---------------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Technique Objective    | Test application automated from the perspective of the user through UI Test                                                                                                                                                                                                       |
| Technique              | Writing Gherkin `.feature` files with clearly defined steps and the expected result. Then running Testcafé to automatically check if they succeed or fail.  [Further information](https://flashcardcommunity.wordpress.com/2018/11/04/hw-week-5-acceptance-tests-with-cucumber/). |
| Oracles                | Expect that the steps of the tests are logged to the command line and be marked as successful.                                                                                                                                                                                    |
| Required Tools         | Dependencies of [Cucumber](https://www.npmjs.com/package/cucumber) and [Testcafé](https://www.npmjs.com/package/testcafe)                                                                                                                                                         |
| Success Criteria       | All UI tests pass.                                                                                                                                                                                                                                                                |
| Special Considerations | -                                                                                                                                                                                                                                                                                 |

![UI test results](graphics/CucumberTestResult.PNG)

#### 5.1.3 API Testing

API Testing is used to ensure that the communication between the front- and backend is working as expected. API Testing is commonly a part of Integration Testing which means that multiple parts of the application are tested together.

|                        | Description                                                                                                            |
| ---------------------- | ---------------------------------------------------------------------------------------------------------------------- |
| Technique Objective    | Test backend APIs automated.                                                                                           |
| Technique              | Write a Postman collection of API queries and test if they all succeed by running the generated JSON file with Newman. |
| Oracles                | Expect that the steps of the tests are logged to the command line and be marked as successful.                         |
| Required Tools         | [Postman](https://www.getpostman.com/) and [Newman](https://www.npmjs.com/package/newman)                              |
| Success Criteria       | All API tests pass.                                                                                                    |
| Special Considerations | -                                                                                                                      |

![API test results](graphics/PostmanTestResult.png)

## 6. Entry and Exit Criteria

### 6.1 Test Plan

#### 6.1.1 Test Plan Entry Criteria

The whole test process is started as soon changes are pushed to our GitHub repository.

#### 6.1.2 Test Plan Exit Criteria

The test process ends as soon as all tests succeeded or one failed.

## 7. Deliverables

## 7.1 Test Evaluation Summaries

The project contains tests in the front- and backend that run each push to our GitHub repository. On top of that, we also use Codacy and SonarCloud to check our code quality (depending on specific style rules) which is also getting triggered each commit.

-   [Codacy](https://app.codacy.com/project/FlashCardCommunity/fc-com/dashboard), Badge: ![Badge](https://api.codacy.com/project/badge/Grade/95260a54312140bf99814090ab89a940?isInternal=true)
-   [SonarCloud](https://sonarcloud.io/dashboard?id=Nifori_fc-com), Badge: ![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=Nifori_fc-com&metric=alert_status)

-   [Travis CI](https://travis-ci.org/phoenixfeder/fc-com), Badge: ![Badge](https://travis-ci.org/phoenixfeder/fc-com.svg?branch=master)

## 7.2 Reporting on Test Coverage

Test Coverage can be send to Codecov when running tests and therefor be displayed as Badge in GitHub or be seen in Coddcov directly. Our main ressource here will be our Jest tests.

-   [Codecov](<>), Badge: ![codecov](https://codecov.io/gh/phoenixfeder/fc-com/branch/master/graph/badge.svg)

## 7.3 Perceived Quality Reports

-   [Codacy](https://app.codacy.com/project/FlashCardCommunity/fc-com/dashboard)
-   [SonarCloud](https://sonarcloud.io/dashboard?id=Nifori_fc-com)

## 7.4 Incident Logs and Change Requests

Codacy as well as TravisCI is able to integrate in our GitHub repository. For each pull request created, Codacy will give information about the Codequality and TravisCI will tell if the build succeeds or fail.

![pr](graphics/ci.png)

## 7.5 Smoke Test Suite and Supporting Test Scripts

Every new push to any branch triggers an execution in our CI/CD pipeline. This way we have an immediate feedback when a new commit breaks any functionallity of our application or limits the users actions.

## 8. Testing Workflow

Our testing workflow is like the following:

1) Local testing on the developers machines
2) Commits trigger building on TravisCI
3) With each pull requests code quality will also be analyzed

## 9. Environmental Needs

### 9.1 Base System Hardware

The following table shows the different base system used in our testing process.

| Resource      | Quantity | Name and Type                                       |
| ------------- | :------: | --------------------------------------------------- |
| CI/CD server  |     1    | Travis CI                                           |
| Local machine |     X    | Local machines (developers notebook, computer etc.) |

### 9.2 Base Software Elements in the Test Environment

The following table shows all software used in our testing process.

| Software Element Name | Type and Other Notes            |
| --------------------- | ------------------------------- |
| Terminal              | Test runner                     |
| Jest                  | JavaScript testing library      |
| Cucumber              | Comprehensible step definitions |
| TestCafé              | UI testing library              |
| Postman               | API Test Runner                 |

### 9.3 Productivity and Support Tools

The following table shows all tools used in our testing process.

| Tool Category or Type    | Tool Brand Name                                 |
| ------------------------ | ----------------------------------------------- |
| Repository               | [github.com](http://github.com/)                |
| Quality, Metrics         | [Codacy](https://app.codacy.com/)               |
| Quality, Metrics         | [SonarCloud](https://sonarcloud.io/)            |
| Coverage                 | [Codecov](https://codecov.io/)                  |
| CI/CD Service            | [Travis CI](http://travis-ci.org/)              |
| Fake SMTP to test E-Mail | [FakeSMTP](https://github.com/Nilhcem/FakeSMTP) |

## 10. Responsibilities, Staffing, and Training Needs

### 10.1 People and Roles

| Role                      |  Person Assigned | Specific Responsibilities or Comments                                    |
| ------------------------- | :--------------: | ------------------------------------------------------------------------ |
| Test Manager              |  Moritz, Sascha  | Provides management oversight.                                           |
| Test Designer             |     Everyone     | Defines the technical approach to the implementation of the test effort. |
| Test System Administrator | Moritz, Benjamin | Ensures test environment and assets are managed and maintained.          |

### 10.2 Staffing and Training Needs

n/a

## 11. Iteration Milestones

We want to keep over 20% code coverage.

Our metrics-tools informed us that the verifyResetPassword-Method from the AccountServicePasswordReset-Class had a high score on Coupling-Between-Object-Metrics and the editAccount-Method had a high n-path-complexity.
So we according to our TestPlan we refactored those Methods to satisfy the quality requirements.

They also noticed that we used the term "password" as part of our variables, which could mean hardcoded credentials. We investigated that issue, but came to the conclusion, that there are no security issues and disabled that rule manually.

## 12. Risks, Dependencies, Assumptions, and Constraints

| Risk                                                                            | Mitigation Strategy          | Contingency (Risk is realized) |
| ------------------------------------------------------------------------------- | ---------------------------- | ------------------------------ |
| Travis does block traffic like outgoing mail traffic (needed for API & UI test) | Use a local fake mail server | Publish new .travis.yml        |
| UI tests fail                                                                   | Refactor tests               | Push refactored test           |
| API tests fail                                                                  | Refactor tests               | Push refactored test           |
| Business logic tests fail                                                       | Refactor tests               | Push refactored test           |
| Unit tests fail                                                                 | Refactor tests               | Push refactored test           |

## 13. Management Process and Procedures

### 13.1 Reporting and Escalation

-   Travis CI logs all occuring error during his build process
-   If a build fails all team members get notified by discord
