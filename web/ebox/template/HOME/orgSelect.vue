 <template>
     <el-cascader ref="myCascader"   :options="orgDatas" :collapse-tags="true" size="small" :show-all-levels="false" :props="props" @change="changeCascader"  placeholder="默认选择所有组织" v-model="text" ></el-cascader>
 </template>

<script>
    module.exports =  {
    props:["keyChilder"],
    data() {
        return {
            orgDatas:null,
            props: { multiple: true },
            text:[],
            keyChilder:null,
            myCascader:null,
        }
    },
    created(){
        
        $.ajax({
            url: SERVER_IP_PORT+SERVER_PREFIX+"queryOrgTreeByUserId",
            type: "POST",
            data:{"userId":PLAT_GET_UID()},
            async: true,
            dataType: "json",
            xhrFields: {
                withCredentials: xhrFieldsState
            },
            success:  (data)=> {
                org = data.data;
                let obj = {};
                let peon = org.reduce((cur,next) => {
                    obj[next.id] ? "" : obj[next.id] = true && cur.push(next);
                    return cur;
                },[]);
                this.orgDatas = transDate(peon,"id","pId");
                sessionStorage.setItem(TENANT_CODE+"_orgTree", JSON.stringify(this.orgDatas));
            }
        });
    },
    mounted(){
        
    },
    methods:{
        changeCascader(){
            //document.getElementById('myframe').contentWindow.location.reload(true);
            let orgCodes = '';
            //document.getElementByclassName('iframes').contentWindow.location.reload(true);
            var myCascaderLength = this.$refs.myCascader.getCheckedNodes();
            let checkCode  = [];
            let checkname  = [];
            if (myCascaderLength.length != "0"){
                let myCascaderName = "";
                let xxx="";
                for (var j=0;j<myCascaderLength.length;j++){
                    myCascaderLength[j].pathNodes.forEach((item,i)=>{
                        checkCode.push(item.value);
                        checkname.push(item.label);
                    });
                }

                let checkCodes = unique(checkCode);
                let checknames = unique(checkname);
                checkCodes.forEach((value,v)=>{
                    xxx += value+",";
                });
                checknames.forEach((name,n)=>{
                    myCascaderName += name+",";
                });

                let myCascaderNames = myCascaderName.substring(0,myCascaderName.length - 1);
                sessionStorage.setItem(TENANT_CODE+"_orgName", myCascaderNames);
                let yyy = xxx.substring(0,xxx.length - 1);
                sessionStorage.setItem(TENANT_CODE+"_orgCode", yyy);
                orgCodes = yyy;
            }else if (myCascaderLength.length == "0"){
                let orgDatasAttr = this.orgDatas;
                let resultid=getOrgCode(orgDatasAttr);
                let resultname=getOrgName(orgDatasAttr);
                
                let resultArrs = resultid.substring(0,resultid.length - 1);
                sessionStorage.setItem(TENANT_CODE+"_orgCode",resultArrs);
                
                let resultnames = resultname.substring(0,resultname.length - 1);
                sessionStorage.setItem(TENANT_CODE+"_orgName", resultnames);
            }
        },
        changeCascaderNew(){
            let orgCodes = '';
            var myCascaderLengthChecked = this.$refs.myCascader.getCheckedNodes();
            let myCascaderLengthList = [];
            console.log(this.text)
            console.log(myCascaderLengthChecked)
            myCascaderLengthChecked.forEach(element=>{
                this.text.forEach(item=>{
                    item.forEach(key=>{
                        if(element.value == key){
                            myCascaderLengthList.push(element)
                        }
                    })
                })
            })
            
            var myCascaderLength = myCascaderLengthList;
            let checkCode  = [];
            let checkname  = [];
            if (myCascaderLength.length != "0"){
                let myCascaderName = "";
                let xxx="";
                for (var j=0;j<myCascaderLength.length;j++){
                    myCascaderLength[j].pathNodes.forEach((item,i)=>{
                        checkCode.push(item.value);
                        checkname.push(item.label);
                    });
                }

                let checkCodes = unique(checkCode);
                let checknames = unique(checkname);
                checkCodes.forEach((value,v)=>{
                    xxx += value+",";
                });
                checknames.forEach((name,n)=>{
                    myCascaderName += name+",";
                });

                let myCascaderNames = myCascaderName.substring(0,myCascaderName.length - 1);
                sessionStorage.setItem(TENANT_CODE+"_orgName", myCascaderNames);
                let yyy = xxx.substring(0,xxx.length - 1);
                sessionStorage.setItem(TENANT_CODE+"_orgCode", yyy);
                orgCodes = yyy;
            }else if (myCascaderLength.length == "0"){
                let orgDatasAttr = this.orgDatas;
                let resultid=getOrgCode(orgDatasAttr);
                let resultname=getOrgName(orgDatasAttr);
                
                let resultArrs = resultid.substring(0,resultid.length - 1);
                sessionStorage.setItem(TENANT_CODE+"_orgCode",resultArrs);
                
                let resultnames = resultname.substring(0,resultname.length - 1);
                sessionStorage.setItem(TENANT_CODE+"_orgName", resultnames);
            }
            //this.$parent.fatherMethod();
            setTimeout(()=>{
                this.$emit('fatherorgnotice');
            },500)
            
        }
    },
        watch:{
            orgDatas: {
                handler(newValue, oldValue) {
                    console.log(this.orgDatas)
                    this.orgDatas = newValue;
                    //取子节点id
                    let orgDatasAttr = newValue;
                    let resultid=getOrgCode(orgDatasAttr);
                    let resultname=getOrgName(orgDatasAttr);
                    
                    let resultArrs = resultid.substring(0,resultid.length - 1);
                    
                    let resultnames = resultname.substring(0,resultname.length - 1);
                    var myCascaderList = [];
                    this.text.forEach(item=>{
                        newValue.forEach(element=>{
                            if(item[0] == element.value){
                                myCascaderList.push(item)
                            }
                        })
                    })
                    this.text = myCascaderList;
                    this.changeCascaderNew();
                },
                //immediate: true
            },
            keyChilder: {
                immediate: true, // 这句重要
                    handler(val) {
                        console.log('监听到了')
                        if(val != '' && val != null){
                            let orgVal = JSON.parse(val)
                            this.orgDatas = orgVal
                        }
                        
                },
            },
        }
}
</script>
 <style>
     .el-cascader__tags i{
         display: none;
     }
 </style>