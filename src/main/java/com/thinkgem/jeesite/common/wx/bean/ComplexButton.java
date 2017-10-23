package com.thinkgem.jeesite.common.wx.bean;

/**菜单组合按钮即一级菜单按钮
 * @author wufl.fnst
 *
 */
public class ComplexButton extends Button {  
    private Button[] sub_button;//一级菜单按钮，可以有最多五个二级菜单
  
    public Button[] getSub_button() {  
        return sub_button;  
    }  
  
    public void setSub_button(Button[] sub_button) {  
        this.sub_button = sub_button;  
    }  
}  
