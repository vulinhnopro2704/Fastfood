<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" type="text/css" href="<c:url value="/css/styles.css" />">
  <link rel="stylesheet" type="text/css" href="<c:url value="/css/footer.css" />">
  <link rel="stylesheet" type="text/css" href="<c:url value="/css/form.css" />">
  <title>Document</title>
</head>

<body>
<main class="container">
  <div class="form-card">
    <h1 class="form-header">Login</h1>
    <form action="<c:url value='/auth/login'/>" method="post">
      <div class="form-group">
        <label for="email">Email</label>
        <input type="text" id="email" name="email">
      </div>
      <div class="form-group">
        <div>
          <label for="password">Password</label>
          <input type="password" id="password" name="password">
        </div>
      </div>
      <button type="submit">Login</button>
    </form>
  </div>
</main>
</body>

</html>