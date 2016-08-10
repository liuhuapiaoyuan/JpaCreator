package <%=packageName%>.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import <%=packageName%>.entity.<%=_name%>;
import <%=packageName%>.repositories.<%=_name%>Repository;
import <%=packageName%>.service.<%=_name%>Service;


@Service
public class <%=_name%>ServiceImpl extends BaseServiceImpl<<%=_name%>, Long> implements <%=_name%>Service {

	@Autowired
	<%=_name%>Repository repository; 
	
	@Autowired
	void setRepository(<%=_name%>Repository repository){
		super.setBaseRepository(repository);
	}
 

}
