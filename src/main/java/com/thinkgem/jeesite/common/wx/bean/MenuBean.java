package com.thinkgem.jeesite.common.wx.bean;

/**菜单
 * @author wufl.fnst
 *
 */
public class MenuBean {  
    private Button[] button;//一级菜单按钮：可以一至三个，每一个一级菜单按钮下可以包含最多五个二级菜单按钮
  
    public Button[] getButton() {  
        return button;  
    }  
  
    public void setButton(Button[] button) {  
        this.button = button;  
    }  
    
   
}  
