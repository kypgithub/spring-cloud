package com.abugong.cloud.mongo.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;
import com.abugong.cloud.mongo.entity.Product;
import com.abugong.cloud.mongo.service.ProductService;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

@Service
public class ProductServiceImpl implements ProductService {

	private static final String collectionName = "s1";
	@Autowired
	private MongoTemplate mongoTemplate;

	
	@Override
	public Product insert(Product product) {
		// TODO Auto-generated method stub
		Product pro = mongoTemplate.insert(product);
		return pro;
	}

	@Override
	public long updata(Product product) {
		// TODO Auto-generated method stub
		Query query = Query.query(Criteria.where("id").is(product.getId()));
		Update update = new Update();
		update.set("productName", product.getProductName());
		UpdateResult res = mongoTemplate.updateFirst(query, update, collectionName);
		return res.getMatchedCount();
	}

	@Override
	public long delete(Integer id) {
		// TODO Auto-generated method stub
		Query query = Query.query(Criteria.where("id").is(id));
		DeleteResult res = mongoTemplate.remove(query, collectionName);
		return res.getDeletedCount();
	}

	@Override
	public Page<Product> queryByParams(Map<String, Object> params) {
		String productName = params.get("productName").toString();
		Integer offset = (Integer)params.get("offset");
		Integer limit = (Integer)params.get("limit");
		Query query = Query.query(Criteria.where("productName").is(productName));
        Pageable pageable = new PageRequest(offset, limit);
        query.with(pageable);
        query.with(new Sort(Direction.ASC, "productName"));
        List<Product> items = mongoTemplate.find(query, Product.class, collectionName);
        return PageableExecutionUtils.getPage(items, pageable, () -> 0);
	}

}
