/**
 * @author Administrator
 */
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
//
//1
function fn(x){
	var t = x;//2
	return function(x){//3
		t += x;
		alert(t);
	};
}
var a = fn(50);
alert(a);
a(5);
a(10);

////
if(true){
	var v = 'test ';
}
alert(v);






