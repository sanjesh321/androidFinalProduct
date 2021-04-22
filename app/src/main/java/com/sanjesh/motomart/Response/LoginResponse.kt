import com.sanjesh.motomart.Entity.User

data class Login_Response(
    val success:Boolean?=null,
    val message:String?=null,
    val token:String?=null,
    val data: User?=null
)
