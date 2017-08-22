/**
 * @author Administrator
 */
//3种方式建函数
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
	// var test = new Function();//不占用内存 ，方法体结束了之后自动销毁，所以每次到这行都要重新建方法,动态
}

var t2 = new Date().getTime(); 
console.info(t2-t1);

console.info('--------顺序对比---------');


f1();//执行成功，f1会优先解析
function f1(){
	//alert('t111');
}

//f2();//执行出错,放前面不行了，f2是顺序解析 还没解析到定义函数的地方就调用了就出错了
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





