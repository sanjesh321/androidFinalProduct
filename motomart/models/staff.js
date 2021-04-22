const mongoose = require("mongoose")
const staff = mongoose.model("staff",{
    Customer_Name :{
        type :String
    },
     Customer_ID: {
        type : String
    },
    Customer_Book: {
        type : String
    }
})
module.exports = staff