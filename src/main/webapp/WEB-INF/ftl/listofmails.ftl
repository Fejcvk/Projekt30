<#-- @ftlvariable name="name" type="String[]" -->

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>List of Attachments</title>
</head>
<body>
<nav role="navigation">
    <ul>
        <li><a href="/">Home</a></li>
        <li><a href="/user/create">Create a new user</a></li>
    </ul>
</nav>

<h1>List of Attachments</h1>

<table>
    <thead>
    <tr>

<#list 0..86 as x>
<#if name[x]!="">
    <li><a href="abc/download/${name[x]}">${name[x]}</a></li>

</#if>
</#list>
    </tr>

</table>
</body>
</html>