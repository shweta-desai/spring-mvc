# spring-mvc
Spring MVC with Job Management use case


1. Import as Maven project

2. Run JUnit by right clicking the project and Run with JUnit runner

3. In order to run the application, open JobInvoker Main class and right click and "Run As Java Application"

4. Job will be analysed and executed in 5 minutes interval. It can be configurable in JobInvoker

5. New Jobs can be added using jobdetails.json file. Please provide your data in json format

6. As of now, Job Management supports job scheduling based on Month, Day and Hour.
   But it can be extended to Minutes, Year by adding rules in JobRuleEngine.

7. At the moment, InMemory Map database is used for storing Job Status.
 
8. At the moment, Job Management is capable of running 5 jobs in parallel and it be configurable in JobProcessor

9. Design diagram is attached in Issues tab
 
 
