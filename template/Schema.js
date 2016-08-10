/**
 * schema template
 */
module.exports = {
	packageName:"packagename",
	name:'tablename',
	info:'entity info',
	updateIgnoreProperties:["name","age"],		//admin更新时候忽略的对象
	fields:[
		{
			name:'username',
			info:'用户名',
			type:'String',
			annotation:[
				"@NotEmpty(groups = Save.class)",
				"@JsonIgnore"
			]
		},
		{
			name:'password',
			info:'密码',
			type:'String',
			annotation:[
				"@NotEmpty(groups = Save.class)"
			]
		},
		{
			name:'age',
			info:'年龄',
			type:'Integer',
			annotation:[
				"@NotNull"
			]
		},
		{
			name:'mobile',
			info:'绑定的手机',
			type:'String',
			annotation:[
				"@NotEmpty(groups = Save.class)"
			]
		}
	]
}
 