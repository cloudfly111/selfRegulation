package com.judy.self.regulation.dataClass

//create data class for https://api.github.com/users/cloudfly111/repos
data class RepoData(
    val id :String,
    val node_id:String,
    val name:String,
    val full_name:String,
    val private:Boolean,

)
