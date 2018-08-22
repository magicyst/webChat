// JavaScript Document
/*
  新建请求对象
*/
function createXMLHttpRequest(){
		var xmlHttp = false;
		if(window.ActiveXObject){
			try{
				xmlHttp = new ActiveXObject("Msxml2.XMLHTTP"); 
			}catch(e1){
				try{
					xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
				}catch(e2){
					
				}
			}
		}
		else if(window.XMLHttpRequest){
			try{
				xmlHttp = new XMLHttpRequest();
			}catch(e3){
				xmlHttp = false;
			}
		}
		return xmlHttp;
	}