/**
 * 隐藏三级节点
 * @param {Object} treeObj
 */
function hideNode(treeId){
	var zTree = $.fn.zTree.getZTreeObj(treeId),
	rootObject = zTree.getNodes()[0];
	$.each(rootObject.children, function(index,secondNode) {
		secondNode.isParent = true;
		$.each(secondNode.children,function(_index,thirdNode){
			console.log(thirdNode.children)
			thirdNode.isParent = false;
			zTree.hideNodes(thirdNode.children)
		})
	});
}



//将后台传过来的树转成需要的格式
function transTree2Children(list, idstr, pidstr) {
    var result = [], temp = {};
    for (i = 0; i < list.length; i++) {
        temp[list[i][idstr]] = list[i];//将nodes数组转成对象类型
    }
    for (j = 0; j < list.length; j++) {
        tempVp = temp[list[j][pidstr]]; //获取每一个子对象的父对象
        if (tempVp) {//判断父对象是否存在，如果不存在直接将对象放到第一层
            if (!tempVp["children"]) tempVp["children"] = [];//如果父元素的nodes对象不存在，则创建数组
            tempVp["children"].push(list[j]);//将本对象压入父对象的nodes数组
        } else {
            result.push(list[j]);//将不存在父对象的对象直接放入一级目录
        }
    }
    return result;
}



 //递归查询当前节点下面的全部子节点（一）
function getzTreeChildrenNode(treeNode, result){
	//检测是否为父节点
	if (treeNode.isParent) {
		var childrenNodes = treeNode.children;//查询子节点
		if (childrenNodes) {
			//子节点拼接
			for (var i = 0 ; i<childrenNodes.length ; i++){
			   result += ',' + childrenNodes[i].id;
			   result = getzTreeChildrenNode(childrenNodes[i], result);//循环调用
			}
		}
	}
	return result;//返回
}
