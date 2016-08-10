package <%=packageName%>.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;



/**
 * Entity <%=_name%> - <%=info%>
 * 
 */
@Entity
public class <%=_name%> extends BaseEntity {

	private static final long serialVersionUID = <%=serialVersionUID%>L;
	 
<% for(var i=0; i<fields.length; i++) {%>
	/**<%=fields[i].info%> **/
	private <%=fields[i].type%> <%=fields[i].name%> ;
	
	/**
	 * 获取<%=fields[i].name%>
	 * @return Mobile
	 */
	<% for(var j=0; j<fields[i].annotation.length; j++){%><%=fields[i].annotation[j]%> 
	<% } %>public <%=fields[i].type%> get<%=fields[i].name.replace(/(\w)/,function(v){return v.toUpperCase()});%>() {
		return this.<%=fields[i].name%>;
	}
	
	/**
	 * 设置<%=fields[i].info%>
	 * @param <%=fields[i].name%>  <%=fields[i].info%>
	 */
	public void set<%=fields[i].name.replace(/(\w)/,function(v){return v.toUpperCase()});%>(<%=fields[i].type%> <%=fields[i].name%>) {
		this.<%=fields[i].name%> = <%=fields[i].name%>;
	}
	
<% } %> 
	 
}