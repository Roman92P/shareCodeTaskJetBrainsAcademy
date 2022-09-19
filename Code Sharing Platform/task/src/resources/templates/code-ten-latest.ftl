<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Latest</title>
    <link rel="stylesheet"
          href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
    <link rel="stylesheet"
          href="/general.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<table>
    <tr>
        <th>Code</th>
        <th>Load Date</th>
    </tr>
    <#list codeList as code>
    <tr>
        <div class="container-fluid">
            <div class="row">
                <div class="col-3">
                    <td>
                        <pre id="code_snippet"><code>${code.code}</code></pre>
                    </td>
                </div>
            </div>
        </div>
        <div class="container-fluid">
            <div class="row">
                <div class="col-3">
                    <td><span class="code_date" id="load_date">${code.date}</span></td>
                </div>
            </div>
        </div>
    </tr>
    </#list>
</table>
</body>
</html>