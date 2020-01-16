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
    stage("find") {
        def pomPath = findFiles(glob: "**/my.csv")[0].path
        echo new File(env.WORKSPACE, pomPath).getParent() +"\my.csv"

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
}
return this
