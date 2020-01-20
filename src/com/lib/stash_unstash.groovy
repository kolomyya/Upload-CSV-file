node('slave') {
   node('master'){
    dir('path to file'){
    stash name: 'my_stash' includes:'My.csv'
}
unstash name:'my_stash'
sh 'ls'
}
}
