/**
 * @author Administrator
 */
var obj = new Object();
obj.name='zs';
obj.sex ='femal';
obj.sayHello=function(){
	//...
	
};



//第一种形式 工厂模型
function createPerson(name,sex){
	var o = new Object();
	o.name = name;
	o.sex=sex;
	return o;
}

var p1 = createPerson('zs','male');
console.info(p1.sex);


//第二种 构造函数式 
function Person(name){
	this.name = name;
	this.sayName = function(){
		alert(this.name);
	};
}

var p1 = new Person('zs');

var p2 = new Person('ls');

console.info(p1.constructor == Person);
console.info(p2.constructor == Person);
console.info(p1.constructor);
console.info(p1 instanceof Person);
console.info(p1 instanceof Object);


//创建对象的方式
//1当构造函数使用
var p3 = new Person('zs');

//2 当成普通函数使用
Person('zs');//在全局函数 window 定义  	直接定义在window上
console.info(name);

//3 在另一个对象中使用
var o = new Object();
Person.call(o,'ls');
console.info(o.name);





