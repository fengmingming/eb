package net.sls.service.product;

import java.util.List;

import net.sls.dto.product.Brand;
import util.model.ComboxDto;
import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;

public interface IBrandService {

	public ResBo<Object> insertSelective(ReqBo reqBo);
	public ResBo<Object> updateByPrimaryKeySelective(ReqBo reqBo);
	public ResBo<Pager<List<Brand>>> selectBrand(ReqBo reqBo);
	public ResBo<List<ComboxDto>> selectBrandCombobox(ReqBo reqBo);
}
