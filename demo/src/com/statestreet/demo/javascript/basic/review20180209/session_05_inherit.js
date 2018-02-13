console.info('#################### 原型继承  ######################');
//js中怎么实现继承  ：采用原型链的概念
function Person(){
	
}

console.info(Person.prototype.constructor == Person);//true

var p = new Person();
console.info(Person.prototype.isPrototypeOf(p));//true

//
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

console.info('#################### 继承的三种方式  ######################');
//原型继承 请看前面一章
//原型继承的特点 即继承了父类的模板  又继承了父类的原型对象  


/////类继承  只继承魔板  不继承原型对象  借用构造函数的方式继承
function Person(name,age){
	this.name = name;
	this.age = age;
}

Person.prototype.id = 10;

function Boy(name,age,sex){
	Person.call(this,name,age);
	this.sex = sex;
}

var b = new Boy('zs',20,'femal');
console.info(b.name);
console.info(b.sex);
//不继承原型对象 只继承模板
console.info(b.id);


//原型继承 + 借用构造函数继承=混合继承
function Person2(name,age){
	this.name = name;
	this.age = age;
}

Person2.prototype.id = 10;
Person2.prototype.sayName = function(){
	alert(this.name);
};

function Boy2(name,age,sex){
	Person2.call(this,name,age);//1.借用构造函数
	this.sex = sex;
}


//2原型对象
Boy2.prototype = new Person2();

var b2 = new Boy2('zs',20,'femal');
console.info(b2.id);

console.info('#################### extjs 继承实现  ######################');
