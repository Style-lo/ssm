package com.ssm.api.bean.response;

import java.math.BigDecimal;
import java.math.BigInteger;



public class Order {
	
	private int order_id;
	private BigInteger order_sn;
	private BigInteger pay_sn;
	private int store_id;
	private String store_name;
	private int buyer_id;
	private String buyer_name;
	private String payment_code;
	private double order_amount;
	private BigDecimal shipping_fee;
	private Integer evaluation_state;
	private Integer order_state;
	private Integer refund_state;
	private Integer lock_state;
	private Integer order_type;
	private Integer order_receiving;
	private long add_time;
	private String state_desc;	//交易完成
	private String payment_name;	//支付宝移动支付
	
	private int orderStatus;
	
	private String order_state_msg;
	private int goods_total_num;
	private long finnshed_time;
	private double goods_amount;
	private long delay_time;	
	private long api_pay_time;
	private long receiving_time;
	private String order_msg;
	private double rpt_amount;
	private String shipping_code;
	private int payment_time;
	private String shipping_type="小龟配送";
	private String pintuanInfoNum;
	private String estimatedTime;	//预计到达时间
	private String trade_no;
	private int cancel_time;	//取消订单时间
	private int pintuan_succ_time;	//拼团完成时间
	private double refund_amount;	//退款金额
	private BigInteger buyer_phone;	//电话
	private double rcb_amount;	//充值卡支付金额
	private double pd_amount;	//预存款支付金额
	private String store_avatar;	//店铺头像
	private int evaluation_again_state;	//追加评价状态
	private int delete_state;	//删除状态
	private int order_from;
	private int chain_id;	//自提门店ID
	private int chain_code;	//门店提货码
	private int is_free;	//是否免邮 
	private double logistics_fee;	//小龟服务费价格
	private double packagingFee;	//打包费 
	private int address_id;	//配送地址Id
	private int order_serial;	//序列号
	private String area_code;	//地区code
	private String booking_time;	//预约时间
	private int since_hand;		//是否自提
	private int order_source;		//线上还是线下  0 线上    1线下
	private String QRCode;		//二维码
	private int surplusTime;		//失效时间
	private String cart_id;		//购物车id
	private String formId;		//小程序用
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Order(int order_id, BigInteger order_sn, BigInteger pay_sn,
			int store_id, String store_name, int buyer_id, String buyer_name,
			String payment_code, double order_amount, BigDecimal shipping_fee,
			Integer evaluation_state, Integer order_state,
			Integer refund_state, Integer lock_state, Integer order_type,
			Integer order_receiving, long add_time, String state_desc,
			String payment_name, int orderStatus, String order_state_msg,
			int goods_total_num, long finnshed_time, double goods_amount,
			long delay_time, long api_pay_time, long receiving_time,
			String order_msg, double rpt_amount, String shipping_code,
			int payment_time, String shipping_type, String pintuanInfoNum,
			String estimatedTime, String trade_no, int cancel_time,
			int pintuan_succ_time, double refund_amount,
			BigInteger buyer_phone, double rcb_amount, double pd_amount,
			String store_avatar, int evaluation_again_state, int delete_state,
			int order_from, int chain_id, int chain_code, int is_free,
			double logistics_fee, double packagingFee, int address_id,
			int order_serial, String area_code, String booking_time,
			int since_hand, int order_source, String qRCode, int surplusTime,
			String cart_id, String formId) {
		super();
		this.order_id = order_id;
		this.order_sn = order_sn;
		this.pay_sn = pay_sn;
		this.store_id = store_id;
		this.store_name = store_name;
		this.buyer_id = buyer_id;
		this.buyer_name = buyer_name;
		this.payment_code = payment_code;
		this.order_amount = order_amount;
		this.shipping_fee = shipping_fee;
		this.evaluation_state = evaluation_state;
		this.order_state = order_state;
		this.refund_state = refund_state;
		this.lock_state = lock_state;
		this.order_type = order_type;
		this.order_receiving = order_receiving;
		this.add_time = add_time;
		this.state_desc = state_desc;
		this.payment_name = payment_name;
		this.orderStatus = orderStatus;
		this.order_state_msg = order_state_msg;
		this.goods_total_num = goods_total_num;
		this.finnshed_time = finnshed_time;
		this.goods_amount = goods_amount;
		this.delay_time = delay_time;
		this.api_pay_time = api_pay_time;
		this.receiving_time = receiving_time;
		this.order_msg = order_msg;
		this.rpt_amount = rpt_amount;
		this.shipping_code = shipping_code;
		this.payment_time = payment_time;
		this.shipping_type = shipping_type;
		this.pintuanInfoNum = pintuanInfoNum;
		this.estimatedTime = estimatedTime;
		this.trade_no = trade_no;
		this.cancel_time = cancel_time;
		this.pintuan_succ_time = pintuan_succ_time;
		this.refund_amount = refund_amount;
		this.buyer_phone = buyer_phone;
		this.rcb_amount = rcb_amount;
		this.pd_amount = pd_amount;
		this.store_avatar = store_avatar;
		this.evaluation_again_state = evaluation_again_state;
		this.delete_state = delete_state;
		this.order_from = order_from;
		this.chain_id = chain_id;
		this.chain_code = chain_code;
		this.is_free = is_free;
		this.logistics_fee = logistics_fee;
		this.packagingFee = packagingFee;
		this.address_id = address_id;
		this.order_serial = order_serial;
		this.area_code = area_code;
		this.booking_time = booking_time;
		this.since_hand = since_hand;
		this.order_source = order_source;
		QRCode = qRCode;
		this.surplusTime = surplusTime;
		this.cart_id = cart_id;
		this.formId = formId;
	}
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public BigInteger getOrder_sn() {
		return order_sn;
	}
	public void setOrder_sn(BigInteger order_sn) {
		this.order_sn = order_sn;
	}
	public BigInteger getPay_sn() {
		return pay_sn;
	}
	public void setPay_sn(BigInteger pay_sn) {
		this.pay_sn = pay_sn;
	}
	public int getStore_id() {
		return store_id;
	}
	public void setStore_id(int store_id) {
		this.store_id = store_id;
	}
	public String getStore_name() {
		return store_name;
	}
	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}
	public int getBuyer_id() {
		return buyer_id;
	}
	public void setBuyer_id(int buyer_id) {
		this.buyer_id = buyer_id;
	}
	public String getBuyer_name() {
		return buyer_name;
	}
	public void setBuyer_name(String buyer_name) {
		this.buyer_name = buyer_name;
	}
	public String getPayment_code() {
		return payment_code;
	}
	public void setPayment_code(String payment_code) {
		this.payment_code = payment_code;
	}
	public double getOrder_amount() {
		return order_amount;
	}
	public void setOrder_amount(double order_amount) {
		this.order_amount = order_amount;
	}
	public BigDecimal getShipping_fee() {
		return shipping_fee;
	}
	public void setShipping_fee(BigDecimal shipping_fee) {
		this.shipping_fee = shipping_fee;
	}
	public Integer getEvaluation_state() {
		return evaluation_state;
	}
	public void setEvaluation_state(Integer evaluation_state) {
		this.evaluation_state = evaluation_state;
	}
	public Integer getOrder_state() {
		return order_state;
	}
	public void setOrder_state(Integer order_state) {
		this.order_state = order_state;
	}
	public Integer getRefund_state() {
		return refund_state;
	}
	public void setRefund_state(Integer refund_state) {
		this.refund_state = refund_state;
	}
	public Integer getLock_state() {
		return lock_state;
	}
	public void setLock_state(Integer lock_state) {
		this.lock_state = lock_state;
	}
	public Integer getOrder_type() {
		return order_type;
	}
	public void setOrder_type(Integer order_type) {
		this.order_type = order_type;
	}
	public Integer getOrder_receiving() {
		return order_receiving;
	}
	public void setOrder_receiving(Integer order_receiving) {
		this.order_receiving = order_receiving;
	}
	public long getAdd_time() {
		return add_time;
	}
	public void setAdd_time(long add_time) {
		this.add_time = add_time;
	}
	public String getState_desc() {
		return state_desc;
	}
	public void setState_desc(String state_desc) {
		this.state_desc = state_desc;
	}
	public String getPayment_name() {
		return payment_name;
	}
	public void setPayment_name(String payment_name) {
		this.payment_name = payment_name;
	}
	public int getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getOrder_state_msg() {
		return order_state_msg;
	}
	public void setOrder_state_msg(String order_state_msg) {
		this.order_state_msg = order_state_msg;
	}
	public int getGoods_total_num() {
		return goods_total_num;
	}
	public void setGoods_total_num(int goods_total_num) {
		this.goods_total_num = goods_total_num;
	}
	public long getFinnshed_time() {
		return finnshed_time;
	}
	public void setFinnshed_time(long finnshed_time) {
		this.finnshed_time = finnshed_time;
	}
	public double getGoods_amount() {
		return goods_amount;
	}
	public void setGoods_amount(double goods_amount) {
		this.goods_amount = goods_amount;
	}
	public long getDelay_time() {
		return delay_time;
	}
	public void setDelay_time(long delay_time) {
		this.delay_time = delay_time;
	}
	public long getApi_pay_time() {
		return api_pay_time;
	}
	public void setApi_pay_time(long api_pay_time) {
		this.api_pay_time = api_pay_time;
	}
	public long getReceiving_time() {
		return receiving_time;
	}
	public void setReceiving_time(long receiving_time) {
		this.receiving_time = receiving_time;
	}
	public String getOrder_msg() {
		return order_msg;
	}
	public void setOrder_msg(String order_msg) {
		this.order_msg = order_msg;
	}
	public double getRpt_amount() {
		return rpt_amount;
	}
	public void setRpt_amount(double rpt_amount) {
		this.rpt_amount = rpt_amount;
	}
	public String getShipping_code() {
		return shipping_code;
	}
	public void setShipping_code(String shipping_code) {
		this.shipping_code = shipping_code;
	}
	public int getPayment_time() {
		return payment_time;
	}
	public void setPayment_time(int payment_time) {
		this.payment_time = payment_time;
	}
	public String getShipping_type() {
		return shipping_type;
	}
	public void setShipping_type(String shipping_type) {
		this.shipping_type = shipping_type;
	}
	public String getPintuanInfoNum() {
		return pintuanInfoNum;
	}
	public void setPintuanInfoNum(String pintuanInfoNum) {
		this.pintuanInfoNum = pintuanInfoNum;
	}
	public String getEstimatedTime() {
		return estimatedTime;
	}
	public void setEstimatedTime(String estimatedTime) {
		this.estimatedTime = estimatedTime;
	}
	public String getTrade_no() {
		return trade_no;
	}
	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}
	public int getCancel_time() {
		return cancel_time;
	}
	public void setCancel_time(int cancel_time) {
		this.cancel_time = cancel_time;
	}
	public int getPintuan_succ_time() {
		return pintuan_succ_time;
	}
	public void setPintuan_succ_time(int pintuan_succ_time) {
		this.pintuan_succ_time = pintuan_succ_time;
	}
	public double getRefund_amount() {
		return refund_amount;
	}
	public void setRefund_amount(double refund_amount) {
		this.refund_amount = refund_amount;
	}
	public BigInteger getBuyer_phone() {
		return buyer_phone;
	}
	public void setBuyer_phone(BigInteger buyer_phone) {
		this.buyer_phone = buyer_phone;
	}
	public double getRcb_amount() {
		return rcb_amount;
	}
	public void setRcb_amount(double rcb_amount) {
		this.rcb_amount = rcb_amount;
	}
	public double getPd_amount() {
		return pd_amount;
	}
	public void setPd_amount(double pd_amount) {
		this.pd_amount = pd_amount;
	}
	public String getStore_avatar() {
		return store_avatar;
	}
	public void setStore_avatar(String store_avatar) {
		this.store_avatar = store_avatar;
	}
	public int getEvaluation_again_state() {
		return evaluation_again_state;
	}
	public void setEvaluation_again_state(int evaluation_again_state) {
		this.evaluation_again_state = evaluation_again_state;
	}
	public int getDelete_state() {
		return delete_state;
	}
	public void setDelete_state(int delete_state) {
		this.delete_state = delete_state;
	}
	public int getOrder_from() {
		return order_from;
	}
	public void setOrder_from(int order_from) {
		this.order_from = order_from;
	}
	public int getChain_id() {
		return chain_id;
	}
	public void setChain_id(int chain_id) {
		this.chain_id = chain_id;
	}
	public int getChain_code() {
		return chain_code;
	}
	public void setChain_code(int chain_code) {
		this.chain_code = chain_code;
	}
	public int getIs_free() {
		return is_free;
	}
	public void setIs_free(int is_free) {
		this.is_free = is_free;
	}
	public double getLogistics_fee() {
		return logistics_fee;
	}
	public void setLogistics_fee(double logistics_fee) {
		this.logistics_fee = logistics_fee;
	}
	public double getPackagingFee() {
		return packagingFee;
	}
	public void setPackagingFee(double packagingFee) {
		this.packagingFee = packagingFee;
	}
	public int getAddress_id() {
		return address_id;
	}
	public void setAddress_id(int address_id) {
		this.address_id = address_id;
	}
	public int getOrder_serial() {
		return order_serial;
	}
	public void setOrder_serial(int order_serial) {
		this.order_serial = order_serial;
	}
	public String getArea_code() {
		return area_code;
	}
	public void setArea_code(String area_code) {
		this.area_code = area_code;
	}
	public String getBooking_time() {
		return booking_time;
	}
	public void setBooking_time(String booking_time) {
		this.booking_time = booking_time;
	}
	public int getSince_hand() {
		return since_hand;
	}
	public void setSince_hand(int since_hand) {
		this.since_hand = since_hand;
	}
	public int getOrder_source() {
		return order_source;
	}
	public void setOrder_source(int order_source) {
		this.order_source = order_source;
	}
	public String getQRCode() {
		return QRCode;
	}
	public void setQRCode(String qRCode) {
		QRCode = qRCode;
	}
	public int getSurplusTime() {
		return surplusTime;
	}
	public void setSurplusTime(int surplusTime) {
		this.surplusTime = surplusTime;
	}
	public String getCart_id() {
		return cart_id;
	}
	public void setCart_id(String cart_id) {
		this.cart_id = cart_id;
	}
	public String getFormId() {
		return formId;
	}
	public void setFormId(String formId) {
		this.formId = formId;
	}

}
