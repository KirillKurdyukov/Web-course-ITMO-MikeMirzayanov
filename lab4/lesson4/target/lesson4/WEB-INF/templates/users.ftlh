<#import "./common.ftlh" as c>

<#assign additionstyle = ["/css/table.css"]/>
<@c.page additionstyle>
    <div class="datatable">
        <div class="caption">Submissions</div>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Created</th>
                <th>User</th>
                <th>Status</th>
                <th>Test</th>
            </tr>
            </thead>
            <#list users as u>
            <thead>
            <tr>
                <th>${u.id}</th>
                <th>2018-09-10 12:32:11</th>
                <th><@c.userlink u/></th>
                <th>Accepted</th>
                <th>228</th>
            </tr>
            </thead>
            </#list>
        </table>
    </div>
</@c.page>
