const mongoose = require("mongoose")//third
mongoose.connect("mongodb://127.0.0.1:27017/motomart",{
    useNewUrlParser: true,
    useCreateIndex: true,
    useUnifiedTopology : true
   
})