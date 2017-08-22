/**
 * @author Administrator
 */

//简单原型

function Person(){
	
}

Person.prototype = {
 name : 'zhangsan',
 say : function (){
 	console.info(this.name);
 }
};

console.info(Person.prototype.constructor);//Object

//////////////////////////
function Person2(){
	
}

console.info(Person2.prototype.constructor);//Person2


////////////////////
function Person3(){
	
}
Person3.prototype = {
 constructor : Person3,
 name : 'zhangsan',
 say : function (){
 	console.info(this.name);
 }
};
var p3 = new Person3();
console.info(Person3.prototype.constructor);//Person3
console.info(p3.constructor);

for(att in p3){
	console.info(att);//constructor 也输出了  可是不符合constructor 不能被枚举的特性
}

//ECMA5中给原型对象重新设置构造器的方法
//Object.defineProperty()
//3个参数  1：重设构造器对象  2：设置什么属性 3：options 配置项
function Person4(){
	
}
Person4.prototype = {
 name : 'zhangsan',
 say : function (){
 	console.info(this.name);
 }
};
Object.defineProperty(Person4.prototype,'constructor',{
	enumerable:false,
	value : Person4
});

var p4 = new Person4();
console.info(p4.constructor);
console.info(Person4.prototype.constructor);
console.info(Object.getOwnPropertyNames(Person4.prototype));//不管是否能被枚举都会枚举出来
console.info('-----------------------');
for(at in p4){
	console.info(at);//不会枚举出 constructor 属性
}


//原型的动态特性
function Person5(){
	
}
var p5 = new Person5();
//原型对象的构造器默认为Person5,
Person5.prototype.say = function(){
	console.info('我是方法');
};
p5.say();//我是方法
console.info(Object.getOwnPropertyNames(Person5.prototype));

//////////
//注意简单原型使用顺序 实例对象必须在原型对象后创建
function Person6(){
	
}
var p6 = new Person6();
Person6.prototype = {
	constructor:Person6,
	say : function(){
		alert('i am a method');
	}
};
p6.say();//error
