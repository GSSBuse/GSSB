<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<!-- <title>出价记录</title> -->
 <title>米乐拍卖</title>
	<%@include file="/WEB-INF/views/include/common.jsp"%>	
</head>

<body ontouchstart="" class="ms-loader ms-loading">
	<div class="page-wrapper">
		<div data-title="出价记录" class="page active ms-controller" ms-controller="bidding-list" id="bidding-list" >
			<div class="ui-btn-wrap" style="position: relative;">
				<a href="#ibuy" style="position: absolute;z-index: 99;">
					<i class="ui-icon-return" style="color: white;"></i>
				</a>
				<button ms-if="datas.tmp.isFinished" class="ui-btn-lg ui-btn-danger" style="background-color: rgba(0,0,0,.6);color: white;" disabled>
				{{datas.endTime}} 拍卖结束
				</button>
				<button ms-if="!datas.tmp.isFinished" class="ui-btn-lg ui-btn-primary active" style="color: white;">
				拍卖中&nbsp;&nbsp;{{datas.endTime}}&nbsp;&nbsp;结拍
				</button>
			</div>
			<div class="ui-whitespace"><p class="ui-txt-info">{{datas.domainName}}出价次数 {{datas.bidCount}}</p></div>
			<section class="ui-container">
				<!-- <div ms-widget="overscroll"></div> -->
				<div class="">
					<ul class="ui-list ui-list-text ui-list-cover ui-border-tb">
						<li class="ui-border-b" ms-repeat="datas.domainList" style="padding-top:0px;padding-bottom:0px;">
							<div class="ui-list-thumb" style="margin-bottom: 6px;margin-top: 5px; margin-right: 2px;">
								<a href="#"><img width="46px" height="46px" ms-attr-src="el.photo+'46'" class="auction-domain-size"></a>
							</div>
							<div class="ui-list-info">
								<ul class="ui-row">
									<li class="ui-col ui-col-50" style="  padding-bottom: 0px;  padding-top: 0px;">
										<!-- <h4 class="ui-nowrap" style="color: #576b95">{{el.nickname}}</h4> -->
										<div class="ui-txt-warning" style="  margin-top: 9%;">￥{{el.bidAmount | transferCurrentAmount}}元</div>
									</li>
									<li class="ui-col ui-col-50">
										<div class="ui-flex ui-flex-pack-end">
											<div ms-if="$index==0">
												<div class="iconfont icon-top2 center" style="color: #eb4f38; font-size: 23px; height: 23px"></div>
												<p><span class="small-font ui-txt-info">{{el.bidTime}}</span></p>
											</div>
											<div ms-if="$index>0">
												<div class="iconfont icon-gift-money-active center" ms-if="$index == 1 && el.hasBonusSecond" style="color: #eb4f38; font-size: 23px; height: 23px"></div>
												<div class="iconfont icon-out ui-txt-info center" style="font-size: 30px; height: 28px" ms-if="!el.hasBonusSecond || $index != 1"></div>
												<p><span class="small-font ui-txt-info">{{el.bidTime}}</span></p>
											</div>
										</div>
									</li>
								</ul>
							</div>
						</li>
					</ul>
				</div>
			</section>
			<div id="biddingmore" class="dropload-down has-footer">
				<div class="dropload-load">
					<div class="dropload-showmore"><span class="loading"></span>加载更多...</div>
					<div class="dropload-nomore">没有更多数据了</div>
				</div>
			</div>
		<script type="text/javascript" data-page="bidding-list"></script>
		</div>
		
	</div><!-- /page -->
	<%@include file="/WEB-INF/views/include/commonjs.jsp" %>
</body>
</html>