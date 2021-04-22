const mongoose = require("mongoose");
const customer = mongoose.model("customer",{
    firstname :{
        type :String
    },
    lastname :{
        type : String
    },
    address : {
        type : String
    },
    email:{
        type : String,
        required : true,
        unique : true
    },
    image : {
        type : String
    },
    username:{
        type : String,
        required : true
    },
    password: {
        type : String,
        required : true,
        unique : true
    },
    userType :{
        type : String,
        enum : ["Admin", "Customer", "Staff"]
    }
    
   
    

})
module.exports = customer;