node("mavennexusistio") {
  checkout scm

  stage("Using Environment") {
    sh "kubectl cluster-info"
    sh "istioctl --help"    
  }

  stage("Test") {
    sh "mvn -B clean test"
  }

  stage("Build Image") {
    sh "mvn -DskipTests clean fabric8:resource fabric8:build"
  }

  stage("Deploy App to OpenShift") {
    sh "mvn -DskipTests package"
  }

}
