#!/usr/bin/env node
var JPA = require('./JPA')
var path = require('path')
var fs = require('fs')
console.log("__dirname",__dirname)

//获取命令行参数
var argv = require('yargs')
	.demand(['entity'])
	.describe({entity: '指定一个entitySchema文件'})
	.usage('Usage: jpaCreate [options]')
	.example('jpaCreate -entity Member.json', '创建实体成功')
	.help('h').alias('h', 'help')
	.epilog('copyright xm.yun-net.cn 2015')
  	.argv;
var schemals = argv.entity.split(',')
var list = []
for(var i in schemals){
	var schemal = schemals[i]
	var schemalFile = require('./'+schemal)
	list.push(schemalFile)
}
JPA.cleanRoot(schemalFile,function(){
	for(var i in list){
		JPA.createAll(list[i],false)
	}
})
