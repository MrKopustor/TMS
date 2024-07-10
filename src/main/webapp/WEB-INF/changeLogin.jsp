<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <title>HW 35</title>
    <style>
        .container {
            display: flex;
            flex-direction: column;
            justify-content: center;
            height: 100vh;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Change login</h2>
    <form class="center-block shadow-sm p-3 mb-5 bg-body-tertiary rounded"
          action="${pageContext.request.contextPath}/change-login" method="post">
        <div class="mb-3">
            <label for="userID" class="form-label">ID</label>
            <input type="text" class="form-control" id="userID" name="userID">
            <div id="IDHelp" class="form-text">Specify user ID, change login.</div>
        </div>
        <div class="mb-3">
            <label for="login" class="form-label">Login</label>
            <input type="text" class="form-control" id="login" name="login">
            <div id="LoginHelp" class="form-text">Come up with a login that will contain no more than 50 elements.</div>
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
