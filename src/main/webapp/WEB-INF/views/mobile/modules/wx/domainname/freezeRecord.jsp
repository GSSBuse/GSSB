<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<!-- <title>冻结记录</title> -->
 <title>米乐拍卖</title>
	<%@include file="/WEB-INF/views/include/common.jsp"%>	
</head>
<body ontouchstart="" class="ms-loader ms-loading">
	<div class="page-wrapper">
		<!--  冻结记录页面 -->
		<div data-title="冻结记录" class="page active ms-controller" ms-controller="freezeRecord" id="freezeRecord">
			<header class="ui-header ui-header-stable back" onclick="history.back()">
				<i class="ui-icon-return"></i>
			</header>
			<section class="ui-container">
				<ul class="ui-list ui-list-text ui-border-tb" ms-each-del="datas.freezeinfo">
					<%--冻结记录相应的解冻记录（已解冻的情况） --%>
					<li class="ui-border-t odd" ms-if="del.operate=='解冻' || (del.operate=='提现' && (del.confirmResult == '完成' ||del.confirmResult == '已取消' || del.confirmResult == '打回'))">
						<div class="ui-list-info left cash-flow-date">
							<div class="ui-nowrap">{{ del.updateDate | date("MM-dd HH:mm")}}</div>
							<div class="ui-nowrap cash-flow-operate">
								<span ms-if="del.operate=='解冻'">{{del.dyDomainname.name}}&nbsp;{{del.operate}}</span>
								<span ms-if="del.operate=='提现'">{{del.operate}}解冻（即提现{{del.confirmResult}}）</span>
							</div>
						</div>
						<div class="right cash-flow-operate">
							<div class="ui-nowrap"><span class="cash-flow-num">{{del.operateAmount}}</span>元</div>
						</div>
					</li>
					<%--冻结的记录--%>
					<li class="ui-border-t even">
						<div class="ui-list-info left cash-flow-date">
							<div class="ui-nowrap">{{ del.createDate | date("MM-dd HH:mm")}}</div>
							<div class="ui-nowrap cash-flow-operate">
								<span ms-if="del.operate=='冻结'">{{del.dyDomainname.name}}&nbsp;{{del.operate}}</span>
								<span ms-if="del.operate=='提现'">{{del.operate}}冻结</span>
								<span ms-if="del.operate=='解冻'">{{del.dyDomainname.name}}&nbsp;冻结</span>
							</div>
						</div>
						<div class="right cash-flow-operate">
							<div class="ui-nowrap"><span class="cash-flow-num">{{del.operateAmount}}</span>元</div>
						</div>
					</li>
				</ul>
			</section>
			<div id="loadmore_freeze" class="dropload-down has-footer">
				<div class="dropload-load">
					<div class="dropload-showmore"><span class="loading"></span>加载更多...</div>
					<div class="dropload-nomore">没有更多冻结记录数据了</div>
				</div>
			</div>
			<script type="text/javascript" data-page="freezeRecord"></script>
		</div>
	</div>
	<%@include file="/WEB-INF/views/include/commonjs.jsp" %>
</body>
</html>