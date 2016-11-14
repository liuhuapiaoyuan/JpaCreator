#!/usr/bin/env node
var JPA = require('./JPA')
var path = require('path')
var fs = require('fs')


//获取命令行参数

require('yargs')
	.demand(['entity'])
	.command('create','生成代码',function(yargs){
		
//		function isDir(xpath){  
//			xpath = path.resolve(xpath) 
////			console.log("xpath",xpath)
////	console.log("fs.exists(xpath)",fs.exists(xpath))
////			console.log(fs.existsSync('H:\H5WORK\jianyubujianSchema\schema'))
//		    return fs.existsSync('H:\H5WORK\jianyubujianSchema\schema') && fs.statSync('H:\H5WORK\jianyubujianSchema\schema').isDirectory();  
//		}
		
		var argv = yargs.reset()
	  	.option("e", {
	        alias: "entity",
	        description: "指定一个entitySchema文件"
	    })
	    .help("h")
	    .alias("h", "help")
	    .argv;
		var list = []
		var files = []
		try{
			files = fs.readdirSync(path.resolve(argv.entity))
		}catch(e){
		}
	    if(files.length>0){
	    	for(var i in files){
				var schemal = files[i]
				var schemalFile = require(path.resolve(argv.entity,schemal))
				list.push(schemalFile)
			}
	    }else{
			var schemals = argv.entity.split(',')
			for(var i in schemals){
				var schemal = schemals[i]
				var schemalFile = require(path.resolve(schemal))
				list.push(schemalFile)
			}
	    }
		JPA.cleanRoot(schemalFile,function(){
			for(var i in list){
				JPA.createAll(list[i],false)
			}
		})
	})
	.command('schema','获取schema模板',function(yargs){
		var argv = yargs.reset().argv
		JPA.schema()
	})

	.describe({entity: '指定一个entitySchema文件'})
	.example('jpaCreate create -entity Member.js', '创建实体')
	.example('jpaCreate schema', '获取模板')
	.usage('Usage: jpaCreate [options]')
  	.argv;