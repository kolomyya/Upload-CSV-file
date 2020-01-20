#!/usr/bin/env groovy
package com.lib
import groovy.json.JsonSlurper
import hudson.FilePath

def runPipeline() {
    
node { 

stage ('stash') {
   node { label  'one' }
   steps {
       script {
           sh "echo 1 > my.csv" // runs in $WORKSPACE, creates $WORKSPACE/my.csv
           stash name: "my_stash", includes: "my.csv" // relative to $WORKSPACE
       }
   }
}

stage ('unstash') {
   node { label  'two' }
   steps {
       script {
           unstash name: "my_stash"  // runs in $WORKSPACE, creates $WORKSPACE/my.csv
           sh "cat ./my.csv"
       }
   }
}
