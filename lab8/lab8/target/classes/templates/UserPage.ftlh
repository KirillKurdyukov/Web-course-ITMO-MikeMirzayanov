<#-- @ftlvariable name="userCurrent" type="ru.itmo.wp.domain.User" -->
<#import "/spring.ftl" as spring>
<#import "commons.ftlh" as c>

<@c.page>
    <#if userCurrent??>
        <div class="users datatable">
            <div class="caption">User</div>
            <table>
                <thead>
                <tr>
                    <th>Id</th>
                    <th>Login</th>
                    <th>Created</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td class="id">${userCurrent.id}</td>
                    <td class="login">${userCurrent.login}</td>
                    <td class="creationTime">${userCurrent.creationTime}</td>
                </tr>
                </tbody>
            </table>
        </div>
    <#else>
        No such user!
    </#if>
</@c.page>