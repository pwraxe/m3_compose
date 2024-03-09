package com.codexdroid.m3compose.ui.utils

import com.codexdroid.m3compose.R

data class ComponentData (
    val componentName: Int = 0,
    val componentOutput: String = "",
    val componentCode: String = ""
)

val componentsList = listOf(
    ComponentData(R.string.comp_app_bar_bottom1,AppConstants.AppBar.BottomOutput1,AppConstants.AppBar.BottomCode1),
    ComponentData(R.string.comp_app_bar_bottom2,AppConstants.AppBar.BottomOutput2,AppConstants.AppBar.BottomCode2),

    ComponentData(R.string.comp_app_bar_top1,"",""),
    ComponentData(R.string.comp_app_bar_top2,"",""),
    ComponentData(R.string.comp_badges,"",""),
    ComponentData(R.string.comp_button_elevated,"",""),
    ComponentData(R.string.comp_button_fill,"",""),
    ComponentData(R.string.comp_button_fill_tonal,"",""),
    ComponentData(R.string.comp_button_outline,"",""),
    ComponentData(R.string.comp_button_text,"",""),
    ComponentData(R.string.comp_button_icon,"",""),
    ComponentData(R.string.comp_button_segmented,"",""),
    ComponentData(R.string.comp_button_fab,"",""),
    ComponentData(R.string.comp_button_extended,"",""),
    ComponentData(R.string.comp_card,"",""),
    ComponentData(R.string.comp_carousel,"",""),
    ComponentData(R.string.comp_checkbox,"",""),
    ComponentData(R.string.comp_chip,"",""),
    ComponentData(R.string.comp_date_picker,"",""),
    ComponentData(R.string.comp_dialog,"",""),
    ComponentData(R.string.comp_divider,"",""),
    ComponentData(R.string.comp_list,"",""),
    ComponentData(R.string.comp_menu,"",""),
    ComponentData(R.string.comp_navigation_bar,"",""),
    ComponentData(R.string.comp_navigation_drawer,"",""),
    ComponentData(R.string.comp_navigation_rail,"",""),
    ComponentData(R.string.comp_progress_indicator,"",""),
    ComponentData(R.string.comp_radio_button,"",""),
    ComponentData(R.string.comp_search,"",""),
    ComponentData(R.string.comp_sheet_bottom,"",""),
    ComponentData(R.string.comp_sheet_side,"",""),
    ComponentData(R.string.comp_slider,"",""),
    ComponentData(R.string.comp_snackbar,"",""),
    ComponentData(R.string.comp_button_switch,"",""),
    ComponentData(R.string.comp_tab,"",""),
    ComponentData(R.string.comp_text_field,"",""),
    ComponentData(R.string.comp_time_picker,"",""),
    ComponentData(R.string.comp_tooltip,"","")
)