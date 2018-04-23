/**
 * @author Administrator
 */
//Global 全局的 终极的  兜底的对象(不存在)

var uri = 'http://www.bjsxt.com. cn';

//（://） 不会编码
var str1 = encodeURI(uri);

//常用方法 任何不标准的文字都会转码 编码
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
var str5 = '尚学堂2';
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
console.info('=========');
var d = new Date();
console.info(d);
console.info(d.getYear());
console.info(d.getFullYear());
console.info(d.getTime());

//基本包装类型Boolean String Number


//Function RegExp

