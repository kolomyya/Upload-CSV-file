node('slave') {
   node('master'){
    def stash_file(file_name, name){
    this.steps.stash ('includes': file_name, 'name': name)
}

def unstash_file(name){
    this.steps.unstash name
}
   }
