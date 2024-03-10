package com.codexdroid.m3compose.ui.utils

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.codexdroid.m3compose.R

enum class SCREEN { HOME, COMPONENT, COMPONENT_DETAILS, PLAYGROUND }

val purposes = arrayOf(
    R.string.str_purpose_1,
    R.string.str_purpose_2,
    R.string.str_purpose_3,
    R.string.str_purpose_4,
)

//Todo: Change this custom applied font to default in Theme file, check occurrences
val fontMontserrat = FontFamily(Font(R.font.montserrat))
val fontMontserratBold = FontFamily(Font(R.font.montserrat_bold))

data class DevData(
    val icon:Int,
    val text: Int,
    val url: String
)
val devDetails = listOf(
    DevData(icon = R.drawable.ic_dev_profile, text = R.string.str_profile, url = AppConstants.URLS.PROFILE),
    DevData(icon = R.drawable.ic_asia, text = R.string.str_compose, url = AppConstants.URLS.COMPOSE),
    DevData(icon = R.drawable.ic_linked_in, text = R.string.str_likedIn, url = AppConstants.URLS.LINKEDIN),
    DevData(icon = R.drawable.ic_github, text = R.string.str_git1, url = AppConstants.URLS.GIT_MAIN),
    DevData(icon = R.drawable.ic_github, text = R.string.str_git2, url = AppConstants.URLS.GIT_BACKUP)
)