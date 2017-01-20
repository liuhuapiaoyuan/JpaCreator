package <%=packageName%>.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import <%=packageName%>.entity.<%=_name%>;

import <%=packageName%>.service.<%=_name%>Service;


import <%=packageName%>.repositories.specifications.Filter;
import <%=packageName%>.repositories.specifications.Filters;
import <%=packageName%>.repositories.specifications.QueryFilter;
import <%=packageName%>.repositories.specifications.QueryFilters;
import <%=packageName%>.repositories.specifications.Filter.Operator;


import <%=packageName%>.utils.DTOCover;
import <%=packageName%>.DTO.admin.<%=_name%>DTO;

@RestController("admin<%=_name%>Controller")
@RequestMapping("/admin/<%=name%>")
public class <%=_name%>Controller extends BaseController {
	
	@Autowired 
	<%=_name%>Service <%=name%>Service;
	
	
	
	/**
	 * 获取<%=info%>分页数据
	 * @param pageable	分页数据： {page:0,size:20,sort:['name,desc','age,asc']}
	 * @param filters	检索条件
	 * @return
	 */
	@RequestMapping(value="",method = RequestMethod.GET)
	Object page(Pageable pageable,QueryFilters filters) {			 
		return <%=name%>Service.findPage(pageable, filters.toFilters());
	}
	
	/**
	 * 获取<%=info%>全部数据
	 * @param filters  过滤条件
	 * @param sort		排序条件
	 * @return
	 */
	 @RequestMapping(value = "/list", method = RequestMethod.GET)
	 Object list(QueryFilters filters,Sort sort) {				
		 return <%=name%>Service.findAll(filters.toFilters(), sort);
	 }	
	
	/**
	 * 获取指定的<%=info%>
	 * @param <%=name%>
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	Object get(@PathVariable("id") <%=_name%> <%=name%>){
		if(<%=name%> == null){
			 return Result.error(10005L, "<%=info%>不存在");	
		}
		return <%=name%>;
	}
	
	
	/**
	 * 创建：<%=info%>
	 * @param <%=name%>
	 * @return	返回新建的<%=name%>实体内容
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	Object create(@RequestBody <%=_name%>DTO new<%=_name%>DTO) {
		if(new<%=_name%>DTO == null){
			return Result.error(10005L, "请上传<%=name%>新实体");	
		}
		<%=_name%> <%=name%> = DTOCover.copy(new<%=_name%>DTO, <%=_name%>.class);
		if (!isValid(<%=name%>)) {
			return Result.error(ErrorCode.VIOLATION_CONSTRAINT, getConstraintViolations());
		}
		return <%=name%>Service.save(<%=name%>);
	}
	
	
	/**
	 * 更新：<%=info%>
	 * @param old<%=_name%>		原数据
	 * @param new<%=_name%>  新的实体数据
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public Object modify(@PathVariable("id") <%=_name%> old<%=_name%>,@RequestBody <%=_name%>DTO new<%=_name%>DTO){
		if(old<%=_name%> == null){
			return Result.error(ErrorCode.DATA_NOT_FOUND);
		}
		<%=_name%> new<%=_name%> = DTOCover.copy(new<%=_name%>DTO, <%=_name%>.class);
		if (!isValid(new<%=_name%>)) {
            return Result.error(ErrorCode.VIOLATION_CONSTRAINT, getConstraintViolations());
        }
		return <%=name%>Service.update(old<%=_name%>.getId(), new<%=_name%>, "id","createDate"<%-_updateIgnoreProperties%>);
	}
	
	
	/**
	 * 删除指定：<%=info%>
	 * @param <%=_name%>	要删除的实体，id
	 * @return
	 */
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public String delete(@PathVariable("id") <%=_name%> <%=name%> ){
		if(<%=name%>==null){
			return Result.error(10005L, "要删除的<%=info%>不存在");	
		}
		<%=name%>Service.delete(<%=name%>);
		return "删除成功";
	}
	
	
	
	/**
	 * 批量删除：<%=info%>
	 * @param ids json数组	要删除的id集合
	 * @return
	 */
	@RequestMapping(value="/batch/{ids}", method=RequestMethod.DELETE)
	public String batchDelete(@RequestBody <%=_name%>DTO <%=_name%>DTO){
		if(<%=_name%>DTO.getDelids()==null || <%=_name%>DTO.getDelids().length<1){
			return Result.error(10006L, "请传入要删除的<%=_name%>对象");
		}
		<%=name%>Service.delete(<%=_name%>DTO.delids);
		return "删除成功";
	}
	
	
	
	
}
