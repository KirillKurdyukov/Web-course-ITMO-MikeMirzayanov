<#-- @ftlvariable name="uri" type="java.lang.String" -->

<#assign mainstyle = ["/css/normalize.css", "/css/style.css"]/>

<#macro page additionstyle=[]>
    <!DOCTYPE html>
    <html lang="ru">
    <@head mainstyle=mainstyle additionstyle=additionstyle/>
    <body>
    <@header/>
    <div class="middle">
        <@sidebar/>
        <main>
            <#nested/>
        </main>
    </div>
    <@footer/>
    </body>
    </html>
</#macro>

<#macro head mainstyle additionstyle>
    <head>
        <meta charset="UTF-8">
        <title>Codeforces</title>
        <#list mainstyle + additionstyle as item>
            <link rel="stylesheet" href=${item}>
        </#list>
        <link rel="icon" href="/favicon.ico">
    </head>
</#macro>

<#macro header>
    <header>
        <a href="#" class="logo"><img alt="Codeforces" title="Codeforces"
                                      src="/img/codeforces-vs-coronavirus-65.png"></a>
        <div class="languages">
            <a href="#"><img alt="In English" title="In English"
                             src="/img/gb.png"></a>
            <a href="#"><img alt="In Russian" title="In Russian"
                             src="/img/ru.png"></a>
        </div>
        <div class="enter-or-register-box">
            <#if user??>
                <@userlink user true/>
                |
                <a href="/logout">Logout</a>
            <#else>
                <a href="/enter">Enter</a>
                |
                <a href="/register">Register</a>
            </#if>
        </div>
        <nav>
            <#assign items=[
            {"href": "/index", "name": "Home"},
            {"href": "/misc/help", "name": "Help"},
            {"href": "/contests", "name": "Contests"},
            {"href": "/users", "name": "Users"}
            ]/>
            <ul>
                <#list items as item>
                    <li>
                        <#if item.href == uri>
                            <a class="active-link" href="${item.href}">${item.name}</a>
                        <#else>
                            <a href="${item.href}">${item.name}</a>
                        </#if>
                    </li>
                </#list>
            </ul>
        </nav>
    </header>
</#macro>

<#macro sidebar>
    <aside>
        <#list posts as post>
            <section>
                <#assign text=post.text/>
                <#assign text=text?truncate(250, "...")/>
                <div class="header">Post #${post.id}</div>
                <div class="body">
                    ${text}
                </div>
                <div class="footer">
                    <a href="/post?post_id=${post.id}">View all</a>
                </div>
            </section>
        </#list>
    </aside>
</#macro>

<#macro footer>
    <footer>
        2010-2020 &copy; by Mike Mirzayanov and Kirill Kurdyukov
    </footer>
</#macro>

<#macro userlink user nameOnly=false>
    <#if !nameOnly>
        <a href="/user?handle=${user.handle}"
           class="${user.color.getCssClass()}"
        >${user.handle}</a>
    <#else>
        <a href="/user?handle=${user.handle}">${user.handle}</a>
    </#if>
</#macro>

<#function findBy items key value>
    <#list items as item>
        <#if item[key] == value>
            <#return item/>
        </#if>
    </#list>
</#function>

<#macro doPost post zip>
    <#assign author=findBy(users, "id", post.user_id)/>
    <#assign text=post.text/>
    <#if zip>
        <#assign text=text?truncate(250, "...")/>
    </#if>
    <article>
        <div class="title">${post.title}</div>
        <div class="information">By <@userlink author/> </div>
        <div class="body">
            ${text}
        </div>
        <ul class="attachment">
            <li>Announcement of <a href="#">Codeforces Round #777 (Div. 1)</a></li>
            <li>Announcement of <a href="#">Codeforces Round #777 (Div. 2)</a></li>
        </ul>
        <div class="footer">
            <div class="left">
                <img src="img/voteup.png" title="Vote Up" alt="Vote Up"/>
                <span class="positive-score">+173</span>
                <img src="img/votedown.png" title="Vote Down" alt="Vote Down"/>
            </div>
            <div class="right">
                <img src="img/date_16x16.png" title="Publish Time" alt="Publish Time"/>
                2 days ago
                <img src="img/comments_16x16.png" title="Comments" alt="Comments"/>
                <a href="#">68</a>
            </div>
        </div>
    </article>
</#macro>


<#function getPrevUser user>
    <#list users?reverse as u>
        <#if (u.id < user.id)>
            <#return u/>
        </#if>
    </#list>
</#function>

<#function getNextUser user>
    <#list users as u>
        <#if (u.id > user.id)>
            <#return u/>
        </#if>
    </#list>
</#function>

<#function countPosts user>
    <#assign count=0/>
    <#list posts as post>
        <#if post.user_id==user.id>
            <#assign count=count+1>
        </#if>
    </#list>
    <#return count>
</#function>