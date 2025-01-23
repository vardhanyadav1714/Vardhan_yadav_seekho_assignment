package innovatixhub.`in`.vardhan_yadav_seekho.data

data class Data(

    val episodes: Int=0,

    val genres: List<Genre> = emptyList(),
    val images: Images=Images(),
    val licensors: List<Licensor> = emptyList(),
    val mal_id: Int =0,
    val members: Int =0,

    val rank: Int =0,
    val rating: String="",

    val synopsis: String ="",

    val title: String ="",

    val trailer: Trailer = Trailer(),

    val year: Int=0
)