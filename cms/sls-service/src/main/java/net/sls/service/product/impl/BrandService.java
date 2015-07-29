package net.sls.service.product.impl;

import java.util.Date;
import java.util.List;

import net.sls.component.product.IBrandComponent;
import net.sls.dto.product.Brand;
import net.sls.service.product.IBrandService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import util.model.ComboxDto;
import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;

@Service
public class BrandService implements IBrandService{

	
	@Autowired
	private IBrandComponent brandComponent;
	
	
	@Override
	public ResBo<Object> insertSelective(ReqBo reqBo) {

		Brand brand = reqBo.getReqModel(Brand.class);
		//品牌名是否存在
		if(brandComponent.selectNameIsExist(brand.getName())){
			return new ResBo<Object>(22);
		}
		
		Date createTime = new Date(System.currentTimeMillis());
		brand.setCreateTime(createTime);
		brandComponent.insertSelective(brand);
		return new ResBo<Object>();
	}

	@Override
	public ResBo<Object> updateByPrimaryKeySelective(ReqBo reqBo) {

		Brand brand = reqBo.getReqModel(Brand.class);
		brandComponent.updateByPrimaryKeySelective(brand);
		return new ResBo<Object>();
	}

	@Override
	public ResBo<Pager<List<Brand>>> selectBrand(ReqBo reqBo) {
		
		return new ResBo<Pager<List<Brand>>>( brandComponent.selectBySelective(reqBo.getParamLong("id"), 
				reqBo.getParamStr("name"), reqBo.getParamStr("enName"), 
				reqBo.getParamStr("description"), reqBo.getParamStr("spell"), 
				reqBo.getParamStr("keyword"), reqBo.getParamStr("url"), 
				reqBo.getParamStr("img"), (Date)reqBo.getParam("createTime"),
				reqBo.getParamInt("page"), reqBo.getParamInt("rows")));
	}

	@Override
	public ResBo<List<ComboxDto>> selectBrandCombobox(ReqBo reqBo) {
		return new ResBo<List<ComboxDto>>(brandComponent.selectBrandCombobox());
	}

}
