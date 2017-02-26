package com.pussinboots.morning.os.modules.webfront.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.pussinboots.morning.common.controller.BaseController;
import com.pussinboots.morning.os.modules.content.entity.AdvertDetail;
import com.pussinboots.morning.os.modules.content.entity.NavigationBar;
import com.pussinboots.morning.os.modules.content.enums.AdvertDetailStatusEnum;
import com.pussinboots.morning.os.modules.content.enums.AdvertTypeEnum;
import com.pussinboots.morning.os.modules.content.enums.NavigationBarStatusEnum;
import com.pussinboots.morning.os.modules.content.enums.NavigationBarTypeEnum;
import com.pussinboots.morning.os.modules.content.service.IAdvertDetailService;
import com.pussinboots.morning.os.modules.content.service.INavigationBarService;
import com.pussinboots.morning.os.modules.product.entity.Product;
import com.pussinboots.morning.os.modules.product.enums.ProductStarEnum;
import com.pussinboots.morning.os.modules.product.service.ICategoryService;
import com.pussinboots.morning.os.modules.product.service.IProductService;
import com.pussinboots.morning.os.modules.product.vo.CategoryInNavVO;
import com.pussinboots.morning.os.modules.product.vo.ProductVO;

/**
 * 
* 项目名称：morning-os-webapp   
* 类名称：WebFrontController   
* 类描述：商城首页表示层控制器     
* 创建人：陈星星   
* 创建时间：2017年2月23日 下午3:53:21   
*
 */
@Controller
public class WebFrontController extends BaseController {
	
	/** 电子商城首页 */
	private static final String INDEX = getViewPath("modules/webfront/index");
	
	@Autowired
	private IAdvertDetailService advertDetailService;
	@Autowired
	private INavigationBarService navigationBarService;
	@Autowired
	private ICategoryService categoryService;
	@Autowired
	private IProductService productService;
	
	@GetMapping(value = "/index")
	public String index(Model model) {
		// 首页-广告栏-左部导航配置
		List<NavigationBar> indexAdvertLeft = navigationBarService.selectNavigationBarByType(
				NavigationBarTypeEnum.INDEX_ADVERT_LEFT.getType(), NavigationBarStatusEnum.SHOW.getStatus());
		model.addAttribute(NavigationBarTypeEnum.INDEX_ADVERT_LEFT.getCode(), indexAdvertLeft);
		
		// 广告位配置
		List<AdvertDetail> indexCarouselImgs = advertDetailService
				.selectByAdvertCode(AdvertTypeEnum.INDEX_CAROUSEL.getCode(), AdvertDetailStatusEnum.SHOW.getStatus());
		model.addAttribute("indexCarouselImgs", indexCarouselImgs);// 首页轮播广告列表
		List<AdvertDetail> indexHotAdvertImgs = advertDetailService
				.selectByAdvertCode(AdvertTypeEnum.INDEX_HOT_ADVERT.getCode(), AdvertDetailStatusEnum.SHOW.getStatus());
		model.addAttribute("indexHotAdvertImgs", indexHotAdvertImgs);// 首页轮播广告列表
		
		// 首页导航栏商品列表
		List<CategoryInNavVO> categoryInNavVOs = categoryService.selectCategorysByStatus();
		model.addAttribute("categoryInNavVOs", categoryInNavVOs);// 首页轮播广告列表
		
		// 明星单品
		List<Product> products = productService.selectProductsByStar(ProductStarEnum.STAR_PRODUCT.getStatus(),ProductVO.STAR_PRODUCT_NUMBER);
		model.addAttribute("products", products);
		return INDEX;
	}
	
}
