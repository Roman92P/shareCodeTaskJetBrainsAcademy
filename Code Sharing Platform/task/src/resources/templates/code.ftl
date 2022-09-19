<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Code</title>
    <link rel="stylesheet"
          href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
    <link rel="stylesheet"
          href="/general.css">
    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-4">
            <span class="code_date" id="load_date"> ${code.date}</span>
        </div>
    </div>
</div>
<#if code.applyViewsRule == true>
<div class="container-fluid">
    <div class="row">
        <div class="col-4">
            <span id="views_restriction">${code.views} more views allowed</span>
        </div>
    </div>
</div>
</#if>
<#if code.applyTimeRule == true>
<div class="container-fluid">
    <div class="row">
        <div class="col-4">
            <span id="time_restriction">The code will be available for ${code.time} seconds</span>
        </div>
    </div>
</div>
</#if>
<div class="container-fluid">
    <div class="row">
        <div class="col-4">
            <pre id="code_snippet"><code>${code.code}</code></pre>
        </div>
    </div>
</div>
</body>
</html>