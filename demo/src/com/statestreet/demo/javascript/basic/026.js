/**
 * @author Administrator
 */
//javascript 定义接口有三种方式


//1 注解描述方式
//优点 :程序员有一个参考
//缺点:还是属于文档范畴 松散 ，没有检查接口的方式是否被完全实现
/**
 * interface Composite{
 * 
 * 		function add(obj);
 * 		function remove(obj);
 * 		function doupdate(obj);
 * }
 */
function CompositeImpl(){};
var c = new CompositeImpl(); 
//alert(typeof c);
//alert(c.constructor === CompositeImpl);
CompositeImpl.prototype.add = function(obj){
	
};

CompositeImpl.prototype.remove = function(obj){
	
};

CompositeImpl.prototype.doupdate = function(obj){
	
};

//2.属性检测
//..省略 大致意思就是interface 里面定义一个要实现的接口数组，然后调用前检测下有没有实现接口  没实现则报错
//没多大用  就是用 ['interface1','interface2'] 去检测['interface1','interface2'] 


//3.鸭式辨型方式
/**接口需要两个参数
 * 参数一：接口的名字
 * 参数二：接收方法名称的集合
 * 
 */
var Interface = function(name,method){
	if(arguments.length !=2){
		throw new Error('参数个数不正确！');
	}
	if(method.constructor !== Array){
		throw new Error('第二个参数错误，应该为数组');
	}
		
	this.name = name;
	this.methods = [];
	for(var i = 0;i<method.length;i++){
		if(method[i].constructor !== String){
			throw new Error('方法名只能是字符窜！');
		}
		this.methods.push(method[i]);
	}
	
};

var interface1 = new Interface('interface1',['add']);
// var interface1 = new Interface('interface1',['add1']);
var interface2 = new Interface('interface2',['update']);

//准备工作 InterfaceImpl implements interface1,interface2
var InterfaceImpl = function(){
	
};
InterfaceImpl.prototype.add = function(){
	alert('add');
};

InterfaceImpl.prototype.update = function(){
	alert('update');
};


//检验接口里的方法
Interface.ensureImplements = function(object){
	if(arguments.length < 2){
		throw new Error('参数必须大于两个！');
	}
	for(var i = 1;i<arguments.length;i++){
		var interfaceInstance = arguments[i];
		if(interfaceInstance.constructor !== Interface){
			throw new Error('参数错误！');
		}
		
		for(var j = 0;j<interfaceInstance.methods.length;j++){
			var methodName = interfaceInstance.methods[j];
			if(!object[methodName] || object[methodName].constructor !== Function){
				throw new Error('方法 ' + methodName + " 没找到！");
			}
		}
		
	}
	
};

var c = new InterfaceImpl();

//Interface 加静态方法
Interface.ensureImplements(c,interface1,interface2);
c.add();

