<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="${ctxStatic }/front/js/searchBrand.js"></script> 


<!----Start of Banner------>
<script type="text/javascript">
$(function() {
    $(".flexslider").flexslider({
          animation: "fade",              //String: Select your animation type, "fade" or "slide"图片变换方式：淡入淡出或者滑动
          slideDirection: "horizontal",   //String: Select the sliding direction, "horizontal" or "vertical"图片设置为滑动式时的滑动方向：左右或者上下
          slideshow: true,                //Boolean: Animate slider automatically 载入页面时，是否自动播放
          slideshowSpeed: 3000,           //Integer: Set the speed of the slideshow cycling, in milliseconds 自动播放速度毫秒
          animationDuration: 600,         //Integer: Set the speed of animations, in milliseconds动画淡入淡出效果延时
          directionNav: true,             //Boolean: Create navigation for previous/next navigation? (true/false)是否显示左右控制按钮
          controlNav: false,               //Boolean: Create navigation for paging control of each clide? Note: Leave true for manualControls usage是否显示控制菜单
          keyboardNav: true,              //Boolean: Allow slider navigating via keyboard left/right keys键盘左右方向键控制图片滑动
          mousewheel: false,              //Boolean: Allow slider navigating via mousewheel鼠标滚轮控制制图片滑动
          prevText: "Previous",           //String: Set the text for the "previous" directionNav item
          nextText: "Next",               //String: Set the text for the "next" directionNav item
          pausePlay: false,               //Boolean: Create pause/play dynamic element
          pauseText: 'Pause',             //String: Set the text for the "pause" pausePlay item
          playText: 'Play',               //String: Set the text for the "play" pausePlay item
          randomize: false,               //Boolean: Randomize slide order 是否随即幻灯片
          slideToStart: 0,                //Integer: The slide that the slider should start on. Array notation (0 = first slide)初始化第一次显示图片位置
          animationLoop: true,            //Boolean: Should the animation loop? If false, directionNav will received "disable" classes at either end 是否循环滚动
          pauseOnAction: true,            //Boolean: Pause the slideshow when interacting with control elements, highly recommended.
          pauseOnHover: false,            //Boolean: Pause the slideshow when hovering over slider, then resume when no longer hovering
          controlsContainer: "",          //Selector: Declare which container the navigation elements should be appended too. Default container is the flexSlider element. Example use would be ".flexslider-container", "#container", etc. If the given element is not found, the default action will be taken.
          manualControls: "",             //Selector: Declare custom control navigation. Example would be ".flex-control-nav li" or "#tabs-nav li img", etc. The number of elements in your controlNav should match the number of slides/tabs.自定义控制导航
          manualControlEvent:"",          //String:自定义导航控制触发事件:默认是click,可以设定hover
          start: function(){},            //Callback: function(slider) - Fires when the slider loads the first slide
          before: function(){},           //Callback: function(slider) - Fires asynchronously with each slider animation
          after: function(){},            //Callback: function(slider) - Fires after each slider animation completes
          end: function(){}               //Callback: function(slider) - Fires when the slider reaches the last slide (asynchronous)          
         });
});
</script>
<div class="flexslider">    
   <ul class="slides">
       <li>
           <img src="${ctxStatic }/images/b1.jpg" alt="" />
       </li>
       <li>
           <img src="${ctxStatic }/images/b2.jpg" alt="" />
       </li>
       <li>
           <img src="${ctxStatic }/images/b3.jpg" alt="" />
       </li>
       <li>
           <img src="${ctxStatic }/images/b4.jpg" alt="" />
       </li>
       <li>
           <img src="${ctxStatic }/images/b5.jpg" alt="" />
       </li>
       <li>
           <img src="${ctxStatic }/images/b6.jpg" alt="" />
       </li>
       <li>
           <img src="${ctxStatic }/images/b7.jpg" alt="" />
       </li>
   </ul>
   <div style="position:absolute; bottom:10px; padding:10px; width:60%; left: 20%; background: #ffffff26;
    border-radius: 5px;">
		<ul class="registbox">
			<li>
				<p>商标注册</p>
			</li>

			<li>
				<p>专利申请</p>
			</li>

			<li>
				<p>版权登记</p>
			</li>
		</ul>
		<script>
			$(function() {
				var index = 0;
				$(".registbox>li").mouseover(function() {
					index = $(".registbox>li").index(this);
					$(".registbox>li").removeClass("registlihover");
                    $(".registbox>li").eq(index).addClass("registlihover");
                    $(".registInfo>li").hide();
                    
                    $('#typeId').val(index);
                    
                    
				});
			});
		</script>
        <div class="search-box fr margintop20">
                <div class="serch-bg"> </div>
                <div class="serch-border">
				<input type="text" style="box-sizing: border-box; " value="请输入您想申请的商标、版权、专利名称" id="indexSearchInput"
					onfocus="if (value =='请输入您想申请的商标、版权、专利名称'){value =''}"
					onblur="if (value ==''){value='请输入您想申请的商标、版权、专利名称'}">
					 
					<a href="javascript:void(0);" id="indexSearch" class="button">
					<div class="serch-botton" style="box-sizing: border-box; ">查询能否注册</div>
				</a>
				<div style="clear: both"></div>
			    </div>

                <div class="scroll-box">
		             <ul>
		              <li>历史商标交易记录${GbjBusinessNumber.shangbiaoNumber}</li>
		              <li>历史版权交易记录${GbjBusinessNumber.businessNumber}</li>
		              <li>历史专利交易记录${GbjBusinessNumber.banquanNumber}</li>
		              <li>历史商标交易记录${GbjBusinessNumber.zhuanliNumber}</li>
		              <li>历史版权交易记录${GbjBusinessNumber.zhuanliNumber}</li>
		              <li>历史专利交易记录${GbjBusinessNumber.banquanNumber}</li>
		              <li>历史商标交易记录${GbjBusinessNumber.banquanNumber}</li>
		              <li>历史版权交易记录${GbjBusinessNumber.banquanNumber}</li>
		              <li>历史专利交易记录${GbjBusinessNumber.banquanNumber}</li>
		              <li>历史商标交易记录${GbjBusinessNumber.banquanNumber}</li>
		              <li>历史版权交易记录${GbjBusinessNumber.banquanNumber}</li>
		              <li>历史专利交易记录${GbjBusinessNumber.banquanNumber}</li>
		             </ul>
		        </div>
        </div>
        
    </div>
