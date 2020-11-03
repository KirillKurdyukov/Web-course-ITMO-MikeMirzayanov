<#-- @ftlvariable name="user_id" type="long" -->
<#import "./common.ftlh" as c>

<#assign additionstyle = ["/css/post.css"]/>

<@c.page additionstyle>
    <#list posts?reverse as post>
        <#if post.user_id = user_id>
            <@c.doPost post true/>
        </#if>
    </#list>
</@c.page>