package com.shizhuang.duapp.ideaplugin.switches.v2

/********idea plugin v2************/
data class ProjectMode(
        var component: String?,
        var enable: String?,    //是否依赖本地true/false
        var root_path: String?,
        var modules: MutableList<ComponentMode>? = mutableListOf(),
        var remote: String?,    //远程url
        var installed: String?, //是否安装（即本地拉取代码）
        var group: String? //分组
) {
    override fun toString(): String {
        return super.toString()
    }
}

data class ComponentMode(val name: String?,
                     var enable: String?,
                     var groupName: String? = "",
                     var subPath: String? = "",
                     var installed: String?, //是否安装（即本地拉取代码）
                     var remote: String?
){

    override fun toString(): String {
        return "Component(name=$name, enable=$enable, groupName=$groupName, subPath=$subPath, remote=$remote)"
    }
}

/********idea plugin v2 解析辅助类************/


