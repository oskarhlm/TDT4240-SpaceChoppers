pipeline {
    agent any
    
    triggers {
        githubPush()
    }
    
    environment {
        ANDROID_HOME = '/usr/lib/android-sdk'
    }
    
    stages {
        stage('Clone repository') {
            steps {
                git branch: 'main', url: 
'https://github.com/oskarhlm/TDT4240-SpaceChoppers.git'
            }
        }
 
        
        stage('Build with Gradle') {
            steps {
                dir('SpaceChoppers') {
                    sh 'ls -la'
                    sh 'chmod +x gradlew'
                    sh './gradlew build -x test'
                }
                
            }
        }
        
        stage('Test') {
            steps {
                dir('SpaceChoppers') {
                    sh 'chmod +x gradlew'
                    sh './gradlew core:test'
                }
                
            }
        }
        
        stage('Coverage') {
            steps {
                dir('SpaceChoppers') {
                    sh 'chmod +x gradlew'
                    sh './gradlew jacocoTestReport'
                }
                
            }
        }
    }
}

