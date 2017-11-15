node("mavenwithnexus") {
  checkout scm

  stage("Test") {
    sh "mvn test"
  }

  stage("Build Image") {
    sh "mvn -DskipTests clean fabric8:build"
  }

  stage("Deploy App") {
    sh "mvn -DskipTests clean fabric8:build"
  }
  
}
