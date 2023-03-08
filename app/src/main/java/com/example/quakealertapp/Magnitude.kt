package com.example.quakealertapp

enum class Magnitude {
    BARELY_NOTICEABLE,
    WEAK,
    MEDIUM,
    STRONG,
    VERY_STRONG;

    val title:Int
    get() = when(this){
        BARELY_NOTICEABLE -> R.string.enum_barely_noticeable
        WEAK -> R.string.enum_weak
        MEDIUM -> R.string.enum_medium
        STRONG -> R.string.enum_strong
        VERY_STRONG -> R.string.enum_very_strong

    }

    val color:Int
    get() = when(this){
        BARELY_NOTICEABLE -> R.color.enum_barely_noticeable
        WEAK -> R.color.enum_weak
        MEDIUM -> R.color.enum_medium
        STRONG -> R.color.enum_strong
        VERY_STRONG -> R.color.enum_very_strong
    }

    companion object{
        fun getMagnitude(magnitude: Double):Magnitude{
            return when(magnitude){
                in 1.0..2.0 -> BARELY_NOTICEABLE
                in 2.01..3.0 -> WEAK
                in 3.01..4.5 ->MEDIUM
                in 4.51..5.99 -> STRONG
                else -> VERY_STRONG
            }
        }
    }

}