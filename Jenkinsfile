import java.text.SimpleDateFormat
pipeline{
environment {
        registry = "rafedchraiti/devopsproject"
        registryCredential = 'dckr_pat_wadd9m36GOJGduKhbJig6Pf458Q'
        dockerImage = ''
    }
    agent any
    stages{
        stage("Checkout git"){
                                steps{
                                  git branch: 'master',
                                   url: 'https://github.com/rafed-99/achat';
                               }
                           }
        stage('Date'){
                                 steps {
                                    script{
                                            def date = new Date()
                                            sdf = new SimpleDateFormat("MM/dd/yyyy")
                                            println(sdf.format(date))
                                           }
                                        }
                      }
        stage("MVN Clean"){
                                steps{
                                    sh 'mvn clean'
                                }
        }
        stage('MVN compile'){
                                steps{
                                    sh 'mvn compile'
                                }
        }
        stage('MVN package'){
                                steps{
                                    sh 'mvn package'
                                }
        }
        stage("Test JUnit - Mockito"){
                                steps {
                                    sh 'mvn test'
                                }
        }
        stage('SonarQube CodeQuality'){
                                steps{
                                    sh  'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=41120725'
                                }
        }
        stage("NEXUS"){
                       steps{
                               sh 'mvn deploy:deploy-file -DgroupId=tn.esprit.rh -DartifactId=achat -Dversion=1.0.5 -DgeneratePom=true -Dpackaging=jar -DrepositoryId=deploymentRepo -Durl=http://192.168.1.147:8081/repository/maven-releases -Dfile=target/devopsproject.jar'
                               }
        }
        stage('Building our image') {
                                steps {
                                    script {
                                    dockerImage = docker.build registry + ":$BUILD_NUMBER"
                                            }
                                }
         }

        stage('Deploy our image') {
                                steps {
                                    script {
                                    docker.withRegistry( '', registryCredential ) {
                                    dockerImage.push()}
                                            }
                                }
        }
        stage('Cleaning up') {
                                steps {
                                sh "docker rmi $registry:$BUILD_NUMBER"
                                }
        }
        stage('DOCKER COMPOSE') {
                        steps {
                                    sh 'docker-compose up -d --build'
                        }
        }
    }
}