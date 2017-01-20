/**
 * 
 * 
 * 
 */
var fs = require('fs')
var path = require('path')
var ejs = require('ejs')
var rimraf = require('rimraf')
var colors = require('colors');  

var ejsOptions = {
	
}

//创建多层文件夹 同步
function mkdirsSync(dirpath, mode) { 
    if (!fs.existsSync(dirpath)) {
        var pathtmp;
        dirpath.split(path.sep).forEach(function(dirname) {
        	if(dirname.indexOf('.') > 0 ){return}
            if (pathtmp) {
                pathtmp = path.join(pathtmp, dirname);
            }
            else {
                pathtmp = dirname;
            }
            if (!fs.existsSync(pathtmp) ) {
                if (!fs.mkdirSync(pathtmp, mode)) {
                    return false;
                }
            }
        });
    }
    return true; 
}

function createPath(pathArray){
	var result = "";
	pathArray.forEach(function(item){
		result = path.join(result,item)
	})
	return result;
}
function randomNo(){
	return Date.now() + "" + Math.ceil(Math.random()*1000+1000)
}

function capitalize(str){
	return str.replace(/(\w)/,function(v){return v.toUpperCase()});
}
function firstChartLowCase(str){
	return str.replace(/(\w)/,function(v){return v.toLowerCase()});
}
var actions = ["entity","repositories","service","service.impl","controller.user","controller.admin","DTO.admin","DTO.user"]
//生成repositories
function formatType(type){
	var r = ""
	switch (type){
		case 'entity':
			r="实体"
			break;
		case 'repositories':
			r="仓库"
			break;
		case 'service':
			r="服务"
			break;
		case 'service.impl':
			r="服务impl"
			break;
		case 'controller.user':
			r="用户层controller"
			break;
		case 'controller.admin':
			r="管理层controller"
			break;
		case 'controller.admin.DTO':
			r="管理层DTO"
			break;
		case 'controller.user.DTO':
			r="用户层DTO"
			break;
		default:
			break;
	}
	return r
}

/**
 * 创建相关文件
 * @param {Object} type
 * @param {Object} schema
 * @param {Object} cleanDir	是否清空源文档内容
 */
function createFile(type,schema,cleanDir,version){
	schema.name = firstChartLowCase(schema.name)
	schema.enums = schema.enums || []  
	console.log("================正在创建"+formatType(type)+"====================")
	schema._name = capitalize(schema.name)
	schema.updateIgnoreProperties = schema.updateIgnoreProperties || []
	
	schema._updateIgnoreProperties = []
	for(var i in schema.updateIgnoreProperties){
		schema._updateIgnoreProperties.push('"'+schema.updateIgnoreProperties[i]+'"')	
	}
	schema._updateIgnoreProperties = (schema._updateIgnoreProperties.length > 0 ? ',':'') + schema._updateIgnoreProperties.join(',')
	
	
	var createFileName = schema._name + capitalize(type)
	if(type=='entity'){
		schema.serialVersionUID = randomNo()
		createFileName = schema._name
	}else if(type =='service.impl'){
		createFileName = schema._name + "ServiceImpl"
	}else if(type =='repositories'){
		createFileName = schema._name + "Repository"
	}else if(type =='controller.user'){
		createFileName = schema._name + "Controller"
	}else if(type =='controller.admin'){
		createFileName = schema._name + "Controller"
	}else if(type =='DTO.admin'){
		createFileName = schema._name + "DTO"
	}else if(type =='DTO.user'){
		createFileName = schema._name + "DTO"
	}
	
	
	var savePath = createPath(schema.packageName.split('.').concat(type.split('.')))
	mkdirsSync(savePath)
	var tmpFile = createPath([__dirname,'../','template'+(version||''),capitalize(type)+'.java'])
	ejs.renderFile(tmpFile, schema, ejsOptions, function(err, str){
	    if(err){
//	    	console.log("【"+formatType(type)+"】编译错误",err)
	    	console.error(("================创建【%s】" +formatType(type)+"失败，编译失败！====================").red,schema.info,err)
	    	return 
	    }
		function _create(){
			var saveFilePath = createPath([savePath,createFileName+".java"])
			try{
				var result = fs.writeFileSync(saveFilePath,str)
				console.log(("================创建【%s】"+formatType(type)+"成功！====================").green,schema.info)
			}catch(e){
				console.error(("================创建【%s】"+formatType(type)+"失败！====================").red,schema.info)
			} 
		}
	    var saveFilePath = createPath([savePath,createFileName+".java"])
	   	cleanDir?clean(saveFilePath,_create):_create()
	});
	
} 

function clean(savePath,callback){
	rimraf(savePath,callback)
}


module.exports = {
	/**
	 * 创建实体相关
	 */
	create:createFile,
	
	/**
	 * 创建全部
	 * @param {Object} schema
	 */
	createAll:function(schema,cleanDir,templvateVersion){
		for(var i in actions){
			createFile(actions[i],schema,false,templvateVersion)
		}
	},
	/**
	 * 清空
	 * @param {Object} schema
	 * @param {Object} callback
	 */
	cleanRoot:function(schema,callback){
		var savePath = createPath(schema.packageName.split('.'))
		clean(savePath,callback)
	},
	/**
	 * 创建一个schema模板
	 */
	schema:function(){
		var tplPath = path.join(__dirname,'../','template','Schema.js')
		var content = fs.readFileSync(tplPath)
		var saveFilePath = path.resolve('SchemaTemplate.js')
		var result = fs.writeFileSync(saveFilePath,content)
		
		
//		var jsonContent = fs.readFileSync(path.join(__dirname,'../','template','Schema.json'))
		var saveFilePathJson = path.resolve('SchemaTemplate.json')
		fs.writeFileSync(saveFilePathJson,content)
		console.log(("================创建【%s】成功！====================").green,'schema模板')
	}
	
	
}
