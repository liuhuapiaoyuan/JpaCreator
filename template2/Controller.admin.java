package <%=packageName%>.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import <%=packageName%>.entity.<%=_name%>;

import <%=packageName%>.service.<%=_name%>Service;


import cn.yunnet.jpa.specifications.QueryFilters;
import cn.yunnet.configuration.bean.ErrorCode;
import cn.yunnet.configuration.exception.BusinessException;

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
	Object get(@PathVariable("id") <%=_name%> <%=name%>)  throws BusinessException{
		if(<%=name%> == null){
			throw new BusinessException(ErrorCode.DATA_NOT_FOUND);
		}
		return <%=name%>;
	}
	
	
	/**
	 * 创建：<%=info%>
	 * @param <%=name%>
	 * @return	返回新建的<%=name%>实体内容
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	Object create(@RequestBody @Validated <%=_name%>DTO new<%=_name%>DTO)  throws BusinessException{
		<%=_name%> <%=name%> = DTOCover.copy(new<%=_name%>DTO, <%=_name%>.class);
		return <%=name%>Service.save(<%=name%>);
	}
	
	
	/**
	 * 更新：<%=info%>
	 * @param old<%=_name%>		原数据
	 * @param new<%=_name%>  新的实体数据
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public Object modify(@PathVariable("id") <%=_name%> old<%=_name%>,@RequestBody @Validated <%=_name%>DTO new<%=_name%>DTO)  throws BusinessException{
		<%=_name%> new<%=_name%> = DTOCover.copy(new<%=_name%>DTO, <%=_name%>.class);
		return <%=name%>Service.update(old<%=_name%>.getId(), new<%=_name%>, "id","createDate"<%-_updateIgnoreProperties%>);
	}
	
	
	/**
	 * 删除指定：<%=info%>
	 * @param <%=_name%>	要删除的实体，id
	 * @return
	 */
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public String delete(@PathVariable("id") <%=_name%> <%=name%> )  throws BusinessException {
		if(<%=name%>==null){
			throw new BusinessException(ErrorCode.DATA_NOT_FOUND);
		}
		<%=name%>Service.delete(<%=name%>);
		return "删除成功";
	}
	
	
	
		/**
	 * 批量删除：<%=info%>
	 * @return
	 */
	@RequestMapping(value="", method=RequestMethod.DELETE)
	@ApiOperation("批量删除：<%=info%>")
	public String delete(@RequestBody IDs ids)  throws BusinessException {
		if(ids.getIds()==null){
			throw new BusinessException(ErrorCode.DATA_NOT_FOUND);
		}
		<%=name%>Service.delete(ids.getIds().toArray(new Long[]{}));
		return "删除成功";
	}
 
	
	
 
	
	
	
	
}
