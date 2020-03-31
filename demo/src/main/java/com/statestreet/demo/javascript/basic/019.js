/**
 * @author Administrator
 */

//Obejct.getPrototypeOf()

function Person(name){

}

Person.prototype.name='zs';
Person.prototype.sayName=function(){
	alert('我是原型对象的方法！');
};


var p1 = new Person();
//alert(p1.name);

var prototypeObj = Object.getPrototypeOf(p1);

console.info(Person.prototype == prototypeObj);

//判断一个对象属性 判断是属于原型属性还是属于实例属性

var p2 = new Person();
//p2.name='zl';
console.info(p2.name);
console.info(p2.hasOwnProperty('name'));

//in 操作符: for in 
//in 操作符  判断属性是否存在原型对象或者实例对象中
var p3 = new Person();
console.info('name' in p3);//true
var p4 = new Person();

//判断一个属性是否存在在原型当中
function hasPrototypeProperty(object,name){
	return !object.hasOwnProperty(name) && name in object;
}


//ECMA5 新特性    Object.keys(),返回所有对象在keys数组

var p4 = new Person();
p4.age = 20;
var atts = Object.keys(p4);
console.info(atts);
console.info(Object.keys(Person.prototype));

//ECMA5
//Object.getOwnPropertyNames(); 枚举所有属性 不管该内部属性是否能被枚举
console.info(Object.getOwnPropertyNames(Person.prototype));

