<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Login/Register Admin</title>
  <link rel="stylesheet" href="css/style.css">
</head>
<body>
  <div class="container">
    <div class="card">
      <div class="front">
        <h2>Login Admin</h2>
        <form action="adminLogin" method="post">
          <label>Username:</label>
          <input type="text" name="username" required><br><br>
          <label>Password:</label>
          <input type="password" name="password" required><br><br>
          <button type="submit">Login</button>
          <p class="register-link" onclick="flipCard()">Register Admin</p>
        </form>
      </div>
      <div class="back">
        <h2>Register Admin</h2>
        <form action="registerAdmin" method="post">
          <label for="reg-username">Username:</label>
          <input type="text" id="reg-username" name="username" required><br><br>
          <label for="reg-password">Password:</label>
          <input type="password" id="reg-password" name="password" required><br><br>
          <button type="submit">Register</button>
          <p class="login-link" onclick="flipCard()">Back to Login</p>
        </form>
      </div>
    </div>
  </div>

  <script>
    function flipCard() {
      const card = document.querySelector('.card');
      card.classList.toggle('flipped');
    }
  </script>

</body>
</html>
