node("mavennexusistio") {
  checkout scm
  try {
      timeout(time: 20, unit: 'MINUTES') {
        openshift.withCluster() {
          def workspace = pwd()
          // Select the default project
          openshift.withProject() {           
            echo "Using project ${openshift.project()} in cluster with url ${openshift.cluster()}"
            //Login to create ~/.kube/config
            stage("Login") {
              sh "oc login \'${openshift.cluster()}\' -u system:admin  --insecure-skip-tls-verify"
            }
            stage("Test") {
              sh "mvn -B clean test"
            }
            stage("Build App") {
              sh "mvn -DcurrentProject=${openshift.project()} -DskipTests clean package"
            }
            stage("Deploy App to OpenShift") {
              sh "kubectl apply -f ${workspace}/src/istio/istio-api-gateway-all.yaml"
            }
          }
        }
      }
  }catch (err) {
    echo "Caught: ${err}"
    currentBuild.result = 'FAILURE'
    throw err
  }
}
