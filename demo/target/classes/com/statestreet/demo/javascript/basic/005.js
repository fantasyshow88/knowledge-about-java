/**
 * @author Administrator
 */

//初始化
//var obj = new Object();
var obj = {};

obj.name='zhangsan';
obj.age = 20;
obj.sex = 'female';
obj['birthday'] = '1980-04-17';
obj.say = function (){
	alert('hello world!');
	
};

//alert(obj.name);
//obj.say();

//console.info();
//删除属性

delete obj.age;
//delete obj.say;
/*
alert(obj.name);
alert(obj.age);
obj.say();*/


//iterate obj
for(var attribute in obj){
	console.log(attribute + "==" + obj[attribute]);
}

//methods in object
console.log(obj.constructor);
alert(obj.constructor);

var arr = [];
//alert(arr.constructor);
console.log(arr.constructor);

//hasOwnProperty
console.log(obj.hasOwnProperty('sex'));
console.log(obj.hasOwnProperty('sex1'));
//isProtoTypeOf

//propertyIsEnumerable
console.log(obj.propertyIsEnumerable('name'));
console.log(obj.propertyIsEnumerable('say'));
console.log(obj.propertyIsEnumerable('say1'));

//toLocalString
console.log(obj.toLocaleString());
alert(obj.toLocaleString());
