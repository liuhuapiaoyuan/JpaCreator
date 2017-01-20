#用于创建java代码
底层使用的是node ejs等

###安装：
建议全局安装 npm i jpaCreate -g

###命令：
jpaCreate create --entity=order.js --v 2.0	//创建order相关的entity,repository,service,serviceimpl,controller,dto等 使用2.0模板


#v0.1.6
 - 支持指定模板(现在内置 template2 和 template2套)


#v0.1.5
 - 支持enums声明，结构请看模板
 - 支持Set和HashSet声明
 - --entity参数支持文件夹目录，会自动扫描下面所有文件并生成代码



#v0.1.4
- 修改命令，改为create子命令： jpaCreate create --entity=order.js
- 增加子命令，获取schema模板， jpaCreate schema
- 模板返回js和json，程序都可以识别

#v0.1.2
- 修复路径错误BUG

