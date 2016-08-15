package <%=packageName%>.entity;


import java.util.Date;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Lob;
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
<% enums.forEach(function(item,key){ %>
	/**
	 * <%= item.info %>
	 * @author li
	 */
	public enum <%=item.name-%> {
	<% item.fields.forEach(function(eitem,ekey){ -%>
	/** <%= eitem.info %> **/
		<%= eitem.name -%><%= (item.fields.length-1 == ekey)?'':','%>
	<% }) -%> 
	}
<% }) %> 
	
<%_ for(var i=0; i<fields.length; i++) { _%>
	/**<%-fields[i].info -%> **/
	<%_ if(!fields[i].collectionType){ _%>
	private <%=fields[i].type-%> <%=fields[i].name-%> ;
	<%_ } _%>
	<%_if(fields[i].collectionType){_%>
	private <%=fields[i].collectionType%><<%=fields[i].type%>> <%=fields[i].name%> = new <%-fields[i].collectionType=='Set'?'HashSet':'ArrayList'-%><<%-fields[i].type-%>>();
	<%_}_%>
	
<%_ } _%> 
<% for(var i=0; i<fields.length; i++) {%>
	/**
	 * 获取<%=fields[i].info%>
	 * @return <%=fields[i].type%> 获取<%=fields[i].info%>
	 */
	<% for(var j=0; j<fields[i].annotation.length; j++){%><%-fields[i].annotation[j]%> 
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