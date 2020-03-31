#函数
-概念
-定义方式
-参数
-this
-call apply
-执行环境 作用域链条
-垃圾收集 块级作用域
-closure(闭包)

console.info('----------三种方式构造函数----------');
//function 语句
//优先解析,函数作用域
function test1(){
	
}

//函数直接量
//顺序解析，函数作用域
var test2 = function(){
	
};

//function 构造函数
//顺序解析，具有顶级作用域，即使在函数中声明也和在函数外声明一样的效果
var test3 = new Function('a','b','return a+b;');
console.info(test3(1,2));

console.info('----------效率对比----------');
//效率对比
var t1 = new Date().getTime();
for(var i = 0;i<10000;i++){
	function test(){}//一直占用内存，下次申明就直接从内存中找，静态
	//所以当方法用完就不用的话就用 new Function
    var test = new Function();//不占用内存 ，方法体结束了之后自动销毁，所以每次到这行都要重新建方法,动态
}

var t2 = new Date().getTime(); 
console.info(t2-t1);

console.info('--------顺序对比---------');
f1();//执行成功，f1会优先解析
function f1(){
	alert('t111');
}

f2();//执行出错,放前面不行了，f2是顺序解析 还没解析到定义函数的地方就调用了就出错了
var f2 = function(){
	alert('t22222222');
};
console.info('------函数作用域------');
//函数作用域
var k = 10;

function f3(){
	var k = 2;
/*
	//函数作用域
	function f4(){
		return k;
	}*/

	//具有顶级作用域
	 var f4 = new Function('return k');
	console.info(f4());
}
f3();

console.info('############### arguments #####################');

function test(a,b,c,d){
	console.info(test.length);//形参个数
	
	//函数的实际参数，内部用一个数组接受函数的实际参数
	//arguments对象 可以访问参数的实际参数
	console.info(arguments.length);
	console.info(arguments[0]);
	console.info(arguments[1]);
	//arguments对象只能在函数内部使用
	
/*
	if(test.length === arguments.length){
		return a+b;
	}else{
		return '参数不正确';
	}*/
	
	//arguments对象用得最多的 还是做递归操作
	console.info(arguments.callee);
	//alert(arguments.callee);
	if(arguments.callee.length === arguments.length){
		return a+b;
	}else{
		return '参数不正确';
	}
	

}

console.info(test(10,20));


//arguemnts.callee 用法举例
function face(num){
	if(num <=1){ 
		return 1;
	}else{
		return num * face(num-1);
	}
}
console.info(face(3));

var f = face;
face = null;
console.info(f(3));//ERROR: face is not a function

console.info('-----arguments.callee 用途及优势-----');
function face2(num){
	if(num <=1){ 
		return 1;
	}else{
		console.info(arguments.callee);
		return num * arguments.callee(num-1);
	}
}

var f2 = face2;
face2 = null;
console.info(f2(3));//正常 6


console.info('############### this #####################');
//this 对象是指在运行期间基于执行环境绑定的
//this 总是指向调用者
var k = 10;

function test(){
	this.k = 2;
}
//window.test();
test();//执行环境是顶层作用域 故 this.k = 2 把顶层的 k =10 覆盖掉了
console.info(test.k);//undefined
// console.info(window.k);
console.info(k);//2

console.info('############### call / apply #####################');
#每一个函数包含两个非继承而来的方法：call, apply,用途都是在特点的作用域中调用函数，实际等于设置函数体
内this对象的值
#用途之一就是传递参数，但真正强大的地方在于扩充函数赖以生存的作用域。

//call apply 简单的用法：绑定一些函数  用于传递参数 调用
function sum(x,y){
	return x+y;
}
function call1(num1,num2){
	return sum.call(this,num1,num2);//将sum方法绑定到call1作用域中
}
	function apply1(num1,num2){
	return sum.apply(this,[num1,num2]);
}
console.info(call1(10,20));
console.info(apply1(23,4));

//扩充作用域
window.color  = 'red';
var obj = {color:'blue'};

function showColor(){
	console.info(this.color);
}
showColor.call(this);//red this指圈全局作用域
showColor.call(obj);//blue obj指该对象内的作用域
showColor();//red

//call方法的简单模拟与实现
//....

console.info('############### 执行环境 作用域链 #####################');
//执行环境
var color1 = 'blue';
function changeColor(){
	var color2 ='red';
	function swapColor(){
		var color3 = color2;
		color2 = color1;
		color1 = color3;
	}

	swapColor();	
}
changeColor();//作用域 window 第一个作用环境
console.info(color1);
//环境变量 可以一层一层向上追溯 可以访问它的上级环境


#js是一门具有自动垃圾收集机制的编程语言
//垃圾收集方法1 标记方法  2。引用计数
function test(){
	var a = 10;//刚开始标记为被使用
	var b = 20;
}
test();//执行完毕之后a和b又被标记了一次  没有被使用


//块级作用域的概念,js没有像java 那样 if for循环有块级作用域的概念
function t(){
	for(var i = 1;i<=5;i++){
		console.info(i);
	}
	alert(i);//6       故for循环后还不能垃圾回收 i *********************************************
}
t();
// alert(i);//undefined

//js : () 表示执行

function t2(){
	(function(){
		//执行完后i可以被垃圾回收掉
		for(var i = 1;i<=5;i++){
			console.info(i);
		}
	})();
	//alert(i);//undefined  循环结束后可以马上回收对象i
}
t2();

(function(){alert('hello world!')})();//直接执行了，防止内部变量对外部产生影响

console.info('############### 闭包 closure #####################');

var name = 'AA';
var obj = {
	name:'BB',
	getName : function(){
		return function(){
			return this.name;
		};
	}
};
// console.info(obj.getName);
console.info(obj.getName()());//AA
console.info(obj.getName().call(obj));
console.info('===============================');

var name = 'AA';
var obj2 = {
	name:'BB',
	getName : function(){
		return this.name;
	}
};
var f = obj2.getName;
//alert(obj.getName);
console.info(f());//AA
console.info(obj2.getName());//BB
console.info('===============================');

var obj = {
	name:'BB',
	getName : function(){
		var o = this;
		return function(){
			return o.name;
		};
	}
};

console.info(obj.getName()());//BB

//闭包  ： 一个函数可以访问另外一个函数作用域中的变量
//封闭性，private 起到一个可以保护变量的作用
//1级作用域
function fn(x){
	var t = x;//2级别作用域
	return function(x){//3作用域
		t += x;
		alert(t);
	};
}
var a = fn(50);
alert(a);
a(5);
a(10);

//f2就是闭包
function f1(){
　　　　var n=999;
　　　　function f2(){
　　　　　　alert(n); 
　　　　}
　　　　return f2;
}
var result=f1();
result(); // 999

