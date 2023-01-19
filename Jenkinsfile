pipeline {
    agent any 
    stages {
        stage('Test') { 
            steps {
                sh 'mvn test' 
            }
        }

        stage('Package') { 
            steps {
                sh 'mvn clean package -Dmaven.test.skip=true' 
            }
        }

        stage('Build') { 
            steps {
                sh '''
                    docker build -t azold6/fileupload-pv-pvc:BUILD_NUMBER . 
                ''' 
            }
        }

        stage('Push') { 
            steps {
                sh '''
                    echo "Not yet implemented" 
                ''' 
            }
        }

        stage('Deploy') { 
            steps {
                sh '''
                    echo "Not yet implemented" 
                ''' 
            }
        }
    }
}