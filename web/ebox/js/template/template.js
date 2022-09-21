/**
 * Created by Kevin on 2019/10/29.
 */
//头部
Vue.component('fjec-query', {
    props: [],
    template: `<div class="query">
                <slot name="query"></slot>
            </div>`,
});

//列表
Vue.component('fjec-body', {
    props: [],
    template: `<div class="resultTable" style="background-color: #fff;margin-top: 15px;padding: 0 10px;min-height: 180px">
                <slot name="info"></slot>
    </div>`,
});

//模态框
Vue.component('fjec-dialog', {
    props: [],
    template: `<div class="el-dialog-div">
            <slot name="dialog"></slot>
        </div>`,
});