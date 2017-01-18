<#-- @ftlvariable name="_csrf" type="org.springframework.security.web.csrf.CsrfToken"-->
<#-- @ftlvariable name="currentUser" type="Login.domain.CurrentUser" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Create a new user</title>
         <link href="/css/createuser.css" rel="stylesheet" media="screen">
</head>
<body>
<nav role="navigation">
    <ul>
        <li><a href="/">Home</a></li>
    </ul>
</nav>

<h1>Reset Password</h1>

<form name="form" action="/reset/${currentUser.id}" method="get">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <div>
        <label for="password">Old password</label>
        <input type="password" name="passwordOld" id="password" placeholder="Type old password..." required/>
    </div>
    <div>
        <label for="password">New password</label>
        <input type="password" name="password" id="password" placeholder = "Type new password..." required/>
    </div>
    <div>
        <label for="passwordRepeated">Repeat new password</label>
        <input type="password" name="passwordRepeated" id="passwordRepeated" placeholder="Repeat new password..." required/>
    </div>
    <button type="submit">Save</button>
</form>


</body>
</html>
