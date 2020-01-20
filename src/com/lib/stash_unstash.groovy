node('slave') {
      stage('Stash') {
        dir ('test') {
            sh '''
                cd file
                touch My.csv
                ls -al
            '''

            stash name: 'stash_test', includes: 'file/My.csv'

        } // end dir
    } // end stage 'Stash'

    stage('Check unstash') {
        dir('check') {
            unstash 'stash_test'

            sh 'tree -L 2'
        }
    }

    stage('Clean up') {
        sh 'tree -L 2'
        deleteDir()
    }
}
