<#-- @ftlvariable name="_csrf" type="org.springframework.security.web.csrf.CsrfToken"-->
<#-- @ftlvariable name="currentUser" type="Login.domain.CurrentUser" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Home page</title>
    <link href="/css/homecss.css" rel="stylesheet" media="screen">
</head>
<body>
<nav role="navigation">
    <#if !currentUser??>
    <ul1>
        <li1><a href="/login">Log in</a></li1>
        <li1><a href="/user/create">Create new user</a></li1>
        <li1><a href="/getemail">Forgot Password?
    </ul1>
    </#if>
    <#if currentUser??>
            <form action="/logout" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <button type="submit">Log out</button>
            </form>
        <ul2>
        <li2><a href="/user/${currentUser.id}">View myself</a></li2>
        <li2><a href="/gmail">Gmail</a> </li2>
        <li2><a href="/resetpassword/${currentUser.id}">Set new password</a></li2>
            <li2><a href="/sendatt">Send att</a></li2>
        </ul2>
    </#if>
    <#if currentUser?? && currentUser.role == "ADMIN">
        <ul3>
            <li3 class="dropdown">
                <a href="javascript:void(0)" class="dropbtn">Admin Panel</a>
                <div class="dropdown-content">
                    <a href="/user/create">Create a new user</a>
                    <a href="/users">View all users</a>
                </div>
            </li3>
        </ul3>
    </#if>

</nav>
</body>
</html>