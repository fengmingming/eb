package net.sls.service.dict.impl;

import java.util.List;

import net.sls.component.dict.IDictComponent;
import net.sls.dto.product.Dict;
import net.sls.service.dict.IDictService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import util.model.ComboxDto;
import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;

@Service
public class DictService implements IDictService {

	@Autowired
	private IDictComponent dictComponent;
	
	@Override
	public ResBo<Object> insertDict(ReqBo reqBo) {
		Dict dict = reqBo.getReqModel(Dict.class);
		dictComponent.insertDict(dict);
		return new ResBo<Object>();
	}

	@Override
	public void deleteDict(ReqBo reqBo) {
		dictComponent.deleteDict(reqBo.getParamLong("dictId"));
	}

	@Override
	public ResBo<Dict> updateDict(ReqBo reqBo) {
		dictComponent.updateDict(reqBo.getReqModel(Dict.class));
		return new ResBo<Dict>();
	}

	@Override
	public ResBo<Dict> selectDictById(ReqBo reqBo) {
		Dict dict = dictComponent.selectDict(reqBo.getParamLong("dictId"));
		ResBo<Dict> resBo = new ResBo<Dict>(dict);
		return resBo;
	}

	@Override
	public ResBo<Pager<List<Dict>>> selectDicts(ReqBo reqBo) {
		return new ResBo<Pager<List<Dict>>>(dictComponent.selectDicts(reqBo.getParamStr("code"), reqBo.getParamStr("name"), reqBo.getParamInt("type"), reqBo.getParamInt("page")-1, reqBo.getParamInt("rows")));
	}

	@Override
	public ResBo<List<ComboxDto>> selectDictCombox(ReqBo reqBo) {
		return new ResBo<List<ComboxDto>>(dictComponent.selectDictCombox());
	}

	@Override
	public ResBo<List<ComboxDto>> selectDictComboxByType(ReqBo reqBo) {
		return new ResBo<List<ComboxDto>>(dictComponent.selectDictComboxByType(reqBo.getParamInt("type"), reqBo.getParamStr("key")));
	}

}
