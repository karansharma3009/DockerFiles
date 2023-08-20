pipeline {
        agent any

        environment {
                SSH_CREDENTIALS = credentials("ssh_crdentials") // ssh_credential is ID of credentials saved in Jenkins
        }
        stages {
                stage("fetching password") {
                        steps {
                                echo " username is $SSH_CREDENTIALS_USR"
                                echo " pwd is $SSH_CREDENTIALS_PWD"
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
