
const express = require("express");
const product = require("../models/product");
const router = express.Router();
const authenticate_cus = require("../middleware/authenticate_cus")
const photoupload = require("../middleware/photoupload")


router.post("/product/insert", photoupload.single('Book_Image'),function(req, res){
    // authenticate_cus.verifyUser, authenticate_cus.verifyAdmin, 
    console.log(req.file);

    if(req.file == undefined){
        return res.status(400).json({message : "invalid image"})
    }
    const product_Name= req.body.product_Name;
    const Book_Id = req.body.Book_Id;
    const product_number = req.body.product_number;
    const data = new product({product_Name:product_Name, Book_Id:Book_Id, product_number: product_number,Book_Image : req.file.path});
    data.save()
    .then(function(result){
        res.status(201).json({message: "inserted"})
    })

    .catch(function(e){
        res.status(500).json({message : e})

    })

})

router.get("/product/fetch", function(req, res){
    product.find().then(function(productdata){
        res.send(productdata);

    })

    })

router.get("/product/fetchall", function(req,res){
    product.find().then(function(productdata){
    const product_Name = productdata.product_Name;
    
        res.status(200).json({
            message : "ok",
            product_Name : product_Name,
        
        })
    })
    .catch(function(e){
        res.status(500).json({
            error : e
        })
    })
})







//for delete

router.delete("/product/delete/:id",function(req, res){
    // authenticate_cus.verifyAdmin, authenticate_cus.verifyUser, 
 const id = req.params.id;
    product.deleteOne({_id:id})


        .then(function(result){
            res.status(201).json({message: "deleted"})
        })
    
        .catch(function(e){
            res.status(500).json({message : e})
    
    
        });

    })

        


    //     res.status(200).json({message : err})

    //     .catch(function(){
            
    // res.status(200).json({message : err})
    //     })
       




router.put("/product/update/:id", function(req, res){
    const id = req.params.id;
    const product_Name = req.body.product_Name
    const product_number = req.body.product_number
    const Book_Id = req.body.Book_Id
    product.updateOne({_id:id},{product_Name:product_Name, Book_Id:Book_Id, product_number: product_number}).then(function(){
        res.send("updated")
    })

    
})



module.exports = router;