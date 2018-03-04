<#-- @ftlvariable name="model" type="org.govhack.portal.web.model.IndexModel" -->
<#import 'spring.ftl' as spring/>
<#import 'app/static_import.ftl' as si>

<#macro base_page>
<!DOCTYPE html>
<html>

    <@si.imports_head>
<meta charset="utf-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Govhack</title>

<!-- base url -->
<base href='${rc.contextPath}/'>
        <@spring.csrfMetaTags/>
    </@si.imports_head>

<@si.imports_body>
    <#nested>
</@si.imports_body>
</html>
</#macro>