package com.thinkgem.jeesite.common.wx.bean;

/**点击菜单按钮，可以作为一级菜单和二级菜单按钮
 * @author wufl.fnst
 *
 */
public class ClickCommonButton extends Button {  
    private String type = "click";  //类型 固定为click
    private String key;//点击按钮的key值，唯一标识该按钮
  
    public String getType() {  
        return type;  
    }  
  
    public void setType(String type) {  
        this.type = type;  
    }  
  
    public String getKey() {  
        return key;  
    }  
  
    public void setKey(String key) {  
        this.key = key;  
    }  
}  