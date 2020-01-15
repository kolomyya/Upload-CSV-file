package org.jenkinsci.plugins.workflow.cps.global

import hudson.Extension
import hudson.ExtensionList
import hudson.model.Run
import hudson.util.CopyOnWriteList
import org.apache.commons.io.FilenameUtils
import org.jenkinsci.plugins.workflow.cps.GlobalVariable;
import org.jenkinsci.plugins.workflow.cps.GlobalVariableSet

import javax.inject.Inject
import java.io.File
import java.util.ArrayList
import java.util.Collection
import java.util.List
import hudson.FilePath
import hudson.model.ParametersAction
import hudson.model.FileParameterValue
import hudson.model.Executor

def call(String name, String fname = null) {
    def paramsAction = currentBuild.rawBuild.getAction(ParametersAction.class);

    if (paramsAction == null) {
        error "unstashParam: No file parameter named '${name}'"
    }

    for (param in paramsAction.getParameters()) {
        if (param.getName().equals(name)) {
            if (! param instanceof FileParameterValue) {
                error "unstashParam: not a file parameter: ${name}"
            }
            if (env['NODE_NAME'] == null) {
                error "unstashParam: no node in current context"
            }
            if (env['WORKSPACE'] == null) {
                error "unstashParam: no workspace in current context"
            }
            workspace = new FilePath(getComputer(env['NODE_NAME']), env['WORKSPACE'])
            filename = fname == null ? param.getOriginalFileName() : fname
            file = workspace.child(filename)
            file.copyFrom(param.getFile())
            return filename;
        }
    }
}


def getComputer(name){

    for(computer in Jenkins.getInstance().getComputers()){ 
        if(computer.getDisplayName() == name){
            return computer.getChannel()
        }
    }

    error "Cannot find computer for file parameter workaround"
}