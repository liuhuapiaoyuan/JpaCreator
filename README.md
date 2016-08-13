#用于创建java代码
底层使用的是node ejs等

###安装：
建议全局安装 npm i jpaCreate -g

###命令：
jpaCreate create --entity=order.js	//创建order相关的entity,repository,service,serviceimpl,controller,dto等


#目标
- 拆分每个命令的处理规则，优化模板代码，增加子模块指令



#v0.1.2
- 修复路径错误BUG


#v0.1.4
- 修改命令，改为create子命令： jpaCreate create --entity=order.js
- 增加子命令，获取schema模板， jpaCreate schema
- 模板返回js和json，程序都可以识别

