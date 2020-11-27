<#-- @ftlvariable name="users" type="ru.itmo.wp.domain.User[]" -->
<#import "commons.ftlh" as c>

<@c.page>
    <div class="users datatable">
        <div class="caption">User</div>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Login</th>
                <th>Status</th>
                <th>Created</th>
            </tr>
            </thead>
            <tbody>
            <#if users?size==0>
                <tr class="noData">
                    <td colspan="3">
                        No data
                    </td>
                </tr>
            <#else>
                <#list users as user>
                <tr>
                    <td class="id">${user.id}</td>
                    <td class="login">${user.login}</td>
                    <td class="status">
                        <form method="post">
                            <input type="hidden" name="userId" value="${user.id}">
                            <#if user.status>
                                <input type="hidden" name="status" value="Enable"/>
                                <input type="submit" value="Enable">
                            <#else>
                                <input type="hidden" name="status" value="Disable"/>
                                <input type="submit" value="Disable">
                            </#if>
                        </form>
                    </td>
                    <td class="creationTime">${user.creationTime}</td>
                </tr>
                </#list>
            </#if>
            </tbody>
        </table>
    </div>
</@c.page>
