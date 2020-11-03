<#import "./common.ftlh" as c>

<#assign additionstyle = ["/css/post.css"]/>

<@c.page additionstyle>
    <#list posts?reverse as post>
        <@c.doPost post true/>
    </#list>
</@c.page>