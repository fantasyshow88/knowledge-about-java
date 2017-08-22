/**
 * @author Administrator
 */
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
console.info(p1.friends);
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
