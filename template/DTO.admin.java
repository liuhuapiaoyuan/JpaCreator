package <%=packageName%>.DTO.admin;

import <%=packageName%>.entity.<%=_name%>;
/**
 * DTO
 * <%=_name%>
 */
public class <%=_name%>DTO extends <%=_name%> {

	/**
	 * 
	 */
	private static final long serialVersionUID = <%=serialVersionUID%>L;
	
	/**
	 * 批量删除用
	 */
	public Long[] delids ;


	public Long[] getDelids() {
		return delids;
	}


	public void setDelids(Long[] delids) {
		this.delids = delids;
	}
}
