<template>
    <span style="width:100%;display:inline-block;text-align: right;line-height: 37px;overflow: hidden;height: 39px;white-space: nowrap;text-overflow: ellipsis;" :title="content"><span v-show="required" style="color: red;">*</span>{{content}}：</span>
</template>

<script>
    module.exports =  {
    props: ["content","required"],
    data: function () {
        return {

        }
    },
}
</script>