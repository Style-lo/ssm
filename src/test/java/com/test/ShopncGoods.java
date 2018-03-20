package com.test;

import org.apache.solr.client.solrj.beans.Field;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ShopncGoods {

	@Field//solr需要加这个注解才能使用这个对象差进solr库
	public String goods_name;
	@Field
	public String store_name;
	@Field
	public Integer goods_id;
	
}
