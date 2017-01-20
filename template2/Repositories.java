package <%=packageName%>.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;

import <%=packageName%>.entity.<%=_name%>;


/**
 * <%=_name%>的repository
 * @author li
 */
public interface <%=_name%>Repository extends BaseRepository<<%=_name%>, Long> {
 

}
