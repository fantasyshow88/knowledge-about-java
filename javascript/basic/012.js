/**
 * @author Administrator
 */
//this 对象是指在运行期间基于执行环境绑定的
//this 总是指向调用者
var k = 10;

function test(){
	this.k = 2;
}
//window.test();
test();//执行环境是顶层作用域 故 this.k = 2 把顶层的 k =10 覆盖掉了
console.info(test.k);//undefined
// console.info(window.k);
console.info(k);//2


