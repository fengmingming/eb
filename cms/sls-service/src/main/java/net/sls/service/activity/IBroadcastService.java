package net.sls.service.activity;

import java.util.List;

import net.sls.dto.activity.Broadcast;
import net.sls.dto.user.User;
import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;

public interface IBroadcastService {

	ResBo<Broadcast> insertBroadcast(ReqBo reqBo);

	ResBo<Pager<List<Broadcast>>> selectBroadcastList(ReqBo reqBo);

	ResBo<Broadcast> updateBroadcast(ReqBo reqBo);

}
