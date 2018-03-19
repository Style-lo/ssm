package com.ssm.api.bean.entity;

import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.apache.solr.client.solrj.beans.Field;

import com.ssm.api.bean.request.User;

/**
 * Created by mkk on 2017/1/2. 商品信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopncGoods {
	@Field
	private int goods_id;// 商品id
	@Field
	private int goods_commonid;// 商品公共表id
	@Field
	private String goods_name;// 商品名称
	@Field
	private String goods_jingle;// 商品信息
	@Field
	private int store_id;// 店铺id
	@Field
	private String store_name;// 店铺名称
	@Field
	private int gc_id;// 商品类别
	@Field
	private int gc_id_1;// 一级类别
	@Field
	private int gc_id_2;// 二级类别
	private String gc_id_1Name;
	private String gc_id_2Name;
	private String gc_id_3Name;
	private int gc_id_3;// 三级类别
	@Field
	private double goods_price;// 商品价格//真实价格
	@Field
	private double goods_promotion_price;// 商品促销价格
	@Field
	private double goods_marketprice;// 市场价//虚价
	@Field
	private String goods_serial;// 商品货号
	@Field
	private int goods_storage_alarm;// 库存报警值
	@Field
	private int goods_salenum;// 销量
	@Field
	private int goods_collect;// 收藏数
	@Field
	private String spec_name;// 规格名称
	@Field
	private int goods_storage;// 商品库存
	@Field
	private String goods_image;// 商品主图
	@Field
	private int goods_state;// 商品状态//商品状态 0下架，1正常，10违规（禁售）
	@Field
	private String goods_state_time;// 上架时间
	@Field
	private int goods_verify;// 商品审核 1通过，0未通过，10审核中
	@Field
	private int color_id;// 颜色规格
	@Field
	private double goods_freight;// 运费//0为免运费
	@Field
	private boolean goods_commend;// 商品热门推荐 1是，0否 默认0
	@Field
	private int goods_commend_sort;// 商品热门排序
	@Field
	private int evaluation_count;// 评价数
	@Field
	private double longitude;// 经度
	@Field
	private double latitude;// 维度
	@Field
	private double longitudegd;// 经度
	@Field
	private double latitudegd;// 维度
	@Field
	private int forwardNum;// 转发量
	@Field
	private int goods_spec_parentId;// 规格父类id
	private String goods_spec_parentName;// 规格父类Name
	@Field
	private String goods_spec = "";// 商品规格json
	@Field
	private String goods_specValue = "";// 商品规格json值；键值对，库存价格
	@Field
	private String goodsTag;// 商品tag//img：图片；video：视频
	@Field
	private double packagingFee;// 打包费
	@Field
	private int goods_stcids;// 店内分类
	@Field
	private String goods_stcidsName;// 店内分类名称
	@Field
	private String goods_areazone_areaid;
	@Field
	private String goods_area_province;
	@Field
	private String goods_area_city;
	@Field
	private String goods_area_district;
	@Field
	private String goods_area_town;
	@Field
	private String goods_addtime;
	@Field
	private String goods_edittime;
	@Field
	private int store_sort;// 店内排序
	@Field
	private int isXianshi;// 是否是特价商品
	@Field
	private int is_store_commend;// 是否店铺推荐
	@Field
	private int thumbUp;
	@Field
	private int setpOn;
	@Field
	private int pintuanFlag;// 0:不是拼团商品；1是拼团商品；2审核中
	@Field
	private int pintuanOrderNum;// 拼团单数,多少单成团
	@Field
	private double pintuanPrice;// 拼团价格
	@Field
	private int pintuanStorage;// 拼团库存
	@Field
	private double pintuanDiscount;// 团长折扣，0~10，小数点两位
	@Field
	private String pintuanFlagInfo;// 拼团状态描述
	@Field
	private String pintuanStr;// 拼团规格Str
	private int goodsNum;// 商品数量
	private String imageOrVideoStr;// 图片视频地址
	@Field
	private String goods_body;// 商品描述
	@Field
	private String mobile_body;// 手机端商品描述
	@Field
	private String goods_param;// 商品参数
	@Field
	private String keyword_seo;// 商品关键字
	@Field
	private String goods_verify_msg;// 审核失败说明
	@Transient
	private boolean has_mobile_body = false; // 是否有移动端商品详情
	@Transient
	private int evaluation_num;// 评价数（新）
	@Transient
	private float evaluation_avg = 100;// 好评率

	@Transient
	private String distance;// 用户距离店铺
	@Transient
	private String store_avatar;// 店铺头像
	@Transient
	private int memberId;// 用户Id，用于聊天

	@Transient
	private String[] imageOrVideo;

	@Transient
	private int cartNum = 0;// 购物车数量

	@Transient
	private boolean isFocus;// 是否关注
	@Transient
	private boolean isFavorites;// 是否收藏

	@Transient
	private String run_time;// 营业时间
	@Transient
	private boolean store_state;// 店铺状态，0关闭，1开启，2审核中

	@Transient
	private boolean isBusiness;// 是否营业

	@Transient
	private long start_time;// 天天特价开始时间

	@Transient
	private long end_time;// 天天特价结束时间
	@Field
	private double price_discount;// 限时折扣


	@Transient
	private double allPackagingFee = 0;
	@Transient
	private double cartTotalPrice = 0;
	@Transient
	private String web_goods_page;
	@Transient
	private String app_goods_page;
	@Transient
	private double discountPrice;// 商品现价
	@Transient
	private int store_show_type;// 店铺显示模板
	@Transient
	private String goodsPriceStr;// 小程序用的价钱原价
	@Transient
	private String discountPriceStr;// 小程序用的现价
	@Transient
	private int isMoreSpec;// 是否为多规格(0=单，1=多)
	@Transient
	private boolean xianshi;
	@Transient
	private double goodsPrice;// 商品原价
	@Transient
	private double newGoodsPrice;// 商品原价(新)
	@Transient
	private double newDiscountPrice;// 商品现价(新)
	@Transient
	private String goods_barcode;// 商品条形码
	@Transient
	private String hdSkuId;// 海鼎的skuId;
	@Transient
	private String spec;// 订单确认页规格
	@Transient
	private int goods_num;// 订单确认页商品数量
	@Transient
	private double goods_total;// 订单确认页商品总金额
	@Transient
	private int speediness;// 是否支持快速购物 1为支持 0为不支持
	@Transient
	private int shopNum;// 下单商品数量

	
}
