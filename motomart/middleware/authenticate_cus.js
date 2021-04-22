const jwt = require("jsonwebtoken");
const user = require("../models/customer")
module.exports.verifyUser  = function(req,res,next){

try{
//    console.log(req.headers.authorization.split(" ")[1])
    const token = req.headers.authorization.split(" ")[1];
    const data = jwt.verify(token, "secretkey")
    user.findOne({_id : data.uid})
    .then(function(result){
        req.user = result;// console.log(result)
        res.status(200).json({message : "auth success!!"})
        next();
    })

    .catch(function(result){
        //invalid
        res.status(403).json({error : "failed"})
    })

    
}
    catch(e){
        res.status(403).json({error : e})
    }
}

//second guard

module.exports.verifyAdmin = function(req, res, next){
    if(!req.user){
        return res.status(401).json({message : "unauthorized1111"});
    }

else if (req.user.userType!=="Admin"){

    return res.status(401).json({message : "unauthorized"});


}
next();
    }

    
module.exports.verifyStaff = function(req, res, next){
    if(!req.user){
        return res.status(401).json({message : "verified1111"});
    }

else if (req.user.userType!=="Staff"){

    return res.status(401).json({message : "unverified"});


}
next();
    }
