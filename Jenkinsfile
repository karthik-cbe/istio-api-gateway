node("mavennexusistio") {
  checkout scm

  // stage("Using Environment") {
  //   sh "kubectl cluster-info"
  //   sh "istioctl --help"    
  // }

  stage("Test") {
    sh "mvn -B clean test"
  }

  stage("Build Image and App") {
    sh "mvn -DskipTests clean package"
  }

  stage("Deploy App to OpenShift") {
    sh "kubectl apply -f src/istio/istio-api-gateway-all.yaml"
  }

}
