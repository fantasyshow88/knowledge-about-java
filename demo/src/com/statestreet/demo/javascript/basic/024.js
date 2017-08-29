/**
 * @author Administrator
 */
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


/////原型继承 + 借用构造函数继承=混合继承
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


