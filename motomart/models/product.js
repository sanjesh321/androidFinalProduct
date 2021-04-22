const mongoose = require("mongoose")//third
const product = mongoose.model("product",{
    Product_name :{ //product_Name ko satta Product_name
        type :String
    },
    Product_price: {//same as product_name
        type : String
    },
    Product_details: {//same as product_name
        type : String
    },
    Product_Catergory :{//same as product_name
    enum:['Helmet','Gloves'],//categories
    type : String
    }


})
module.exports = product;