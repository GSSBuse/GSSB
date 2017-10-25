var Zepto=(function(){var undefined,key,$,classList,emptyArray=[],slice=emptyArray.slice,filter=emptyArray.filter,document=window.document,elementDisplay={},classCache={},cssNumber={"column-count":1,columns:1,"font-weight":1,"line-height":1,opacity:1,"z-index":1,zoom:1},fragmentRE=/^\s*<(\w+|!)[^>]*>/,singleTagRE=/^<(\w+)\s*\/?>(?:<\/\1>|)$/,tagExpanderRE=/<(?!area|br|col|embed|hr|img|input|link|meta|param)(([\w:]+)[^>]*)\/>/ig,rootNodeRE=/^(?:body|html)$/i,capitalRE=/([A-Z])/g,methodAttributes=["val","css","html","text","data","width","height","offset"],adjacencyOperators=["after","prepend","before","append"],table=document.createElement("table"),tableRow=document.createElement("tr"),containers={tr:document.createElement("tbody"),tbody:table,thead:table,tfoot:table,td:tableRow,th:tableRow,"*":document.createElement("div")},readyRE=/complete|loaded|interactive/,simpleSelectorRE=/^[\w-]*$/,class2type={},toString=class2type.toString,zepto={},camelize,uniq,tempParent=document.createElement("div"),propMap={tabindex:"tabIndex",readonly:"readOnly","for":"htmlFor","class":"className",maxlength:"maxLength",cellspacing:"cellSpacing",cellpadding:"cellPadding",rowspan:"rowSpan",colspan:"colSpan",usemap:"useMap",frameborder:"frameBorder",contenteditable:"contentEditable"},isArray=Array.isArray||function(object){return object instanceof Array};zepto.matches=function(element,selector){if(!selector||!element||element.nodeType!==1){return false}var matchesSelector=element.webkitMatchesSelector||element.mozMatchesSelector||element.oMatchesSelector||element.matchesSelector;if(matchesSelector){return matchesSelector.call(element,selector)}var match,parent=element.parentNode,temp=!parent;if(temp){(parent=tempParent).appendChild(element)}match=~zepto.qsa(parent,selector).indexOf(element);temp&&tempParent.removeChild(element);return match};function type(obj){return obj==null?String(obj):class2type[toString.call(obj)]||"object"}function isFunction(value){return type(value)=="function"}function isWindow(obj){return obj!=null&&obj==obj.window}function isDocument(obj){return obj!=null&&obj.nodeType==obj.DOCUMENT_NODE}function isObject(obj){return type(obj)=="object"}function isPlainObject(obj){return isObject(obj)&&!isWindow(obj)&&Object.getPrototypeOf(obj)==Object.prototype}function likeArray(obj){return typeof obj.length=="number"}function compact(array){return filter.call(array,function(item){return item!=null})}function flatten(array){return array.length>0?$.fn.concat.apply([],array):array}camelize=function(str){return str.replace(/-+(.)?/g,function(match,chr){return chr?chr.toUpperCase():""})};function dasherize(str){return str.replace(/::/g,"/").replace(/([A-Z]+)([A-Z][a-z])/g,"$1_$2").replace(/([a-z\d])([A-Z])/g,"$1_$2").replace(/_/g,"-").toLowerCase()}uniq=function(array){return filter.call(array,function(item,idx){return array.indexOf(item)==idx})};function classRE(name){return name in classCache?classCache[name]:(classCache[name]=new RegExp("(^|\\s)"+name+"(\\s|$)"))}function maybeAddPx(name,value){return(typeof value=="number"&&!cssNumber[dasherize(name)])?value+"px":value}function defaultDisplay(nodeName){var element,display;if(!elementDisplay[nodeName]){element=document.createElement(nodeName);document.body.appendChild(element);display=getComputedStyle(element,"").getPropertyValue("display");element.parentNode.removeChild(element);display=="none"&&(display="block");elementDisplay[nodeName]=display}return elementDisplay[nodeName]}function children(element){return"children" in element?slice.call(element.children):$.map(element.childNodes,function(node){if(node.nodeType==1){return node}})}zepto.fragment=function(html,name,properties){var dom,nodes,container;if(singleTagRE.test(html)){dom=$(document.createElement(RegExp.$1))}if(!dom){if(html.replace){html=html.replace(tagExpanderRE,"<$1></$2>")}if(name===undefined){name=fragmentRE.test(html)&&RegExp.$1}if(!(name in containers)){name="*"}container=containers[name];container.innerHTML=""+html;dom=$.each(slice.call(container.childNodes),function(){container.removeChild(this)})}if(isPlainObject(properties)){nodes=$(dom);$.each(properties,function(key,value){if(methodAttributes.indexOf(key)>-1){nodes[key](value)}else{nodes.attr(key,value)}})}return dom};zepto.Z=function(dom,selector){dom=dom||[];dom.__proto__=$.fn;dom.selector=selector||"";return dom};zepto.isZ=function(object){return object instanceof zepto.Z};zepto.init=function(selector,context){var dom;if(!selector){return zepto.Z()}else{if(typeof selector=="string"){selector=selector.trim();if(selector[0]=="<"&&fragmentRE.test(selector)){dom=zepto.fragment(selector,RegExp.$1,context),selector=null}else{if(context!==undefined){return $(context).find(selector)}else{dom=zepto.qsa(document,selector)}}}else{if(isFunction(selector)){return $(document).ready(selector)}else{if(zepto.isZ(selector)){return selector}else{if(isArray(selector)){dom=compact(selector)}else{if(isObject(selector)){dom=[selector],selector=null}else{if(fragmentRE.test(selector)){dom=zepto.fragment(selector.trim(),RegExp.$1,context),selector=null}else{if(context!==undefined){return $(context).find(selector)}else{dom=zepto.qsa(document,selector)}}}}}}}}return zepto.Z(dom,selector)};$=function(selector,context){return zepto.init(selector,context)};function extend(target,source,deep){for(key in source){if(deep&&(isPlainObject(source[key])||isArray(source[key]))){if(isPlainObject(source[key])&&!isPlainObject(target[key])){target[key]={}}if(isArray(source[key])&&!isArray(target[key])){target[key]=[]}extend(target[key],source[key],deep)}else{if(source[key]!==undefined){target[key]=source[key]}}}}$.extend=function(target){var deep,args=slice.call(arguments,1);if(typeof target=="boolean"){deep=target;target=args.shift()}args.forEach(function(arg){extend(target,arg,deep)});return target};zepto.qsa=function(element,selector){var found,maybeID=selector[0]=="#",maybeClass=!maybeID&&selector[0]==".",nameOnly=maybeID||maybeClass?selector.slice(1):selector,isSimple=simpleSelectorRE.test(nameOnly);return(isDocument(element)&&isSimple&&maybeID)?((found=element.getElementById(nameOnly))?[found]:[]):(element.nodeType!==1&&element.nodeType!==9)?[]:slice.call(isSimple&&!maybeID?maybeClass?element.getElementsByClassName(nameOnly):element.getElementsByTagName(selector):element.querySelectorAll(selector))};function filtered(nodes,selector){return selector==null?$(nodes):$(nodes).filter(selector)}$.contains=function(parent,node){return parent!==node&&parent.contains(node)};function funcArg(context,arg,idx,payload){return isFunction(arg)?arg.call(context,idx,payload):arg}function setAttribute(node,name,value){value==null?node.removeAttribute(name):node.setAttribute(name,value)}function className(node,value){var klass=node.className,svg=klass&&klass.baseVal!==undefined;if(value===undefined){return svg?klass.baseVal:klass}svg?(klass.baseVal=value):(node.className=value)}function deserializeValue(value){var num;try{return value?value=="true"||(value=="false"?false:value=="null"?null:!/^0/.test(value)&&!isNaN(num=Number(value))?num:/^[\[\{]/.test(value)?$.parseJSON(value):value):value}catch(e){return value}}$.type=type;$.isFunction=isFunction;$.isWindow=isWindow;$.isArray=isArray;$.isPlainObject=isPlainObject;$.isEmptyObject=function(obj){var name;for(name in obj){return false}return true};$.inArray=function(elem,array,i){return emptyArray.indexOf.call(array,elem,i)};$.camelCase=camelize;$.trim=function(str){return str==null?"":String.prototype.trim.call(str)};$.uuid=0;$.support={};$.expr={};$.map=function(elements,callback){var value,values=[],i,key;if(likeArray(elements)){for(i=0;i<elements.length;i++){value=callback(elements[i],i);if(value!=null){values.push(value)}}}else{for(key in elements){value=callback(elements[key],key);if(value!=null){values.push(value)}}}return flatten(values)};$.each=function(elements,callback){var i,key;if(likeArray(elements)){for(i=0;i<elements.length;i++){if(callback.call(elements[i],i,elements[i])===false){return elements}}}else{for(key in elements){if(callback.call(elements[key],key,elements[key])===false){return elements}}}return elements};$.grep=function(elements,callback){return filter.call(elements,callback)};if(window.JSON){$.parseJSON=JSON.parse}$.each("Boolean Number String Function Array Date RegExp Object Error".split(" "),function(i,name){class2type["[object "+name+"]"]=name.toLowerCase()});$.fn={forEach:emptyArray.forEach,reduce:emptyArray.reduce,push:emptyArray.push,sort:emptyArray.sort,indexOf:emptyArray.indexOf,concat:emptyArray.concat,map:function(fn){return $($.map(this,function(el,i){return fn.call(el,i,el)}))},slice:function(){return $(slice.apply(this,arguments))},ready:function(callback){if(readyRE.test(document.readyState)&&document.body){callback($)}else{document.addEventListener("DOMContentLoaded",function(){callback($)},false)}return this},get:function(idx){return idx===undefined?slice.call(this):this[idx>=0?idx:idx+this.length]},toArray:function(){return this.get()},size:function(){return this.length},remove:function(){return this.each(function(){if(this.parentNode!=null){this.parentNode.removeChild(this)}})},each:function(callback){emptyArray.every.call(this,function(el,idx){return callback.call(el,idx,el)!==false});return this},filter:function(selector){if(isFunction(selector)){return this.not(this.not(selector))}return $(filter.call(this,function(element){return zepto.matches(element,selector)}))},add:function(selector,context){return $(uniq(this.concat($(selector,context))))},is:function(selector){return this.length>0&&zepto.matches(this[0],selector)},not:function(selector){var nodes=[];if(isFunction(selector)&&selector.call!==undefined){this.each(function(idx){if(!selector.call(this,idx)){nodes.push(this)}})}else{var excludes=typeof selector=="string"?this.filter(selector):(likeArray(selector)&&isFunction(selector.item))?slice.call(selector):$(selector);this.forEach(function(el){if(excludes.indexOf(el)<0){nodes.push(el)}})}return $(nodes)},has:function(selector){return this.filter(function(){return isObject(selector)?$.contains(this,selector):$(this).find(selector).size()})},eq:function(idx){return idx===-1?this.slice(idx):this.slice(idx,+idx+1)},first:function(){var el=this[0];return el&&!isObject(el)?el:$(el)},last:function(){var el=this[this.length-1];return el&&!isObject(el)?el:$(el)},find:function(selector){var result,$this=this;if(typeof selector=="object"){result=$(selector).filter(function(){var node=this;return emptyArray.some.call($this,function(parent){return $.contains(parent,node)})})}else{if(this.length==1){result=$(zepto.qsa(this[0],selector))}else{result=this.map(function(){return zepto.qsa(this,selector)})}}return result},closest:function(selector,context){var node=this[0],collection=false;if(typeof selector=="object"){collection=$(selector)}while(node&&!(collection?collection.indexOf(node)>=0:zepto.matches(node,selector))){node=node!==context&&!isDocument(node)&&node.parentNode}return $(node)},parents:function(selector){var ancestors=[],nodes=this;while(nodes.length>0){nodes=$.map(nodes,function(node){if((node=node.parentNode)&&!isDocument(node)&&ancestors.indexOf(node)<0){ancestors.push(node);return node}})}return filtered(ancestors,selector)},parent:function(selector){return filtered(uniq(this.pluck("parentNode")),selector)},children:function(selector){return filtered(this.map(function(){return children(this)}),selector)},contents:function(){return this.map(function(){return slice.call(this.childNodes)})},siblings:function(selector){return filtered(this.map(function(i,el){return filter.call(children(el.parentNode),function(child){return child!==el})}),selector)},empty:function(){return this.each(function(){this.innerHTML=""})},pluck:function(property){return $.map(this,function(el){return el[property]})},show:function(){return this.each(function(){this.style.display=="none"&&(this.style.display="");if(getComputedStyle(this,"").getPropertyValue("display")=="none"){this.style.display=defaultDisplay(this.nodeName)}})},replaceWith:function(newContent){return this.before(newContent).remove()},wrap:function(structure){var func=isFunction(structure);if(this[0]&&!func){var dom=$(structure).get(0),clone=dom.parentNode||this.length>1}return this.each(function(index){$(this).wrapAll(func?structure.call(this,index):clone?dom.cloneNode(true):dom)})},wrapAll:function(structure){if(this[0]){$(this[0]).before(structure=$(structure));var children;while((children=structure.children()).length){structure=children.first()}$(structure).append(this)}return this},wrapInner:function(structure){var func=isFunction(structure);return this.each(function(index){var self=$(this),contents=self.contents(),dom=func?structure.call(this,index):structure;contents.length?contents.wrapAll(dom):self.append(dom)})},unwrap:function(){this.parent().each(function(){$(this).replaceWith($(this).children())});return this},clone:function(){return this.map(function(){return this.cloneNode(true)})},hide:function(){return this.css("display","none")},toggle:function(setting){return this.each(function(){var el=$(this);(setting===undefined?el.css("display")=="none":setting)?el.show():el.hide()})},prev:function(selector){return $(this.pluck("previousElementSibling")).filter(selector||"*")},next:function(selector){return $(this.pluck("nextElementSibling")).filter(selector||"*")},html:function(html){return arguments.length===0?(this.length>0?this[0].innerHTML:null):this.each(function(idx){var originHtml=this.innerHTML;$(this).empty().append(funcArg(this,html,idx,originHtml))})},text:function(text){return arguments.length===0?(this.length>0?this[0].textContent:null):this.each(function(){this.textContent=(text===undefined)?"":""+text})},attr:function(name,value){var result;return(typeof name=="string"&&value===undefined)?(this.length==0||this[0].nodeType!==1?undefined:(name=="value"&&this[0].nodeName=="INPUT")?this.val():(!(result=this[0].getAttribute(name))&&name in this[0])?this[0][name]:result):this.each(function(idx){if(this.nodeType!==1){return}if(isObject(name)){for(key in name){setAttribute(this,key,name[key])}}else{setAttribute(this,name,funcArg(this,value,idx,this.getAttribute(name)))}})},removeAttr:function(name){return this.each(function(){this.nodeType===1&&setAttribute(this,name)})},prop:function(name,value){name=propMap[name]||name;return(value===undefined)?(this[0]&&this[0][name]):this.each(function(idx){this[name]=funcArg(this,value,idx,this[name])})},data:function(name,value){var data=this.attr("data-"+name.replace(capitalRE,"-$1").toLowerCase(),value);return data!==null?deserializeValue(data):undefined},val:function(value){return arguments.length===0?(this[0]&&(this[0].multiple?$(this[0]).find("option").filter(function(){return this.selected}).pluck("value"):this[0].value)):this.each(function(idx){this.value=funcArg(this,value,idx,this.value)})},offset:function(coordinates){if(coordinates){return this.each(function(index){var $this=$(this),coords=funcArg(this,coordinates,index,$this.offset()),parentOffset=$this.offsetParent().offset(),props={top:coords.top-parentOffset.top,left:coords.left-parentOffset.left};if($this.css("position")=="static"){props.position="relative"}$this.css(props)})}if(this.length==0){return null}var obj=this[0].getBoundingClientRect();return{left:obj.left+window.pageXOffset,top:obj.top+window.pageYOffset,width:Math.round(obj.width),height:Math.round(obj.height)}},css:function(property,value){if(arguments.length<2){var element=this[0],computedStyle=getComputedStyle(element,"");if(!element){return}if(typeof property=="string"){return element.style[camelize(property)]||computedStyle.getPropertyValue(property)}else{if(isArray(property)){var props={};$.each(isArray(property)?property:[property],function(_,prop){props[prop]=(element.style[camelize(prop)]||computedStyle.getPropertyValue(prop))});return props}}}var css="";if(type(property)=="string"){if(!value&&value!==0){this.each(function(){this.style.removeProperty(dasherize(property))})}else{css=dasherize(property)+":"+maybeAddPx(property,value)}}else{for(key in property){if(!property[key]&&property[key]!==0){this.each(function(){this.style.removeProperty(dasherize(key))})}else{css+=dasherize(key)+":"+maybeAddPx(key,property[key])+";"}}}return this.each(function(){this.style.cssText+=";"+css})},index:function(element){return element?this.indexOf($(element)[0]):this.parent().children().indexOf(this[0])},hasClass:function(name){if(!name){return false}return emptyArray.some.call(this,function(el){return this.test(className(el))},classRE(name))},addClass:function(name){if(!name){return this}return this.each(function(idx){classList=[];var cls=className(this),newName=funcArg(this,name,idx,cls);newName.split(/\s+/g).forEach(function(klass){if(!$(this).hasClass(klass)){classList.push(klass)}},this);classList.length&&className(this,cls+(cls?" ":"")+classList.join(" "))})},removeClass:function(name){return this.each(function(idx){if(name===undefined){return className(this,"")}classList=className(this);funcArg(this,name,idx,classList).split(/\s+/g).forEach(function(klass){classList=classList.replace(classRE(klass)," ")});className(this,classList.trim())})},toggleClass:function(name,when){if(!name){return this}return this.each(function(idx){var $this=$(this),names=funcArg(this,name,idx,className(this));names.split(/\s+/g).forEach(function(klass){(when===undefined?!$this.hasClass(klass):when)?$this.addClass(klass):$this.removeClass(klass)})})},scrollTop:function(value){if(!this.length){return}var hasScrollTop="scrollTop" in this[0];if(value===undefined){return hasScrollTop?this[0].scrollTop:this[0].pageYOffset}return this.each(hasScrollTop?function(){this.scrollTop=value}:function(){this.scrollTo(this.scrollX,value)})},scrollLeft:function(value){if(!this.length){return}var hasScrollLeft="scrollLeft" in this[0];if(value===undefined){return hasScrollLeft?this[0].scrollLeft:this[0].pageXOffset}return this.each(hasScrollLeft?function(){this.scrollLeft=value}:function(){this.scrollTo(value,this.scrollY)})},position:function(){if(!this.length){return}var elem=this[0],offsetParent=this.offsetParent(),offset=this.offset(),parentOffset=rootNodeRE.test(offsetParent[0].nodeName)?{top:0,left:0}:offsetParent.offset();offset.top-=parseFloat($(elem).css("margin-top"))||0;offset.left-=parseFloat($(elem).css("margin-left"))||0;parentOffset.top+=parseFloat($(offsetParent[0]).css("border-top-width"))||0;parentOffset.left+=parseFloat($(offsetParent[0]).css("border-left-width"))||0;return{top:offset.top-parentOffset.top,left:offset.left-parentOffset.left}},offsetParent:function(){return this.map(function(){var parent=this.offsetParent||document.body;while(parent&&!rootNodeRE.test(parent.nodeName)&&$(parent).css("position")=="static"){parent=parent.offsetParent}return parent})}};$.fn.detach=$.fn.remove;["width","height"].forEach(function(dimension){var dimensionProperty=dimension.replace(/./,function(m){return m[0].toUpperCase()});$.fn[dimension]=function(value){var offset,el=this[0];if(value===undefined){return isWindow(el)?el["inner"+dimensionProperty]:isDocument(el)?el.documentElement["scroll"+dimensionProperty]:(offset=this.offset())&&offset[dimension]}else{return this.each(function(idx){el=$(this);el.css(dimension,funcArg(this,value,idx,el[dimension]()))})}}});function traverseNode(node,fun){fun(node);for(var key in node.childNodes){traverseNode(node.childNodes[key],fun)}}adjacencyOperators.forEach(function(operator,operatorIndex){var inside=operatorIndex%2;$.fn[operator]=function(){var argType,nodes=$.map(arguments,function(arg){argType=type(arg);return argType=="object"||argType=="array"||arg==null?arg:zepto.fragment(arg)}),parent,copyByClone=this.length>1;if(nodes.length<1){return this}return this.each(function(_,target){parent=inside?target:target.parentNode;target=operatorIndex==0?target.nextSibling:operatorIndex==1?target.firstChild:operatorIndex==2?target:null;nodes.forEach(function(node){if(copyByClone){node=node.cloneNode(true)}else{if(!parent){return $(node).remove()}}traverseNode(parent.insertBefore(node,target),function(el){if(el.nodeName!=null&&el.nodeName.toUpperCase()==="SCRIPT"&&(!el.type||el.type==="text/javascript")&&!el.src){window["eval"].call(window,el.innerHTML)}})})})};$.fn[inside?operator+"To":"insert"+(operatorIndex?"Before":"After")]=function(html){$(html)[operator](this);return this}});zepto.Z.prototype=$.fn;zepto.uniq=uniq;zepto.deserializeValue=deserializeValue;$.zepto=zepto;return $})();window.Zepto=Zepto;window.$===undefined&&(window.$=Zepto);(function($){var _zid=1,undefined,slice=Array.prototype.slice,isFunction=$.isFunction,isString=function(obj){return typeof obj=="string"},handlers={},specialEvents={},focusinSupported="onfocusin" in window,focus={focus:"focusin",blur:"focusout"},hover={mouseenter:"mouseover",mouseleave:"mouseout"};specialEvents.click=specialEvents.mousedown=specialEvents.mouseup=specialEvents.mousemove="MouseEvents";function zid(element){return element._zid||(element._zid=_zid++)}function findHandlers(element,event,fn,selector){event=parse(event);if(event.ns){var matcher=matcherFor(event.ns)}return(handlers[zid(element)]||[]).filter(function(handler){return handler&&(!event.e||handler.e==event.e)&&(!event.ns||matcher.test(handler.ns))&&(!fn||zid(handler.fn)===zid(fn))&&(!selector||handler.sel==selector)})}function parse(event){var parts=(""+event).split(".");return{e:parts[0],ns:parts.slice(1).sort().join(" ")}}function matcherFor(ns){return new RegExp("(?:^| )"+ns.replace(" "," .* ?")+"(?: |$)")}function eventCapture(handler,captureSetting){return handler.del&&(!focusinSupported&&(handler.e in focus))||!!captureSetting}function realEvent(type){return hover[type]||(focusinSupported&&focus[type])||type}function add(element,events,fn,data,selector,delegator,capture){var id=zid(element),set=(handlers[id]||(handlers[id]=[]));events.split(/\s/).forEach(function(event){if(event=="ready"){return $(document).ready(fn)}var handler=parse(event);handler.fn=fn;handler.sel=selector;if(handler.e in hover){fn=function(e){var related=e.relatedTarget;if(!related||(related!==this&&!$.contains(this,related))){return handler.fn.apply(this,arguments)}}}handler.del=delegator;var callback=delegator||fn;handler.proxy=function(e){e=compatible(e);if(e.isImmediatePropagationStopped()){return}e.data=data;var result=callback.apply(element,e._args==undefined?[e]:[e].concat(e._args));if(result===false){e.preventDefault(),e.stopPropagation()}return result};handler.i=set.length;set.push(handler);if("addEventListener" in element){element.addEventListener(realEvent(handler.e),handler.proxy,eventCapture(handler,capture))}})}function remove(element,events,fn,selector,capture){var id=zid(element);(events||"").split(/\s/).forEach(function(event){findHandlers(element,event,fn,selector).forEach(function(handler){delete handlers[id][handler.i];if("removeEventListener" in element){element.removeEventListener(realEvent(handler.e),handler.proxy,eventCapture(handler,capture))}})})}$.event={add:add,remove:remove};$.proxy=function(fn,context){if(isFunction(fn)){var proxyFn=function(){return fn.apply(context,arguments)};proxyFn._zid=zid(fn);return proxyFn}else{if(isString(context)){return $.proxy(fn[context],fn)}else{throw new TypeError("expected function")}}};$.fn.bind=function(event,data,callback){return this.on(event,data,callback)};$.fn.unbind=function(event,callback){return this.off(event,callback)};$.fn.one=function(event,selector,data,callback){return this.on(event,selector,data,callback,1)};var returnTrue=function(){return true},returnFalse=function(){return false},ignoreProperties=/^([A-Z]|returnValue$|layer[XY]$)/,eventMethods={preventDefault:"isDefaultPrevented",stopImmediatePropagation:"isImmediatePropagationStopped",stopPropagation:"isPropagationStopped"};function compatible(event,source){if(source||!event.isDefaultPrevented){source||(source=event);$.each(eventMethods,function(name,predicate){var sourceMethod=source[name];event[name]=function(){this[predicate]=returnTrue;return sourceMethod&&sourceMethod.apply(source,arguments)};event[predicate]=returnFalse});if(source.defaultPrevented!==undefined?source.defaultPrevented:"returnValue" in source?source.returnValue===false:source.getPreventDefault&&source.getPreventDefault()){event.isDefaultPrevented=returnTrue}}return event}function createProxy(event){var key,proxy={originalEvent:event};for(key in event){if(!ignoreProperties.test(key)&&event[key]!==undefined){proxy[key]=event[key]}}return compatible(proxy,event)}$.fn.delegate=function(selector,event,callback){return this.on(event,selector,callback)};$.fn.undelegate=function(selector,event,callback){return this.off(event,selector,callback)};$.fn.live=function(event,callback){$(document.body).delegate(this.selector,event,callback);return this};$.fn.die=function(event,callback){$(document.body).undelegate(this.selector,event,callback);return this};$.fn.on=function(event,selector,data,callback,one){var autoRemove,delegator,$this=this;if(event&&!isString(event)){$.each(event,function(type,fn){$this.on(type,selector,data,fn,one)});return $this}if(!isString(selector)&&!isFunction(callback)&&callback!==false){callback=data,data=selector,selector=undefined}if(isFunction(data)||data===false){callback=data,data=undefined}if(callback===false){callback=returnFalse}return $this.each(function(_,element){if(one){autoRemove=function(e){remove(element,e.type,callback);return callback.apply(this,arguments)}}if(selector){delegator=function(e){var evt,match=$(e.target).closest(selector,element).get(0);if(match&&match!==element){evt=$.extend(createProxy(e),{currentTarget:match,liveFired:element});return(autoRemove||callback).apply(match,[evt].concat(slice.call(arguments,1)))}}}add(element,event,callback,data,selector,delegator||autoRemove)})};$.fn.off=function(event,selector,callback){var $this=this;if(event&&!isString(event)){$.each(event,function(type,fn){$this.off(type,selector,fn)});return $this}if(!isString(selector)&&!isFunction(callback)&&callback!==false){callback=selector,selector=undefined}if(callback===false){callback=returnFalse}return $this.each(function(){remove(this,event,callback,selector)})};$.fn.trigger=function(event,args){event=(isString(event)||$.isPlainObject(event))?$.Event(event):compatible(event);event._args=args;return this.each(function(){if("dispatchEvent" in this){this.dispatchEvent(event)}else{$(this).triggerHandler(event,args)}})};$.fn.triggerHandler=function(event,args){var e,result;this.each(function(i,element){e=createProxy(isString(event)?$.Event(event):event);e._args=args;e.target=element;$.each(findHandlers(element,event.type||event),function(i,handler){result=handler.proxy(e);if(e.isImmediatePropagationStopped()){return false}})});return result};("focusin focusout load resize scroll unload click dblclick mousedown mouseup mousemove mouseover mouseout mouseenter mouseleave change select keydown keypress keyup error").split(" ").forEach(function(event){$.fn[event]=function(callback){return callback?this.bind(event,callback):this.trigger(event)}});["focus","blur"].forEach(function(name){$.fn[name]=function(callback){if(callback){this.bind(name,callback)}else{this.each(function(){try{this[name]()}catch(e){}})}return this}});$.Event=function(type,props){if(!isString(type)){props=type,type=props.type}var event=document.createEvent(specialEvents[type]||"Events"),bubbles=true;if(props){for(var name in props){(name=="bubbles")?(bubbles=!!props[name]):(event[name]=props[name])}}event.initEvent(type,bubbles,true);return compatible(event)}})(Zepto);(function($){var jsonpID=0,document=window.document,key,name,rscript=/<script\b[^<]*(?:(?!<\/script>)<[^<]*)*<\/script>/gi,scriptTypeRE=/^(?:text|application)\/javascript/i,xmlTypeRE=/^(?:text|application)\/xml/i,jsonType="application/json",htmlType="text/html",blankRE=/^\s*$/;function triggerAndReturn(context,eventName,data){var event=$.Event(eventName);$(context).trigger(event,data);return !event.isDefaultPrevented()}function triggerGlobal(settings,context,eventName,data){if(settings.global){return triggerAndReturn(context||document,eventName,data)}}$.active=0;function ajaxStart(settings){if(settings.global&&$.active++===0){triggerGlobal(settings,null,"ajaxStart")}}function ajaxStop(settings){if(settings.global&&!(--$.active)){triggerGlobal(settings,null,"ajaxStop")}}function ajaxBeforeSend(xhr,settings){var context=settings.context;if(settings.beforeSend.call(context,xhr,settings)===false||triggerGlobal(settings,context,"ajaxBeforeSend",[xhr,settings])===false){return false}triggerGlobal(settings,context,"ajaxSend",[xhr,settings])}function ajaxSuccess(data,xhr,settings,deferred){var context=settings.context,status="success";settings.success.call(context,data,status,xhr);if(deferred){deferred.resolveWith(context,[data,status,xhr])}triggerGlobal(settings,context,"ajaxSuccess",[xhr,settings,data]);ajaxComplete(status,xhr,settings)}function ajaxError(error,type,xhr,settings,deferred){var context=settings.context;settings.error.call(context,xhr,type,error);if(deferred){deferred.rejectWith(context,[xhr,type,error])}triggerGlobal(settings,context,"ajaxError",[xhr,settings,error||type]);ajaxComplete(type,xhr,settings)}function ajaxComplete(status,xhr,settings){var context=settings.context;settings.complete.call(context,xhr,status);triggerGlobal(settings,context,"ajaxComplete",[xhr,settings]);ajaxStop(settings)}function empty(){}$.ajaxJSONP=function(options,deferred){if(!("type" in options)){return $.ajax(options)}var _callbackName=options.jsonpCallback,callbackName=($.isFunction(_callbackName)?_callbackName():_callbackName)||("jsonp"+(++jsonpID)),script=document.createElement("script"),originalCallback=window[callbackName],responseData,abort=function(errorType){$(script).triggerHandler("error",errorType||"abort")},xhr={abort:abort},abortTimeout;if(deferred){deferred.promise(xhr)}$(script).on("load error",function(e,errorType){clearTimeout(abortTimeout);$(script).off().remove();if(e.type=="error"||!responseData){ajaxError(null,errorType||"error",xhr,options,deferred)}else{ajaxSuccess(responseData[0],xhr,options,deferred)}window[callbackName]=originalCallback;if(responseData&&$.isFunction(originalCallback)){originalCallback(responseData[0])}originalCallback=responseData=undefined});if(ajaxBeforeSend(xhr,options)===false){abort("abort");return xhr}window[callbackName]=function(){responseData=arguments};script.src=options.url.replace(/\?(.+)=\?/,"?$1="+callbackName);document.head.appendChild(script);if(options.timeout>0){abortTimeout=setTimeout(function(){abort("timeout")},options.timeout)}return xhr};$.ajaxSettings={type:"GET",beforeSend:empty,success:empty,error:empty,complete:empty,context:null,global:true,xhr:function(){return new window.XMLHttpRequest()},accepts:{script:"text/javascript, application/javascript, application/x-javascript",json:jsonType,xml:"application/xml, text/xml",html:htmlType,text:"text/plain"},crossDomain:false,timeout:0,processData:true,cache:true};function mimeToDataType(mime){if(mime){mime=mime.split(";",2)[0]}return mime&&(mime==htmlType?"html":mime==jsonType?"json":scriptTypeRE.test(mime)?"script":xmlTypeRE.test(mime)&&"xml")||"text"}function appendQuery(url,query){if(query==""){return url}return(url+"&"+query).replace(/[&?]{1,2}/,"?")}function serializeData(options){if(options.processData&&options.data&&$.type(options.data)!="string"){options.data=$.param(options.data,options.traditional)}if(options.data&&(!options.type||options.type.toUpperCase()=="GET")){options.url=appendQuery(options.url,options.data),options.data=undefined}}$.ajax=function(options){var settings=$.extend({},options||{}),deferred=$.Deferred&&$.Deferred();for(key in $.ajaxSettings){if(settings[key]===undefined){settings[key]=$.ajaxSettings[key]}}ajaxStart(settings);if(!settings.crossDomain){settings.crossDomain=/^([\w-]+:)?\/\/([^\/]+)/.test(settings.url)&&RegExp.$2!=window.location.host}if(!settings.url){settings.url=window.location.toString()}serializeData(settings);if(settings.cache===false){settings.url=appendQuery(settings.url,"_="+Date.now())}var dataType=settings.dataType,hasPlaceholder=/\?.+=\?/.test(settings.url);if(dataType=="jsonp"||hasPlaceholder){if(!hasPlaceholder){settings.url=appendQuery(settings.url,settings.jsonp?(settings.jsonp+"=?"):settings.jsonp===false?"":"callback=?")}return $.ajaxJSONP(settings,deferred)}var mime=settings.accepts[dataType],headers={},setHeader=function(name,value){headers[name.toLowerCase()]=[name,value]},protocol=/^([\w-]+:)\/\//.test(settings.url)?RegExp.$1:window.location.protocol,xhr=settings.xhr(),nativeSetHeader=xhr.setRequestHeader,abortTimeout;if(deferred){deferred.promise(xhr)}if(!settings.crossDomain){setHeader("X-Requested-With","XMLHttpRequest")}setHeader("Accept",mime||"*/*");if(mime=settings.mimeType||mime){if(mime.indexOf(",")>-1){mime=mime.split(",",2)[0]}xhr.overrideMimeType&&xhr.overrideMimeType(mime)}if(settings.contentType||(settings.contentType!==false&&settings.data&&settings.type.toUpperCase()!="GET")){setHeader("Content-Type",settings.contentType||"application/x-www-form-urlencoded")}if(settings.headers){for(name in settings.headers){setHeader(name,settings.headers[name])}}xhr.setRequestHeader=setHeader;xhr.onreadystatechange=function(){if(xhr.readyState==4){xhr.onreadystatechange=empty;clearTimeout(abortTimeout);var result,error=false;if((xhr.status>=200&&xhr.status<300)||xhr.status==304||(xhr.status==0&&protocol=="file:")){dataType=dataType||mimeToDataType(settings.mimeType||xhr.getResponseHeader("content-type"));result=xhr.responseText;try{if(dataType=="script"){(1,eval)(result)}else{if(dataType=="xml"){result=xhr.responseXML}else{if(dataType=="json"){result=blankRE.test(result)?null:$.parseJSON(result)}}}}catch(e){error=e}if(error){ajaxError(error,"parsererror",xhr,settings,deferred)}else{ajaxSuccess(result,xhr,settings,deferred)}}else{ajaxError(xhr.statusText||null,xhr.status?"error":"abort",xhr,settings,deferred)}}};if(ajaxBeforeSend(xhr,settings)===false){xhr.abort();ajaxError(null,"abort",xhr,settings,deferred);return xhr}if(settings.xhrFields){for(name in settings.xhrFields){xhr[name]=settings.xhrFields[name]}}var async="async" in settings?settings.async:true;xhr.open(settings.type,settings.url,async,settings.username,settings.password);for(name in headers){nativeSetHeader.apply(xhr,headers[name])}if(settings.timeout>0){abortTimeout=setTimeout(function(){xhr.onreadystatechange=empty;xhr.abort();ajaxError(null,"timeout",xhr,settings,deferred)},settings.timeout)}xhr.send(settings.data?settings.data:null);return xhr};function parseArguments(url,data,success,dataType){if($.isFunction(data)){dataType=success,success=data,data=undefined}if(!$.isFunction(success)){dataType=success,success=undefined}return{url:url,data:data,success:success,dataType:dataType}}$.get=function(){return $.ajax(parseArguments.apply(null,arguments))};$.post=function(){var options=parseArguments.apply(null,arguments);options.type="POST";return $.ajax(options)};$.getJSON=function(){var options=parseArguments.apply(null,arguments);options.dataType="json";return $.ajax(options)};$.fn.load=function(url,data,success){if(!this.length){return this}var self=this,parts=url.split(/\s/),selector,options=parseArguments(url,data,success),callback=options.success;if(parts.length>1){options.url=parts[0],selector=parts[1]}options.success=function(response){self.html(selector?$("<div>").html(response.replace(rscript,"")).find(selector):response);callback&&callback.apply(self,arguments)};$.ajax(options);return this};var escape=encodeURIComponent;function serialize(params,obj,traditional,scope){var type,array=$.isArray(obj),hash=$.isPlainObject(obj);$.each(obj,function(key,value){type=$.type(value);if(scope){key=traditional?scope:scope+"["+(hash||type=="object"||type=="array"?key:"")+"]"}if(!scope&&array){params.add(value.name,value.value)}else{if(type=="array"||(!traditional&&type=="object")){serialize(params,value,traditional,key)}else{params.add(key,value)}}})}$.param=function(obj,traditional){var params=[];params.add=function(k,v){this.push(escape(k)+"="+escape(v))};serialize(params,obj,traditional);return params.join("&").replace(/%20/g,"+")}})(Zepto);(function($){$.fn.serializeArray=function(){var result=[],el;$([].slice.call(this.get(0).elements)).each(function(){el=$(this);var type=el.attr("type");if(this.nodeName.toLowerCase()!="fieldset"&&!this.disabled&&type!="submit"&&type!="reset"&&type!="button"&&((type!="radio"&&type!="checkbox")||this.checked)){result.push({name:el.attr("name"),value:el.val()})}});return result};$.fn.serialize=function(){var result=[];this.serializeArray().forEach(function(elm){result.push(encodeURIComponent(elm.name)+"="+encodeURIComponent(elm.value))});return result.join("&")};$.fn.submit=function(callback){if(callback){this.bind("submit",callback)}else{if(this.length){var event=$.Event("submit");this.eq(0).trigger(event);if(!event.isDefaultPrevented()){this.get(0).submit()}}}return this}})(Zepto);(function($){if(!("__proto__" in {})){$.extend($.zepto,{Z:function(dom,selector){dom=dom||[];$.extend(dom,$.fn);dom.selector=selector||"";dom.__Z=true;return dom},isZ:function(object){return $.type(object)==="array"&&"__Z" in object}})}try{getComputedStyle(undefined)}catch(e){var nativeGetComputedStyle=getComputedStyle;window.getComputedStyle=function(element){try{return nativeGetComputedStyle(element)}catch(e){return null}}}})(Zepto);(function($){function detect(ua){var os=this.os={},browser=this.browser={},webkit=ua.match(/Web[kK]it[\/]{0,1}([\d.]+)/),android=ua.match(/(Android);?[\s\/]+([\d.]+)?/),osx=!!ua.match(/\(Macintosh\; Intel /),ipad=ua.match(/(iPad).*OS\s([\d_]+)/),ipod=ua.match(/(iPod)(.*OS\s([\d_]+))?/),iphone=!ipad&&ua.match(/(iPhone\sOS)\s([\d_]+)/),webos=ua.match(/(webOS|hpwOS)[\s\/]([\d.]+)/),wp=ua.match(/Windows Phone ([\d.]+)/),touchpad=webos&&ua.match(/TouchPad/),kindle=ua.match(/Kindle\/([\d.]+)/),silk=ua.match(/Silk\/([\d._]+)/),blackberry=ua.match(/(BlackBerry).*Version\/([\d.]+)/),bb10=ua.match(/(BB10).*Version\/([\d.]+)/),rimtabletos=ua.match(/(RIM\sTablet\sOS)\s([\d.]+)/),playbook=ua.match(/PlayBook/),chrome=ua.match(/Chrome\/([\d.]+)/)||ua.match(/CriOS\/([\d.]+)/),firefox=ua.match(/Firefox\/([\d.]+)/),ie=ua.match(/MSIE\s([\d.]+)/)||ua.match(/Trident\/[\d](?=[^\?]+).*rv:([0-9.].)/),webview=!chrome&&ua.match(/(iPhone|iPod|iPad).*AppleWebKit(?!.*Safari)/),safari=webview||ua.match(/Version\/([\d.]+)([^S](Safari)|[^M]*(Mobile)[^S]*(Safari))/);if(browser.webkit=!!webkit){browser.version=webkit[1]}if(android){os.android=true,os.version=android[2]}if(iphone&&!ipod){os.ios=os.iphone=true,os.version=iphone[2].replace(/_/g,".")}if(ipad){os.ios=os.ipad=true,os.version=ipad[2].replace(/_/g,".")}if(ipod){os.ios=os.ipod=true,os.version=ipod[3]?ipod[3].replace(/_/g,"."):null}if(wp){os.wp=true,os.version=wp[1]}if(webos){os.webos=true,os.version=webos[2]}if(touchpad){os.touchpad=true}if(blackberry){os.blackberry=true,os.version=blackberry[2]}if(bb10){os.bb10=true,os.version=bb10[2]}if(rimtabletos){os.rimtabletos=true,os.version=rimtabletos[2]}if(playbook){browser.playbook=true}if(kindle){os.kindle=true,os.version=kindle[1]}if(silk){browser.silk=true,browser.version=silk[1]}if(!silk&&os.android&&ua.match(/Kindle Fire/)){browser.silk=true}if(chrome){browser.chrome=true,browser.version=chrome[1]}if(firefox){browser.firefox=true,browser.version=firefox[1]}if(ie){browser.ie=true,browser.version=ie[1]}if(safari&&(osx||os.ios)){browser.safari=true;if(osx){browser.version=safari[1]}}if(webview){browser.webview=true}os.tablet=!!(ipad||playbook||(android&&!ua.match(/Mobile/))||(firefox&&ua.match(/Tablet/))||(ie&&!ua.match(/Phone/)&&ua.match(/Touch/)));os.phone=!!(!os.tablet&&!os.ipod&&(android||iphone||webos||blackberry||bb10||(chrome&&ua.match(/Android/))||(chrome&&ua.match(/CriOS\/([\d.]+)/))||(firefox&&ua.match(/Mobile/))||(ie&&ua.match(/Touch/))))}detect.call($,navigator.userAgent);$.__detect=detect})(Zepto);(function($,undefined){var prefix="",eventPrefix,endEventName,endAnimationName,vendors={Webkit:"webkit",Moz:"",O:"o"},document=window.document,testEl=document.createElement("div"),supportedTransforms=/^((translate|rotate|scale)(X|Y|Z|3d)?|matrix(3d)?|perspective|skew(X|Y)?)$/i,transform,transitionProperty,transitionDuration,transitionTiming,transitionDelay,animationName,animationDuration,animationTiming,animationDelay,cssReset={};function dasherize(str){return str.replace(/([a-z])([A-Z])/,"$1-$2").toLowerCase()}function normalizeEvent(name){return eventPrefix?eventPrefix+name:name.toLowerCase()}$.each(vendors,function(vendor,event){if(testEl.style[vendor+"TransitionProperty"]!==undefined){prefix="-"+vendor.toLowerCase()+"-";eventPrefix=event;return false}});transform=prefix+"transform";cssReset[transitionProperty=prefix+"transition-property"]=cssReset[transitionDuration=prefix+"transition-duration"]=cssReset[transitionDelay=prefix+"transition-delay"]=cssReset[transitionTiming=prefix+"transition-timing-function"]=cssReset[animationName=prefix+"animation-name"]=cssReset[animationDuration=prefix+"animation-duration"]=cssReset[animationDelay=prefix+"animation-delay"]=cssReset[animationTiming=prefix+"animation-timing-function"]="";$.fx={off:(eventPrefix===undefined&&testEl.style.transitionProperty===undefined),speeds:{_default:400,fast:200,slow:600},cssPrefix:prefix,transitionEnd:normalizeEvent("TransitionEnd"),animationEnd:normalizeEvent("AnimationEnd")};$.fn.animate=function(properties,duration,ease,callback,delay){if($.isFunction(duration)){callback=duration,ease=undefined,duration=undefined}if($.isFunction(ease)){callback=ease,ease=undefined}if($.isPlainObject(duration)){ease=duration.easing,callback=duration.complete,delay=duration.delay,duration=duration.duration}if(duration){duration=(typeof duration=="number"?duration:($.fx.speeds[duration]||$.fx.speeds._default))/1000}if(delay){delay=parseFloat(delay)/1000}return this.anim(properties,duration,ease,callback,delay)};$.fn.anim=function(properties,duration,ease,callback,delay){var key,cssValues={},cssProperties,transforms="",that=this,wrappedCallback,endEvent=$.fx.transitionEnd,fired=false;if(duration===undefined){duration=$.fx.speeds._default/1000}if(delay===undefined){delay=0}if($.fx.off){duration=0}if(typeof properties=="string"){cssValues[animationName]=properties;cssValues[animationDuration]=duration+"s";cssValues[animationDelay]=delay+"s";cssValues[animationTiming]=(ease||"linear");endEvent=$.fx.animationEnd}else{cssProperties=[];for(key in properties){if(supportedTransforms.test(key)){transforms+=key+"("+properties[key]+") "}else{cssValues[key]=properties[key],cssProperties.push(dasherize(key))}}if(transforms){cssValues[transform]=transforms,cssProperties.push(transform)}if(duration>0&&typeof properties==="object"){cssValues[transitionProperty]=cssProperties.join(", ");cssValues[transitionDuration]=duration+"s";cssValues[transitionDelay]=delay+"s";cssValues[transitionTiming]=(ease||"linear")}}wrappedCallback=function(event){if(typeof event!=="undefined"){if(event.target!==event.currentTarget){return}$(event.target).unbind(endEvent,wrappedCallback)}else{$(this).unbind(endEvent,wrappedCallback)}fired=true;$(this).css(cssReset);callback&&callback.call(this)};if(duration>0){this.bind(endEvent,wrappedCallback);setTimeout(function(){if(fired){return}wrappedCallback.call(that)},(duration*1000)+25)}this.size()&&this.get(0).clientLeft;this.css(cssValues);if(duration<=0){setTimeout(function(){that.each(function(){wrappedCallback.call(this)})},0)}return this};testEl=null})(Zepto);(function($){var cache=[],timeout;$.fn.remove=function(){return this.each(function(){if(this.parentNode){if(this.tagName==="IMG"){cache.push(this);this.src="data:image/gif;base64,R0lGODlhAQABAAD/ACwAAAAAAQABAAACADs=";if(timeout){clearTimeout(timeout)}timeout=setTimeout(function(){cache=[]},60000)}this.parentNode.removeChild(this)}})}})(Zepto);(function($){var data={},dataAttr=$.fn.data,camelize=$.camelCase,exp=$.expando="Zepto"+(+new Date()),emptyArray=[];function getData(node,name){var id=node[exp],store=id&&data[id];if(name===undefined){return store||setData(node)}else{if(store){if(name in store){return store[name]}var camelName=camelize(name);if(camelName in store){return store[camelName]}}return dataAttr.call($(node),name)}}function setData(node,name,value){var id=node[exp]||(node[exp]=++$.uuid),store=data[id]||(data[id]=attributeData(node));if(name!==undefined){store[camelize(name)]=value}return store}function attributeData(node){var store={};$.each(node.attributes||emptyArray,function(i,attr){if(attr.name.indexOf("data-")==0){store[camelize(attr.name.replace("data-",""))]=$.zepto.deserializeValue(attr.value)}});return store}$.fn.data=function(name,value){return value===undefined?$.isPlainObject(name)?this.each(function(i,node){$.each(name,function(key,value){setData(node,key,value)})}):this.length==0?undefined:getData(this[0],name):this.each(function(){setData(this,name,value)})};$.fn.removeData=function(names){if(typeof names=="string"){names=names.split(/\s+/)}return this.each(function(){var id=this[exp],store=id&&data[id];if(store){$.each(names||store,function(key){delete store[names?camelize(this):key]})}})};["remove","empty"].forEach(function(methodName){var origFn=$.fn[methodName];$.fn[methodName]=function(){var elements=this.find("*");if(methodName==="remove"){elements=elements.add(this)}elements.removeData();return origFn.call(this)}})})(Zepto);(function($){var slice=Array.prototype.slice;function Deferred(func){var tuples=[["resolve","done",$.Callbacks({once:1,memory:1}),"resolved"],["reject","fail",$.Callbacks({once:1,memory:1}),"rejected"],["notify","progress",$.Callbacks({memory:1})]],state="pending",promise={state:function(){return state},always:function(){deferred.done(arguments).fail(arguments);return this},then:function(){var fns=arguments;return Deferred(function(defer){$.each(tuples,function(i,tuple){var fn=$.isFunction(fns[i])&&fns[i];deferred[tuple[1]](function(){var returned=fn&&fn.apply(this,arguments);if(returned&&$.isFunction(returned.promise)){returned.promise().done(defer.resolve).fail(defer.reject).progress(defer.notify)}else{var context=this===promise?defer.promise():this,values=fn?[returned]:arguments;defer[tuple[0]+"With"](context,values)}})});fns=null}).promise()},promise:function(obj){return obj!=null?$.extend(obj,promise):promise}},deferred={};$.each(tuples,function(i,tuple){var list=tuple[2],stateString=tuple[3];promise[tuple[1]]=list.add;if(stateString){list.add(function(){state=stateString},tuples[i^1][2].disable,tuples[2][2].lock)}deferred[tuple[0]]=function(){deferred[tuple[0]+"With"](this===deferred?promise:this,arguments);return this};deferred[tuple[0]+"With"]=list.fireWith});promise.promise(deferred);if(func){func.call(deferred,deferred)}return deferred}$.when=function(sub){var resolveValues=slice.call(arguments),len=resolveValues.length,i=0,remain=len!==1||(sub&&$.isFunction(sub.promise))?len:0,deferred=remain===1?sub:Deferred(),progressValues,progressContexts,resolveContexts,updateFn=function(i,ctx,val){return function(value){ctx[i]=this;val[i]=arguments.length>1?slice.call(arguments):value;if(val===progressValues){deferred.notifyWith(ctx,val)}else{if(!(--remain)){deferred.resolveWith(ctx,val)}}}};if(len>1){progressValues=new Array(len);progressContexts=new Array(len);resolveContexts=new Array(len);for(;i<len;++i){if(resolveValues[i]&&$.isFunction(resolveValues[i].promise)){resolveValues[i].promise().done(updateFn(i,resolveContexts,resolveValues)).fail(deferred.reject).progress(updateFn(i,progressContexts,progressValues))}else{--remain}}}if(!remain){deferred.resolveWith(resolveContexts,resolveValues)}return deferred.promise()};$.Deferred=Deferred})(Zepto);(function($){$.Callbacks=function(options){options=$.extend({},options);var memory,fired,firing,firingStart,firingLength,firingIndex,list=[],stack=!options.once&&[],fire=function(data){memory=options.memory&&data;fired=true;firingIndex=firingStart||0;firingStart=0;firingLength=list.length;firing=true;for(;list&&firingIndex<firingLength;++firingIndex){if(list[firingIndex].apply(data[0],data[1])===false&&options.stopOnFalse){memory=false;break}}firing=false;if(list){if(stack){stack.length&&fire(stack.shift())}else{if(memory){list.length=0}else{Callbacks.disable()}}}},Callbacks={add:function(){if(list){var start=list.length,add=function(args){$.each(args,function(_,arg){if(typeof arg==="function"){if(!options.unique||!Callbacks.has(arg)){list.push(arg)}}else{if(arg&&arg.length&&typeof arg!=="string"){add(arg)}}})};add(arguments);if(firing){firingLength=list.length}else{if(memory){firingStart=start;fire(memory)}}}return this},remove:function(){if(list){$.each(arguments,function(_,arg){var index;while((index=$.inArray(arg,list,index))>-1){list.splice(index,1);if(firing){if(index<=firingLength){--firingLength}if(index<=firingIndex){--firingIndex}}}})}return this},has:function(fn){return !!(list&&(fn?$.inArray(fn,list)>-1:list.length))},empty:function(){firingLength=list.length=0;return this},disable:function(){list=stack=memory=undefined;return this},disabled:function(){return !list},lock:function(){stack=undefined;if(!memory){Callbacks.disable()}return this},locked:function(){return !stack},fireWith:function(context,args){if(list&&(!fired||stack)){args=args||[];args=[context,args.slice?args.slice():args];if(firing){stack.push(args)}else{fire(args)}}return this},fire:function(){return Callbacks.fireWith(this,arguments)},fired:function(){return !!fired}};return Callbacks}})(Zepto);(function($){var zepto=$.zepto,oldQsa=zepto.qsa,oldMatches=zepto.matches;function visible(elem){elem=$(elem);return !!(elem.width()||elem.height())&&elem.css("display")!=="none"}var filters=$.expr[":"]={visible:function(){if(visible(this)){return this}},hidden:function(){if(!visible(this)){return this}},selected:function(){if(this.selected){return this}},checked:function(){if(this.checked){return this}},parent:function(){return this.parentNode},first:function(idx){if(idx===0){return this}},last:function(idx,nodes){if(idx===nodes.length-1){return this}},eq:function(idx,_,value){if(idx===value){return this}},contains:function(idx,_,text){if($(this).text().indexOf(text)>-1){return this}},has:function(idx,_,sel){if(zepto.qsa(this,sel).length){return this}}};var filterRe=new RegExp("(.*):(\\w+)(?:\\(([^)]+)\\))?$\\s*"),childRe=/^\s*>/,classTag="Zepto"+(+new Date());function process(sel,fn){sel=sel.replace(/=#\]/g,'="#"]');var filter,arg,match=filterRe.exec(sel);if(match&&match[2] in filters){filter=filters[match[2]],arg=match[3];sel=match[1];if(arg){var num=Number(arg);if(isNaN(num)){arg=arg.replace(/^["']|["']$/g,"")}else{arg=num}}}return fn(sel,filter,arg)}zepto.qsa=function(node,selector){return process(selector,function(sel,filter,arg){try{var taggedParent;if(!sel&&filter){sel="*"}else{if(childRe.test(sel)){taggedParent=$(node).addClass(classTag),sel="."+classTag+" "+sel}}var nodes=oldQsa(node,sel)}catch(e){console.error("error performing selector: %o",selector);throw e}finally{if(taggedParent){taggedParent.removeClass(classTag)}}return !filter?nodes:zepto.uniq($.map(nodes,function(n,i){return filter.call(n,i,nodes,arg)}))})};zepto.matches=function(node,selector){return process(selector,function(sel,filter,arg){return(!sel||oldMatches(node,sel))&&(!filter||filter.call(node,null,arg)===node)})}})(Zepto);(function($){var touch={},touchTimeout,tapTimeout,swipeTimeout,longTapTimeout,longTapDelay=750,gesture;function swipeDirection(x1,x2,y1,y2){return Math.abs(x1-x2)>=Math.abs(y1-y2)?(x1-x2>0?"Left":"Right"):(y1-y2>0?"Up":"Down")}function longTap(){longTapTimeout=null;if(touch.last){touch.el.trigger("longTap");touch={}}}function cancelLongTap(){if(longTapTimeout){clearTimeout(longTapTimeout)}longTapTimeout=null}function cancelAll(){if(touchTimeout){clearTimeout(touchTimeout)}if(tapTimeout){clearTimeout(tapTimeout)}if(swipeTimeout){clearTimeout(swipeTimeout)}if(longTapTimeout){clearTimeout(longTapTimeout)}touchTimeout=tapTimeout=swipeTimeout=longTapTimeout=null;touch={}}function isPrimaryTouch(event){return(event.pointerType=="touch"||event.pointerType==event.MSPOINTER_TYPE_TOUCH)&&event.isPrimary}function isPointerEventType(e,type){return(e.type=="pointer"+type||e.type.toLowerCase()=="mspointer"+type)}$(document).ready(function(){var now,delta,deltaX=0,deltaY=0,firstTouch,_isPointerType;if("MSGesture" in window){gesture=new MSGesture();gesture.target=document.body}$(document).bind("MSGestureEnd",function(e){var swipeDirectionFromVelocity=e.velocityX>1?"Right":e.velocityX<-1?"Left":e.velocityY>1?"Down":e.velocityY<-1?"Up":null;if(swipeDirectionFromVelocity){touch.el.trigger("swipe");touch.el.trigger("swipe"+swipeDirectionFromVelocity)}}).on("touchstart MSPointerDown pointerdown",function(e){if((_isPointerType=isPointerEventType(e,"down"))&&!isPrimaryTouch(e)){return}firstTouch=_isPointerType?e:e.touches[0];if(e.touches&&e.touches.length===1&&touch.x2){touch.x2=undefined;touch.y2=undefined}now=Date.now();delta=now-(touch.last||now);touch.el=$("tagName" in firstTouch.target?firstTouch.target:firstTouch.target.parentNode);touchTimeout&&clearTimeout(touchTimeout);touch.x1=firstTouch.pageX;touch.y1=firstTouch.pageY;if(delta>0&&delta<=250){touch.isDoubleTap=true}touch.last=now;longTapTimeout=setTimeout(longTap,longTapDelay);if(gesture&&_isPointerType){gesture.addPointer(e.pointerId)}}).on("touchmove MSPointerMove pointermove",function(e){if(touch.last){if((_isPointerType=isPointerEventType(e,"move"))&&!isPrimaryTouch(e)){return}firstTouch=_isPointerType?e:e.touches[0];cancelLongTap();touch.x2=firstTouch.pageX;touch.y2=firstTouch.pageY;deltaX+=Math.abs(touch.x1-touch.x2);deltaY+=Math.abs(touch.y1-touch.y2)}}).on("touchend MSPointerUp pointerup",function(e){if((_isPointerType=isPointerEventType(e,"up"))&&!isPrimaryTouch(e)){return}cancelLongTap();if((touch.x2&&Math.abs(touch.x1-touch.x2)>30)||(touch.y2&&Math.abs(touch.y1-touch.y2)>30)){swipeTimeout=setTimeout(function(){touch.el.trigger("swipe");touch.el.trigger("swipe"+(swipeDirection(touch.x1,touch.x2,touch.y1,touch.y2)));touch={}},0)}else{if("last" in touch){if(deltaX<30&&deltaY<30){tapTimeout=setTimeout(function(){var event=$.Event("tap");event.cancelTouch=cancelAll;touch.el.trigger(event);if(touch.isDoubleTap){if(touch.el){touch.el.trigger("doubleTap")}touch={}}else{touchTimeout=setTimeout(function(){touchTimeout=null;if(touch.el){touch.el.trigger("singleTap")}touch={}},250)}},0)}else{touch={}}}}deltaX=deltaY=0}).on("touchcancel MSPointerCancel pointercancel",cancelAll);$(window).on("scroll",cancelAll)});["swipe","swipeLeft","swipeRight","swipeUp","swipeDown","doubleTap","tap","singleTap","longTap"].forEach(function(eventName){$.fn[eventName]=function(callback){return this.on(eventName,callback)}})})(Zepto);(function($){if($.os.ios){var gesture={},gestureTimeout;function parentIfText(node){return"tagName" in node?node:node.parentNode}$(document).bind("gesturestart",function(e){var now=Date.now(),delta=now-(gesture.last||now);gesture.target=parentIfText(e.target);gestureTimeout&&clearTimeout(gestureTimeout);gesture.e1=e.scale;gesture.last=now}).bind("gesturechange",function(e){gesture.e2=e.scale}).bind("gestureend",function(e){if(gesture.e2>0){Math.abs(gesture.e1-gesture.e2)!=0&&$(gesture.target).trigger("pinch")&&$(gesture.target).trigger("pinch"+(gesture.e1-gesture.e2>0?"In":"Out"));gesture.e1=gesture.e2=gesture.last=0}else{if("last" in gesture){gesture={}}}});["pinch","pinchIn","pinchOut"].forEach(function(m){$.fn[m]=function(callback){return this.bind(m,callback)}})}})(Zepto);(function($){$.fn.end=function(){return this.prevObject||$()};$.fn.andSelf=function(){return this.add(this.prevObject||$())};"filter,add,not,eq,first,last,find,closest,parents,parent,children,siblings".split(",").forEach(function(property){var fn=$.fn[property];$.fn[property]=function(){var ret=fn.apply(this,arguments);ret.prevObject=this;return ret}})})(Zepto);if(typeof define==="function"&&define.amd){define([],function(){return Zepto})};(function ($) {
    /**
     * If no touch events are available map touch events to corresponding mouse events.
     **/
    if(!('ontouchstart' in window)){
        var _fakeCallbacks = {}; // store the faked callbacks so that they can be unbound
        function touch2mouse(context,args){
            var type = args[0],//事件类型
                t = type ,matched = false;
            if ((typeof type) == "object") {
                return args;
            }
            if(type.indexOf('touchstart')>-1){
                matched = true;
                t = t.replace('touchstart','mousedown');
            }else if(t.indexOf('touchend')>-1){
                matched = true;
                t = t.replace('touchend','mouseup');
            }else if(t.indexOf('touchmove')>-1){
                matched = true;
                t = t.replace('touchmove','mousemove');
            }
            if(matched){//匹配到touch事件
                var ci = -1;
                //找出callback，需要更换里面的event对象的属性
                for(var i =1;i<args.length;i++){
                    if(typeof args[i] == 'function'){
                        ci = i;
                        break;
                    }
                }
                args[0] = t;
                if(ci > -1){
                    args[ci] = fakeTouches(type, args[ci] , context);
                }
            }
            return args;
        }

        function fakeTouches(type, callback, context) {
            // wrap the callback with a function that adds a fake
            // touches property to the event.
            return _fakeCallbacks[callback] = function (event) {
                if(event.liveFired)context = this;//if it is delegate event,change context to target element
                if (event.button) {
                    return false;
                }
                event.touches = [{
                    length: 1,// 1 mouse (finger)
                    clientX: event.clientX,
                    clientY: event.clientY,
                    pageX: event.pageX,
                    pageY: event.pageY,
                    screenX: event.screenX,
                    screenY: event.screenY,
                    target: event.target
                }];
                event.touchType = type;
                return callback.apply(context, [event]);
            }
        }

        var _trigger = $.fn.trigger;
        $.fn.trigger = function(event, data){
            var args = Array.prototype.slice.call(arguments,0);
            return _trigger.apply(this,touch2mouse(this,args));
        };
        var _triggerHandler = $.fn.triggerHandler;
        $.fn.triggerHandler = function(event, data){
            var args = Array.prototype.slice.call(arguments,0);
            return _triggerHandler.apply(this, touch2mouse(this,args));
        };
        var _on = $.fn.on;
        $.fn.on = function(event, selector, data, callback, one){
            var args = Array.prototype.slice.call(arguments,0);
            return _on.apply(this,touch2mouse(this,args));
        }
        var _off = $.fn.off;
        $.fn.off = function(event, selector, callback){
            var args = [event];
            if(typeof selector == 'string'){
                args.push(selector);
            }
            var result = touch2mouse(this,args).concat([_fakeCallbacks[callback]||callback]);
            delete(_fakeCallbacks[callback]);
            return _off.apply(this,result);
        }
    }
})(Zepto);/*!
 * iScroll v4.2.5 ~ Copyright (c) 2012 Matteo Spinelli, http://cubiq.org
 * Released under MIT license, http://cubiq.org/license
 */
(function(window, doc){
var m = Math,
	dummyStyle = doc.createElement('div').style,
	vendor = (function () {
		var vendors = 't,webkitT,MozT,msT,OT'.split(','),
			t,
			i = 0,
			l = vendors.length;

		for ( ; i < l; i++ ) {
			t = vendors[i] + 'ransform';
			if ( t in dummyStyle ) {
				return vendors[i].substr(0, vendors[i].length - 1);
			}
		}

		return false;
	})(),
	cssVendor = vendor ? '-' + vendor.toLowerCase() + '-' : '',

	// Style properties
	transform = prefixStyle('transform'),
	transitionProperty = prefixStyle('transitionProperty'),
	transitionDuration = prefixStyle('transitionDuration'),
	transformOrigin = prefixStyle('transformOrigin'),
	transitionTimingFunction = prefixStyle('transitionTimingFunction'),
	transitionDelay = prefixStyle('transitionDelay'),

    // Browser capabilities
	isAndroid = (/android/gi).test(navigator.appVersion),
	isIDevice = (/iphone|ipad/gi).test(navigator.appVersion),
	isTouchPad = (/hp-tablet/gi).test(navigator.appVersion),

    has3d = prefixStyle('perspective') in dummyStyle,
    hasTouch = 'ontouchstart' in window && !isTouchPad,
    hasTransform = vendor !== false,
    hasTransitionEnd = prefixStyle('transition') in dummyStyle,

	RESIZE_EV = 'onorientationchange' in window ? 'orientationchange' : 'resize',
	START_EV = hasTouch ? 'touchstart' : 'mousedown',
	MOVE_EV = hasTouch ? 'touchmove' : 'mousemove',
	END_EV = hasTouch ? 'touchend' : 'mouseup',
	CANCEL_EV = hasTouch ? 'touchcancel' : 'mouseup',
	TRNEND_EV = (function () {
		if ( vendor === false ) return false;

		var transitionEnd = {
				''			: 'transitionend',
				'webkit'	: 'webkitTransitionEnd',
				'Moz'		: 'transitionend',
				'O'			: 'otransitionend',
				'ms'		: 'MSTransitionEnd'
			};

		return transitionEnd[vendor];
	})(),

	nextFrame = (function() {
		return window.requestAnimationFrame ||
			window.webkitRequestAnimationFrame ||
			window.mozRequestAnimationFrame ||
			window.oRequestAnimationFrame ||
			window.msRequestAnimationFrame ||
			function(callback) { return setTimeout(callback, 1); };
	})(),
	cancelFrame = (function () {
		return window.cancelRequestAnimationFrame ||
			window.webkitCancelAnimationFrame ||
			window.webkitCancelRequestAnimationFrame ||
			window.mozCancelRequestAnimationFrame ||
			window.oCancelRequestAnimationFrame ||
			window.msCancelRequestAnimationFrame ||
			clearTimeout;
	})(),

	// Helpers
	translateZ = has3d ? ' translateZ(0)' : '',

	// Constructor
	iScroll = function (el, options) {
		var that = this,
			i;

		that.wrapper = typeof el == 'object' ? el : doc.getElementById(el);
		that.wrapper.style.overflow = 'hidden';
		that.scroller = that.wrapper.children[0];

		// Default options
		that.options = {
			hScroll: true,
			vScroll: true,
			x: 0,
			y: 0,
			bounce: true,
			bounceLock: false,
			momentum: true,
			lockDirection: true,
			useTransform: true,
			useTransition: false,
			topOffset: 0,
			checkDOMChanges: false,		// Experimental
			handleClick: false,

			// Scrollbar
			hScrollbar: true,
			vScrollbar: true,
			fixedScrollbar: isAndroid,
			hideScrollbar: isIDevice,
			fadeScrollbar: isIDevice && has3d,
			scrollbarClass: '',

			// Zoom
			zoom: false,
			zoomMin: 1,
			zoomMax: 4,
			doubleTapZoom: 2,
			wheelAction: 'scroll',

			// Snap
			snap: false,
			snapThreshold: 1,

			// Events
			onRefresh: null,
			onBeforeScrollStart: function (e) { e.preventDefault(); },
			onScrollStart: null,
			onBeforeScrollMove: null,
			onScrollMove: null,
			onBeforeScrollEnd: null,
			onScrollEnd: null,
			onTouchEnd: null,
			onDestroy: null,
			onZoomStart: null,
			onZoom: null,
			onZoomEnd: null
		};

		// User defined options
		for (i in options) that.options[i] = options[i];
		
		// Set starting position
		that.x = that.options.x;
		that.y = that.options.y;

		// Normalize options
		that.options.useTransform = hasTransform && that.options.useTransform;
		that.options.hScrollbar = that.options.hScroll && that.options.hScrollbar;
		that.options.vScrollbar = that.options.vScroll && that.options.vScrollbar;
		that.options.zoom = that.options.useTransform && that.options.zoom;
		that.options.useTransition = hasTransitionEnd && that.options.useTransition;

		// Helpers FIX ANDROID BUG!
		// translate3d and scale doesn't work together!
		// Ignoring 3d ONLY WHEN YOU SET that.options.zoom
		if ( that.options.zoom && isAndroid ){
			translateZ = '';
		}
		
		// Set some default styles
		that.scroller.style[transitionProperty] = that.options.useTransform ? cssVendor + 'transform' : 'top left';
		that.scroller.style[transitionDuration] = '0';
		that.scroller.style[transformOrigin] = '0 0';
		if (that.options.useTransition) that.scroller.style[transitionTimingFunction] = 'cubic-bezier(0.33,0.66,0.66,1)';
		
		if (that.options.useTransform) that.scroller.style[transform] = 'translate(' + that.x + 'px,' + that.y + 'px)' + translateZ;
		else that.scroller.style.cssText += ';position:absolute;top:' + that.y + 'px;left:' + that.x + 'px';

		if (that.options.useTransition) that.options.fixedScrollbar = true;

		that.refresh();

		that._bind(RESIZE_EV, window);
		that._bind(START_EV);
		if (!hasTouch) {
			if (that.options.wheelAction != 'none') {
				that._bind('DOMMouseScroll');
				that._bind('mousewheel');
			}
		}

		if (that.options.checkDOMChanges) that.checkDOMTime = setInterval(function () {
			that._checkDOMChanges();
		}, 500);
	};

// Prototype
iScroll.prototype = {
	enabled: true,
	x: 0,
	y: 0,
	steps: [],
	scale: 1,
	currPageX: 0, currPageY: 0,
	pagesX: [], pagesY: [],
	aniTime: null,
	wheelZoomCount: 0,
	
	handleEvent: function (e) {
		var that = this;
		switch(e.type) {
			case START_EV:
				if (!hasTouch && e.button !== 0) return;
				that._start(e);
				break;
			case MOVE_EV: that._move(e); break;
			case END_EV:
			case CANCEL_EV: that._end(e); break;
			case RESIZE_EV: that._resize(); break;
			case 'DOMMouseScroll': case 'mousewheel': that._wheel(e); break;
			case TRNEND_EV: that._transitionEnd(e); break;
		}
	},
	
	_checkDOMChanges: function () {
		if (this.moved || this.zoomed || this.animating ||
			(this.scrollerW == this.scroller.offsetWidth * this.scale && this.scrollerH == this.scroller.offsetHeight * this.scale)) return;

		this.refresh();
	},
	
	_scrollbar: function (dir) {
		var that = this,
			bar;

		if (!that[dir + 'Scrollbar']) {
			if (that[dir + 'ScrollbarWrapper']) {
				if (hasTransform) that[dir + 'ScrollbarIndicator'].style[transform] = '';
				that[dir + 'ScrollbarWrapper'].parentNode.removeChild(that[dir + 'ScrollbarWrapper']);
				that[dir + 'ScrollbarWrapper'] = null;
				that[dir + 'ScrollbarIndicator'] = null;
			}

			return;
		}

		if (!that[dir + 'ScrollbarWrapper']) {
			// Create the scrollbar wrapper
			bar = doc.createElement('div');

			if (that.options.scrollbarClass) bar.className = that.options.scrollbarClass + dir.toUpperCase();
			else bar.style.cssText = 'position:absolute;z-index:100;' + (dir == 'h' ? 'height:7px;bottom:1px;left:2px;right:' + (that.vScrollbar ? '7' : '2') + 'px' : 'width:7px;bottom:' + (that.hScrollbar ? '7' : '2') + 'px;top:2px;right:1px');

			bar.style.cssText += ';pointer-events:none;' + cssVendor + 'transition-property:opacity;' + cssVendor + 'transition-duration:' + (that.options.fadeScrollbar ? '350ms' : '0') + ';overflow:hidden;opacity:' + (that.options.hideScrollbar ? '0' : '1');

			that.wrapper.appendChild(bar);
			that[dir + 'ScrollbarWrapper'] = bar;

			// Create the scrollbar indicator
			bar = doc.createElement('div');
			if (!that.options.scrollbarClass) {
				bar.style.cssText = 'position:absolute;z-index:100;background:rgba(0,0,0,0.5);border:1px solid rgba(255,255,255,0.9);' + cssVendor + 'background-clip:padding-box;' + cssVendor + 'box-sizing:border-box;' + (dir == 'h' ? 'height:100%' : 'width:100%') + ';' + cssVendor + 'border-radius:3px;border-radius:3px';
			}
			bar.style.cssText += ';pointer-events:none;' + cssVendor + 'transition-property:' + cssVendor + 'transform;' + cssVendor + 'transition-timing-function:cubic-bezier(0.33,0.66,0.66,1);' + cssVendor + 'transition-duration:0;' + cssVendor + 'transform: translate(0,0)' + translateZ;
			if (that.options.useTransition) bar.style.cssText += ';' + cssVendor + 'transition-timing-function:cubic-bezier(0.33,0.66,0.66,1)';

			that[dir + 'ScrollbarWrapper'].appendChild(bar);
			that[dir + 'ScrollbarIndicator'] = bar;
		}

		if (dir == 'h') {
			that.hScrollbarSize = that.hScrollbarWrapper.clientWidth;
			that.hScrollbarIndicatorSize = m.max(m.round(that.hScrollbarSize * that.hScrollbarSize / that.scrollerW), 8);
			that.hScrollbarIndicator.style.width = that.hScrollbarIndicatorSize + 'px';
			that.hScrollbarMaxScroll = that.hScrollbarSize - that.hScrollbarIndicatorSize;
			that.hScrollbarProp = that.hScrollbarMaxScroll / that.maxScrollX;
		} else {
			that.vScrollbarSize = that.vScrollbarWrapper.clientHeight;
			that.vScrollbarIndicatorSize = m.max(m.round(that.vScrollbarSize * that.vScrollbarSize / that.scrollerH), 8);
			that.vScrollbarIndicator.style.height = that.vScrollbarIndicatorSize + 'px';
			that.vScrollbarMaxScroll = that.vScrollbarSize - that.vScrollbarIndicatorSize;
			that.vScrollbarProp = that.vScrollbarMaxScroll / that.maxScrollY;
		}

		// Reset position
		that._scrollbarPos(dir, true);
	},
	
	_resize: function () {
		var that = this;
		setTimeout(function () { that.refresh(); }, isAndroid ? 200 : 0);
	},
	
	_pos: function (x, y) {
		if (this.zoomed) return;

		x = this.hScroll ? x : 0;
		y = this.vScroll ? y : 0;

		if (this.options.useTransform) {
			this.scroller.style[transform] = 'translate(' + x + 'px,' + y + 'px) scale(' + this.scale + ')' + translateZ;
		} else {
			x = m.round(x);
			y = m.round(y);
			this.scroller.style.left = x + 'px';
			this.scroller.style.top = y + 'px';
		}

		this.x = x;
		this.y = y;

		this._scrollbarPos('h');
		this._scrollbarPos('v');
	},

	_scrollbarPos: function (dir, hidden) {
		var that = this,
			pos = dir == 'h' ? that.x : that.y,
			size;

		if (!that[dir + 'Scrollbar']) return;

		pos = that[dir + 'ScrollbarProp'] * pos;

		if (pos < 0) {
			if (!that.options.fixedScrollbar) {
				size = that[dir + 'ScrollbarIndicatorSize'] + m.round(pos * 3);
				if (size < 8) size = 8;
				that[dir + 'ScrollbarIndicator'].style[dir == 'h' ? 'width' : 'height'] = size + 'px';
			}
			pos = 0;
		} else if (pos > that[dir + 'ScrollbarMaxScroll']) {
			if (!that.options.fixedScrollbar) {
				size = that[dir + 'ScrollbarIndicatorSize'] - m.round((pos - that[dir + 'ScrollbarMaxScroll']) * 3);
				if (size < 8) size = 8;
				that[dir + 'ScrollbarIndicator'].style[dir == 'h' ? 'width' : 'height'] = size + 'px';
				pos = that[dir + 'ScrollbarMaxScroll'] + (that[dir + 'ScrollbarIndicatorSize'] - size);
			} else {
				pos = that[dir + 'ScrollbarMaxScroll'];
			}
		}

		that[dir + 'ScrollbarWrapper'].style[transitionDelay] = '0';
		that[dir + 'ScrollbarWrapper'].style.opacity = hidden && that.options.hideScrollbar ? '0' : '1';
		that[dir + 'ScrollbarIndicator'].style[transform] = 'translate(' + (dir == 'h' ? pos + 'px,0)' : '0,' + pos + 'px)') + translateZ;
	},
	
	_start: function (e) {
		var that = this,
			point = hasTouch ? e.touches[0] : e,
			matrix, x, y,
			c1, c2;

		if (!that.enabled) return;

		if (that.options.onBeforeScrollStart) that.options.onBeforeScrollStart.call(that, e);

		if (that.options.useTransition || that.options.zoom) that._transitionTime(0);

		that.moved = false;
		that.animating = false;
		that.zoomed = false;
		that.distX = 0;
		that.distY = 0;
		that.absDistX = 0;
		that.absDistY = 0;
		that.dirX = 0;
		that.dirY = 0;

		// Gesture start
		if (that.options.zoom && hasTouch && e.touches.length > 1) {
			c1 = m.abs(e.touches[0].pageX-e.touches[1].pageX);
			c2 = m.abs(e.touches[0].pageY-e.touches[1].pageY);
			that.touchesDistStart = m.sqrt(c1 * c1 + c2 * c2);

			that.originX = m.abs(e.touches[0].pageX + e.touches[1].pageX - that.wrapperOffsetLeft * 2) / 2 - that.x;
			that.originY = m.abs(e.touches[0].pageY + e.touches[1].pageY - that.wrapperOffsetTop * 2) / 2 - that.y;

			if (that.options.onZoomStart) that.options.onZoomStart.call(that, e);
		}

		if (that.options.momentum) {
			if (that.options.useTransform) {
				// Very lame general purpose alternative to CSSMatrix
				matrix = getComputedStyle(that.scroller, null)[transform].replace(/[^0-9\-.,]/g, '').split(',');
				x = +(matrix[12] || matrix[4]);
				y = +(matrix[13] || matrix[5]);
			} else {
				x = +getComputedStyle(that.scroller, null).left.replace(/[^0-9-]/g, '');
				y = +getComputedStyle(that.scroller, null).top.replace(/[^0-9-]/g, '');
			}
			
			if (x != that.x || y != that.y) {
				if (that.options.useTransition) that._unbind(TRNEND_EV);
				else cancelFrame(that.aniTime);
				that.steps = [];
				that._pos(x, y);
				if (that.options.onScrollEnd) that.options.onScrollEnd.call(that);
			}
		}

		that.absStartX = that.x;	// Needed by snap threshold
		that.absStartY = that.y;

		that.startX = that.x;
		that.startY = that.y;
		that.pointX = point.pageX;
		that.pointY = point.pageY;

		that.startTime = e.timeStamp || Date.now();

		if (that.options.onScrollStart) that.options.onScrollStart.call(that, e);

		that._bind(MOVE_EV, window);
		that._bind(END_EV, window);
		that._bind(CANCEL_EV, window);
	},
	
	_move: function (e) {
		var that = this,
			point = hasTouch ? e.touches[0] : e,
			deltaX = point.pageX - that.pointX,
			deltaY = point.pageY - that.pointY,
			newX = that.x + deltaX,
			newY = that.y + deltaY,
			c1, c2, scale,
			timestamp = e.timeStamp || Date.now();

		if (that.options.onBeforeScrollMove) that.options.onBeforeScrollMove.call(that, e);

		// Zoom
		if (that.options.zoom && hasTouch && e.touches.length > 1) {
			c1 = m.abs(e.touches[0].pageX - e.touches[1].pageX);
			c2 = m.abs(e.touches[0].pageY - e.touches[1].pageY);
			that.touchesDist = m.sqrt(c1*c1+c2*c2);

			that.zoomed = true;

			scale = 1 / that.touchesDistStart * that.touchesDist * this.scale;

			if (scale < that.options.zoomMin) scale = 0.5 * that.options.zoomMin * Math.pow(2.0, scale / that.options.zoomMin);
			else if (scale > that.options.zoomMax) scale = 2.0 * that.options.zoomMax * Math.pow(0.5, that.options.zoomMax / scale);

			that.lastScale = scale / this.scale;

			newX = this.originX - this.originX * that.lastScale + this.x;
			newY = this.originY - this.originY * that.lastScale + this.y;

			this.scroller.style[transform] = 'translate(' + newX + 'px,' + newY + 'px) scale(' + scale + ')' + translateZ;

			if (that.options.onZoom) that.options.onZoom.call(that, e);
			return;
		}

		that.pointX = point.pageX;
		that.pointY = point.pageY;

		// Slow down if outside of the boundaries
		if (newX > 0 || newX < that.maxScrollX) {
			newX = that.options.bounce ? that.x + (deltaX / 2) : newX >= 0 || that.maxScrollX >= 0 ? 0 : that.maxScrollX;
		}
		if (newY > that.minScrollY || newY < that.maxScrollY) {
			newY = that.options.bounce ? that.y + (deltaY / 2) : newY >= that.minScrollY || that.maxScrollY >= 0 ? that.minScrollY : that.maxScrollY;
		}

		that.distX += deltaX;
		that.distY += deltaY;
		that.absDistX = m.abs(that.distX);
		that.absDistY = m.abs(that.distY);

		if (that.absDistX < 6 && that.absDistY < 6) {
			return;
		}

		// Lock direction
		if (that.options.lockDirection) {
			if (that.absDistX > that.absDistY + 5) {
				newY = that.y;
				deltaY = 0;
			} else if (that.absDistY > that.absDistX + 5) {
				newX = that.x;
				deltaX = 0;
			}
		}

		that.moved = true;
		that._pos(newX, newY);
		that.dirX = deltaX > 0 ? -1 : deltaX < 0 ? 1 : 0;
		that.dirY = deltaY > 0 ? -1 : deltaY < 0 ? 1 : 0;

		if (timestamp - that.startTime > 300) {
			that.startTime = timestamp;
			that.startX = that.x;
			that.startY = that.y;
		}
		
		if (that.options.onScrollMove) that.options.onScrollMove.call(that, e);
	},
	
	_end: function (e) {
		if (hasTouch && e.touches.length !== 0) return;

		var that = this,
			point = hasTouch ? e.changedTouches[0] : e,
			target, ev,
			momentumX = { dist:0, time:0 },
			momentumY = { dist:0, time:0 },
			duration = (e.timeStamp || Date.now()) - that.startTime,
			newPosX = that.x,
			newPosY = that.y,
			distX, distY,
			newDuration,
			snap,
			scale;

		that._unbind(MOVE_EV, window);
		that._unbind(END_EV, window);
		that._unbind(CANCEL_EV, window);

		if (that.options.onBeforeScrollEnd) that.options.onBeforeScrollEnd.call(that, e);

		if (that.zoomed) {
			scale = that.scale * that.lastScale;
			scale = Math.max(that.options.zoomMin, scale);
			scale = Math.min(that.options.zoomMax, scale);
			that.lastScale = scale / that.scale;
			that.scale = scale;

			that.x = that.originX - that.originX * that.lastScale + that.x;
			that.y = that.originY - that.originY * that.lastScale + that.y;
			
			that.scroller.style[transitionDuration] = '200ms';
			that.scroller.style[transform] = 'translate(' + that.x + 'px,' + that.y + 'px) scale(' + that.scale + ')' + translateZ;
			
			that.zoomed = false;
			that.refresh();

			if (that.options.onZoomEnd) that.options.onZoomEnd.call(that, e);
			return;
		}

		if (!that.moved) {
			if (hasTouch) {
				if (that.doubleTapTimer && that.options.zoom) {
					// Double tapped
					clearTimeout(that.doubleTapTimer);
					that.doubleTapTimer = null;
					if (that.options.onZoomStart) that.options.onZoomStart.call(that, e);
					that.zoom(that.pointX, that.pointY, that.scale == 1 ? that.options.doubleTapZoom : 1);
					if (that.options.onZoomEnd) {
						setTimeout(function() {
							that.options.onZoomEnd.call(that, e);
						}, 200); // 200 is default zoom duration
					}
				} else if (this.options.handleClick) {
					that.doubleTapTimer = setTimeout(function () {
						that.doubleTapTimer = null;

						// Find the last touched element
						target = point.target;
						while (target.nodeType != 1) target = target.parentNode;

						if (target.tagName != 'SELECT' && target.tagName != 'INPUT' && target.tagName != 'TEXTAREA') {
							ev = doc.createEvent('MouseEvents');
							ev.initMouseEvent('click', true, true, e.view, 1,
								point.screenX, point.screenY, point.clientX, point.clientY,
								e.ctrlKey, e.altKey, e.shiftKey, e.metaKey,
								0, null);
							ev._fake = true;
							target.dispatchEvent(ev);
						}
					}, that.options.zoom ? 250 : 0);
				}
			}

			that._resetPos(400);

			if (that.options.onTouchEnd) that.options.onTouchEnd.call(that, e);
			return;
		}

		if (duration < 300 && that.options.momentum) {
			momentumX = newPosX ? that._momentum(newPosX - that.startX, duration, -that.x, that.scrollerW - that.wrapperW + that.x, that.options.bounce ? that.wrapperW : 0) : momentumX;
			momentumY = newPosY ? that._momentum(newPosY - that.startY, duration, -that.y, (that.maxScrollY < 0 ? that.scrollerH - that.wrapperH + that.y - that.minScrollY : 0), that.options.bounce ? that.wrapperH : 0) : momentumY;

			newPosX = that.x + momentumX.dist;
			newPosY = that.y + momentumY.dist;

			if ((that.x > 0 && newPosX > 0) || (that.x < that.maxScrollX && newPosX < that.maxScrollX)) momentumX = { dist:0, time:0 };
			if ((that.y > that.minScrollY && newPosY > that.minScrollY) || (that.y < that.maxScrollY && newPosY < that.maxScrollY)) momentumY = { dist:0, time:0 };
		}

		if (momentumX.dist || momentumY.dist) {
			newDuration = m.max(m.max(momentumX.time, momentumY.time), 10);

			// Do we need to snap?
			if (that.options.snap) {
				distX = newPosX - that.absStartX;
				distY = newPosY - that.absStartY;
				if (m.abs(distX) < that.options.snapThreshold && m.abs(distY) < that.options.snapThreshold) { that.scrollTo(that.absStartX, that.absStartY, 200); }
				else {
					snap = that._snap(newPosX, newPosY);
					newPosX = snap.x;
					newPosY = snap.y;
					newDuration = m.max(snap.time, newDuration);
				}
			}

			that.scrollTo(m.round(newPosX), m.round(newPosY), newDuration);

			if (that.options.onTouchEnd) that.options.onTouchEnd.call(that, e);
			return;
		}

		// Do we need to snap?
		if (that.options.snap) {
			distX = newPosX - that.absStartX;
			distY = newPosY - that.absStartY;
			if (m.abs(distX) < that.options.snapThreshold && m.abs(distY) < that.options.snapThreshold) that.scrollTo(that.absStartX, that.absStartY, 200);
			else {
				snap = that._snap(that.x, that.y);
				if (snap.x != that.x || snap.y != that.y) that.scrollTo(snap.x, snap.y, snap.time);
			}

			if (that.options.onTouchEnd) that.options.onTouchEnd.call(that, e);
			return;
		}

		that._resetPos(200);
		if (that.options.onTouchEnd) that.options.onTouchEnd.call(that, e);
	},
	
	_resetPos: function (time) {
		var that = this,
			resetX = that.x >= 0 ? 0 : that.x < that.maxScrollX ? that.maxScrollX : that.x,
			resetY = that.y >= that.minScrollY || that.maxScrollY > 0 ? that.minScrollY : that.y < that.maxScrollY ? that.maxScrollY : that.y;

		if (resetX == that.x && resetY == that.y) {
			if (that.moved) {
				that.moved = false;
				if (that.options.onScrollEnd) that.options.onScrollEnd.call(that);		// Execute custom code on scroll end
			}

			if (that.hScrollbar && that.options.hideScrollbar) {
				if (vendor == 'webkit') that.hScrollbarWrapper.style[transitionDelay] = '300ms';
				that.hScrollbarWrapper.style.opacity = '0';
			}
			if (that.vScrollbar && that.options.hideScrollbar) {
				if (vendor == 'webkit') that.vScrollbarWrapper.style[transitionDelay] = '300ms';
				that.vScrollbarWrapper.style.opacity = '0';
			}

			return;
		}

		that.scrollTo(resetX, resetY, time || 0);
	},

	_wheel: function (e) {
		var that = this,
			wheelDeltaX, wheelDeltaY,
			deltaX, deltaY,
			deltaScale;

		if ('wheelDeltaX' in e) {
			wheelDeltaX = e.wheelDeltaX / 12;
			wheelDeltaY = e.wheelDeltaY / 12;
		} else if('wheelDelta' in e) {
			wheelDeltaX = wheelDeltaY = e.wheelDelta / 12;
		} else if ('detail' in e) {
			wheelDeltaX = wheelDeltaY = -e.detail * 3;
		} else {
			return;
		}
		
		if (that.options.wheelAction == 'zoom') {
			deltaScale = that.scale * Math.pow(2, 1/3 * (wheelDeltaY ? wheelDeltaY / Math.abs(wheelDeltaY) : 0));
			if (deltaScale < that.options.zoomMin) deltaScale = that.options.zoomMin;
			if (deltaScale > that.options.zoomMax) deltaScale = that.options.zoomMax;
			
			if (deltaScale != that.scale) {
				if (!that.wheelZoomCount && that.options.onZoomStart) that.options.onZoomStart.call(that, e);
				that.wheelZoomCount++;
				
				that.zoom(e.pageX, e.pageY, deltaScale, 400);
				
				setTimeout(function() {
					that.wheelZoomCount--;
					if (!that.wheelZoomCount && that.options.onZoomEnd) that.options.onZoomEnd.call(that, e);
				}, 400);
			}
			
			return;
		}
		
		deltaX = that.x + wheelDeltaX;
		deltaY = that.y + wheelDeltaY;

		if (deltaX > 0) deltaX = 0;
		else if (deltaX < that.maxScrollX) deltaX = that.maxScrollX;

		if (deltaY > that.minScrollY) deltaY = that.minScrollY;
		else if (deltaY < that.maxScrollY) deltaY = that.maxScrollY;
    
		if (that.maxScrollY < 0) {
			that.scrollTo(deltaX, deltaY, 0);
		}
	},
	
	_transitionEnd: function (e) {
		var that = this;

		if (e.target != that.scroller) return;

		that._unbind(TRNEND_EV);
		
		that._startAni();
	},


	/**
	*
	* Utilities
	*
	*/
	_startAni: function () {
		var that = this,
			startX = that.x, startY = that.y,
			startTime = Date.now(),
			step, easeOut,
			animate;

		if (that.animating) return;
		
		if (!that.steps.length) {
			that._resetPos(400);
			return;
		}
		
		step = that.steps.shift();
		
		if (step.x == startX && step.y == startY) step.time = 0;

		that.animating = true;
		that.moved = true;
		
		if (that.options.useTransition) {
			that._transitionTime(step.time);
			that._pos(step.x, step.y);
			that.animating = false;
			if (step.time) that._bind(TRNEND_EV);
			else that._resetPos(0);
			return;
		}

		animate = function () {
			var now = Date.now(),
				newX, newY;

			if (now >= startTime + step.time) {
				that._pos(step.x, step.y);
				that.animating = false;
				if (that.options.onAnimationEnd) that.options.onAnimationEnd.call(that);			// Execute custom code on animation end
				that._startAni();
				return;
			}

			now = (now - startTime) / step.time - 1;
			easeOut = m.sqrt(1 - now * now);
			newX = (step.x - startX) * easeOut + startX;
			newY = (step.y - startY) * easeOut + startY;
			that._pos(newX, newY);
			if (that.animating) that.aniTime = nextFrame(animate);
		};

		animate();
	},

	_transitionTime: function (time) {
		time += 'ms';
		this.scroller.style[transitionDuration] = time;
		if (this.hScrollbar) this.hScrollbarIndicator.style[transitionDuration] = time;
		if (this.vScrollbar) this.vScrollbarIndicator.style[transitionDuration] = time;
	},

	_momentum: function (dist, time, maxDistUpper, maxDistLower, size) {
		var deceleration = 0.0006,
			speed = m.abs(dist) / time,
			newDist = (speed * speed) / (2 * deceleration),
			newTime = 0, outsideDist = 0;

		// Proportinally reduce speed if we are outside of the boundaries
		if (dist > 0 && newDist > maxDistUpper) {
			outsideDist = size / (6 / (newDist / speed * deceleration));
			maxDistUpper = maxDistUpper + outsideDist;
			speed = speed * maxDistUpper / newDist;
			newDist = maxDistUpper;
		} else if (dist < 0 && newDist > maxDistLower) {
			outsideDist = size / (6 / (newDist / speed * deceleration));
			maxDistLower = maxDistLower + outsideDist;
			speed = speed * maxDistLower / newDist;
			newDist = maxDistLower;
		}

		newDist = newDist * (dist < 0 ? -1 : 1);
		newTime = speed / deceleration;

		return { dist: newDist, time: m.round(newTime) };
	},

	_offset: function (el) {
		var left = -el.offsetLeft,
			top = -el.offsetTop;
			
		while (el = el.offsetParent) {
			left -= el.offsetLeft;
			top -= el.offsetTop;
		}
		
		if (el != this.wrapper) {
			left *= this.scale;
			top *= this.scale;
		}

		return { left: left, top: top };
	},

	_snap: function (x, y) {
		var that = this,
			i, l,
			page, time,
			sizeX, sizeY;

		// Check page X
		page = that.pagesX.length - 1;
		for (i=0, l=that.pagesX.length; i<l; i++) {
			if (x >= that.pagesX[i]) {
				page = i;
				break;
			}
		}
		if (page == that.currPageX && page > 0 && that.dirX < 0) page--;
		x = that.pagesX[page];
		sizeX = m.abs(x - that.pagesX[that.currPageX]);
		sizeX = sizeX ? m.abs(that.x - x) / sizeX * 500 : 0;
		that.currPageX = page;

		// Check page Y
		page = that.pagesY.length-1;
		for (i=0; i<page; i++) {
			if (y >= that.pagesY[i]) {
				page = i;
				break;
			}
		}
		if (page == that.currPageY && page > 0 && that.dirY < 0) page--;
		y = that.pagesY[page];
		sizeY = m.abs(y - that.pagesY[that.currPageY]);
		sizeY = sizeY ? m.abs(that.y - y) / sizeY * 500 : 0;
		that.currPageY = page;

		// Snap with constant speed (proportional duration)
		time = m.round(m.max(sizeX, sizeY)) || 200;

		return { x: x, y: y, time: time };
	},

	_bind: function (type, el, bubble) {
		(el || this.scroller).addEventListener(type, this, !!bubble);
	},

	_unbind: function (type, el, bubble) {
		(el || this.scroller).removeEventListener(type, this, !!bubble);
	},


	/**
	*
	* Public methods
	*
	*/
	destroy: function () {
		var that = this;

		that.scroller.style[transform] = '';

		// Remove the scrollbars
		that.hScrollbar = false;
		that.vScrollbar = false;
		that._scrollbar('h');
		that._scrollbar('v');

		// Remove the event listeners
		that._unbind(RESIZE_EV, window);
		that._unbind(START_EV);
		that._unbind(MOVE_EV, window);
		that._unbind(END_EV, window);
		that._unbind(CANCEL_EV, window);
		
		if (!that.options.hasTouch) {
			that._unbind('DOMMouseScroll');
			that._unbind('mousewheel');
		}
		
		if (that.options.useTransition) that._unbind(TRNEND_EV);
		
		if (that.options.checkDOMChanges) clearInterval(that.checkDOMTime);
		
		if (that.options.onDestroy) that.options.onDestroy.call(that);
	},

	refresh: function () {
		var that = this,
			offset,
			i, l,
			els,
			pos = 0,
			page = 0;

		if (that.scale < that.options.zoomMin) that.scale = that.options.zoomMin;
		that.wrapperW = that.wrapper.clientWidth || 1;
		that.wrapperH = that.wrapper.clientHeight || 1;

		that.minScrollY = -that.options.topOffset || 0;
		that.scrollerW = m.round(that.scroller.offsetWidth * that.scale);
		that.scrollerH = m.round((that.scroller.offsetHeight + that.minScrollY) * that.scale);
		that.maxScrollX = that.wrapperW - that.scrollerW;
		that.maxScrollY = that.wrapperH - that.scrollerH + that.minScrollY;
		that.dirX = 0;
		that.dirY = 0;

		if (that.options.onRefresh) that.options.onRefresh.call(that);

		that.hScroll = that.options.hScroll && that.maxScrollX < 0;
		that.vScroll = that.options.vScroll && (!that.options.bounceLock && !that.hScroll || that.scrollerH > that.wrapperH);

		that.hScrollbar = that.hScroll && that.options.hScrollbar;
		that.vScrollbar = that.vScroll && that.options.vScrollbar && that.scrollerH > that.wrapperH;

		offset = that._offset(that.wrapper);
		that.wrapperOffsetLeft = -offset.left;
		that.wrapperOffsetTop = -offset.top;

		// Prepare snap
		if (typeof that.options.snap == 'string') {
			that.pagesX = [];
			that.pagesY = [];
			els = that.scroller.querySelectorAll(that.options.snap);
			for (i=0, l=els.length; i<l; i++) {
				pos = that._offset(els[i]);
				pos.left += that.wrapperOffsetLeft;
				pos.top += that.wrapperOffsetTop;
				that.pagesX[i] = pos.left < that.maxScrollX ? that.maxScrollX : pos.left * that.scale;
				that.pagesY[i] = pos.top < that.maxScrollY ? that.maxScrollY : pos.top * that.scale;
			}
		} else if (that.options.snap) {
			that.pagesX = [];
			while (pos >= that.maxScrollX) {
				that.pagesX[page] = pos;
				pos = pos - that.wrapperW;
				page++;
			}
			if (that.maxScrollX%that.wrapperW) that.pagesX[that.pagesX.length] = that.maxScrollX - that.pagesX[that.pagesX.length-1] + that.pagesX[that.pagesX.length-1];

			pos = 0;
			page = 0;
			that.pagesY = [];
			while (pos >= that.maxScrollY) {
				that.pagesY[page] = pos;
				pos = pos - that.wrapperH;
				page++;
			}
			if (that.maxScrollY%that.wrapperH) that.pagesY[that.pagesY.length] = that.maxScrollY - that.pagesY[that.pagesY.length-1] + that.pagesY[that.pagesY.length-1];
		}

		// Prepare the scrollbars
		that._scrollbar('h');
		that._scrollbar('v');

		if (!that.zoomed) {
			that.scroller.style[transitionDuration] = '0';
			that._resetPos(400);
		}
	},

	scrollTo: function (x, y, time, relative) {
		var that = this,
			step = x,
			i, l;

		that.stop();

		if (!step.length) step = [{ x: x, y: y, time: time, relative: relative }];
		
		for (i=0, l=step.length; i<l; i++) {
			if (step[i].relative) { step[i].x = that.x - step[i].x; step[i].y = that.y - step[i].y; }
			that.steps.push({ x: step[i].x, y: step[i].y, time: step[i].time || 0 });
		}

		that._startAni();
	},

	scrollToElement: function (el, time) {
		var that = this, pos;
		el = el.nodeType ? el : that.scroller.querySelector(el);
		if (!el) return;

		pos = that._offset(el);
		pos.left += that.wrapperOffsetLeft;
		pos.top += that.wrapperOffsetTop;

		pos.left = pos.left > 0 ? 0 : pos.left < that.maxScrollX ? that.maxScrollX : pos.left;
		pos.top = pos.top > that.minScrollY ? that.minScrollY : pos.top < that.maxScrollY ? that.maxScrollY : pos.top;
		time = time === undefined ? m.max(m.abs(pos.left)*2, m.abs(pos.top)*2) : time;

		that.scrollTo(pos.left, pos.top, time);
	},

	scrollToPage: function (pageX, pageY, time) {
		var that = this, x, y;
		
		time = time === undefined ? 400 : time;

		if (that.options.onScrollStart) that.options.onScrollStart.call(that);

		if (that.options.snap) {
			pageX = pageX == 'next' ? that.currPageX+1 : pageX == 'prev' ? that.currPageX-1 : pageX;
			pageY = pageY == 'next' ? that.currPageY+1 : pageY == 'prev' ? that.currPageY-1 : pageY;

			pageX = pageX < 0 ? 0 : pageX > that.pagesX.length-1 ? that.pagesX.length-1 : pageX;
			pageY = pageY < 0 ? 0 : pageY > that.pagesY.length-1 ? that.pagesY.length-1 : pageY;

			that.currPageX = pageX;
			that.currPageY = pageY;
			x = that.pagesX[pageX];
			y = that.pagesY[pageY];
		} else {
			x = -that.wrapperW * pageX;
			y = -that.wrapperH * pageY;
			if (x < that.maxScrollX) x = that.maxScrollX;
			if (y < that.maxScrollY) y = that.maxScrollY;
		}

		that.scrollTo(x, y, time);
	},

	disable: function () {
		this.stop();
		this._resetPos(0);
		this.enabled = false;

		// If disabled after touchstart we make sure that there are no left over events
		this._unbind(MOVE_EV, window);
		this._unbind(END_EV, window);
		this._unbind(CANCEL_EV, window);
	},
	
	enable: function () {
		this.enabled = true;
	},
	
	stop: function () {
		if (this.options.useTransition) this._unbind(TRNEND_EV);
		else cancelFrame(this.aniTime);
		this.steps = [];
		this.moved = false;
		this.animating = false;
	},
	
	zoom: function (x, y, scale, time) {
		var that = this,
			relScale = scale / that.scale;

		if (!that.options.useTransform) return;

		that.zoomed = true;
		time = time === undefined ? 200 : time;
		x = x - that.wrapperOffsetLeft - that.x;
		y = y - that.wrapperOffsetTop - that.y;
		that.x = x - x * relScale + that.x;
		that.y = y - y * relScale + that.y;

		that.scale = scale;
		that.refresh();

		that.x = that.x > 0 ? 0 : that.x < that.maxScrollX ? that.maxScrollX : that.x;
		that.y = that.y > that.minScrollY ? that.minScrollY : that.y < that.maxScrollY ? that.maxScrollY : that.y;

		that.scroller.style[transitionDuration] = time + 'ms';
		that.scroller.style[transform] = 'translate(' + that.x + 'px,' + that.y + 'px) scale(' + scale + ')' + translateZ;
		that.zoomed = false;
	},
	
	isReady: function () {
		return !this.moved && !this.zoomed && !this.animating;
	}
};

function prefixStyle (style) {
	if ( vendor === '' ) return style;

	style = style.charAt(0).toUpperCase() + style.substr(1);
	return vendor + style;
}

dummyStyle = null;	// for the sake of it

if (typeof exports !== 'undefined') exports.iScroll = iScroll;
else window.iScroll = iScroll;

if ( typeof define === "function" && define.amd ) {
	// AMD. Register as an anonymous module.
	define( [], function () {
		return iScroll;
	});
}

})(window, document);
/*
 * jQuery hashchange event - v1.3 - 7/21/2010
 * http://benalman.com/projects/jquery-hashchange-plugin/
 * 
 * Copyright (c) 2010 "Cowboy" Ben Alman
 * Dual licensed under the MIT and GPL licenses.
 * http://benalman.com/about/license/
 */
var initfunc=function($,e,b){var c="hashchange",h=document,f,g=$.event.special,i=h.documentMode,d="on"+c in e&&(i===b||i>7);function a(j){j=j||location.href;return"#"+j.replace(/^[^#]*#?(.*)$/,"$1")}$.fn[c]=function(j){return j?this.bind(c,j):this.trigger(c)};$.fn[c].delay=50;if(g){g[c]=$.extend(g[c],{setup:function(){if(d){return false}$(f.start)},teardown:function(){if(d){return false}$(f.stop)}})}else{if(!d){$(f.start)}}f=(function(){var j={},p,m=a(),k=function(q){return q},l=k,o=k;j.start=function(){p||n()};j.stop=function(){p&&clearTimeout(p);p=b};function n(){var r=a(),q=o(m);if(r!==m){l(m=r,q);$(e).trigger(c)}else{if(q!==m){location.href=location.href.replace(/#.*/,"")+q}}p=setTimeout(n,$.fn[c].delay)}return j})()};if(typeof define==="function"&&define.amd){define(function(){initfunc($,window);return $})}else{initfunc(window.jQuery||window.Zepto,this)};!function(a){function b(b,d){var i=b[h],j=i&&e[i];if(void 0===d)return j||c(b);if(j){if(d in j)return j[d];var k=g(d);if(k in j)return j[k]}return f.call(a(b),d)}function c(b,c,f){var i=b[h]||(b[h]=++a.uuid),j=e[i]||(e[i]=d(b));return void 0!==c&&(j[g(c)]=f),j}function d(b){var c={};return a.each(b.attributes||i,function(b,d){0==d.name.indexOf("data-")&&(c[g(d.name.replace("data-",""))]=a.zepto.deserializeValue(d.value))}),c}var e={},f=a.fn.data,g=a.camelCase,h=a.expando="Zepto"+ +new Date,i=[];a.fn.data=function(d,e){return void 0===e?a.isPlainObject(d)?this.each(function(b,e){a.each(d,function(a,b){c(e,a,b)})}):0 in this?b(this[0],d):void 0:this.each(function(){c(this,d,e)})},a.fn.removeData=function(b){return"string"==typeof b&&(b=b.split(/\s+/)),this.each(function(){var c=this[h],d=c&&e[c];d&&a.each(b||d,function(a){delete d[b?g(this):a]})})},["remove","empty"].forEach(function(b){var c=a.fn[b];a.fn[b]=function(){var a=this.find("*");return"remove"===b&&(a=a.add(this)),a.removeData(),c.call(this)}})}(window.Zepto),!function(a){var b={};b.cache={},a.tpl=function(a,c,d){var e=/[^\w\-\.:]/.test(a)?function(a,b){var c,d=[],f=[];for(c in a)d.push(c),f.push(a[c]);return new Function(d,e.code).apply(b||a,f)}:b.cache[a]=b.cache[a]||this.get(document.getElementById(a).innerHTML);return e.code=e.code||"var $parts=[]; $parts.push('"+a.replace(/\\/g,"\\\\").replace(/[\r\t\n]/g," ").split("<%").join("	").replace(/(^|%>)[^\t]*/g,function(a){return a.replace(/'/g,"\\'")}).replace(/\t=(.*?)%>/g,"',$1,'").split("	").join("');").split("%>").join("$parts.push('")+"'); return $parts.join('');",c?e(c,d):e},a.adaptObject=function(b,c,d,e,f,g){var h=b;if("string"!=typeof d){var i=a.extend({},c,"object"==typeof d&&d),j=!1;a.isArray(h)&&h.length&&"script"==a(h)[0].nodeName.toLowerCase()?(h=a(a.tpl(h[0].innerHTML,i)).appendTo("body"),j=!0):a.isArray(h)&&h.length&&""==h.selector?(h=a(a.tpl(h[0].outerHTML,i)).appendTo("body"),j=!0):a.isArray(h)||(h=a(a.tpl(e,i)).appendTo("body"),j=!0)}return h.each(function(){var b=a(this),e=b.data("fz."+g);e||b.data("fz."+g,e=new f(this,a.extend({},c,"object"==typeof d&&d),j)),"string"==typeof d&&e[d]()})}}(window.Zepto),!function(a){function b(){return!1}function c(b){return a.adaptObject(this,e,b,d,f,"dialog")}var d='<div class="ui-dialog"><div class="ui-dialog-cnt"><div class="ui-dialog-bd"><div><h4><%=title%></h4><div><%=content%></div></div></div><div class="ui-dialog-ft ui-btn-group"><% for (var i = 0; i < button.length; i++) { %><% if (i == select) { %><button type="button" data-role="button"  class="select" id="dialogButton<%=i%>"><%=button[i]%></button><% } else { %><button type="button" data-role="button" id="dialogButton<%=i%>"><%=button[i]%></div><% } %><% } %></div></div></div>',e={title:"",content:"",button:["确认"],select:0,allowScroll:!1,callback:function(){}},f=function(b,c,d){this.option=a.extend(e,c),this.element=a(b),this._isFromTpl=d,this.button=a(b).find('[data-role="button"]'),this._bindEvent(),this.toggle()};f.prototype={_bindEvent:function(){var b=this;b.button.on("tap",function(){var c=a(b.button).index(a(this)),d=a.Event("dialog:action");d.index=c,b.element.trigger(d),b.hide.apply(b)})},toggle:function(){this.element.hasClass("show")?this.hide():this.show()},show:function(){var c=this;c.element.trigger(a.Event("dialog:show")),c.element.addClass("show"),this.option.allowScroll&&c.element.on("touchmove",b)},hide:function(){var c=this;c.element.trigger(a.Event("dialog:hide")),c.element.off("touchmove",b),c.element.removeClass("show"),c._isFromTpl&&c.element.remove()}},a.fn.dialog=a.dialog=c}(window.Zepto),!function(a){function b(b){return a.adaptObject(this,d,b,c,e,"loading")}var c='<div class="ui-loading-block show"><div class="ui-loading-cnt"><i class="ui-loading-bright"></i><p><%=content%></p></div></div>',d={content:"加载中..."},e=function(b,c,e){this.element=a(b),this._isFromTpl=e,this.option=a.extend(d,c),this.show()};e.prototype={show:function(){var b=a.Event("loading:show");this.element.trigger(b),this.element.show()},hide:function(){var b=a.Event("loading:hide");this.element.trigger(b),this.element.remove()}},a.fn.loading=a.loading=b}(window.Zepto),function(a){function b(b,c){this.wrapper="string"==typeof b?a(b)[0]:b,this.options={startX:0,startY:0,scrollY:!0,scrollX:!1,directionLockThreshold:5,momentum:!0,duration:300,bounce:!0,bounceTime:600,bounceEasing:"",preventDefault:!0,eventPassthrough:!0,freeScroll:!1,bindToWrapper:!0,resizePolling:60,disableMouse:!1,disableTouch:!1,disablePointer:!1,tap:!0,click:!1,preventDefaultException:{tagName:/^(INPUT|TEXTAREA|BUTTON|SELECT)$/},HWCompositing:!0,useTransition:!0,useTransform:!0};for(var e in c)this.options[e]=c[e];if(this.options.role||this.options.scrollX!==!1||(this.options.eventPassthrough="horizontal"),"slider"===this.options.role){if(this.options.scrollX=!0,this.options.scrollY=!1,this.options.momentum=!1,this.scroller=a(this.wrapper).find(".ui-slider-content")[0],a(this.scroller.children[0]).addClass("current"),this.currentPage=0,this.count=this.scroller.children.length,this.scroller.style.width=this.count+"00%",this.itemWidth=this.scroller.children[0].clientWidth,this.scrollWidth=this.itemWidth*this.count,this.options.indicator){for(var f='<ul class="ui-slider-indicators">',e=1;e<=this.count;e++)f+=1===e?'<li class="current">'+e+"</li>":"<li>"+e+"</li>";f+="</ul>",a(this.wrapper).append(f),this.indicator=a(this.wrapper).find(".ui-slider-indicators")[0]}}else"tab"===this.options.role?(this.options.scrollX=!0,this.options.scrollY=!1,this.options.momentum=!1,this.scroller=a(this.wrapper).find(".ui-tab-content")[0],this.nav=a(this.wrapper).find(".ui-tab-nav")[0],a(this.scroller.children[0]).addClass("current"),a(this.nav.children[0]).addClass("current"),this.currentPage=0,this.count=this.scroller.children.length,this.scroller.style.width=this.count+"00%",this.itemWidth=this.scroller.children[0].clientWidth,this.scrollWidth=this.itemWidth*this.count):this.scroller=this.wrapper.children[0];if(this.scrollerStyle=this.scroller.style,this.translateZ=d.hasPerspective&&this.options.HWCompositing?" translateZ(0)":"",this.options.useTransition=d.hasTransition&&this.options.useTransition,this.options.useTransform=d.hasTransform&&this.options.useTransform,this.options.eventPassthrough=this.options.eventPassthrough===!0?"vertical":this.options.eventPassthrough,this.options.preventDefault=!this.options.eventPassthrough&&this.options.preventDefault,this.options.scrollX="horizontal"==this.options.eventPassthrough?!1:this.options.scrollX,this.options.scrollY="vertical"==this.options.eventPassthrough?!1:this.options.scrollY,this.options.freeScroll=this.options.freeScroll&&!this.options.eventPassthrough,this.options.directionLockThreshold=this.options.eventPassthrough?0:this.options.directionLockThreshold,this.options.bounceEasing="string"==typeof this.options.bounceEasing?d.ease[this.options.bounceEasing]||d.ease.circular:this.options.bounceEasing,this.options.resizePolling=void 0===this.options.resizePolling?60:this.options.resizePolling,this.options.tap===!0&&(this.options.tap="tap"),this.options.useTransform===!1&&(this.scroller.style.position="relative"),this.x=0,this.y=0,this.directionX=0,this.directionY=0,this._events={},this._init(),this.refresh(),this.scrollTo(this.options.startX,this.options.startY),this.enable(),this.options.autoplay){var g=this;this.options.interval=this.options.interval||2e3,this.options.flag=setTimeout(function(){g._autoplay.apply(g)},g.options.interval)}}var c=window.requestAnimationFrame||window.webkitRequestAnimationFrame||window.mozRequestAnimationFrame||window.oRequestAnimationFrame||window.msRequestAnimationFrame||function(a){window.setTimeout(a,1e3/60)},d=function(){function a(a){return d===!1?!1:""===d?a:d+a.charAt(0).toUpperCase()+a.substr(1)}var b={},c=document.createElement("div").style,d=function(){for(var a,b=["t","webkitT","MozT","msT","OT"],d=0,e=b.length;e>d;d++)if(a=b[d]+"ransform",a in c)return b[d].substr(0,b[d].length-1);return!1}();b.getTime=Date.now||function(){return(new Date).getTime()},b.extend=function(a,b){for(var c in b)a[c]=b[c]},b.addEvent=function(a,b,c,d){a.addEventListener(b,c,!!d)},b.removeEvent=function(a,b,c,d){a.removeEventListener(b,c,!!d)},b.prefixPointerEvent=function(a){return window.MSPointerEvent?"MSPointer"+a.charAt(9).toUpperCase()+a.substr(10):a},b.momentum=function(a,b,c,d,e,f){var g,h,i=a-b,j=Math.abs(i)/c;return f=void 0===f?6e-4:f,g=a+j*j/(2*f)*(0>i?-1:1),h=j/f,d>g?(g=e?d-e/2.5*(j/8):d,i=Math.abs(g-a),h=i/j):g>0&&(g=e?e/2.5*(j/8):0,i=Math.abs(a)+g,h=i/j),{destination:Math.round(g),duration:h}};var e=a("transform");return b.extend(b,{hasTransform:e!==!1,hasPerspective:a("perspective")in c,hasTouch:"ontouchstart"in window,hasPointer:window.PointerEvent||window.MSPointerEvent,hasTransition:a("transition")in c}),b.isBadAndroid=/Android /.test(window.navigator.appVersion)&&!/Chrome\/\d/.test(window.navigator.appVersion),b.extend(b.style={},{transform:e,transitionTimingFunction:a("transitionTimingFunction"),transitionDuration:a("transitionDuration"),transitionDelay:a("transitionDelay"),transformOrigin:a("transformOrigin"),transitionProperty:a("transitionProperty")}),b.offset=function(a){for(var b=-a.offsetLeft,c=-a.offsetTop;a=a.offsetParent;)b-=a.offsetLeft,c-=a.offsetTop;return{left:b,top:c}},b.preventDefaultException=function(a,b){for(var c in b)if(b[c].test(a[c]))return!0;return!1},b.extend(b.eventType={},{touchstart:1,touchmove:1,touchend:1,mousedown:2,mousemove:2,mouseup:2,pointerdown:3,pointermove:3,pointerup:3,MSPointerDown:3,MSPointerMove:3,MSPointerUp:3}),b.extend(b.ease={},{quadratic:{style:"cubic-bezier(0.25, 0.46, 0.45, 0.94)",fn:function(a){return a*(2-a)}},circular:{style:"cubic-bezier(0.1, 0.57, 0.1, 1)",fn:function(a){return Math.sqrt(1- --a*a)}},back:{style:"cubic-bezier(0.175, 0.885, 0.32, 1.275)",fn:function(a){var b=4;return(a-=1)*a*((b+1)*a+b)+1}},bounce:{style:"",fn:function(a){return(a/=1)<1/2.75?7.5625*a*a:2/2.75>a?7.5625*(a-=1.5/2.75)*a+.75:2.5/2.75>a?7.5625*(a-=2.25/2.75)*a+.9375:7.5625*(a-=2.625/2.75)*a+.984375}},elastic:{style:"",fn:function(a){var b=.22,c=.4;return 0===a?0:1==a?1:c*Math.pow(2,-10*a)*Math.sin(2*(a-b/4)*Math.PI/b)+1}}}),b.tap=function(a,b){var c=document.createEvent("Event");c.initEvent(b,!0,!0),c.pageX=a.pageX,c.pageY=a.pageY,a.target.dispatchEvent(c)},b.click=function(a){var b,c=a.target;/(SELECT|INPUT|TEXTAREA)/i.test(c.tagName)||(b=document.createEvent("MouseEvents"),b.initMouseEvent("click",!0,!0,a.view,1,c.screenX,c.screenY,c.clientX,c.clientY,a.ctrlKey,a.altKey,a.shiftKey,a.metaKey,0,null),b._constructed=!0,c.dispatchEvent(b))},b}();b.prototype={_init:function(){this._initEvents()},_initEvents:function(a){var b=a?d.removeEvent:d.addEvent,c=this.options.bindToWrapper?this.wrapper:window;b(window,"orientationchange",this),b(window,"resize",this),this.options.click&&b(this.wrapper,"click",this,!0),this.options.disableMouse||(b(this.wrapper,"mousedown",this),b(c,"mousemove",this),b(c,"mousecancel",this),b(c,"mouseup",this)),d.hasPointer&&!this.options.disablePointer&&(b(this.wrapper,d.prefixPointerEvent("pointerdown"),this),b(c,d.prefixPointerEvent("pointermove"),this),b(c,d.prefixPointerEvent("pointercancel"),this),b(c,d.prefixPointerEvent("pointerup"),this)),d.hasTouch&&!this.options.disableTouch&&(b(this.wrapper,"touchstart",this),b(c,"touchmove",this),b(c,"touchcancel",this),b(c,"touchend",this)),b(this.scroller,"transitionend",this),b(this.scroller,"webkitTransitionEnd",this),b(this.scroller,"oTransitionEnd",this),b(this.scroller,"MSTransitionEnd",this),"tab"===this.options.role&&(b(this.nav,"touchend",this),b(this.nav,"mouseup",this),b(this.nav,"pointerup",this))},refresh:function(){this.wrapper.offsetHeight;this.wrapperWidth=this.wrapper.clientWidth,this.wrapperHeight=this.wrapper.clientHeight;var a=window.getComputedStyle(this.wrapper,null),b=a["padding-top"].replace(/[^-\d.]/g,""),c=a["padding-bottom"].replace(/[^-\d.]/g,""),e=a["padding-left"].replace(/[^-\d.]/g,""),f=a["padding-right"].replace(/[^-\d.]/g,""),g=window.getComputedStyle(this.scroller,null),h=g["margin-top"].replace(/[^-\d.]/g,""),i=g["margin-bottom"].replace(/[^-\d.]/g,""),j=g["margin-left"].replace(/[^-\d.]/g,""),k=g["margin-right"].replace(/[^-\d.]/g,"");this.scrollerWidth=this.scroller.offsetWidth+parseInt(e)+parseInt(f)+parseInt(j)+parseInt(k),this.scrollerHeight=this.scroller.offsetHeight+parseInt(b)+parseInt(c)+parseInt(h)+parseInt(i),("slider"===this.options.role||"tab"===this.options.role)&&(this.itemWidth=this.scroller.children[0].clientWidth,this.scrollWidth=this.itemWidth*this.count,this.scrollerWidth=this.scrollWidth),this.maxScrollX=this.wrapperWidth-this.scrollerWidth,this.maxScrollY=this.wrapperHeight-this.scrollerHeight,this.hasHorizontalScroll=this.options.scrollX&&this.maxScrollX<0,this.hasVerticalScroll=this.options.scrollY&&this.maxScrollY<0,this.hasHorizontalScroll||(this.maxScrollX=0,this.scrollerWidth=this.wrapperWidth),this.hasVerticalScroll||(this.maxScrollY=0,this.scrollerHeight=this.wrapperHeight),this.endTime=0,this.directionX=0,this.directionY=0,this.wrapperOffset=d.offset(this.wrapper),this.resetPosition()},handleEvent:function(a){switch(a.type){case"touchstart":case"pointerdown":case"MSPointerDown":case"mousedown":this._start(a);break;case"touchmove":case"pointermove":case"MSPointerMove":case"mousemove":this._move(a);break;case"touchend":case"pointerup":case"MSPointerUp":case"mouseup":case"touchcancel":case"pointercancel":case"MSPointerCancel":case"mousecancel":this._end(a);break;case"orientationchange":case"resize":this._resize();break;case"transitionend":case"webkitTransitionEnd":case"oTransitionEnd":case"MSTransitionEnd":this._transitionEnd(a);break;case"wheel":case"DOMMouseScroll":case"mousewheel":this._wheel(a);break;case"keydown":this._key(a);break;case"click":a._constructed||(a.preventDefault(),a.stopPropagation())}},_start:function(a){if(!(1!=d.eventType[a.type]&&0!==a.button||!this.enabled||this.initiated&&d.eventType[a.type]!==this.initiated)){!this.options.preventDefault||d.isBadAndroid||d.preventDefaultException(a.target,this.options.preventDefaultException)||a.preventDefault();var b,c=a.touches?a.touches[0]:a;if(this.initiated=d.eventType[a.type],this.moved=!1,this.distX=0,this.distY=0,this.directionX=0,this.directionY=0,this.directionLocked=0,this._transitionTime(),this.startTime=d.getTime(),this.options.useTransition&&this.isInTransition&&"slider"!==this.options.role&&"tab"!==this.options.role?(this.isInTransition=!1,b=this.getComputedPosition(),this._translate(Math.round(b.x),Math.round(b.y))):!this.options.useTransition&&this.isAnimating&&(this.isAnimating=!1),this.startX=this.x,this.startY=this.y,this.absStartX=this.x,this.absStartY=this.y,this.pointX=c.pageX,this.pointY=c.pageY,this.options.autoplay){var e=this;clearTimeout(this.options.flag),this.options.flag=setTimeout(function(){e._autoplay.apply(e)},e.options.interval)}event.stopPropagation()}},_move:function(b){if(this.enabled&&d.eventType[b.type]===this.initiated){this.options.preventDefault&&b.preventDefault();var c,e,f,g,h=b.touches?b.touches[0]:b,i=h.pageX-this.pointX,j=h.pageY-this.pointY,k=d.getTime();if(this.pointX=h.pageX,this.pointY=h.pageY,this.distX+=i,this.distY+=j,f=Math.abs(this.distX),g=Math.abs(this.distY),!(k-this.endTime>300&&10>f&&10>g)){if(this.directionLocked||this.options.freeScroll||(this.directionLocked=f>g+this.options.directionLockThreshold?"h":g>=f+this.options.directionLockThreshold?"v":"n"),"h"==this.directionLocked){if("tab"===this.options.role&&a(this.scroller).children("li").height("auto"),"vertical"==this.options.eventPassthrough)b.preventDefault();else if("horizontal"==this.options.eventPassthrough)return void(this.initiated=!1);j=0}else if("v"==this.directionLocked){if("horizontal"==this.options.eventPassthrough)b.preventDefault();else if("vertical"==this.options.eventPassthrough)return void(this.initiated=!1);i=0}i=this.hasHorizontalScroll?i:0,j=this.hasVerticalScroll?j:0,c=this.x+i,e=this.y+j,(c>0||c<this.maxScrollX)&&(c=this.options.bounce?this.x+i/3:c>0?0:this.maxScrollX),(e>0||e<this.maxScrollY)&&(e=this.options.bounce?this.y+j/3:e>0?0:this.maxScrollY),this.directionX=i>0?-1:0>i?1:0,this.directionY=j>0?-1:0>j?1:0,this.moved=!0,this._translate(c,e),k-this.startTime>300&&(this.startTime=k,this.startX=this.x,this.startY=this.y)}}},_end:function(b){if(this.enabled&&d.eventType[b.type]===this.initiated){this.options.preventDefault&&!d.preventDefaultException(b.target,this.options.preventDefaultException)&&b.preventDefault();var c,e,f=(b.changedTouches?b.changedTouches[0]:b,d.getTime()-this.startTime),g=Math.round(this.x),h=Math.round(this.y),i=Math.abs(g-this.startX),j=(Math.abs(h-this.startY),0),k="";if(this.isInTransition=0,this.initiated=0,this.endTime=d.getTime(),this.resetPosition(this.options.bounceTime))return void("tab"===this.options.role&&a(this.scroller.children[this.currentPage]).siblings("li").height(0));if(this.scrollTo(g,h),this.moved||(this.options.tap&&1===d.eventType[b.type]&&d.tap(b,this.options.tap),this.options.click&&d.click(b)),this.options.momentum&&300>f&&(c=this.hasHorizontalScroll?d.momentum(this.x,this.startX,f,this.maxScrollX,this.options.bounce?this.wrapperWidth:0,this.options.deceleration):{destination:g,duration:0},e=this.hasVerticalScroll?d.momentum(this.y,this.startY,f,this.maxScrollY,this.options.bounce?this.wrapperHeight:0,this.options.deceleration):{destination:h,duration:0},g=c.destination,h=e.destination,j=Math.max(c.duration,e.duration),this.isInTransition=1),g!=this.x||h!=this.y)return(g>0||g<this.maxScrollX||h>0||h<this.maxScrollY)&&(k=d.ease.quadratic),void this.scrollTo(g,h,j,k);if("tab"===this.options.role&&a(event.target).closest("ul").hasClass("ui-tab-nav")){a(this.nav).children().removeClass("current"),a(event.target).addClass("current");var l=this.currentPage;this.currentPage=a(event.target).index(),a(this.scroller).children().height("auto"),this._execEvent("beforeScrollStart",l,this.currentPage)}("slider"===this.options.role||"tab"===this.options.role)&&(30>i?this.scrollTo(-this.itemWidth*this.currentPage,0,this.options.bounceTime,this.options.bounceEasing):g-this.startX<0?(this._execEvent("beforeScrollStart",this.currentPage,this.currentPage+1),this.scrollTo(-this.itemWidth*++this.currentPage,0,this.options.bounceTime,this.options.bounceEasing)):g-this.startX>=0&&(this._execEvent("beforeScrollStart",this.currentPage,this.currentPage-1),this.scrollTo(-this.itemWidth*--this.currentPage,0,this.options.bounceTime,this.options.bounceEasing)),"tab"===this.options.role&&a(this.scroller.children[this.currentPage]).siblings("li").height(0),this.indicator&&i>=30?(a(this.indicator).children().removeClass("current"),a(this.indicator.children[this.currentPage]).addClass("current")):this.nav&&i>=30&&(a(this.nav).children().removeClass("current"),a(this.nav.children[this.currentPage]).addClass("current")),a(this.scroller).children().removeClass("current"),a(this.scroller.children[this.currentPage]).addClass("current"))}},_resize:function(){var a=this;clearTimeout(this.resizeTimeout),this.resizeTimeout=setTimeout(function(){a.refresh()},this.options.resizePolling)},_transitionEnd:function(a){a.target==this.scroller&&this.isInTransition&&(this._transitionTime(),this.resetPosition(this.options.bounceTime)||(this.isInTransition=!1,this._execEvent("scrollEnd",this.currentPage)))},destroy:function(){this._initEvents(!0)},resetPosition:function(a){var b=this.x,c=this.y;return a=a||0,!this.hasHorizontalScroll||this.x>0?b=0:this.x<this.maxScrollX&&(b=this.maxScrollX),!this.hasVerticalScroll||this.y>0?c=0:this.y<this.maxScrollY&&(c=this.maxScrollY),b==this.x&&c==this.y?!1:(this.scrollTo(b,c,a,this.options.bounceEasing),!0)},disable:function(){this.enabled=!1},enable:function(){this.enabled=!0},on:function(a,b){this._events[a]||(this._events[a]=[]),this._events[a].push(b)},off:function(a,b){if(this._events[a]){var c=this._events[a].indexOf(b);c>-1&&this._events[a].splice(c,1)}},_execEvent:function(a){if(this._events[a]){var b=0,c=this._events[a].length;if(c)for(;c>b;b++)this._events[a][b].apply(this,[].slice.call(arguments,1))}},scrollTo:function(a,b,c,e){e=e||d.ease.circular,this.isInTransition=this.options.useTransition&&c>0,!c||this.options.useTransition&&e.style?(("slider"===this.options.role||"tab"===this.options.role)&&(c=this.options.duration,this.scrollerStyle[d.style.transitionProperty]=d.style.transform),this.scrollerStyle[d.style.transitionTimingFunction]=e.style,this._transitionTime(c),this._translate(a,b)):this._animate(a,b,c,e.fn)},scrollToElement:function(a,b,c,e,f){if(a=a.nodeType?a:this.scroller.querySelector(a)){var g=d.offset(a);g.left-=this.wrapperOffset.left,g.top-=this.wrapperOffset.top,c===!0&&(c=Math.round(a.offsetWidth/2-this.wrapper.offsetWidth/2)),e===!0&&(e=Math.round(a.offsetHeight/2-this.wrapper.offsetHeight/2)),g.left-=c||0,g.top-=e||0,g.left=g.left>0?0:g.left<this.maxScrollX?this.maxScrollX:g.left,g.top=g.top>0?0:g.top<this.maxScrollY?this.maxScrollY:g.top,b=void 0===b||null===b||"auto"===b?Math.max(Math.abs(this.x-g.left),Math.abs(this.y-g.top)):b,this.scrollTo(g.left,g.top,b,f)}},_transitionTime:function(a){a=a||0,this.scrollerStyle[d.style.transitionDuration]=a+"ms",!a&&d.isBadAndroid&&(this.scrollerStyle[d.style.transitionDuration]="0.001s")},_translate:function(a,b){this.options.useTransform?this.scrollerStyle[d.style.transform]="translate("+a+"px,"+b+"px)"+this.translateZ:(a=Math.round(a),b=Math.round(b),this.scrollerStyle.left=a+"px",this.scrollerStyle.top=b+"px"),this.x=a,this.y=b},getComputedPosition:function(){var a,b,c=window.getComputedStyle(this.scroller,null);return this.options.useTransform?(c=c[d.style.transform].split(")")[0].split(", "),a=+(c[12]||c[4]),b=+(c[13]||c[5])):(a=+c.left.replace(/[^-\d.]/g,""),b=+c.top.replace(/[^-\d.]/g,"")),{x:a,y:b}},_animate:function(a,b,e,f){function g(){var m,n,o,p=d.getTime();return p>=l?(h.isAnimating=!1,h._translate(a,b),void(h.resetPosition(h.options.bounceTime)||h._execEvent("scrollEnd",this.currentPage))):(p=(p-k)/e,o=f(p),m=(a-i)*o+i,n=(b-j)*o+j,h._translate(m,n),void(h.isAnimating&&c(g)))}var h=this,i=this.x,j=this.y,k=d.getTime(),l=k+e;this.isAnimating=!0,g()},_autoplay:function(){var b=this,c=b.currentPage;b.currentPage=b.currentPage>=b.count-1?0:++b.currentPage,b._execEvent("beforeScrollStart",c,b.currentPage),"tab"===this.options.role&&(a(this.scroller).children().height("auto"),document.body.scrollTop=0),b.scrollTo(-b.itemWidth*b.currentPage,0,b.options.bounceTime,b.options.bounceEasing),b.indicator?(a(b.indicator).children().removeClass("current"),a(b.indicator.children[b.currentPage]).addClass("current"),a(b.scroller).children().removeClass("current"),a(b.scroller.children[b.currentPage]).addClass("current")):b.nav&&(a(b.nav).children().removeClass("current"),a(b.nav.children[b.currentPage]).addClass("current"),a(b.scroller).children().removeClass("current"),a(b.scroller.children[b.currentPage]).addClass("current")),b.options.flag=setTimeout(function(){b._autoplay.apply(b)},b.options.interval)}},window.fz=window.fz||{},window.frozen=window.frozen||{},window.fz.Scroll=window.frozen.Scroll=b,"function"==typeof define&&define(function(a,c,d){d.exports=window.fz})}(window.Zepto),!function(a){function b(b){return a.adaptObject(this,d,b,c,e,"tips")}var c='<div class="ui-poptips ui-poptips-<%=type%>"><div class="ui-poptips-cnt"><i></i><%=content%></div></div>',d={content:"",stayTime:1e3,type:"info",callback:function(){}},e=function(b,c,e){var f=this;this.element=a(b),this._isFromTpl=e,this.elementHeight=a(b).height(),this.option=a.extend(d,c),a(b).css({"-webkit-transform":"translateY(-"+this.elementHeight+"px)"}),setTimeout(function(){a(b).css({"-webkit-transition":"all .5s"}),f.show()},20)};e.prototype={show:function(){var b=this;b.element.trigger(a.Event("tips:show")),this.element.css({"-webkit-transform":"translateY(0px)"}),b.option.stayTime>0&&setTimeout(function(){b.hide()},b.option.stayTime)},hide:function(){var b=this;b.element.trigger(a.Event("tips:hide")),this.element.css({"-webkit-transform":"translateY(-"+this.elementHeight+"px)"}),setTimeout(function(){b._isFromTpl&&b.element.remove()},500)}},a.fn.tips=a.tips=b}(window.Zepto);
(function() {
var factory = function(){
	//标准处理日期，生成Date
	parseDateStr = function (date){
		var dateRE = /^(\d{4})(?:\-|\/)(\d{1,2})(?:\-|\/)(\d{1,2})(?:\s)(\d{1,2}):(\d{1,2}):(\d{1,2})$/;
		return dateRE.test(date) ? new Date(parseInt(RegExp.$1, 10), parseInt(RegExp.$2, 10) - 1, parseInt(RegExp.$3, 10),
				parseInt(RegExp.$4, 10), parseInt(RegExp.$5, 10), parseInt(RegExp.$6, 10)) : null;
	} 
	
	//优化价格显示
	priceDisplay = function(amount){
		if(amount<10000){
			return amount;
		}
		if(amount>=10000){
			return amount/10000+"万";
		}
	}
	//生成分享地址 herf为window.location.herf的值，shareFrom为分享动作来源入ibuy.html
	var shareHref = function(href,shareFrom){
		var href = href.substring(0,href.indexOf('domainname/')+11);
		var url = href+shareFrom;
		return url;
	}

	// 毫秒数转换时分秒(提供时分秒返回值，默认时分秒的显示方式)
	var millisecondToDate = function(msd) {
		if(msd >= 1000){
			var time = parseFloat(msd) / 1000;
			time = parseInt(time);
			if (null != time && "" != time) {
				var hours = mins = secs = 0;
				if (time > 60 && time < 60 * 60) {
					mins = parseInt(time / 60);
					secs = time % 60;//parseInt((parseFloat(time / 60.0) - parseInt(time / 60.0)) * 60);
					time = {
							day : 0,
							hours : 0,
							mins : mins,
							secs : secs
					}
				} else if (time >= 60 * 60) { // time >= 60 * 60 && time < 60 * 60 * 24
					hours = parseInt(time / 3600);
					time = time % 3600;//扣除小时数
					if(time != 0){
						mins = parseInt(time / 60);//parseInt((parseFloat(time / 3600.0) - parseInt(time / 3600.0)) * 60);
						time = time % 60;//扣除分钟数
						secs = time;//parseInt((parseFloat((parseFloat(time / 3600.0) - parseInt(time / 3600.0)) * 60) - parseInt((parseFloat(time / 3600.0) - parseInt(time / 3600.0)) * 60)) * 60);
					}else{
						mins = 0;
						secs = 0;
					}
					day = parseInt(hours/24);
					hours = hours%24;
					time = {
							day : day,
							hours : hours,
							mins : mins,
							secs : secs
					}
//				if (mins == 0) {
//					time = {
//							hours : hours - 1,
//							mins : 60,
//							secs : secs
//					}
//				} else {
//					time = {
//							hours : hours,
//							mins : mins,
//							secs : secs
//					}
//				}
				} else {
					secs = parseInt(time);
					time = {
							day : 0,
							hours : 0,
							mins : 0,
							secs : secs
					}
				}
			}
			
		}else{
			time = {
					day : 0,
					hours : 0,
					mins : 0,
					secs : 0
			}
		}
		
		var displayTime = (time.day? time.day+"天":"") + (time.hours < 10 ? ("0" + time.hours) : time.hours) + "时"
				+ (time.mins < 10 ? ("0" + time.mins) : time.mins) + "分"
				+ (time.secs < 10 ? ("0" + time.secs) : time.secs) + "秒";
		return {
			time : time,
			displayTime : displayTime
		}
	}
	
	var getScreenHeight = function () {
		return document.compatMode === "BackCompat" ? document.body.clientHeight : document.documentElement.clientHeight;
	}

	var loadmore = function(element, callback) {
		var self = $("#" + element);
		self.removeClass("nomore");
		self.removeClass("hide-loadmore");
		if (self.data("loadmore-init") == "true") {
			return;
		}
		self.data("loadmore-init", "true");
		self.data("loadmore-status", "ready");
		try {
			var parentPage = $(self).closest(".page");
			
			var checkload = function(e){
				if (self.hasClass("nomore")) {
					return true;
				}
				$(self).show();
				if (self.data("loadmore-status") != "ready") {
					return true;
				}
				self.data("loadmore-status", "checking");
				
				
				var item = self[0],
					eleTop = item.offsetTop,
					eleHeight = item.offsetHeight,
					winTop = parentPage.scrollTop(),//document.compatMode === "BackCompat" ? document.body.scrollTop : document.documentElement.scrollTop,
					winHeight = getScreenHeight();
	
				if(winTop === 0){ //修正chrome下取不到的問題
					winTop = document.body.scrollTop;
				}
				
				if (parentPage.data("scroll")) {
					var scl = $.m.Scroll(parentPage);
					winTop = -scl.scroller.y;
					winHeight = scl.scroller.wrapperH;
				}
//					console.log("eleTop:" + eleTop + ",eleHeight:" + eleHeight + ",winTop:" + winTop + ",winHeight:" + winHeight);
//					if (eleTop < winTop + winHeight && eleTop + eleHeight > winTop) {
				var merge = 10;
				merge = !$(self).hasClass("has-footer") ? merge : merge + $("footer").height();
				var delta = (eleTop) - (winTop + winHeight-merge);
				parentPage.find("#debug").html(
					"eleTop:" + eleTop + "<br>"+
					"merge:" + merge + "<br>"+
					"winTop:" + winTop + "<br>"+
					"winHeight:" + winHeight + "<br>"+
					"delta:" + delta + "<br>"+
					""
				);
				if (delta < 500) {
					if (!$(self).hasClass("nomore") && self.data("loadmore-status") == "checking") {
						self.data("loadmore-status", "loading");
						setTimeout(function(){
								callback(function(type){
									if(type === "warn") {
										$(self).addClass("nomore");
									} else {
										//$(self).hide();
									}
									//$(self).hide();
									self.data("loadmore-status", "ready");
								});
						}, 1);
					} else if (self.data("loadmore-status") == "checking"){
						self.data("loadmore-status", "ready");
					}
				} else {
					self.data("loadmore-status", "ready");
				}
				
				return true;
			}
			
			//var eventName = "touchmove";
			//if (parentPage.data("scroll")) {
			//	eventName = "scrollEnd";
			//	parentPage.on(eventName, checkload);
			//} else {
				setInterval(checkload, 1000);
			//}
		} catch (e) {
			alert(e);
		}
	}

	var getUrlParam = function(name) {
		var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		var value = "";
		if(r != null) {
			value = unescape(r[2]);
		}
		return value;
	}
	
	var jscache = {
		cache : {},
		getV : function(key) {
			return jscache.cache[key];
		},
		setV : function (key, value) {
			jscache.cache[key] = value;
		},
		clear : function() {
			jscache.cache = {};
		}
	}

	return {
		millisecondToDate : millisecondToDate,
		loadmore : loadmore,
		getUrlParam : getUrlParam,
		shareHref : shareHref,
		priceDisplay:priceDisplay,
		parseDateStr : parseDateStr,
		getScreenHeight: getScreenHeight,
		jscache : jscache
	}
};

	if (typeof window.define == "function") {
		if (define.amd || define.cmd) {
			define("utils", factory);
		}
	} else {
		window.utils = factory();
	}
})();!function(a,b){"function"==typeof define&&(define.amd||define.cmd)?define(function(){return b(a)}):b(a,!0)}(this,function(a,b){function c(b,c,d){a.WeixinJSBridge?WeixinJSBridge.invoke(b,e(c),function(a){g(b,a,d)}):j(b,d)}function d(b,c,d){a.WeixinJSBridge?WeixinJSBridge.on(b,function(a){d&&d.trigger&&d.trigger(a),g(b,a,c)}):d?j(b,d):j(b,c)}function e(a){return a=a||{},a.appId=z.appId,a.verifyAppId=z.appId,a.verifySignType="sha1",a.verifyTimestamp=z.timestamp+"",a.verifyNonceStr=z.nonceStr,a.verifySignature=z.signature,a}function f(a){return{timeStamp:a.timestamp+"",nonceStr:a.nonceStr,"package":a.package,paySign:a.paySign,signType:a.signType||"SHA1"}}function g(a,b,c){var d,e,f;switch(delete b.err_code,delete b.err_desc,delete b.err_detail,d=b.errMsg,d||(d=b.err_msg,delete b.err_msg,d=h(a,d,c),b.errMsg=d),c=c||{},c._complete&&(c._complete(b),delete c._complete),d=b.errMsg||"",z.debug&&!c.isInnerInvoke&&alert(JSON.stringify(b)),e=d.indexOf(":"),f=d.substring(e+1)){case"ok":c.success&&c.success(b);break;case"cancel":c.cancel&&c.cancel(b);break;default:c.fail&&c.fail(b)}c.complete&&c.complete(b)}function h(a,b){var d,e,f,g;if(b){switch(d=b.indexOf(":"),a){case o.config:e="config";break;case o.openProductSpecificView:e="openProductSpecificView";break;default:e=b.substring(0,d),e=e.replace(/_/g," "),e=e.replace(/\b\w+\b/g,function(a){return a.substring(0,1).toUpperCase()+a.substring(1)}),e=e.substring(0,1).toLowerCase()+e.substring(1),e=e.replace(/ /g,""),-1!=e.indexOf("Wcpay")&&(e=e.replace("Wcpay","WCPay")),f=p[e],f&&(e=f)}g=b.substring(d+1),"confirm"==g&&(g="ok"),"failed"==g&&(g="fail"),-1!=g.indexOf("failed_")&&(g=g.substring(7)),-1!=g.indexOf("fail_")&&(g=g.substring(5)),g=g.replace(/_/g," "),g=g.toLowerCase(),("access denied"==g||"no permission to execute"==g)&&(g="permission denied"),"config"==e&&"function not exist"==g&&(g="ok"),b=e+":"+g}return b}function i(a){var b,c,d,e;if(a){for(b=0,c=a.length;c>b;++b)d=a[b],e=o[d],e&&(a[b]=e);return a}}function j(a,b){if(z.debug&&!b.isInnerInvoke){var c=p[a];c&&(a=c),b&&b._complete&&delete b._complete,console.log('"'+a+'",',b||"")}}function k(){if(!("6.0.2">w||y.systemType<0)){var b=new Image;y.appId=z.appId,y.initTime=x.initEndTime-x.initStartTime,y.preVerifyTime=x.preVerifyEndTime-x.preVerifyStartTime,C.getNetworkType({isInnerInvoke:!0,success:function(a){y.networkType=a.networkType;var c="https://open.weixin.qq.com/sdk/report?v="+y.version+"&o="+y.isPreVerifyOk+"&s="+y.systemType+"&c="+y.clientVersion+"&a="+y.appId+"&n="+y.networkType+"&i="+y.initTime+"&p="+y.preVerifyTime+"&u="+y.url;b.src=c}})}}function l(){return(new Date).getTime()}function m(b){t&&(a.WeixinJSBridge?b():q.addEventListener&&q.addEventListener("WeixinJSBridgeReady",b,!1))}function n(){C.invoke||(C.invoke=function(b,c,d){a.WeixinJSBridge&&WeixinJSBridge.invoke(b,e(c),d)},C.on=function(b,c){a.WeixinJSBridge&&WeixinJSBridge.on(b,c)})}var o,p,q,r,s,t,u,v,w,x,y,z,A,B,C;if(!a.jWeixin)return o={config:"preVerifyJSAPI",onMenuShareTimeline:"menu:share:timeline",onMenuShareAppMessage:"menu:share:appmessage",onMenuShareQQ:"menu:share:qq",onMenuShareWeibo:"menu:share:weiboApp",previewImage:"imagePreview",getLocation:"geoLocation",openProductSpecificView:"openProductViewWithPid",addCard:"batchAddCard",openCard:"batchViewCard",chooseWXPay:"getBrandWCPayRequest"},p=function(){var b,a={};for(b in o)a[o[b]]=b;return a}(),q=a.document,r=q.title,s=navigator.userAgent.toLowerCase(),t=-1!=s.indexOf("micromessenger"),u=-1!=s.indexOf("android"),v=-1!=s.indexOf("iphone")||-1!=s.indexOf("ipad"),w=function(){var a=s.match(/micromessenger\/(\d+\.\d+\.\d+)/)||s.match(/micromessenger\/(\d+\.\d+)/);return a?a[1]:""}(),x={initStartTime:l(),initEndTime:0,preVerifyStartTime:0,preVerifyEndTime:0},y={version:1,appId:"",initTime:0,preVerifyTime:0,networkType:"",isPreVerifyOk:1,systemType:v?1:u?2:-1,clientVersion:w,url:encodeURIComponent(location.href)},z={},A={_completes:[]},B={state:0,res:{}},m(function(){x.initEndTime=l()}),C={config:function(a){z=a,j("config",a);var b=z.check===!1?!1:!0;m(function(){var a,d,e;if(b)c(o.config,{verifyJsApiList:i(z.jsApiList)},function(){A._complete=function(a){x.preVerifyEndTime=l(),B.state=1,B.res=a},A.success=function(){y.isPreVerifyOk=0},A.fail=function(a){A._fail?A._fail(a):B.state=-1};var a=A._completes;return a.push(function(){z.debug||k()}),A.complete=function(){for(var c=0,d=a.length;d>c;++c)a[c]();A._completes=[]},A}()),x.preVerifyStartTime=l();else{for(B.state=1,a=A._completes,d=0,e=a.length;e>d;++d)a[d]();A._completes=[]}}),z.beta&&n()},ready:function(a){0!=B.state?a():(A._completes.push(a),!t&&z.debug&&a())},error:function(a){"6.0.2">w||(-1==B.state?a(B.res):A._fail=a)},checkJsApi:function(a){var b=function(a){var c,d,b=a.checkResult;for(c in b)d=p[c],d&&(b[d]=b[c],delete b[c]);return a};c("checkJsApi",{jsApiList:i(a.jsApiList)},function(){return a._complete=function(a){if(u){var c=a.checkResult;c&&(a.checkResult=JSON.parse(c))}a=b(a)},a}())},onMenuShareTimeline:function(a){d(o.onMenuShareTimeline,{complete:function(){c("shareTimeline",{title:a.title||r,desc:a.title||r,img_url:a.imgUrl,link:a.link||location.href},a)}},a)},onMenuShareAppMessage:function(a){d(o.onMenuShareAppMessage,{complete:function(){c("sendAppMessage",{title:a.title||r,desc:a.desc||"",link:a.link||location.href,img_url:a.imgUrl,type:a.type||"link",data_url:a.dataUrl||""},a)}},a)},onMenuShareQQ:function(a){d(o.onMenuShareQQ,{complete:function(){c("shareQQ",{title:a.title||r,desc:a.desc||"",img_url:a.imgUrl,link:a.link||location.href},a)}},a)},onMenuShareWeibo:function(a){d(o.onMenuShareWeibo,{complete:function(){c("shareWeiboApp",{title:a.title||r,desc:a.desc||"",img_url:a.imgUrl,link:a.link||location.href},a)}},a)},startRecord:function(a){c("startRecord",{},a)},stopRecord:function(a){c("stopRecord",{},a)},onVoiceRecordEnd:function(a){d("onVoiceRecordEnd",a)},playVoice:function(a){c("playVoice",{localId:a.localId},a)},pauseVoice:function(a){c("pauseVoice",{localId:a.localId},a)},stopVoice:function(a){c("stopVoice",{localId:a.localId},a)},onVoicePlayEnd:function(a){d("onVoicePlayEnd",a)},uploadVoice:function(a){c("uploadVoice",{localId:a.localId,isShowProgressTips:0==a.isShowProgressTips?0:1},a)},downloadVoice:function(a){c("downloadVoice",{serverId:a.serverId,isShowProgressTips:0==a.isShowProgressTips?0:1},a)},translateVoice:function(a){c("translateVoice",{localId:a.localId,isShowProgressTips:0==a.isShowProgressTips?0:1},a)},chooseImage:function(a){c("chooseImage",{scene:"1|2",count:a.count||9,sizeType:a.sizeType||["original","compressed"]},function(){return a._complete=function(a){if(u){var b=a.localIds;b&&(a.localIds=JSON.parse(b))}},a}())},previewImage:function(a){c(o.previewImage,{current:a.current,urls:a.urls},a)},uploadImage:function(a){c("uploadImage",{localId:a.localId,isShowProgressTips:0==a.isShowProgressTips?0:1},a)},downloadImage:function(a){c("downloadImage",{serverId:a.serverId,isShowProgressTips:0==a.isShowProgressTips?0:1},a)},getNetworkType:function(a){var b=function(a){var c,d,e,b=a.errMsg;if(a.errMsg="getNetworkType:ok",c=a.subtype,delete a.subtype,c)a.networkType=c;else switch(d=b.indexOf(":"),e=b.substring(d+1)){case"wifi":case"edge":case"wwan":a.networkType=e;break;default:a.errMsg="getNetworkType:fail"}return a};c("getNetworkType",{},function(){return a._complete=function(a){a=b(a)},a}())},openLocation:function(a){c("openLocation",{latitude:a.latitude,longitude:a.longitude,name:a.name||"",address:a.address||"",scale:a.scale||28,infoUrl:a.infoUrl||""},a)},getLocation:function(a){a=a||{},c(o.getLocation,{type:a.type||"wgs84"},function(){return a._complete=function(a){delete a.type},a}())},hideOptionMenu:function(a){c("hideOptionMenu",{},a)},showOptionMenu:function(a){c("showOptionMenu",{},a)},closeWindow:function(a){a=a||{},c("closeWindow",{immediate_close:a.immediateClose||0},a)},hideMenuItems:function(a){c("hideMenuItems",{menuList:a.menuList},a)},showMenuItems:function(a){c("showMenuItems",{menuList:a.menuList},a)},hideAllNonBaseMenuItem:function(a){c("hideAllNonBaseMenuItem",{},a)},showAllNonBaseMenuItem:function(a){c("showAllNonBaseMenuItem",{},a)},scanQRCode:function(a){a=a||{},c("scanQRCode",{needResult:a.needResult||0,scanType:a.scanType||["qrCode","barCode"]},function(){return a._complete=function(a){var b,c;v&&(b=a.resultStr,b&&(c=JSON.parse(b),a.resultStr=c&&c.scan_code&&c.scan_code.scan_result))},a}())},openProductSpecificView:function(a){c(o.openProductSpecificView,{pid:a.productId,view_type:a.viewType||0},a)},addCard:function(a){var e,f,g,h,b=a.cardList,d=[];for(e=0,f=b.length;f>e;++e)g=b[e],h={card_id:g.cardId,card_ext:g.cardExt},d.push(h);c(o.addCard,{card_list:d},function(){return a._complete=function(a){var c,d,e,b=a.card_list;if(b){for(b=JSON.parse(b),c=0,d=b.length;d>c;++c)e=b[c],e.cardId=e.card_id,e.cardExt=e.card_ext,e.isSuccess=e.is_succ?!0:!1,delete e.card_id,delete e.card_ext,delete e.is_succ;a.cardList=b,delete a.card_list}},a}())},chooseCard:function(a){c("chooseCard",{app_id:z.appId,location_id:a.shopId||"",sign_type:a.signType||"SHA1",card_id:a.cardId||"",card_type:a.cardType||"",card_sign:a.cardSign,time_stamp:a.timestamp+"",nonce_str:a.nonceStr},function(){return a._complete=function(a){a.cardList=a.choose_card_info,delete a.choose_card_info},a}())},openCard:function(a){var e,f,g,h,b=a.cardList,d=[];for(e=0,f=b.length;f>e;++e)g=b[e],h={card_id:g.cardId,code:g.code},d.push(h);c(o.openCard,{card_list:d},a)},chooseWXPay:function(a){c(o.chooseWXPay,f(a),a)}},b&&(a.wx=a.jWeixin=C),C});