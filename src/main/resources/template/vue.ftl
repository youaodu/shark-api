<template>
    <div>
        ${page}
    </div>
</template>
<script>
    export default {
        created() {
<#--            ${created}-->
        },
        data() {
            return {
                <#list models as it>
                    ${it}: ''
                    <#if it_has_next>,</#if>
                </#list>
            }
        },
        methods: {
<#--            ${methods}-->
        }
    }
</script>
<style scope>
<#--    ${style}-->
</style>