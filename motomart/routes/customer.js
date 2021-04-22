const express = require("express");
const router = express.Router();
const customer = require("../models/customer")
const {check, validationResult} = require("express-validator");
const bcryptjs = require("bcryptjs");
const { JsonWebTokenError } = require("jsonwebtoken");
const jsonwebtoken = require("jsonwebtoken")



router.post("/login", function(req, res){
    const username  = req.body.username;
    const password = req.body.password;

    customer.findOne({username : username})
    .then(function(customerData){
        if(customerData===null){
         //return from here
            return res.status(401).json({message : "invalid credentials"})
            
            //if username exists

        }
        
        bcryptjs.compare(password, customerData.password, function(err, result){

            if(result===false){
                return res.status(401).json({message : " credentials"})
            }
        
            const token = jsonwebtoken.sign({uid : customerData._id}, "secretkey");   
            res.status(200).json({message : "auth success!!", token :token})
            
        })

    })
    .catch()




    
})





router.post("/register", [

    check("address", "address is required!").not().isEmpty(),
    check("email", "email is required!").not().isEmpty(),
    check("password", "password is required!").not().isEmpty(),
    check("username", "Email is required!").not().isEmpty(),
  
], function(req, res){
    const errors = validationResult(req);

    //valid

    if(errors.isEmpty()){

    const address = req.body.address;
    const email = req.body.email;
    const userType = req.body.userType;
    const username = req.body.username;
    const password = req.body.password;
 

    bcryptjs.hash(password, 10, function(err, hash){
        
    const data = new customer({address : address, email : email, userType : userType, username : username, password : hash});
    data.save()

    .then(function(result){
        res.status(201).json({
            message: "data registered successfully!!"
        })
    })
.catch(function(err){
        res.status(500).json({
            error : err
        })
    })
    

})

  

    }
    else{
       //invalid
        res.status(400).json(errors.array())
    }
    //res.send(errors.array())

    //const fn1 = req.body.fn;
    //const ln1 = req.body.ln;
    //const email1 = req.body.email;
    //const un1 = req.body.un;
    //const pw1 = req.body.pw;

    //const data = new Register({firstname:fn1, lastname:ln1, email : email1, username : un1, password : pw1});
    //data.save();
    //res.send("inserted!!")
});




module.exports = router;