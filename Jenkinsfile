node("mavennexusistio") {
  checkout scm
  try {
      timeout(time: 20, unit: 'MINUTES') {
        openshift.withCluster() {
          def workspace = pwd()
          def token =  sh (
              script: '/run/secrets/kubernetes.io/serviceaccount/token',
              returnStdout: true
          ).trim()
          // Select the default project
          openshift.withProject() {
            echo "Using project ${openshift.project()} in cluster with url ${openshift.cluster()}"
            //Login to create ~/.kube/config
            stage("Login") {
              sh "oc login --token=\'${token}\'"
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
