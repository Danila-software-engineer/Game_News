package orders.appup_kw.gamenews.network


import com.google.gson.annotations.SerializedName


data class POJONews (
    @SerializedName("id")    val id: Int,
    @SerializedName("text")  val text: String,
    @SerializedName("name")  val name: String,
    @SerializedName("data")  val data: String,
    @SerializedName("likes") val likes: Int,
    @SerializedName("img")   val img: String,
)