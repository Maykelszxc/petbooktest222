package Back;const loginForm = document.getElementById("login-form");
const signupForm = document.getElementById("signup-form");
const { Pool } = require("pg");

// Initialize an empty array to store user data
        let users = [];

// Function to add a new user to the users array
        function addUser(name, email, password) {
        // Create a new user object
        const pool = new Pool({
        user: "your_username",
        host: "localhost",
        database: "your_database_name",
        password: "your_password",
        port: 8080,
        });
        // Add the user object to the users array
        users.push(user);
        pool.query(
        "CREATE TABLE IF NOT EXISTS users (id SERIAL PRIMARY KEY, name VARCHAR(255) NOT NULL, email VARCHAR(255) UNIQUE NOT NULL, password VARCHAR(255) NOT NULL)"
        );


        }


        loginForm.addEventListener("submit", async (e) => {
        e.preventDefault();
        const email = loginForm.email.value;
        const password = loginForm.password.value;

        try {
        const query = {
        text: "SELECT * FROM users WHERE email = $1 AND password = $2",
        values: [email, password],
        };
        const { rows } = await pool.query(query);
        if (rows.length === 1) {
        const user = rows[0];
        alert(`Welcome back, ${user.name}!`);
        loginForm.reset();
        } else {
        alert("Invalid email or password");
        }
        } catch (err) {
        console.error(err);
        alert("An error occurred while logging in");
        }
        });

        signupForm.addEventListener("submit", (e) => {
        e.preventDefault();
        const name = signupForm.name.value;
        const email = signupForm.email.value;
        const password = signupForm.password.value;

        // Check if email is already taken
        const emailTaken = users.some((user) => user.email === email);

        if (emailTaken) {
        alert("Email already taken");
        } else {
        // Add new user to users array
        addUser(name, email, password);
        alert("Sign up successful!");
        signupForm.reset();
        }
        });
