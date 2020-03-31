/**
 * @author Administrator
 */
/////js中怎么实现继承  ：采用原型链的概念
function Person(){
	
}

console.info(Person.prototype.constructor == Person);//true
alert(Person.prototype.constructor);
alert(Person);
var p = new Person();
console.info(Person.prototype.isPrototypeOf(p));//true

////////
function Sup(name){
	this.name = name;
}

Sup.prototype = {
	constructor: Sup,
	say : function(){
		console.info(this.name);
	}
};

function Sub(age){
	this.age = age;
}

//让原型对象等于另外一个对象的实例(子类的原型等于另外父类实例) js 继承
Sub.prototype = new Sup('father');
var s1 = new Sub(); 
console.info(s1.name);
s1.say();
alert(Sub.prototype.constructor);

