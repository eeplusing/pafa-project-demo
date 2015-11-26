package com.ring.front.web.form;

import com.paic.pafa.web.form.BaseForm;
/**
 * 
 *     
 * 项目名称：ring_web    
 * 类名称：BuyInfoForm    
 * 类描述：    购买产品的 信息 form 
 * 创建人：zhangguohua    
 * 创建时间：2014-10-28 下午3:54:43        
 * 修改备注：    
 * @version     
 *
 */
public class BuyInfosForm  extends BaseForm{

	/**
     * 用户名
     */
    private String userName;
   /**
    * 购买数量
    */
    private int buyCount;
    /**
     * 购买的产品id
     */
    private String productId;
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public int getBuyCount() {
        return buyCount;
    }
    public void setBuyCount(int buyCount) {
        this.buyCount = buyCount;
    }
    public String getProductId() {
        return productId;
    }
    public void setProductId(String productId) {
        this.productId = productId;
    }
  
}
