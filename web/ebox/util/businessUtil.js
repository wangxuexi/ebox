//此文件为业务util 存放业务中获取orgCode等方法


//获取二级菜单
var GET_SECONDARY_MENU = () => sessionStorage.getItem(TENANT_CODE + "_selectMenu");

//获取三级菜单
var GET_THREE_MENU = () => sessionStorage.getItem(TENANT_CODE + "_threeMenu");

var PAGE_SCROLL = () =>{
    $(document).on('click','.jumpAnchor',function(){
        let href = $(this).attr("data");
        let pos = $(href).offset().top+$("body").scrollTop();
        $('html,body').animate({scrollTop:pos}, 500);
    })
}


function getOrgCode(orgDatasAttr){
    let resultid = "";
    orgDatasAttr.forEach((item,i)=>{
        if(item.children){
            resultid += item.value+","+ getOrgCode(item.children);
        }else{

            resultid += item.value+","
        }
    });
    return resultid;
}


function getOrgName(orgDatasAttr){
    let resultname = "";
    orgDatasAttr.forEach((item,i)=>{
        if(item.children){
            resultname += item.label+","+ getOrgName(item.children);
        }else{
            resultname += item.label+","

        }
    });
    return resultname;
}