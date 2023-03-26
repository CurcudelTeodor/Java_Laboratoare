<html>
<head>
    <title>${catalogName}</title>
</head>
<body>
    <h1>${catalogName}</h1>
    <table>
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Location</th>
        </tr>
        <#list documents as document>
            <tr>
                <td>${document.id}</td>
                <td>${document.title}</td>
                <td>${document.location}</td>
            </tr>
        </#list>
    </table>
</body>
</html>