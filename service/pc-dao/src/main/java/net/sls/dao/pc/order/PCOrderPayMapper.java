package net.sls.dao.pc.order;

import org.apache.ibatis.annotations.Param;

import net.sls.dto.pc.order.OrderPay;

public interface PCOrderPayMapper {
	
	/**
	 * @Description: TODO 插入定单的支付方式  支付类型（支付宝0，银联1，。。。）
	 * @param record
	 * @return int
	 * @date 2015年1月5日 下午6:03:40
	 */
	int insert(OrderPay record);
	
	/**
	 * alipay支付成功添加用户账户信息
	 * @param account
	 * @param orderId
	 * @return
	 */
	int updateOrderPayByOrderId(@Param("account") String account, @Param("orderId") Long orderId);
}