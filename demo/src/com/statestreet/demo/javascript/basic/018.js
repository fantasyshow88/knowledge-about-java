/**
 * @author Administrator
 */
//原型 prototype

//构造函数

function Person(name){
	this.name = name;
	this.sayName = function(){
		alert(this.name);
	};
}

var p1 = new Person('zs');
var p2 = new Person('ls');
console.info(p1.sayName == p2.sayName);//false
console.info(p1.name == p2.name);//false

//prototype 创建每一个函数都有一个该属性，这个属性是一个指针，而这个指针总是指向一个对象
//这个对象的用途是将指定特定的属性和方法在内，起到一个所有实例所共享的作用

function People(){
	
}

var obj = People.prototype;
alert(obj);

obj.age = 2;
obj.sayName = function(){
	alert(this.name);
};

var p3 = new People();
var p4 = new People();

console.info(p3.name == p4.name);
// alert(People.constructor);
console.info(obj.isPrototypeOf(p3));
console.info(p3.constructor);

//实例构造函数等于prototype对象的构造函数
console.info(p3.constructor == obj.constructor);
console.info(p3.__proto__ == People.prototype);//true
console.info(People == p3.constructor);
console.info(p3);


