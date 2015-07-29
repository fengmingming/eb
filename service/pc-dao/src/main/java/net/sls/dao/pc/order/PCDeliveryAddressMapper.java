package net.sls.dao.pc.order;

import net.sls.dto.pc.order.DeliveryAddress;


public interface PCDeliveryAddressMapper {
   /**@author wangshaohui
     * @Description: TODO 插入定单的派送地址
     * @param record
     * @return int
     * @date 2015年1月5日 下午7:12:09
     */
    int insert(DeliveryAddress record);
}