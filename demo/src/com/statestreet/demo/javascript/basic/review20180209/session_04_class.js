console.info('############### 面向对象程序设计 ###################');
--了解面向对象程序特点
--学会js模拟面向对象方式
--理解prototype
--理解继承

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
console.info(p1 instanceof Person);//true
console.info(p1 instanceof Object);//true

//创建对象的方式
//1当构造函数使用
var p3 = new Person('zs');

//2 当成普通函数使用
Person('zs');//在全局函数 window 定义  	直接定义在window上
console.info(name);

//3 在另一个对象的作用域中使用
var o = new Object();
Person.call(o,'ls');
console.info(o.name);

console.info('#################### 原型  ######################');

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
alert(obj.constructor);

obj.age = 2;
obj.sayName = function(){
	alert(this.name);
};

var p3 = new People();
var p4 = new People();

console.info(p3.name == p4.name);
// alert(People.constructor);
console.info(obj.isPrototypeOf(p3));
console.info(p3.constructor == obj.constructor);
console.info(p3.__proto__ == People.prototype);//true
//以下有如下关系(a和b变成一直可以循环下去了)
a.构造函数.prototype = 原型对象
b.原型对象.constructor = 构造函数
c.实例对象.__proto__ = 原型对象

console.info('#################### 原型常用方法  ######################');
//Object.getPrototypeOf() 根据实例对象获取原型对象
function Person(name){

}

Person.prototype.name='zs';
Person.prototype.sayName=function(){
	alert('我是原型对象的方法！');
};

var p1 = new Person();

var prototypeObj = Object.getPrototypeOf(p1);

console.info(Person.prototype == prototypeObj);

//判断一个对象属性 判断是属于原型属性还是属于实例属性 hasOwnProperty
var p2 = new Person();
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
var atts = Object.keys(p4);//不包含原型属性
console.info(atts);
console.info(Object.keys(Person.prototype));
//ECMA5
//Object.getOwnPropertyNames(); 枚举所有属性 不管该内部属性是否能被枚举
//constructor属性不能被枚举，getOwnPropertyNames可以枚举所有属性，不管能不能被枚举
console.info(Object.getOwnPropertyNames(Person.prototype));

console.info('#################### 原型实例演练  ######################');
var arr = [2,4,1,[4,3,[5]]];
console.info(arr.constructor);
var a = {};
console.info(a.constructor);
var b = 'a';
console.info(b.constructor);
console.info('aa'.constructor);
var c = 1;
console.info(c.constructor);
var d = 1.1;
console.info(c.constructor);
var e = true;
console.info(e.constructor);
var f = NaN;
console.info(f.constructor);
//ECMA5 forEach 只适合遍历一维数组
arr.forEach(function(item,index,array){
	console.info(item);
});

function t(){
	
}
//alert(t.constructor == Function);//true

//自己实现一个Array each 方法  遍历多维数组
Array.prototype.each = function(fn){
	try{
		this.i || (this.i = 0);
		if(this.length > 0 && fn.constructor == Function){
			while(this.i < this.length){
				var e = this[this.i];
				if(e.constructor == Array){
				//递归
				 e.each(fn);
				}else{
					//目的就是为了把当前元素传递给fn函数
					fn.call(e,e);
				}
				this.i ++;
			}
			
		}
		
	}catch(ex){
		//do something
	}	
};

arr.each(function(item){
	console.info(item);
});

console.info('#################### 简单原型  ######################');

function Person(){
	
}
//简单原型的实现
Person.prototype = {
 name : 'zhangsan',
 say : function (){
 	console.info(this.name);
 }
};
//应该是Person可是打印出了 Object，因为prototype直接被覆盖了
console.info(Person.prototype.constructor);

function Person2(){
	
}

console.info(Person2.prototype.constructor);//Person2

function Person3(){
	
}

Person3.prototype = {
 constructor : Person3,//必须表示原型对象的构造器
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


//原型的动态特性，可以比较person5 和 person6
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

console.info('#################### 原型对象的常用开发模式  ######################');

//原型对象的所有属性和方法  被所有构造函数实例化出来的对象所共享
function Person(){
	
}
Person.prototype = {
	constructor: Person,
	name : 'zs',
	age : 30,
	friends:['ls','ww'],
	say : function(){
		console.info('my name');
	}
};

var p1 = new Person();
var p2 = new Person();

p1.friends.push('zl');
//原型里的属性和方法被所有的对象共享
console.info(p1.friends);//缺点 ：就是 friends 影响了所有实例对象
console.info(p2.friends);

//组合使用原型和构造函数式(定义一个类 开发时常用) 组合模式
function Person2(name,age,friends){
	this.name = name;
	this.age = age;
	this.friends = friends;
	
}

Person2.prototype = {
	constructor: Person2,
	say : function (){
		console.info('say method');
	}
};
var p12 = new Person2('zs',20,['aa','bb']);
var p22 = new Person2('ls',30,['c','d']);
p12.friends.push('aaaaa');
console.info(p12.friends);
console.info(p22.friends);


//动态原型模式(为了让你的代码都封装到一起)
function Person3(name,age,friends){
	this.name = name;
	this.age = age;
	this.friends = friends;
	
	if(typeof this.say != 'function'){
		Person3.prototype.say = function(){
			console.info('say method');
		};
	}
}

//稳妥构造函数式 非常安全的环境中 1:没有公用属性 2.不能使用this对象
function Person4(name,age,friends){
	//创建一个要返回的对象
	var obj = new Object();
	//可以定义一个私有的变量和函数 private
	var name = name;
	
	obj.say = function (){
		alert(name);
	};
	return obj;
}
var p3 = new Person4('zs');
p3.say();//只能通过该函数访问 name属性
alert(p3.name);//undefined

