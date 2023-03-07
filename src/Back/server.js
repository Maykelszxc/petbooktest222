const express = require('express');
const bodyParser = require('body-parser');
const fs = require('fs');
const { Pool } = require("pg");

const app = express();
const port = process.env.PORT || 8080;

app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());

app.post('/signup', (req, res) => {
  const { username, email, password } = req.body;

  // Read the accounts data from the file
  const accountsData = JSON.parse(fs.readFileSync('./accounts.json'));

  // Check if the username or email already exists in the accounts data
  const existingAccount = accountsData.find(account => account.username === username || account.email === email);
  if (existingAccount) {
    return res.status(400).json({ error: 'Username or email already exists' });
  }

  // Add the new account data to the accounts data array
  const newAccount = { username, email, password };
  accountsData.push(newAccount);

  // Write the updated accounts data to the file
  fs.writeFileSync('./accounts.json', JSON.stringify(accountsData));

  res.status(201).json({ message: 'Account created successfully' });
});

app.listen(port, () => {
  console.log(`Server running on port ${port}`);
});