package net.sls.service.product.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sls.component.product.ICategoryComponent;
import net.sls.dto.product.Category;
import net.sls.service.product.ICategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import util.model.ComboxDto;
import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;

@Service
public class CategoryService implements ICategoryService {

	@Autowired
	private ICategoryComponent categoryComponent;
	
	@Override
	public ResBo<Object> insertCategory(ReqBo reqBo) {
		Category category = reqBo.getReqModel(Category.class);
		Date createTime = new Date(System.currentTimeMillis());
		category.setCreateTime(createTime);
		categoryComponent.insertCategory(category);
		return new ResBo<Object>();
	}

	@Override
	public ResBo<Object> deleteCategory(ReqBo reqBo) {
		List<Category> list = categoryComponent.selectCategoryByFatherId(reqBo.getParamLong("id"));
		List<Long> ids = new ArrayList<Long>(list.size());
		for(Category c : list){
			ids.add(c.getId());
		}
		categoryComponent.deleteCategorys(ids);
		return new ResBo<Object>();
	}

	@Override
	public ResBo<Category> updateCategory(ReqBo reqBo) {
		categoryComponent.updateCategory(reqBo.getReqModel(Category.class));
		return new ResBo<Category>();
	}

	@Override
	public ResBo<Category> selectCategoryById(ReqBo reqBo) {
		Category category = categoryComponent.selectByPrimaryKey(reqBo.getParamLong("categoryId"));
		ResBo<Category> resBo = new ResBo<Category>(category);
		return resBo;
	}

	@Override
	public ResBo<Pager<List<Category>>> selectCategorys(ReqBo reqBo) {
		return new ResBo<Pager<List<Category>>>(categoryComponent.selectCategorys(
				reqBo.getParamStr("name"), reqBo.getParamLong("parentId"), reqBo.getParamLong("isUse"),
				(Date)reqBo.getParam("createTime1"), (Date)reqBo.getParam("createTime2"), reqBo.getParamLong("type")));
	}

	@Override
	public ResBo<List<Map<String, Object>>> selectCategorysByPid(ReqBo reqBo) {
		List<Category> list = categoryComponent.selectCategoryByFatherId(reqBo.getParamLong("id"));
		List<Long> idList = new ArrayList<Long>(list.size());
		for(Category c : list){
			idList.add(c.getId());
		}
		List<Map<String,Object>> rts = new ArrayList<Map<String,Object>>(list.size());
		Map<String,Object> map = null;
		for(Category c : list){
			map = new HashMap<String,Object>();
			map.put("id", c.getId());
			map.put("name", c.getName());
			map.put("code", c.getCode());
			rts.add(map);
		}
		return new ResBo<List<Map<String, Object>>>(rts);
	}

	@Override
	public ResBo<List<ComboxDto>> selectCategoryComboxByPid(ReqBo reqBo) {
		List<Category> list = categoryComponent.selectCategoryByFatherId(reqBo.getParamLong("pid"));
		List<ComboxDto> rl = new ArrayList<ComboxDto>();
		for(Category c:list){
			ComboxDto dto = new ComboxDto();
			dto.setK(c.getName());
			dto.setV(c.getId().toString());
			rl.add(dto);
		}
		return new ResBo<List<ComboxDto>>(rl);
	}

	@Override
	public ResBo<Category> selectCategoryCodeByPId(ReqBo reqBo) {
		ResBo<Category> resBo = new ResBo<Category>();
		Category category=categoryComponent.selectCategoryCodeByPId(reqBo.getParamLong("parentId"));
		resBo.setResult(category);
		return resBo;
	}
	
}

