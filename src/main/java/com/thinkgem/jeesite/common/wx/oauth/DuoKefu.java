package com.thinkgem.jeesite.common.wx.oauth;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.h2.util.StringUtils;
import org.nutz.json.Json;

import com.thinkgem.jeesite.common.wx.inf.ErrorCode;
import com.thinkgem.jeesite.common.wx.util.HttpKit;

/**
 * 多客服功能
 * 客服管理和查询
 * @author songshuqing
 * @date 2015-11-7
 */
@SuppressWarnings("unchecked")
public class DuoKefu {
	private static Logger log=Logger.getLogger(DuoKefu.class);
    /**
     * 添加客服账号
     * @throws IOException 
     * @throws NoSuchProviderException 
     * @throws NoSuchAlgorithmException 
     * @throws KeyManagementException 
     */
	public boolean addKfAccount(String accessToken,String params) throws KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException, IOException{
        String jsonStr = HttpKit.post("https://api.weixin.qq.com/customservice/kfaccount/add?access_token=" + accessToken, params);
        Map<String, Object> map = Json.fromJson(Map.class,jsonStr);
        log.debug("添加客服："+ErrorCode.get(map));
        return "0".equals(map.get("errcode").toString());
    }
	
	/**
     * 设置客服账号信息
     * @throws IOException 
     * @throws NoSuchProviderException 
     * @throws NoSuchAlgorithmException 
     * @throws KeyManagementException 
     */
	public boolean updateKfAccount(String accessToken,String params) throws KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException, IOException {
        String jsonStr = HttpKit.post("https://api.weixin.qq.com/customservice/kfaccount/update?access_token=" + accessToken, params);
        Map<String, Object> map = Json.fromJson(Map.class,jsonStr);
        log.debug("设置客服："+ErrorCode.get(map));
        return "0".equals(map.get("errcode").toString());
    }
    
	 /**
     * 查询客服信息
     * @throws IOException 
     * @throws NoSuchProviderException 
     * @throws NoSuchAlgorithmException 
     * @throws KeyManagementException 
     */
    public Map<String, Object> getKfList(String accessToken) throws KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException, IOException {
        String jsonStr = HttpKit.get("https://api.weixin.qq.com/cgi-bin/customservice/getkflist?access_token=" + accessToken);
        Map<String, Object> map = Json.fromJson(Map.class,jsonStr);
        return map;
    }
	
    /**
     * 查询在线客服接待信息
     * @throws IOException 
     * @throws NoSuchProviderException 
     * @throws NoSuchAlgorithmException 
     * @throws KeyManagementException 
     */
    public Map<String, Object> getOnlineKfList(String accessToken) throws KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException, IOException {
        String jsonStr = HttpKit.get("https://api.weixin.qq.com/cgi-bin/customservice/getonlinekflist?access_token=" + accessToken);
        Map<String, Object> map = Json.fromJson(Map.class,jsonStr);
        return map;
    }
    
    /**
     * 删除客服
     * @throws IOException 
     * @throws NoSuchProviderException 
     * @throws NoSuchAlgorithmException 
     * @throws KeyManagementException 
     */
    public boolean deleteKfAccount(String accessToken, String kfAccount) throws KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException, IOException {
        String jsonStr = HttpKit.get("https://api.weixin.qq.com/customservice/kfaccount/del?access_token=" + accessToken + "&kf_account=" + kfAccount);
        Map<String, Object> map = Json.fromJson(Map.class,jsonStr);
        log.debug("删除客服："+ErrorCode.get(map));
        return "0".equals(map.get("errcode").toString());
    }
    
    
    /**
     * 判断指定客服是否在线
     * @param accessToken
     * @param kfAccount
     * @return
     * @throws IOException 
     * @throws NoSuchProviderException 
     * @throws NoSuchAlgorithmException 
     * @throws KeyManagementException 
     */
    public boolean isKfAccountOnline(String accessToken, String kfAccount) throws KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException, IOException {
    	Map<String, Object> map = getOnlineKfList(accessToken);
    	log.debug("在线客服:" + map);
    	if (map.size() > 0) {
    		List<Map<String, String>> list = (List<Map<String, String>>) map.get("kf_online_list");
    		for (Map<String, String> kf : list) {
    			if (StringUtils.equals(kf.get("kf_account"), kfAccount)) {
    				return true;
    			}
    		}
    	}
    	
    	return false;
    }
    
    public static void main(String[] args) {
    	DuoKefu duokefu = new DuoKefu();
    	// 创建客服
        try {
        	Map<String,Object> json = new HashMap<String,Object>();
            json.put("kf_account", "jingjiren1@gh_c82588ca6ce2");
            json.put("nickname", "小宋");
            json.put("password", "25d55ad283aa400af464c76d713c07ad");
			duokefu.addKfAccount("cqk7m7-tj7S1kPRBl079o25Tgj_1rM4mKjp9n9aqZmSDW_ZejEEn1EVFS1iygzmA3OxiwP3XE-mc1TeV_RuNt91T4uMr1DwoHf1u8Mg6ss8EVKcADAULA", Json.toJson(json));
			
			json = new HashMap<String,Object>();
	        json.put("kf_account", "jingjiren2@gh_c82588ca6ce2");
	        json.put("nickname", "小沈");
	        json.put("password", "25d55ad283aa400af464c76d713c07ad");
	        duokefu.addKfAccount("cqk7m7-tj7S1kPRBl079o25Tgj_1rM4mKjp9n9aqZmSDW_ZejEEn1EVFS1iygzmA3OxiwP3XE-mc1TeV_RuNt91T4uMr1DwoHf1u8Mg6ss8EVKcADAULA", Json.toJson(json));
	        
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
