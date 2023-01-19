pipeline {
    agent any 

    environment {
      UPLOADED_FILES_DIR='/tmp/spring_fileupload_'
      DOCKER_USR=credentials('docker_usr')
      DOCKER_PASSWD=credentials('docker_passwd')
    }

    stages {
        stage('Test') { 
            steps {
                sh 'mvn test' 
            }
        }

        stage('Build') { 
            steps {
                sh '''
                    docker build -t azold6/fileupload-pv-pvc:$BUILD_NUMBER . 
                ''' 
            }
        }

        stage('Push') { 
            steps {
                sh '''
                    docker login -u="$DOCKER_USR" -p="$DOCKER_PASSWD"
                    docker push azold6/fileupload-pv-pvc:$BUILD_NUMBER
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