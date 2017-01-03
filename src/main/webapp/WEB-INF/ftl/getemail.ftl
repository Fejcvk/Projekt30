<#-- @ftlvariable name="_csrf" type="org.springframework.security.web.csrf.CsrfToken"-->
<#-- @ftlvariable name="error" type="java.util.Optional<String>" -->
<#import "/spring.ftl" as spring>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Reset your password</title>
</head>
<body>
<nav role="navigation">
    <form role="form" action="/redirect" method="post">
        <div>
            <label for="email">Email address</label>
            <input type="email" name="email" value="" id="email" required autofocus/>
        </div>
        <button type="submit">Reset password</button>
    </form>
</nav>
</body>
</html>