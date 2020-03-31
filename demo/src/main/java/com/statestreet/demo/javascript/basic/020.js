/**
 * @author Administrator
 */
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
console.info(t.constructor == Function);//true

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
