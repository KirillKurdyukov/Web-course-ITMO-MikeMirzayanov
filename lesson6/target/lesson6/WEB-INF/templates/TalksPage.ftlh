<#-- @ftlvariable name="user" type="ru.itmo.wp.model.domain.User" -->
<#-- @ftlvariable name="users" type="ru.itmo.wp.model.domain.User[]" -->

<#import "commons.ftlh" as c>

<@c.page>
   <div class="table1">
      <table>
         <tbody>
            <tr>
               <td>You ${user.login}</td>
               <td>Send Message:</td>
               <td>
                  <label>
                     <select class="targetUsers">
                        <#list users as u>
                            <option>${u.login}</option>
                        </#list>
                     </select>
                  </label>
               </td>
            </tr>
         </tbody>
      </table>
   </div>
</@c.page>
