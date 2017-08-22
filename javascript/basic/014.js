/**
 * @author Administrator
 */
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



//垃圾收集方法1 标记方法  2。引用计数
function test(){
	var a = 10;//被使用
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

var a = 'aa';
function t3(){
	var a = 'bb';
	console.info(a);
	
}

t3();


