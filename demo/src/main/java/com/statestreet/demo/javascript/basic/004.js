//第一部分常用数组方法
var a = [];

a.push('a','n');
//alert(a);

a.pop();
//alert(a);

//pop push shift unshift


//concat

//join

//reverse

//sort

//slice splice


//第二部分Ecmascript5 中的方法

//foreach
var data = [1,2,3,4,5]; 
var sum = 0; 
data.forEach(function(value){ 
	//只使用了第一个参数（函数），调用的函数也只使用了第一个参数数组元素 
	 sum += value; 
	 });   
	 console.log(sum);//15 
	 console.log(data);// [1, 2, 3, 4, 5] 


//map
var data2 = [1,2,3,4,5];

var data2_1 = data2.map(function(v){
	return v*v;
});
console.log(data2_1);

//every
var data3 = [1,2,3,4,5];

var r3 = data3.every(function(v){
	return v <4;	
});

console.log(r3);

//some
var data4 = [1,2,3,4,5];

var r4 = data4.some(function(v){
	return v>4;
});
console.log(r4);

//filter
var data5 =[1,2,3,4,5];
var r5 = data5.filter(function(v){
	return v<4;
});
console.log(r5);


//reduece reduceRight 用得不多
var data = [1,2,3,4,5]; 
var sum = data.reduce(function(a,b){  return a+b; });   
var sum1 = data.reduce(function(a,b){  return a+b; },5);   
var min = data.reduce(function(a,b){  return (a<b)?a:b; });   
console.log(data); // [1, 2, 3, 4, 5] 
console.log(sum);// 15 console.log(sum1);// 20 console.log(min);// 1 
//sum中没有第二个参数，所以初始值为第一个数组元素，第一步1+2=3，第二步3+3=6... 最后得15
//sum1中有第二个参数，所以初始值为5，第一步5+1=6，第二步6+2=8... 最后得20

