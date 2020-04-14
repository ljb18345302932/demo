function getXMLHttpRequest (){
	var xhq = null ;
	if(window.XMLHttpRequest){
		xhq = new XMLHttpRequest();
	}else{
		xhq = new ActiveXObject("MicroSoft.XMLHTTP");
	} 
	return xhq;
}