const express = require("express");
const URL = require("url").URL;

const app = express();
const port = process.env.PORT || 5000;


// Here we have a lambda function in javascript as a call back
app.listen(port, () => console.log(`Listening on port ${port}...`));

const doubleFunc = x => 2 * x;
// this is equivalent to the following:
// function doubleFunc(x) {
//     return 2 * x;
// }

// call back lambda function with parameter
app.get("/", (req, res) => {
    let url = new URL(req.originalUrl, `http://${req.headers.host}`);
    let param = url.searchParams.get("param");

    let result = doubleFunc(param);
    res.send("Given parameter: " + param + ", return value: " + result);
});