package com.ssm.api.bean.entity;

import org.apache.solr.client.solrj.beans.Field;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ShopncGoods {

	@Field//solr需要加这个注解才能使用这个对象差进solr库
	private String goods_name;
	@Field
	private String store_name;
	@Field
	private Integer goods_id;
	@Field
	private Integer store_id;
	
}
