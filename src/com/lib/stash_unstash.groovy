node('slave') {
   node('master'){
      stage('Run') {
    def stash_file(file_name, name){
    this.steps.stash ('includes': file_name, 'name': name)
}

def unstash_file(name){
    this.steps.unstash name
}
   }
}
}
