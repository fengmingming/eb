package net.sls.dao.ext.user;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import net.sls.dto.user.RoleFunction;

public interface CmsUserMapperExt {

	public List<Map<String,Object>> selectCmsUsersByFilter(@Param("name") String name,@Param("mobile") String mobile,@Param("department") String department,@Param("roleId") Integer roleId,@Param("start") Integer start,@Param("number") Integer number);
	
	public long countCmsUsersByFilter(@Param("name") String name,@Param("mobile") String mobile,@Param("department") String department,@Param("roleId") Integer roleId);
	
	public RoleFunction selectRoleFunctionByUrl(@Param("roleId") Integer roleId,@Param("url") String url);
}
