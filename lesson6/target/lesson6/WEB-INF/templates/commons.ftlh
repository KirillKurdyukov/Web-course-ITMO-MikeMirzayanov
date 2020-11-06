<#-- @ftlvariable name="user" type="ru.itmo.wp.model.domain.User" -->
<#-- @ftlvariable name="message" type="java.lang.String" -->
<#-- @ftlvariable name="login" type="java.lang.String" -->
<#-- @ftlvariable name="password" type="java.lang.String" -->
<#-- @ftlvariable name="passwordConfirmation" type="java.lang.String" -->
<#-- @ftlvariable name="error" type="java.lang.String" -->
<#-- @ftlvariable name="email" type="java.lang.String" -->
<#-- @ftlvariable name="userCount" type="long" -->
<#macro page>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Codeforces</title>
    <link rel="stylesheet" type="text/css" href="css/normalize.css">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <link rel="stylesheet" type="text/css" href="css/form.css">
    <link rel="stylesheet" type="text/css" href="css/datatable.css">
</head>
<body>
<header>
    <a href="/index"><img src="img/logo.png" alt="Codeforces" title="Codeforces"/></a>
    <div class="languages">
        <a href="#"><img src="img/gb.png" alt="In English" title="In English"/></a>
        <a href="#"><img src="img/ru.png" alt="In Russian" title="In Russian"/></a>
    </div>
    <div class="enter-or-register-box">
        <#if user??>
            ${user.login}
            |
            <a href="/logout">Logout</a>
        <#else>
            <a href="/enter">Enter</a>
            |
            <a href="/register">Register</a>
        </#if>
    </div>
    <nav>
        <ul>
            <li><a href="/index">Home</a></li>
            <li><a href="/users">Users</a></li>
        </ul>
    </nav>
</header>
<#if message??>
    <div class="message">${message}</div>
</#if>
<div class="middle">
    <aside>
        <section>
            <div class="header">
                Pay attention
            </div>
            <div class="body">
                Lorem ipsum dolor sit amet, consectetur adipisicing elit. Cupiditate ducimus enim facere impedit nobis,
                nulla placeat quam suscipit unde voluptatibus.
            </div>
            <div class="footer">
                <a href="#">View all</a>
            </div>
        </section>
    </aside>
    <main>
        <#nested/>
    </main>
</div>
<footer>
    <a href="/index">Codeforces</a> &copy; 2020 by Mike Mirzayanov and Kirill Kurdyukov
    <div class="userCount"> there are already ${userCount!} users on our site! </div>
</footer>
</body>
</html>
</#macro>

<#macro doForm nameForm>
    <div class="form-box">
        <div class="header">${nameForm}</div>
        <div class="body">
            <form method="post" action="">
                <#if nameForm=="Register">
                    <input type="hidden" name="action" value="register"/>
                    <div class="field">
                        <div class="name">
                            <label for="email">Email</label>
                        </div>
                        <div class="value">
                            <input id="email" name="email" value="${email!}"/>
                        </div>
                    </div>
                <#else>
                    <input type="hidden" name="action" value="enter"/>
                </#if>
                <div class="field">
                    <div class="name">
                        <#if nameForm=="Register">
                            <label for="login">Login</label>
                        <#else>
                            <label for="login">LoginOrEmail</label>
                        </#if>
                    </div>
                    <div class="value">
                        <input id="login" name="login" value="${login!}"/>
                    </div>
                </div>
                <div class="field">
                    <div class="name">
                        <label for="password">Password</label>
                    </div>
                    <div class="value">
                        <input id="password" name="password" type="password" value="${password!}"/>
                    </div>
                </div>
                <#if nameForm=="Register">
                    <div class="field">
                        <div class="name">
                            <label for="passwordConfirmation">PasswordConfirmation</label>
                        </div>
                        <div class="value">
                            <input id="passwordConfirmation" name="passwordConfirmation" type="password" value="${passwordConfirmation!}"/>
                        </div>
                    </div>
                </#if>
                <#if error??>
                    <div class="error">
                        ${error!}
                    </div>
                </#if>
                <div class="button-field">
                    <#if nameForm=="Register">
                        <input type="submit" value="Login">
                    <#else>
                        <input type="submit" value="Enter">
                    </#if>
                </div>
            </form>
        </div>
    </div>
</#macro>