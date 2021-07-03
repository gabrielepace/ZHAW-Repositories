# SWEN2-Lab-Continous-Delivery
SWEN2 Lab exercise for Continuous Delivery / Build pipeline

## Setup Continuos Integration/Delivery system in just 4 steps with Jenkins Pipelines and Blue Ocean

The first stable version of [Blue Ocean Plugin](https://jenkins.io/projects/blueocean/) was recently released. It is easier to setup your Continuous Integration/Delivery with Jenkins. [Jenkins](https://jenkins.io/) has also evolved quite a lot in recent years, making easier to setup your automation server and builds in just a couple of steps.

We will using the following concepts throught the setup:

**[Multibranch Pipelines](https://wiki.jenkins-ci.org/display/JENKINS/Pipeline+Multibranch+Plugin):** Pipelines, and especially multibranch pipelines, are a game changer in Jenkins. You can simply setup your repository url and Jenkins will identify all your branches. It will also automatically start new builds as soon as new commits are pushed.

**[Jenkinsfile](https://jenkins.io/doc/book/pipeline/jenkinsfile/):** Together with Pipelines the new concept of `Jenkinsfile` was introduced. This is a file that you create in your repo, which contains your pipeline configuration. Jenkins will then look for this file in your branches and execute the build according to the stages defined in there. That makes possible to have your pipeline configuration together with your project and under version control.

**[Blue Ocean](https://jenkins.io/projects/blueocean/):** This plugin is must for everyone using jenkins pipelines. Blue Ocean it is an opensource plugin that rethinks the user experience of Jenkins. Most amazing feature is the beautiful user interface of Pipelines, allowing for fast and intuitive comprehension of build's status. If the awful user experience of Jenkins was holding you back from using it, there are not more reasons for that.

Let's see how we can get our Continuous Delivery environment up and running in 4 steps:

### 1. Jenkins installation:

You might have an installation already, or you can follow the installation steps for your _OS_ or _Docker_ in the official docs:

*   [https://jenkins.io/doc/book/getting-started/installing/](https://jenkins.io/doc/book/getting-started/installing/)


This automatically creates a jenkins user and daemon that listens on port 8080. That means that your Jenkins system is now reachable on `http://<your_ip>:8080`. In case that you are testing that locally, that would be `http://127.0.0.1:8080` or also `http://localhost:8080`

Open the url and follow the [installation wizard](https://jenkins.io/doc/book/getting-started/installing/#post-install-setup-wizard). Here we recommend that you to select the default option to install the suggested plugins.

[![Installation Wizard](https://res.cloudinary.com/practicaldev/image/fetch/s--o1FtSwu3--/c_limit,f_auto,fl_progressive,q_auto,w_880/https://thepracticaldev.s3.amazonaws.com/i/4tggspeafhjnlryphjxj.png)](https://res.cloudinary.com/practicaldev/image/fetch/s--o1FtSwu3--/c_limit,f_auto,fl_progressive,q_auto,w_880/https://thepracticaldev.s3.amazonaws.com/i/4tggspeafhjnlryphjxj.png)

### 2. Intall Plugins

*   Go to `Manage Jenkins > Manage Plugins > Available` and filter by `Blue Ocean`.
*   Select and Install

[![Plugins](https://res.cloudinary.com/practicaldev/image/fetch/s--XZQ6hhev--/c_limit,f_auto,fl_progressive,q_auto,w_880/https://thepracticaldev.s3.amazonaws.com/i/fqxqqg6jpohnywjqszrf.png)](https://res.cloudinary.com/practicaldev/image/fetch/s--XZQ6hhev--/c_limit,f_auto,fl_progressive,q_auto,w_880/https://thepracticaldev.s3.amazonaws.com/i/fqxqqg6jpohnywjqszrf.png)

### 3. Jenkinsfile

*   In your project repository root, create a new file called `Jenkinsfile`. This file is written in `Groovy` and will define your `Pipeline Stages` configuration. For this tutorial, just copy the following dummy example and paste it into the `Jenkinsfile` just created.

*   Save it, commit and push.

If you run on windows, use the following file.

```
node {
    // Clean workspace before doing anything
    deleteDir()

    try {
        stage ('Clone') {
            checkout scm
        }
        stage ('Build') {
            bat "echo 'shell scripts to build project...'"
        }
        stage ('Tests') {
            parallel 'static': {
                bat "echo 'shell scripts to run static tests...'"
            },
            'unit': {
                bat "echo 'shell scripts to run unit tests...'"
            },
            'integration': {
                bat "echo 'shell scripts to run integration tests...'"
            }
        }
        stage ('Deploy') {
            bat "echo 'shell scripts to deploy to server...'"
        }
    } catch (err) {
        currentBuild.result = 'FAILED'
        throw err
    }
}
```
If you run on macOS or Linux, use the following file.

```
node {
    // Clean workspace before doing anything
    deleteDir()

    try {
        stage ('Clone') {
            checkout scm
        }
        stage ('Build') {
            sh "echo 'shell scripts to build project...'"
        }
        stage ('Tests') {
            parallel 'static': {
                sh "echo 'shell scripts to run static tests...'"
            },
            'unit': {
                sh "echo 'shell scripts to run unit tests...'"
            },
            'integration': {
                sh "echo 'shell scripts to run integration tests...'"
            }
        }
        stage ('Deploy') {
            sh "echo 'shell scripts to deploy to server...'"
        }
    } catch (err) {
        currentBuild.result = 'FAILED'
        throw err
    }
}
```

### 4. Setup Multibranch Pipeline

*   Go to the jenkins home page and click on `new Item`. Give a name to your Job and select `Multibranch Pipeline`.

*   After that, we need to configure the repository url and credentials. You can see examples of that in the following screenshots depending on your source control system (Bitbucket, github, git):

    *   Very important here is that you select `Jenkinsfile` as Build Configuration Mode.

    [![Repository Configuration](https://res.cloudinary.com/practicaldev/image/fetch/s--ueoJUvyK--/c_limit,f_auto,fl_progressive,q_auto,w_880/https://thepracticaldev.s3.amazonaws.com/i/92p77kpbdvrrgy15sd8o.png)](https://res.cloudinary.com/practicaldev/image/fetch/s--ueoJUvyK--/c_limit,f_auto,fl_progressive,q_auto,w_880/https://thepracticaldev.s3.amazonaws.com/i/92p77kpbdvrrgy15sd8o.png)

*   Click save. You will notice that Jenkins starts scanning your repo in search for all your branches. In fact, what Jenkins is doing, it is to look for `Jenkinsfiles` inside your branches.

*   Click on the header `Blue Ocean` button.

*   Here we go, you just setup a Continuous Delivery system that will automatically scan, build and deploy all your branches in your repository.

*   Now click around on the `Blue Ocean` pipelines branches to see what is there.

As you might have noticed, our `Jenkinsfile` is just defining the stages and printing some `echos`. The idea is that you replace these `echos` with your actual scripts.

You can also find more info about `Jenkinsfile` options and syntax here:

*   [https://jenkins.io/doc/book/pipeline/jenkinsfile/](https://jenkins.io/doc/book/pipeline/jenkinsfile/)
