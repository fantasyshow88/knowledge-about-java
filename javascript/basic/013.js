/**
 * @author Administrator
 */
//call apply 简单的用法：绑定一些函数  用于传递参数 调用

function sum(x,y){
	return x+y;
}

function call1(num1,num2){
	return sum.call(this,num1,num2);
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

showColor.call(this);//red
showColor.call(obj);//blue
showColor();//red
//call方法的简单模拟与实现
//....