</div>
<!----End of banner------>  

<!----Start of 弹出框------>




<div id="LoginBox" ms-controller="searchdialog" >
    <div id="search-dialog"  class="easyDialog_wrapper" style="position: fixed;display: block; margin: 70px 180px 180px 180px;">
        <div class="easyDialog_content">
            <div class="easyDialog_text">
                <div class="dialog_con"> <a href="javascript:void(0)" title="关闭窗口" class="close_btn" id="closeBtn">×</a>
                    <h1 class="red_h1">商标查询</h1>
                    <div class="form_wapper">
                   <form id="domainform"  action="${ctx }/index1.html" method="post" ms-widget="validation" >                            
                            <div class="form_con">
                                <p class="form_name">商标名称：</p>
                                <input name="searchContents" id="searchContents" ms-duplex-required="datas.domainInfo.searchContents" type="text" style="border:1px solid #00a6db;">
                            </div>
                            
                            <div class="form_con" style="display:none">
                                <p class="form_name">商标类型：</p>
                                <input name="typeId" id="typeId" ms-duplex-required="datas.domainInfo.typeId" type="text" style="border:1px solid #00a6db;">
                            </div>
                            
                            <div class="form_con">
                                <p class="form_name"><span>*</span>联系人姓名：</p>
                                <input name="name" id="name" ms-duplex-required="datas.domainInfo.name" type="text" style="border:1px solid #00a6db;">
                            </div>
                            <div class="form_con">
                                <p class="form_name"><span>*</span>手机号码：</p>
                                <input name="mobile" id="mobile" ms-duplex-required="datas.domainInfo.mobile" type="text" style="border:1px solid #00a6db;">
                            </div>
                            <div class="form_con">
                                <p class="form_name">QQ：</p>
                                <input name="qq" id="qq" ms-duplex="datas.domainInfo.qq" type="text" style="border:1px solid #00a6db;">
                            </div>
                            <div class="form_con">
                                <input name="submit" type="submit" class="login red_button"  id="submitChange"  value="提交查询">
                            </div>
                            <div class="clearfix"></div>
                        </form>
                    </div>
                    <div class="ewm"> <img src="${ctxStatic }/images/ewm_red.png"> </div>
                    <div class="women"> <img src="${ctxStatic }/images/women.png"> </div>
                </div>
            </div>
        </div>
    </div>
</div>


<script type="text/javascript" src="${ctxStatic }/front/js/searchBrand.js"></script> 
<script type="text/javascript">
 $(function ($) {
     //弹出登录
     $("#indexSearch").hover(function () {
         $(this).stop().animate({
             opacity: '1'
         }, 600);
     }, function () {
         $(this).stop().animate({
             opacity: '0.6'
         }, 1000);
     }).on('click', function () {
         $("body").append("<div id='mask'></div>");
         $("#mask").addClass("mask").fadeIn("slow");
         $("#LoginBox").fadeIn("slow");
     });
     //
     
     //关闭
     $(".close_btn").hover(function () { $(this).css({ color: 'black' }) }, function () { $(this).css({ color: '#fff' }) }).on('click', function () {
         $("#LoginBox").fadeOut("fast");
         $("#mask").css({ display: 'none' });
     });
 });
 </script>    
<!-- End of 弹出框 -->
