#!/usr/bin/env groovy
package com.lib
import groovy.json.JsonSlurper
import hudson.FilePath

def runPipeline() {
    
node { 
    deleteDir() 
    stage("upload") {
def inputFile = input message: 'Upload file', parameters: [file(name: "$workspace")]
        }
  
    stage("checkout") { 
     echo fileExists('restaurantConfigCSV').toString() 
    stage("read") {
        def filenames = readFile 'restaurantConfigCSV'
        def filenameArray = filenames.split(",")
        for(int i = 0; i < filenameArray.size(); i++) {
        def file = filenameArray[i]
        echo file
    }   
    }    
    } 
    } 
}
return this
