/*
       三个参数
       file：一个是文件(类型是图片格式)，
       objDiv：一个是容器或者回调函数
       photoCompress()
       */
function photoCompress(file,objDiv) {
	var ready = new FileReader();
	/*开始读取指定的Blob对象或File对象中的内容. 当读取操作完成时,readyState属性的值会成为DONE,如果设置了onloadend事件处理程序,则调用之.同时,result属性中将包含一个data: URL格式的字符串以表示所读取文件的内容.*/
	ready.readAsDataURL(file);
	ready.onload = function() {
		var re = this.result;
		canvasDataURL(re, file, objDiv)
	}
}

function canvasDataURL(path, obj, callback) {
	var img = new Image();
	img.src = path;
	img.crossOrigin = 'Anonymous';
	img.onload = function() {
		var that = this;
		
		var maxSize = 1024
		var fileSize = obj.size / 1024
		
		// 图像质量
		var base64=null
		var fileRecord=null
		if(fileSize>maxSize){
			var w = that.width * 0.2;
			var h = that.height * 0.2;
			if(fileSize<5*1024){
				w=that.width
				h=that.height
			}
			var quality = 0.7; // 默认图片质量为0.7
			//生成canvas
			var canvas = document.createElement('canvas');
			var ctx = canvas.getContext('2d');
			// 创建属性节点
			var anw = document.createAttribute("width");
			anw.nodeValue = w;
			var anh = document.createAttribute("height");
			anh.nodeValue = h;
			canvas.setAttributeNode(anw);
			canvas.setAttributeNode(anh);
			ctx.drawImage(img, 0, 0,w,h);
			base64 = canvas.toDataURL(obj.type, quality);
			fileRecord= dataURLtoFile(base64, obj.name)
		}else{
			base64=path
			fileRecord= obj
		}
		callback(fileRecord, base64);
	}
}

function dataURLtoFile(dataurl, filename) { //将base64转换为文件
	var arr = dataurl.split(','),
		mime = arr[0].match(/:(.*?);/)[1],
		bstr = atob(arr[1]),
		n = bstr.length,
		u8arr = new Uint8Array(n);
	while (n--) {
		u8arr[n] = bstr.charCodeAt(n);
	}
	return new File([u8arr], filename, {
		type: mime
	});
}
