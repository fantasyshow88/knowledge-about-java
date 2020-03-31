/**
 * @author Administrator
 */

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
	
	//arguments对象用得最多的 还是做敌递归操作
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

//console.info(f(3));//ERROR: face is not a function

console.info('arguments.callee 用途及优势');
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



