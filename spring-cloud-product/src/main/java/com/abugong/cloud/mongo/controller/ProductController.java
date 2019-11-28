package com.abugong.cloud.mongo.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.abugong.cloud.mongo.entity.Product;
import com.abugong.cloud.mongo.service.ProductService;
import com.abugong.cloud.utils.R;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping("/add")
	public R add(@RequestBody Product product) {
		Product pro = productService.insert(product);
		return R.ok().put("product", pro);
	}

	@GetMapping("/delete")
	public R delete(@RequestParam("id") Integer id) {
		long delete = productService.delete(id);
		if (delete > 0) {
			return R.ok();
		}
		return R.error();
	}
	
	@PostMapping("/updata")
	public R updata(@RequestBody Product product) throws Exception {
		long updata = productService.updata(product);
		if (updata > 0) {
			return R.ok();
		}
		return R.ok();
	}
	
	@PostMapping("/queryByParams")
	public R queryByParams(@RequestBody Map<String, Object> params) throws Exception {
		Page<Product> page = productService.queryByParams(params);
		return R.ok().put("page", page);
	}
}
