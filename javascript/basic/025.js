/**
 * @author Administrator
 */

//只继承父类的原型对象  不继承模板
function extend (sub,sup) {
	var tempFun = function(){};
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


//////2件事 ： 继承一次父类的模板  继承一次父类的原型对象
//extend 方法


