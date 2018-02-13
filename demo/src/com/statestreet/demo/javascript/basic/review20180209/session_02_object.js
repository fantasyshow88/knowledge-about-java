#Object***********************************************
1. 每个Objecy都有如下属性和方法
-constructor 构造
-hasOwnProperty(propertyName):检测在多年前实例对象中是否存在（不在原型中）
-isPropertyOf(Object):检测是否是原型
—propertyIsEnumerable(properyName):该属性是否能for-in 语句来枚举
-toLocaleString():返回对象的字符串表示。该字符与执行环境地区对应
-toString:返回对象字符串表示
-valueOf

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

//删除属性
delete obj.age;

//iterate obj
for(var attribute in obj){
	console.log(attribute + " == " + obj[attribute]);
}

//methods in object
console.log(obj.constructor);

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

2.其他引用对象
//Global 全局的 终极的  兜底的对象(不存在)
var uri = 'http://www.statestr.com. cn';

//（://） 不会编码
var str1 = encodeURI(uri);

//常用方法， 任何不标准的文字都会转码 编码
var str2 = encodeURIComponent(uri);

console.info(str1);
console.info(str2);

//解码
console.info(decodeURI(str1));
console.info(decodeURIComponent(str2));

//eval 无形的 Javascript 解析器
var str3 = 'var a = 10;var b =20;';
eval(str3);
console.info(a+b);

//response.write(str4) str4-->js 对象
//数组字符串直接使用 eval(str4)
var arr = eval("['a','b',2]");
console.info(arr[1]);
//处理对象
var str4 = "{name:'zhsan',age:30}";
var obj = eval('(' + str4 + ')');//括号 封闭的执行代码赋值给 obj 
console.info(obj['name']);

//parseInt
var num1 = parseInt('30');
var num2 = parseFloat('20.5');
console.info(typeof num1 + " " + typeof num2);

//escape unescape uri
var str5 = '你好2';
var un = escape(str5);
console.info(un);
console.info(unescape(un));
console.info(escape('http://  aa /'));

//isNaN
console.info(isNaN('1a'));
console.info(isNaN('1'));
//在 js里面只有 NaN 自己不等以自己本身的
console.info(NaN === NaN);//false

//Math

//Date
var d = new Date();
console.info(d);
console.info(d.getYear());
console.info(d.getFullYear());
console.info(d.getTime());

//基本包装类型Boolean String Number

//Function RegExp
