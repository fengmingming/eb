package net.sls.controller.product;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sls.dto.product.Brand;
import net.sls.service.product.IBrandService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import util.framework.FindServiceUtil;
import util.model.ComboxDto;
import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;

@Controller
@RequestMapping("brand")
public class BrandController {

	@RequestMapping("add.htm")
	@ResponseBody
	public ResBo<Object> insertBrand(@ModelAttribute Brand brand){
		IBrandService ds = FindServiceUtil.findService(IBrandService.class);
		ds.insertSelective(new ReqBo(brand));
		ResBo<Object> resBo=new ResBo<Object>();
		resBo.setResult(brand);
		return resBo;
	}
	
	@RequestMapping("update.htm")
	@ResponseBody
	public ResBo<Object> updateBrand(@ModelAttribute Brand brand){
		IBrandService ds = FindServiceUtil.findService(IBrandService.class);
		return ds.updateByPrimaryKeySelective(new ReqBo(brand));
	}
	
	@RequestMapping("brandList.htm")
	@ResponseBody
	public ResBo<Pager<List<Brand>>> selectGoodsBrand(
			HttpServletRequest request) {
		ReqBo reqBo = new ReqBo(request);
		IBrandService brandService = FindServiceUtil.findService(IBrandService.class);
		return brandService.selectBrand(reqBo);
	}
	
	@RequestMapping("addinit.htm")
	public String insertInit(){
		return "brand/addinit";
	}
	
	@RequestMapping("select.htm")
	public String selectInit(){
		return "brand/selectBrand";
	}
	
	@RequestMapping("combobox.htm")
	@ResponseBody
	public ResBo<List<ComboxDto>> selectBrandCombobox(){
		IBrandService brandService = FindServiceUtil.findService(IBrandService.class);
		return brandService.selectBrandCombobox(new ReqBo());
	}
}
