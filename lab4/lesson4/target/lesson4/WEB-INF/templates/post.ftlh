<#-- @ftlvariable name="post_id" type="long" -->
<#import "./common.ftlh" as c>

<#assign additionstyle = ["/css/post.css"]/>

<@c.page additionstyle>
    <#assign viewedPost=c.findBy(posts, "id", post_id)!/>
    <#if viewedPost?has_content>
        <@c.doPost viewedPost false/>
    <#else>
        No such user
    </#if>
</@c.page>