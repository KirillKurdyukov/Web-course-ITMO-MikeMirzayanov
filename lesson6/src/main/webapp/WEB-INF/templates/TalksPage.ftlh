<#-- @ftlvariable name="user" type="ru.itmo.wp.model.domain.User" -->
<#-- @ftlvariable name="users" type="ru.itmo.wp.model.domain.User[]" -->

<#import "commons.ftlh" as c>

<@c.page>
   <div class="table1">
      <form method="post">
         <table>
            <tbody>
               <tr>
                  <td>You ${user.login}</td>
                  <td>Send Message:</td>
                  <td>
                     <label>
                        <select class="targetUsers" name="targetUserId">
                           <#list users as u>
                               <option value="${u.id}">${u.login}</option>
                           </#list>
                        </select>
                     </label>
                  </td>
                  <td>

                  </td>
               </tr>
            </tbody>
         </table>
      </form>
   </div>
</@c.page>
