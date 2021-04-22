const mongoose = require("mongoose")//third
const express = require("express")//third party module
const bodyParser = require("body-parser")//core module

const db = require("./database/db")
const customer = require("./routes/customer")
const product = require("./routes/product")
const staff = require("./routes/staff")



const app = express();
app.use(express.json());
app.use(bodyParser.urlencoded({ extended: false }));
app.use(customer)
app.use(product)
app.use(staff)






app.listen(90);