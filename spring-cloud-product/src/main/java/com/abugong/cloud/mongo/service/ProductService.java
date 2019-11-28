package com.abugong.cloud.mongo.service;

import java.util.Map;
import org.springframework.data.domain.Page;
import com.abugong.cloud.mongo.entity.Product;

public interface ProductService {
   
	Product insert(Product product);

	long updata(Product product);

	long delete(Integer id);

	Page<Product> queryByParams(Map<String, Object> params);

}
