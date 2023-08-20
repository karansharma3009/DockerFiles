pipeline {
        agent any

        environment {
                TEST= "abc"
              //  SSH_CREDENTIALS = credentials("ssh_crdentials") // ssh_credential is ID of credentials saved in Jenkins
        }
        stages {
                stage("fetching password") {
                        steps {
                                // echo " username is $SSH_CREDENTIALS_USR"
                               // echo " pwd is $SSH_CREDENTIALS_PWD"
                                echo "hi this is test job"
                        }
                }

                stage("changelog example") {

                        when {
                                changelog ".*some_text*." // this will check if commit message in linked repo has commit message which contains "some_text" then only below steps will vbe triggered. 
                        }

                        steps {
                                echo "HELLO WORLD"
                        }
            }
            
             stage("changeSet example") {

                        when {
                                changelog ".*some_text*." // this will check if commit message in linked repo has commit message which contains "some_text" then only below steps will vbe triggered. 
                        }

                        steps {
                                echo "HELLO WORLD"
                        }
            }
        }
    
}
