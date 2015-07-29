package net.sls.service.impl.pc.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import framework.web.ReqBo;
import framework.web.ResBo;
import net.sls.component.pc.product.ICategoryComponent;
import net.sls.dto.pc.product.Category;
import net.sls.dto.pc.product.GoodsCategory;
import net.sls.service.pc.product.ICategoryService;
@Service("categoryService")
public class CategoryService implements ICategoryService{

	@Autowired
	private ICategoryComponent categoryComponent;
	@Override
	public ResBo<List<Category>> getCategoryList(ReqBo reqBo) {
		ResBo<List<Category>> resBo=new ResBo<List<Category>>(); 
		List<Category> categoryList=categoryComponent.selectCategoryList(reqBo.getParamLong("parentId"));
		resBo.setResult(categoryList);
		return resBo;
	}
	@Override
	public ResBo<GoodsCategory> selectCategoryNames(ReqBo reqBo) {
		Long goodsId = reqBo.getParamLong("goodsId");
		if(goodsId==null)
			return null;
		ResBo<GoodsCategory> resBo = new ResBo<GoodsCategory>();
		GoodsCategory category = categoryComponent.selectCategory(goodsId );
		if(category==null)
			return null;
		String name = null;
		if(category.getOneId()!=null){
			//找到一级品类的名称，如果为空，直接返回null
			name = categoryComponent.selectCategoryName(category.getOneId());
			if(name==null)
				return null;
			category.setFirstName(name);
			if(category.getTwoId()!=null){
				//找到二级品类的名称，如果为空，返回category
				name = categoryComponent.selectCategoryName(category.getTwoId());
				if(name==null){
					//把查到的一级品类名称设置进去
					resBo.setResult(category);
					return resBo;
				}
				category.setSecondName(name);
				if(category.getThreeId()!=null){
					//找到三级品类的名称，如果为空，返回category
					name = categoryComponent.selectCategoryName(category.getThreeId());
					if(name==null){
						//把查到的二级品类名称设置进去
						resBo.setResult(category);
						return resBo;
					}
					category.setThirdName(name);
				}
			}
		}
		//把查到的三级品类名称设置进去
		resBo.setResult(category);
		
		return resBo;
	}
	


}
