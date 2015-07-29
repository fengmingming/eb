package net.sls.dao.pc.order;

import net.sls.dto.pc.order.OrderLog;


public interface PCOrderLogMapper {
	 /**@author wangshaohui
     * @Description: TODO 在插入定单时，插入定单的日志
     * @param record
     * @return int
     * @date 2015年1月5日 下午7:12:40
     */
    int insert(OrderLog record);
}