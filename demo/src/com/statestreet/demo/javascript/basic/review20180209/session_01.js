1.了解js组成部分
页面交互设计的脚本语言，由三部分组成
--ECMAscript提供核心语言功能
	ECMAScript是一种由Ecma国际（前身为欧洲计算机制造商协会,英文名称是European Computer Manufacturers Association）通过ECMA-262标准化的脚本程序设计语言。
	这种语言在万维网上应用广泛，它往往被称为JavaScript或JScript，所以它可以理解为是javascript的一个标准,但实际上后两者是ECMA-262标准的实现和扩展。
-- 文档对象模型(DOM),提供访问和操作网页内容的方法和接口(DOM 元素)
-- 浏览器对象模型(BOM)提供与浏览器交互的方法和接口(Window,Screen,Location,Cookie...)

function setCookie(c_name,value,expiredays)
{
var exdate=new Date()
exdate.setDate(exdate.getDate()+expiredays)
document.cookie=c_name+ "=" +escape(value)+
((expiredays==null) ? "" : ";expires="+exdate.toGMTString())
}

1.1 javascript是一种可以和html混合使用的脚本语言，可以直接在浏览器中解释执行，边解释边执行，半自动化语言。
（计算机只认识二进制文件，因此所有的编程语言或脚本语言都需要进行底层的编译。）

1.2 hello world:
	<script>标记可以位于页面任何位置，一般定义在<head>标签中，type='java/script',scr='js path',defer,charset='UTF-8'

window.alert('hello world!');//浏览器对象(BOM)
document.write('hello world1');//文档对象DOM
console.info("hello"); //在 console页面显示,把内容打印到控制台，js很大调试用方便

#外部引入js
<script type='text/javascript' src='comm.js'>
//当外部引入js代码后，在此标签中的js代码不会执行。
alert('hello world!');
<script>

#js解析顺序是从上往下，除非延迟执行：可以延迟执行代码（当页面加载完毕之后再执行Window.onload）
<script type='text/javascript' defer='defer'>
alert('hello world');
</script>

2.变量
全局变量：方法外部或者方法内部没有var修饰的变量
局部变量：方法内部申明的变量

3.数据类型
基本数据类型：Number(10,NaN,infinity),Boolean,String,Undefined,Null
注意：
	小数为浮点数 不要做小数的相加操作(a + b == 0.3),肯定为 false，0.30000000000000004 <> 0.3

引用变量： Object类型(对象，数组，RegExp,Date...)

3.1. 变量的自动转化
var a =10;
var b = 10.0;
alert(a==b);//true

# == 表示可以经过自动转化，比较的是数值，=== 表示进过自动转化，先比较值再比较类型
var c = 1;
var d = true;
alert(a==b);//true,自动转化
alert(a===b)//false

4.引用类型

4.1 数组
var a = [];
a.push('a','n');
a.pop();
//pop push shift unshift
//concat
//join
//reverse
//sort
var a1 = [2,1,3,4];
var a2 = [10,2,5,1];
a2.sort();//1,10,2,5
function compare(v1,v2){
	if(v1 < v2){
		return -1;
	}else if(v1 > v2){
		return 1;
	}else{
		return 0;
	}
}
a2.sort(compare);
//slice splice

#第二部分Ecmascript5 中的方法
//foreach
var data = [1,2,3,4,5]; 
var sum = 0; 
data.forEach(function(value){ 
	//只使用了第一个参数（函数），调用的函数也只使用了第一个参数数组元素 
	 sum += value; 
});   
console.log(sum);//15 

//map
var data2 = [1,2,3,4,5];

var data2_1 = data2.map(function(v){
	return v*v;
});
console.log(data2_1);

//every
var data3 = [1,2,3,4,5];

var r3 = data3.every(function(v){
	return v <4;	
});

console.log(r3);

//some
var data4 = [1,2,3,4,5];
var r4 = data4.some(function(v){
	return v>4;
});
console.log(r4);

//filter
var data5 =[1,2,3,4,5];
var r5 = data5.filter(function(v){
	return v<4;
});
console.log(r5);

//reduece reduceRight 用得不多
var result = data.reduce(function(prev,cur,index,array){});

var data = [1,2,3,4,5]; 
var sum = data.reduce(function(a,b){  return a+b; });   
var sum1 = data.reduce(function(a,b){  return a+b; },5);   
var min = data.reduce(function(a,b){  return (a<b)?a:b; });   
console.log(data); // [1, 2, 3, 4, 5] 
console.log(sum);// 15 
console.log(sum1);// 20 
console.log(min);// 1 
//sum中没有第二个参数，所以初始值为第一个数组元素，第一步1+2=3，第二步3+3=6... 最后得15
//sum1中有第二个参数，所以初始值为5，第一步5+1=6，第二步6+2=8... 最后得20


 
