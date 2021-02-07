# spring-mvc
Spring MVC with Job Management use case


Import as Maven project

Run JUnit by right clicking the project and Run with JUnit runner

In order to run the application, open JobInvoker Main class and right click and "Run As Java Application"

Job will be analysed and executed in 5 minutes interval. It can be configurable in JobInvoker

New Jobs can be added using jobdetails.json file. Please provide your data in json format

As of now, Job Management supports job scheduling based on Month, Day and Hour.
But it can be extended to Minutes, Year by adding rules in JobRuleEngine.

At the moment, InMemory Map database is used for storing Job Status.
 
At the moment, Job Management is capable of running 5 jobs in parallel and it be configurable in JobProcessor
 
 
