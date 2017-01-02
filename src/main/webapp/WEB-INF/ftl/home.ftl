<#-- @ftlvariable name="_csrf" type="org.springframework.security.web.csrf.CsrfToken"-->
<#-- @ftlvariable name="currentUser" type="Login.domain.CurrentUser" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Home page</title>
</head>
<body>
<nav role="navigation">
    <ul>
    <#if !currentUser??>
        <li><a href="/login">Log in</a></li>
        <li><a href="/user/create">Create new user</a></li>
        <li><a href="/user/resetpassword">Forgot Password?</a></li>
    </#if>
    <#if currentUser??>

        <li><a href="/succes/${currentUser.userEmail}/${currentUser.id}">Send confirmation email</a></li>
    </#if>

    </ul>
</nav>
</body>
</html>