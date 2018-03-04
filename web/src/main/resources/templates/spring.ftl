<#macro csrfInput>
    <#if (_csrf.token)?has_content>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </#if>
</#macro>

<#macro csrfMetaTags>
    <#if (_csrf.token)?has_content>
    <meta name="_csrf_parameter" content="${_csrf.parameterName}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <meta name="_csrf" content="${_csrf.token}"/>
    </#if>
</#macro>