package com.ssm.api.bean.entity;

import org.apache.solr.client.solrj.beans.Field;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ShopncStore {

	@Field//solr需要加这个注解才能使用这个对象差进solr库
	public Integer store_id;
	@Field
	public String store_name;
	
}
