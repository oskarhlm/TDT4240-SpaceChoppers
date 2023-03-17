pipeline {
    agent any
    
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
        
        // stage('Setup Android SDK') {
        //     steps {
        //         sh 'yes | /usr/lib/android-sdk/tools/bin/sdkmanager 
--licenses'
        //     }
        // }
        
        stage('Build with Gradle') {
            steps {
                dir('SpaceChoppers') {
                    sh 'ls -la' // just for verification, remove if not 
needed
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

