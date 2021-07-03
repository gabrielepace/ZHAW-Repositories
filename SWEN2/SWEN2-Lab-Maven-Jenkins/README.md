SWEN2 Lab - Maven & Jenkins
===========================

## Introduction
This lab is designed to help you become familiar with the Maven build automation tool and the Jenkins Continuous Integration Server that you will be using to manage your projects.

## Objectives
In this excercise you will:
- Setup set up your Maven and Jenkins environment
- Learn to master Maven configuration, lifecycle and goals
- Practice to setup and run integration tasks on a Jenkins Server

## Requirements

**Hardware:** none

**Software:**
- An up to date web browser
- Your preferred text editor
- Basic Maven installation for your platform (see installation section)
- optional: Eclipse to test the Maven Eclipse integration

**Resources:**
- User Account on a SWEN2 lab server with installed Jenkins server
  (Get the server name and account information from your lab assistant)

## Expected results
Complete the tasks given below. Look up the required commands in the available documentation (see [References](#References)). To complete the assignment show your lab assistant the installed task on the Jenkins server.

<!--
## Grading
- none
--> 

## References
Following some references which might help you to complete the tasks:
- [Maven Quick Reference Card][mvnqref]
- [DZone Maven Refcard][mvndzone]
- [Maven Reference Book][mvnref]
- [Maven By Example Book][mvnex]
- [Maven Central Repository][mvncentral]
- [Jenkins Webpage][jenkins-ci]

[mvnqref]:    https://maven.apache.org/guides/MavenQuickReferenceCard.pdf "Maven Quick Reference Card"
[mvndzone]:   http://refcardz.dzone.com/refcardz/apache-maven-2 "DZone Maven Refcard"
[mvnref]:     http://books.sonatype.com/mvnref-book/reference "Maven Reference Book"
[mvnex]:      http://books.sonatype.com/mvnex-book/reference "Maven By Example Book"
[mvncentral]: http://search.maven.org/#browse "Maven Central Repository"
[jenkins-ci]: http://jenkins-ci.org "Jenkins Webpage"

[mvninstall]: http://books.sonatype.com/mvnex-book/reference/installation-sect-maven-install.html
[zhaw-gh]:    https://github.engineering.zhaw.ch/ "ZHAW SoE GitHub Enterprise"
[mvn-archetype]:  http://books.sonatype.com/mvnref-book/reference/archetype-sect-using.html
[mvn-dependency]: http://books.sonatype.com/mvnref-book/reference/pom-relationships-sect-project-dependencies.html
[mvn-tomcat]: http://tomcat.apache.org/maven-plugin-2.1/run-mojo-features.html
[mvn-eclipse]: http://maven.apache.org/plugins/maven-eclipse-plugin/
<!--
##Preparation Work before the Lab
- none
-->
## General Notes
#### Using the command line
Maven is integrated in many IDEs and can be used graphically. To learn how it works it is best to use the *native* tools. In the first two parts of this lab you will therefore primarily use the command line.
 
#### Ask for help
If you are stuck and have a problem you can not solve using the documentation, ask your colleques, the lecturer or lab assistant for help.


## Tasks

This lab consists of three parts:

1. [Setting up Maven](#part1-settingupmaven)
2. [Master Maven](#part2-mastermaven)
3. [Practice Jenkins](#part3-practicejenkins)

### Part 1 - Setting up Maven
#### Installation
Ensure that the command line version of Maven is installed on your computer and that you know how to access it via the command line / terminal interface.

Find some basic info for all Systems in the [install instructions][mvninstall].

The official way to install Maven is to download and unpack the binary ZIP file to a common directory, set the environment variable `M2_HOME` and add the bin directory to your `PATH` environment variable.

##### Windows
Follow process in the [install instructions][mvninstall].

##### Unix (Linux / OS X / Solaris / FreeBSD) manual installation
Download newest apache-maven-x.x.x-bin.zip from <http://maven.apache.org/download.html>.
Open shell:
```
$ sudo unzip ~/apache-maven-x.x.x-bin.zip -d /usr/share/
$ sudo ln -s apache-maven-x.x.x /usr/share/maven
```

Open `~/.profile` (single user) or `/etc/profile` (all users) and add the following lines:

```
export M2_HOME=/usr/share/maven
export PATH=$PATH:$M2_HOME/bin
```

##### Unix (Linux / OS X / Solaris / FreeBSD) installation using package manager
An alternative option to install maven is to use the package manager of the unix system. 

* on DEB based systems (Debian,Ubuntu,...) 
  `$ sudo apt-get install maven` (this is a quite outdated version 3.0.x)
* on RPM based systems (RedHat,CentOS,Fedora,...) exists no official package (use above manual instructions).
* on OS X you can install Maven using a packet manager for OS X like Homebrew or MacPorts.
  Because the packages are usually compiled during installation you need to install Xcode beforehand. This is recommended especially, if you already have Xcode installed or you would like to install also other common unix packages. 
  Homebrew (<http://brew.sh>): 
  `$ brew install maven`
  MacPorts (<http://www.macports.org/install.php>):
  `$ port install maven2`

##### Verify installation
Open a new shell or cmd.exe session and test if maven is available:
```
$ mvn -version
Apache Maven 3.5.0 (ff8f5e7444045639af65f6095c62210b5713f426; 2017-04-03T21:39:06+02:00)
Maven home: /usr/local/Cellar/maven/3.5.0/libexec
Java version: 1.8.0_144, vendor: Oracle Corporation
Java home: /Library/Java/JavaVirtualMachines/jdk1.8.0_144.jdk/Contents/Home/jre
Default locale: en_US, platform encoding: UTF-8
OS name: "mac os x", version: "10.12.6", arch: "x86_64", family: "mac"
```

> This concludes part 1 of the lab. You should now have a working Maven installation.



### Part 2 - Master Maven

In this section you will learn how to use Maven to automate an existing project. This is also called 'mavenizing' a project.

#### Fork and clone your copy of the project on GitHub
Create your personal fork of this repository on the [ZHAW SoE GitHub Server][zhaw-gh]. You will need it to commit/push your updated project and use it on Jenkins.

> **Do NOT try to push to the original repository in the @SWEN2 organization**

Create a local clone on your work computer to continue with the excercise.

#### Create a basic POM file
Your local project directory contains only a `src` folder (beside these instructions).
It contains all the sources which typically will be checked in using version control.

'Mavenizing' an applications means basically to create a Project Object Model (POM) file describing this artifact.

- The first step is to create a basic `pom.xml` file. This can be done easiest using the Maven _archetype_ plugin.
  It is normally used to create new projects (new directory containing a pom.xml and the required files). Because we only need the pom.xml, you should call the `mvn archetype:generate` goal in a temporary directory.
  See the [archetype reference page][mvn-archetype] to see the exact syntax.
  To create the correct values, you should use the following properties:
    - groupId=ch.zhaw.swen2
    - artifactId=mvnlab
    - packageName=com.company (very often identical to the groupId, but must not be)
    - archetypeArtifactId=maven-archetype-webapp
    - interactiveMode=false
  for the rest of the properties default values will be used, if not set.

- Copy `mvnlab/pom.xml` to your project root.
- Check the content of your `pom.xml` file. (Optionally you can change the name and URL e.g. to the GitHub homepage)

- Try to build your project using `mvn compile`. It will produce errors, because it is missing some classes. This will be solved in the next step, when you define the dependencies.
- Before committing to the repository you should add the `target/` directory to `.gitignore`. Because maven will put all generated files by default to `target/`, this will avoid to check in generated files.

> You should avoid to check in _generated_ files into version control.

- Time to commit your first changes.

#### Setting source code endcoding
Since Maven 3.0 it is recommended to specify the required Maven version of the project. This has to do mainly with dependency management of plugins specified in the parent/super pom.

- Add the following block before of the dependencies

```
	<properties>
	    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
	</properties>
```

#### Add dependencies
In this step you will define all the required dependencies. Maven will then automatically resolve the dependencies and download the required artifacts from [Maven Central][mvncentral].

- Usually you would have to go throug your project and find all required artifacts.
- Add the required dependencies to the `<dependencies>` element.
  To make it a bit easier following a list of the working artifacts and versions:
  (See the [dependency reference page][mvn-dependency] to see exact syntax)

| groupId                    | artifactId               | version        | scope    |
|----------------------------|--------------------------|----------------|----------|
| org.springframework        | spring-context           | 4.3.12.RELEASE | compile  |
| org.springframework        | spring-webmvc            | 4.3.12.RELEASE | compile  |
| org.aspectj                | aspectjrt                | 1.8.11         | compile  |
| javax.inject               | javax.inject             | 1              | compile  |
| javax.servlet              | servlet-api              | 2.5            | provided |
| org.apache.geronimo.specs  | geronimo-servlet_3.0_spec| 1.0            | test     |
| javax.servlet.jsp          | jsp-api                  | 2.1            | provided |
| javax.servlet              | jstl                     | 1.2            | compile  |
| org.springframework.data   | spring-data-jpa          | 1.11.8.RELEASE | compile  |
| org.springframework        | spring-jdbc              | 4.3.12.RELEASE | compile  |
| org.hibernate              | hibernate-entitymanager  | 4.3.5.Final    | compile  |
| com.h2database             | h2                       | 1.4.196        | compile  |
| com.fasterxml.jackson.core | jackson-databind         | 2.8.10         | compile  |
| junit                      | junit                    | 4.12           | test     |
| org.springframework        | spring-test              | 4.3.12.RELEASE | test     |
| com.jayway.jsonpath        | json-path                | 2.4.0          | test     |

- What is the reason for the **scope**? what does it achieve?

- Add the following block at the end of the dependencies to configure logging:

```
    <!-- Logging -->
    <dependency>
      <groupId>org.slf4j</groupId>
      <artifactId>slf4j-api</artifactId>
      <version>1.7.25</version>
    </dependency>
    <dependency>
      <groupId>org.slf4j</groupId>
      <artifactId>jcl-over-slf4j</artifactId>
      <version>1.7.25</version>
      <scope>runtime</scope>
    </dependency>
    <dependency>
      <groupId>org.slf4j</groupId>
      <artifactId>slf4j-log4j12</artifactId>
      <version>1.7.25</version>
      <scope>runtime</scope>
    </dependency>
    <dependency>
      <groupId>log4j</groupId>
      <artifactId>log4j</artifactId>
      <version>1.2.15</version>
      <scope>runtime</scope>
      <exclusions>
        <exclusion>
          <groupId>javax.mail</groupId>
          <artifactId>mail</artifactId>
        </exclusion>
        <exclusion>
          <groupId>javax.jms</groupId>
          <artifactId>jms</artifactId>
        </exclusion>
        <exclusion>
          <groupId>com.sun.jdmk</groupId>
          <artifactId>jmxtools</artifactId>
        </exclusion>
        <exclusion>
          <groupId>com.sun.jmx</groupId>
          <artifactId>jmxri</artifactId>
        </exclusion>
      </exclusions>
    </dependency>
```

- What are the exclusions used for? Why are they used here?
  (What happens, if you comment out the <exlusions> block?)

- You can see the full dependency tree using `mvn dependency:tree -Dverbose`

- now it should be possible to compile the sources:
  `mvn clean compile`

- to make sure the tests are compiling (without running them) you can use:
  `mvn test-compile`
  (see the build lifecycle) 

#### Verify the default lifecycles
Now lets test, if the goals of the default, clean and site lifecycles work.

- Cleanup the project by using `mvn clean`
  This will delete the `target` directory, which contains all the generated files.
- What is the comand to generate all class files?
- Let's run the unit tests
  `mvn test`
- What happens, if you run `mvn package`?
- How can you skip the unit test, when you only want to package our artifact?
- Try `mvn install`. What does install do?
  (check `~/.m2/repository/ch/zhaw/swen2/`)

#### Make sure your application builds with Java 8
Maven uses by default Java 5. To change that behaviour we need configure / override the maven-compiler-plugin.

- First add the compiler maven plugin into the `<build><plugins>` block (after `<dependencies>`).

```
  ...
  <build>
    <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-compiler-plugin</artifactId>
        <version>3.6.2</version>
        <configuration>
          <source>1.8</source>
          <target>1.8</target>
        </configuration>
      </plugin>
    </plugins>
  </build>
```

#### Run an embedded tomcat
Maven allows to run web applications in an embedded tomcat (or jetty) container.
This is usefull to run integration tests (like selenium) or test your application manually.

To do this you have to integrate and configure the [maven tomcat plugin][mvn-tomcat].

- First add the tomcat maven plugin into the `<build><plugins>` block (after `<dependencies>`).

```
  ...
  <build>
    <plugins>
      <plugin>
        <groupId>org.apache.tomcat.maven</groupId>
        <artifactId>tomcat7-maven-plugin</artifactId>
        <version>2.2</version>
        <executions>
          <execution>
            <id>start-tomcat</id>
            <goals>
              <goal>run</goal>
            </goals>
            <configuration>
              <fork>true</fork>
            </configuration>
          </execution>
          <execution>
            <id>stop-tomcat</id>
            <goals>
              <goal>shutdown</goal>
            </goals>
          </execution>
        </executions> 
        <configuration>
          <port>8080</port>
          <uriEncoding>UTF-8</uriEncoding>
        </configuration>      
      </plugin>
    </plugins>
  </build>
```

- Now you can start tomcat using `mvn tomcat7:run`.
- It will log the port and URLs of your application.
- To access the test application open <http://localhost:8080/mvnlab/product/searchForm> in your browser

#### Use Maven with Eclipse 
Often you want to manage your projects using maven, but develop in your favourite IDE.
Maven therfore supports integration plugins for the major IDEs (Eclipse, IntelliJ, Netbeans,...)
To use Maven with Eclipse can use the [Maven Eclipse Plugin][mvn-eclipse].
The plugin allows to generate the Eclipse configuration files based on the pom.xml definition.

- Add the eclipse maven plugin into the `<build><plugins>` block.

```
      <plugin>
          <artifactId>maven-eclipse-plugin</artifactId>
          <version>2.10</version>
          <configuration>
              <additionalProjectnatures>
                  <projectnature>org.springframework.ide.eclipse.core.springnature</projectnature>
              </additionalProjectnatures>
              <additionalBuildcommands>
                  <buildcommand>org.springframework.ide.eclipse.core.springbuilder</buildcommand>
              </additionalBuildcommands>
              <downloadSources>true</downloadSources>
              <downloadJavadocs>true</downloadJavadocs>
          </configuration>
      </plugin>

```

- Now you can automatically generate the required project files using
  `mvn eclipse:clean eclipse:eclipse`
  (the eclipse:clean goal removes first all existing eclipse project files)

- It is now possible to import the project into your Eclipse environment.

- Because the Eclipse project files can now be generated, they should not be checked into git.
  You should add the following entries to your .gitignore file:
  ```
  # Eclipse files
  .project
  .metadata
  .classpath
  .settings/
  .loadpath
  bin/
  ```

- Now `git status` should not list eclipse files

#### Cleanup your project

- Show the tests and running server to your lab assistant.
- Clean up your project using `mvn clean`.
  This will not remove the Eclipse configuration. To do this you explicitly have to call `mvn eclipse:clean`.
- Check in the current status to your git repository and push it upstream to your public repo on the [ZHAW SoE GitHub Server][zhaw-gh].


> This concludes part 2 of the lab. You should now master the basic Maven workflows.


### Part 3 - Practice Jenkins
Until now you were executing the build tools only on your local development workstation.
In part 3 of this lab you will use Jenkins to check out your project from version control, compile it and run the tests.

#### Login and configure Jenkins
- Download and start Jenkins from [here](https://jenkins.io/) and start the application just by double-click or in the terminal with java -jar jenkins.war .
- Follow the instructions in [this](http://obscuredclarity.blogspot.ch/2012/04/continuous-integration-using-jenkins.html) blog post to configure your Server and start your first manual build job.
  - Use your public git repository as the Project source.
  - Use your maven pom.xml to configure the environment.
  - In case of a fail an email should be sent to you.
- Modify your source code to create a build failure and check if the email is delivered.

#### Nightly build
- Create a copy of your first job and configure a nightly build. The job should run once a day (e.g. at 3:45 in the morning).

#### Trigger push on a GitHub repository
Next you want to configure Jenkins to create a build each time a version of your code is pushed to github.
- Follow the instructions in [this](http://fourword.fourkitchens.com/article/trigger-jenkins-builds-pushing-github) blog post to configure your SoE GitHub repository to trigger a build.

- Show the push functionality to your lab-assistant.

> This concludes part 3 of the lab. You should now have configured Jenkins to run task periodically and on a git push.

> **Congratulations! You finished the Lab Maven & Jenkins**
