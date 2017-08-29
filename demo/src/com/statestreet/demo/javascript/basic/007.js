/**
 * @author Administrator
 */
var arr = [123,2,1,3,2,1,5,2,4,5,10,13];

//js 对象特性, key在js对象中不会 重复

var obj = new Object();

obj.name = 'zhangsan';

function deleteDuplicatedElements(arr){
	var o = new Object();
	for(var i = 0;i<arr.length;i++){
		o[arr[i]] = '';	
	}
	var a = [];
	for(var attr in o){
		if(o.hasOwnProperty(attr)){
		 	a.push(attr);
		}
		
	}
	return a;
	
}

console.info(deleteDuplicatedElements(arr));


