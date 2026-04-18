pipeline {
  agent any

  options {
    timestamps()
  }

  stages {
    stage('Build') {
      steps {
        sh 'mvn -q -DskipTests clean package'
      }
    }

    stage('Test - Web + API (Parallel)') {
      failFast true
      parallel {
        stage('Web Tests') {
          steps {
            sh 'mvn -q surefire:test@web-tests -Dsurefire.reportsDirectory=target/surefire-reports/web'
          }
        }
        stage('API Tests') {
          steps {
            sh 'mvn -q failsafe:integration-test@api-tests failsafe:verify@api-tests -Dfailsafe.reportsDirectory=target/failsafe-reports/api -Dkarate.output.dir=target/karate-reports/api'
          }
        }
      }
      post {
        always {
          junit allowEmptyResults: true, testResults: '**/target/surefire-reports/**/*.xml'
          junit allowEmptyResults: true, testResults: '**/target/failsafe-reports/**/*.xml'
          archiveArtifacts allowEmptyArchive: true, artifacts: '**/target/cucumber/**'
          archiveArtifacts allowEmptyArchive: true, artifacts: '**/target/karate-reports/**'
        }
      }
    }
  }
}
