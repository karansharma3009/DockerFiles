// THIS IS SAMPLE PIPELINE WHICH WILL DEPLOY DOCKER CONTAINER On REMOTE SERVER 

pipeline {
    agent any





    stages {

        stage('Run Command as Different User') {
            steps {
                script {
                    def command = 'aws ecr get-login-password --region ap-south-1'
                    def username = 'root'

                    // Use sudo to run the command as a different user
                    // echo "dfgggg" | sudo -S -u karans $command

                }
            }
        }
        stage('Build and Deploy') {

            steps {
                script {
                    def server = "172.16.216.160"
                    def $buildName = "524881529748.dkr.ecr.ap-south-1.amazonaws.com/hol-webservices:release-1567"

                    // sshCommand("aws ecr get-login-password --region ap-south-1 | docker login --username AWS --password-stdin 524881529748.dkr.ecr.ap-south-1.amazonaws.com")


                    def awsLoginCommand = sh (
                        script: 'aws ecr get-login-password --region ap-south-1',
                        returnStdout: true
                    ).trim()

                    // echo "AWS Login Command: ${awsLoginCommand}"

                    docker.withServer("tcp://$server:4243") {
                      //  docker.withRegistry("https://524881529748.dkr.ecr.ap-south-1.amazonaws.com") {
                            try {
                                //     sh "cd /root/.ssh"
                                //    sh "ssh -i jen_rsa root@172.16.216.161"
                                // jen_rsa should be present in Sever you are login so that root user can login without password
                                sh "ifconfig"
                                sh "whoami"
                                //  sh "aws ecr get-login-password --region ap-south-1"

                                sh "echo ${awsLoginCommand} | docker login --username AWS --password-stdin 524881529748.dkr.ecr.ap-south-1.amazonaws.com > /dev/null 2>&1"
                               // this will stop as well rm container from the server having image name - > hol-webservices
                               sh  "docker ps -a | grep 'hol-webservices' | awk '{print \$1}' | xargs -r -I {} sh -c 'docker stop {} && docker rm {}'"

                            }
                            catch (Exception e) {
                                echo e
                                echo "No Container in local"
                            }
                            echo "Docker Deployment Going On !!"
                            sh "docker run --name 'BuildDeployedViaJenkins'  -e SKIP_PRE_SALES_KAFKA_FLOW=true -e env='staging'  -p 8090:8080  -d 524881529748.dkr.ecr.ap-south-1.amazonaws.com/hol-webservices:release-1566"
                            //  }
                       // }
                    }
                }
            }
        }
    }
}
