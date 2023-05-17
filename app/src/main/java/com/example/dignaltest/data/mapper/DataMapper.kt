package com.example.dignaltest.data.mapper

class DataMapper @Inject constructor() {

    private fun mapAlbumModel(response: AlbumModel): Album {
        return Album(
            title = response.title,
            url = response.url,
            thumbnailUrl = response.thumbnailUrl
        )
    }

    fun mapList(response: List<AlbumModel>): List<Album> {
        return response.map {
            mapAlbumModel(it)
        }
    }

}