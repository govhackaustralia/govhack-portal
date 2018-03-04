<#import 'base_page.ftl' as base/>

<@base.base_page>

<script>
    window.BASE_URL = '${rc.contextPath}';
</script>
<div id="app">
    <router-view></router-view>
</div>

</@base.base_page>