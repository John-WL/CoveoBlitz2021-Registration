const express = require("express");
const { argv } = require("yargs");
const app = express();

const PORT = argv.port || argv.p || 27178;

app.use(express.json({ limit: "2mb" }));
app.use(express.urlencoded({ limit: "2mb", extended: true }));
app.use(express.json());

app.post("/microchallenge", (req, res) => {
  console.log(
    "\n\n\n-------------------------- REQUEST LOGS STARTING HERE --------------------------"
  );
  console.log(
    "You can log stuff and download the logs from the UI in the replay section."
  );
  console.log("Here is the current problem:");
  console.log(req.body);
  console.log(
    "---------------------------------------------------------------------------------"
  );

  res.json([4, 1]);
});

app.listen(PORT, () => {
  console.log(`Example app listening on port ${PORT}!`);
});
