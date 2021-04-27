package com.github.wangsongbin001.ideplugindemo.services

import com.github.wangsongbin001.ideplugindemo.MyBundle
import com.intellij.openapi.project.Project

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
