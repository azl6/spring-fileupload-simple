pipeline {
    agent any 

    environment {
      UPLOADED_FILES_DIR='/tmp/spring_fileupload_dir'
      DOCKER_USR=credentials('docker_usr')
      DOCKER_PASSWD=credentials('docker_passwd')
    }

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
                sh """
                    docker build -t azold6/fileupload-pv-pvc:$BUILD_NUMBER . 
                """ 
            }
        }

        stage('Push') { 
            steps {
                sh """
                    docker login -u="$DOCKER_USR" -p="$DOCKER_PASSWD"
                    docker push azold6/fileupload-pv-pvc:$BUILD_NUMBER
                """ 
            }
        }

        stage('Deploy') { 
            steps {
                sshagent(credentials : ['key-to-ec2']) {
                    sh """
                    ssh ec2-user@52.67.76.219 "docker run -p 8080:8080 -d --name fileupload --rm azold6/fileupload-pv-pvc:$BUILD_NUMBER"
                    """                
                }
            }
        }
    }
}