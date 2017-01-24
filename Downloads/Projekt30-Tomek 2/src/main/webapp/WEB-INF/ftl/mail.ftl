<#-- @ftlvariable name="_csrf" type="org.springframework.security.web.csrf.CsrfToken"-->
<#-- @ftlvariable name="form" type="Login.domain.UserCreateForm" -->
<#-- @ftlvariable name="error" type="java.util.Optional<String>" -->
<#import "/spring.ftl" as spring>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Reset your password</title>
    <title>Home page</title>
    <link href="/css/mailcss.css" rel="stylesheet" media="screen">
</head>
<body>
<nav role="navigation">
    <ul>
    <li><a href="/">Home</a></li>
    </ul>
</nav>
    <form role="form" action="/abc" method="get">
        <div>
            <div>
                <label for="email">Email address</label>
                <input type="email" name="email" id="email"/>
            </div>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <div>
                <label for="password">Password</label>
                <input type="password" name="password" id="password" required/>
            </div>
        </div>
        <button type="submit">Reset password</button>
    </form>
</body>
</html>
