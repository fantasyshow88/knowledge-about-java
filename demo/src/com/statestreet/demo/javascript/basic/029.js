/**
 * @author Administrator
 */
//函数链式编程
function Dog(){
	this.eat = function(){
		console.info('eating...');
		return this;
	};
	this.sleep = function(){
		console.info('sleep...');
		return this;
	};
}

var d = new Dog();
d.eat().sleep();

/////////////////
//console.info(Object.keys(Function.prototype));
//jquery 就是通过传递一个window对象  然后把里面的函数绑定到window, 然后在外面就可以直接用了,重点是学习新鲜的点
(function(win){
	
	function $_(){
		console.info('res');
	}
	win.$ = $_;
})(window);
$();//res

function A(){
	//alert('aaa');	
}
var a = new A();

console.info(this);//window
console.info(a.constructor);
console.info(A.prototype);
console.info(A.constructor);

var arr = [];
console.info(typeof arr.push);
var push = arr.push;
A.prototype.push = push;//A 对象具有数组的push方法 即有数组的push特性
A.prototype.pop = arr.pop;
var b = new A();
b.push('a');
b.push('b');
console.info(b);
b.pop();
console.info(b);

var arr = new Array();
arr.push('a');
arr.push('b');
console.info(arr);
///////////////////////////////

console.info(A.prototype);
console.info(a.__proto__ === A.prototype );//true  当向a对象找某个属性的时候 如果找不到就会去__proto__ 指向的  A.prototype 对象中找 再找不到则 undefined
/////////////////////////////////////

var Bird = function (name){//相反于 var Bird = new Function('name');
	this.name;
};
Bird.prototype.id='aa';
console.info(Bird);
console.info(Bird.prototype);
console.info(Bird.__proto__);
var bird = new Bird('bi');
console.info(bird.__proto__ ===Bird.prototype);

console.info(Bird.__proto__ === Function.prototype);
console.info(Bird.prototype.__proto__ === Object.prototype);
////////////////////////////////////////
Function.prototype.addFun = function(){
	console.info('addFun...');
};

function TT(){
	
}
//addFun...
TT.addFun();//因为TT 是 Function 类的一个实例对象  相当于 var TT = new Function(); 故  TT 实例拥有 Function 类的 prototype中 addFun 的方法

//////////
TT.prototype.addEvent = function(){
	alert('addEvent');
	return this;
};

TT.prototype.setStyle = function(){
	alert('setStyle');
	return this;
};

var t = new TT();
t.addEvent().setStyle();
////// 为某一个document 对象添加事件属性(Firefox)
//document.getElementById('id').addEventListener('click',function(){});
//IE
//document.getElementById('id').attachEvent('onclick',function(){});


//////////
//simulate jquery 省略...

