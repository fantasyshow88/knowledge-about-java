/**
 * @author Administrator
 */
//单体模式 （singleton）
/////1 简单单体
var singleton = {
	attr1: true,
	method1:function(){
	 alert('method1 executed!');	
	}
} ;

alert(singleton.attr1);

//划分命名空间的
var BGX = {};
BGX.Singleton = {
	attr2 : true,
	method2:function(){
		alert('method2 executed!');
	}
};

/////2.闭包单体 ： 借用闭包创建单体 ：闭包的主要作用是保护数据
//把块级作用域的对象赋值给单体
var BHX = {};
BHX.Singleton = (
	function(){
		var a1 = 10;
		var f1 = function(){
			alert('f1');
		};
		
		return{
			att1:a1,
			method1:function(){
				return f1();
			}
		};
	}
)();

alert(BHX.Singleton.att1);
BHX.Singleton.method1();

/////3.惰性单体
var Ext = {};//命名空间 只返回自己需要的那部分
Ext.Base = (function(){
	var uniqueInstance;
	function init(){
		var a = 1;
		var method = function(){
			alert('method executed!');		
		};
		return{
			attr1:a,
			fn : method
		};
	}
	
	return {
		getInstance:function(){
			if(!uniqueInstance){
				uniqueInstance = init();
			}	
			return uniqueInstance;
		}
		
	};
})();

alert(Ext.Base.getInstance().attr1);

////4 分支单体： 根据条件不同返回不同的对象 例子
