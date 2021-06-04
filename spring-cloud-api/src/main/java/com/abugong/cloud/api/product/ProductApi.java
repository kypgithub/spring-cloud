package com.abugong.cloud.api.product;

import com.abugong.cloud.api.configuration.FeignLog;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value="spring-cloud-product", configuration = FeignLog.class)
public interface ProductApi {

	@PostMapping("/product/add")
	public R add(@RequestBody Product product);

	@PostMapping("/product/updata")
	public R updata(@RequestBody Product product);
	
	@GetMapping("/product/delete")
	public R delete(@RequestParam("id") Integer id);
	
	@PostMapping("/product/queryByParams")
	public R queryByParams(@RequestBody Map<String, Object> params);

}
