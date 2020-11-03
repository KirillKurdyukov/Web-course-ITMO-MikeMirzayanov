<#-- @ftlvariable name="handle" type="java.lang.String" -->
<#-- @ftlvariable name="user_id" type="long" -->

<#assign additionstyle = ["/css/user.css", "/css/error.css"]/>

<#import "./common.ftlh" as c>
<@c.page additionstyle>
    <#if handle??>
        <#assign u = c.findBy(users, "handle", handle)!/>
    <#else>
        <#assign u = c.findBy(users, "id", user_id)!/>
    </#if>
    <#if u?has_content>
        <div class="user">
            <img src="/img/person.png" alt="person">
            <div class="handle">
                <@c.userlink u/>
            </div>
            <ul>
                <li>
                    "id": ${u.id}
                </li>
                <li>
                    "handle": ${u.handle}
                </li>
                <li>
                    "name": ${u.name}
                </li>
                <li>
                    "posts":  <a href="/posts?user_id=${u.id}" style="text-decoration: none">${c.countPosts(u)}</a>
                </li>
            </ul>
        </div>
        <div class="prev-next-user">
            <#assign prev=c.getPrevUser(u)!/>
            <#assign next=c.getNextUser(u)!/>
            <#if prev?has_content>
                <a class="arrow-to-the-left-link" href="/user?handle=${prev.handle}">←</a>
            <#else>
                <div class="arrow-to-the-left-without-reference">←</div>
            </#if>
            <#if next?has_content>
                <a class="arrow-to-the-right-link" href="/user?handle=${next.handle}">→</a>
            <#else>
                <div class="arrow-to-the-right-without-reference">→</div>
            </#if>
        </div>
    <#else>
        No such user
    </#if>
</@c.page>
