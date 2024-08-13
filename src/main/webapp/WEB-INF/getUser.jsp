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
    <h2>Information User</h2>
    <form class="center-block shadow-sm p-3 mb-5 bg-body-tertiary rounded"
          action="${pageContext.request.contextPath}/user-information" method="get">
        <div class="mb-3">
            <label for="userID" class="form-label">ID</label>
            <input type="text" class="form-control" id="userID" name="userID">
            <div id="IDHelp" class="form-text">Specify the ID of the user you want to see.</div>
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
