#!/usr/bin/env groovy
package com.lib
import groovy.json.JsonSlurper
import hudson.FilePath

def runPipeline() {
    
node { 
    deleteDir() 
    stage("upload") { 
        def inputFile = input message: 'Upload file', parameters: [file(name: 'restaurantConfigCSV')] 
        new hudson.FilePath(new File("$workspace/restaurantConfigCSV")).copyFrom(inputFile) 
        inputFile.delete() 
    }
    

    stage("checkout") { 
     echo fileExists("$workspace/restaurantConfigCSV".toString() 
    stage("read") {
        def filenames = readFile "$workspace/restaurantConfigCSV"
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
