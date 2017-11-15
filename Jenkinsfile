node("mavennexusistio") {
  checkout scm

  stage("Using Environment") {
    sh "istioctl version"
    sh "kubectl cluster-info"
  }

  // stage("Test") {
  //   sh "mvn -B clean test"
  // }

  // stage("Build Image") {
  //   sh "mvn -DskipTests clean fabric8:build"
  // }

  // stage("Deploy App to OpenShift") {
  //   sh "mvn -DskipTests clean fabric8:build"
  // }

}
