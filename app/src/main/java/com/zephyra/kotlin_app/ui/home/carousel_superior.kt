package com.zephyra.kotlin_app.ui.home

import androidx.constraintlayout.helper.widget.Carousel
import org.imaginativeworld.whynotimagecarousel.ImageCarousel
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

class carousel_superior {
    fun init_carousel(carousel: ImageCarousel){
        var list = mutableListOf<CarouselItem>()

        list.add(CarouselItem("https://i.redd.it/rfftqdg5flv71.jpg"))
        list.add(CarouselItem("https://img.freepik.com/premium-photo/3d-style-random-abstract-futuristic-technology-graphic-banner-background-wallpaper-generative-ai_159242-26294.jpg"))
        carousel.addData(list)
    }
}