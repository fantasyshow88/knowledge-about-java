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


//原型继承 + 借用构造函数继承=混合继承 //继承两次父类模板
function Person2(name,age){
	this.name = name;
	this.age = age;
}

Person2.prototype.id = 10;
Person2.prototype.sayName = function(){
	alert(this.name);
};

function Boy2(name,age,sex){
	Person2.call(this,name,age);//1.借用构造函数 继承父类模板
	this.sex = sex;
}


//2原型对象
Boy2.prototype = new Person2();//继承父类模板
var b2 = new Boy2('zs',20,'femal');
console.info(b2.id);

console.info('#################### extjs 继承实现,经典的继承方式  ######################');
//只继承父类的原型对象  不继承模板
function extend (sub,sup) {
	var tempFun = function(){};//利用空函数进行中转
	tempFun.prototype = sup.prototype;
	
	sub.prototype = new tempFun();
	
	// alert(sub.prototype.constructor);
	//还原构造器
   sub.prototype.constructor = sub;
   
   //保存一下父类的原型对象  方便解耦和获取父类的原型对象
   sub.superClass = sup.prototype;//利用一个类的静态属性来保存  相对的动态属性是指 this.xxx 什么的

}
//混合继承
function Person(name,age){
	this.name = name;
	this.age = age;
}

Person.prototype = {
	constructor:Person,
	say : function(){
		alert('hello world');
	}
};

function Boy(name,age,sex){
	//Person.call(this,name,age);
	Boy.superClass.constructor.call(this,name,age);
	this.sex = sex;
}

//Boy.prototype = new Person(); //继承了模板和原型对象  其中模板就重复了
//怎么实现只继承一次父类的原型对象(模拟extjs的继承机制)
extend(Boy,Person);

var b = new Boy('zs',12,'femal');
console.info(b.name);
console.info(b.sex);
b.say();
//console.info(Boy.prototype.constructor);
//混合继承做了三件事  继承了父类的两次模板 (如果属性多性能就会变差)继承了一次父类的原型


//2件事 ： 继承一次父类的模板  继承一次父类的原型对象
//如上的extend 方法



