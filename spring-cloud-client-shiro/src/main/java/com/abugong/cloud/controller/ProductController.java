package com.abugong.cloud.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.abugong.cloud.mongo.entity.Product;
import com.abugong.cloud.product.ProductApi;
import com.abugong.cloud.utils.R;

@RestController
@RequestMapping("product")
public class ProductController {
    
	@Autowired
	private ProductApi productApi;

	@GetMapping("/addGoods")
	@RequiresPermissions("user:add")
	public ModelAndView addGoods() {
        Random p_r =new Random();
        Random s_r =new Random();
       
        Product product = new Product();
        product.setProductName("iphone" +p_r.nextInt(100));
        product.setState(s_r.nextInt(1));
   
		R r = productApi.add(product);
		ModelAndView mv = new ModelAndView();
		mv.addObject("res", r);
		mv.setViewName("add");
		return mv;
	}

	@GetMapping("/updataGoods")
	@RequiresPermissions("user:updata")
	public ModelAndView updata() {
        Random p_r =new Random(100);
        Random s_r =new Random(1);
		Product product = new Product();
        product.setProductName("iphone" +p_r.nextInt());
        product.setState(s_r.nextInt());
		R r = productApi.updata(product);
		ModelAndView mv = new ModelAndView();
		mv.addObject("res", r);
		mv.setViewName("add");
		return mv;
	}
	
	@GetMapping("/findProductListByParam")
	@RequiresPermissions("user:list")
	public R findProductListByParam() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("productName", "iphone22");
		params.put("offset", 3);
		params.put("limit", 5);
		return productApi.queryByParams(params);
	}
}
