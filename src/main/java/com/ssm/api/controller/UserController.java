package com.ssm.api.controller;

import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ssm.api.bean.entity.UserLog;
import com.ssm.api.bean.entity.UserMoney;
import com.ssm.api.bean.request.User;
import com.ssm.api.bean.response.Order;
import com.ssm.api.service.OrderService;
import com.ssm.api.service.UserService;


@Controller
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value="getUserId",method={(RequestMethod.GET),(RequestMethod.POST)})
	public Object getUserId(@RequestParam Integer id){
		
		System.out.println("controller中-------");
		User userId = userService.getUserId(id);
		return userId.getUser_name();
		
	}
	@ResponseBody
	@RequestMapping(value="test",method={(RequestMethod.GET),(RequestMethod.POST)})
	public Object test(@RequestParam double id){
		System.out.println("controller中-------"+id);
		return id;
	}
	
	@ResponseBody
	@RequestMapping(value="test1",method={(RequestMethod.GET),(RequestMethod.POST)})
	public Object test1(@RequestBody(required=false) User user){
		System.out.println("controller中-------"+user.getTest());
		return Double.valueOf(user.getTest());
	}
	@RequestMapping(value="stateGetUser",method={(RequestMethod.GET),(RequestMethod.POST)})
	public Object stateGetUser(@RequestParam String type,@RequestParam int state){
		
		List<User> stateGetUser = userService.stateGetUser(type, state);
		for (User user : stateGetUser) {
			System.out.println(user);
		}
		return "ok";
		
	}
	
	
	
	@RequestMapping(value="insertUser")
	public String insertUser(User user,UserLog userLog, UserMoney userMoney){
			userService.insertUser(user, userLog, userMoney);
		
		return null;
		
	}
	
	@RequestMapping(value="test1",method=RequestMethod.GET)
	@ResponseBody
	public Object test1(){
		System.out.println("_----88");
		return 1 ;
	}
	
	@RequestMapping("/download")
    public void downLoadExcel(HttpServletResponse response) {
		
		 List<User> user = userService.getUser();
		 
		//作出最简单的一张表，不设其他字体，样式等
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("sheet1");
        HSSFRow row = sheet.createRow(0);
        
        String[] heads = {"姓名", "密码", "年龄"};
        for (int i = 0; i <heads.length ; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(heads[i]);
        }
        for (int j = 0; j <user.size() ; j++) {
        	HSSFRow rowbody = sheet.createRow(j + 1);
//            rowbody.createCell(0).setCellValue(j+1);
//            rowbody.createCell(1).setCellValue(user.get(j).getId());
            rowbody.createCell(0).setCellValue(user.get(j).getUser_name());
            rowbody.createCell(1).setCellValue(user.get(j).getPassword());
            rowbody.createCell(2).setCellValue(user.get(j).getAge());
        }
        try {
            response.setHeader("content-Type", "application/vnd.ms-excel;charset=utf-8");
            String name = name = URLEncoder.encode("mys导出.xls", "UTF8");
            response.setHeader("Content-Disposition", "attachment;filename=" + name);
            workbook.write(response.getOutputStream());

        } catch (Exception e) {
        	e.printStackTrace();
        }
	}
	
	
	
	
	@RequestMapping("/downloadOrder")
    public void orderDownLoadExcel(HttpServletResponse response) {
		
		 List<Order> order = orderService.getOrder();
		 
		//作出最简单的一张表，不设其他字体，样式等
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("sheet1");
        HSSFRow row = sheet.createRow(0);
        
        String[] heads = {" ","订单号", "支付单号", "店铺id","店铺名字","用户id","用户名","用户手机号","空","订单生成时间","支付方式","支付时间","订单完成时间","商品总价格","订单总价格"};
        for (int i = 0; i <heads.length ; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(heads[i]);
        }
        for (int j = 0; j <order.size() ; j++) {
        	HSSFRow rowbody = sheet.createRow(j + 1);
            rowbody.createCell(0).setCellValue(j+1);
//            rowbody.createCell(1).setCellValue(user.get(j).getId());
            rowbody.createCell(1).setCellValue(order.get(j).getOrder_sn().toString());
            rowbody.createCell(2).setCellValue(order.get(j).getPay_sn().toString());
            rowbody.createCell(3).setCellValue(order.get(j).getStore_id());
            rowbody.createCell(4).setCellValue(order.get(j).getStore_name());
            rowbody.createCell(5).setCellValue(order.get(j).getBuyer_id());
            rowbody.createCell(6).setCellValue(order.get(j).getBuyer_name());
            rowbody.createCell(7).setCellValue(order.get(j).getBuyer_phone().toString());
            rowbody.createCell(9).setCellValue(order.get(j).getAdd_time());
            rowbody.createCell(10).setCellValue(order.get(j).getPayment_code());
            rowbody.createCell(11).setCellValue(order.get(j).getPayment_time());
            rowbody.createCell(12).setCellValue(order.get(j).getFinnshed_time());
            rowbody.createCell(13).setCellValue(order.get(j).getGoods_amount());
            rowbody.createCell(14).setCellValue(order.get(j).getOrder_amount());
        }
        try {
            response.setHeader("content-Type", "application/vnd.ms-excel;charset=utf-8");
            String name = name = URLEncoder.encode("Order导出.xls", "UTF8");
            response.setHeader("Content-Disposition", "attachment;filename=" + name);
            workbook.write(response.getOutputStream());

        } catch (Exception e) {
        	e.printStackTrace();
        }
	}
	
	
	/**
	 * excel 导入，需要先访问这个jsp文件 \jsp\excelInsert.jsp
	 * @param file
	 * @param request
	 * @param response
	 * @return
	 */
    @RequestMapping(value="/upload",method = RequestMethod.POST,produces="text/html;charset=UTF-8")  
    @ResponseBody  
    public String  upload(@RequestParam(value="file",required = false)MultipartFile file,HttpServletRequest request, HttpServletResponse response){  
        String result = userService.readExcelFile(file);  
        return result;  
    } 
}
