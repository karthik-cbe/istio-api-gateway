node("mavennexusistio") {
  checkout scm

  openshift.withCluster() {
    // Select the default project
    openshift.withProject() {
      echo "Using project ${openshift.project()} in cluster with url ${openshift.cluster()}"
    }
  }

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
