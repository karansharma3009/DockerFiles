import groovy.sql.Sql
import hudson.Util
pipeline {
    agent any ;
    stages {
        stage('Build') {
            steps {
                echo "hahaha"
            }
        }
        stage('Test') {
            steps {
                script {
                    def emailBody = "This is body ${currentBuild.fullDisplayName}"
                    /*   mail(
                           bcc: '', // List of BCC recipients
                           body: emailBody, // Email body
                           cc: '', // List of CC recipients
                           from: 'karan.sharma@go-mmt.com', // Sender's email address
                           replyTo: '', // Reply-to email address
                           subject: 'Automation Report', // Email subject
                           to: 'karan.sharma@go-mmt.com' // Recipient's email address
                       )*/
                }
            }
        }
        stage('Deploy') {
            steps {
                script {
                    // Extracting test results, code coverage, etc.
                    def testResults = "Test results summary"

                    // Generate email body
                    def emailBody = "Build Number: ${env.BUILD_NUMBER}\n"
                    emailBody += "Test Results: ${testResults}\n"

                    emailBody += "link to open Reports: http://<LINK TO OPEN REPORT>/"
                    emailBody += "${env.BUILD_NUMBER}"
                    emailBody += "/allure/"

                    echo emailBody
                    // Send email
                    mail(
                        bcc: '', // List of BCC recipients
                        body: emailBody, // Email body
                        cc: '', // List of CC recipients
                        from: 'karan.sharma@go-mmt.com', // Sender's email address
                        replyTo: '', // Reply-to email address
                        subject: 'Automation Report', // Email subject
                        to: 'karan.sharma@go-mmt.com' // Recipient's email address
                    )
                }
            }
        }
    }
}


