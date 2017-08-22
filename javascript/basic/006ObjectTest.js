/**
 * @author Administrator
 */
function Map(){
	var obj = {};
	
	this.put = function(key,value){
		obj[key] = value;
	};
	
	this.size = function(){
		var size = 0;
		for(var attribute in obj){
			size++;
		}
		return size;
	};
	
	this.remove = function(key){
		if(obj[key] || obj[key] === 0 || obj[key] === false){
			delete obj[key];
		}
	};
	
	this.get = function(key){
		if(obj[key] || obj[key] === 0 || obj[key] === false){
			return obj[key];
		}else{
			return null;
		}
		
	};
	
	
	this.eachMap = function(){
		for(var attr in obj){
			console.info(attr + " = "+ obj[attr]);			
		}
	};
	
	this.callBack = function(fn){
		for(var attr in obj){
			fn(attr,obj[attr]);
		}
	};
	
}

var map  = new Map();

map.put('a','11');
map.put('b','22');
map.put('c',0);
map.put('d',true);

console.info(map.size());

map.remove('a');

console.info(map.get('a'));
console.info(map.get('c'));
console.info(map.size());
map.eachMap();
console.info("==========================");
map.callBack(function(key,value){
	console.info(key + "==" + value);
});
