package net.sls.component.user;

import net.sls.dto.user.Role;

public interface IRoleComponent {

	public Role selectRoleById(int roleId);
	
	// update the Role object to new 
	int updateRole(Role r);
}
