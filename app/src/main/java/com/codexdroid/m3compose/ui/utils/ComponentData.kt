package com.codexdroid.m3compose.ui.utils

import com.codexdroid.m3compose.R

data class ComponentData (
    val componentName: Int = 0,
    val componentOutput: String = "",
    val componentCode: String = ""
)

val componentsList = listOf(
    ComponentData(R.string.comp_app_bar_bottom1,AppConstants.AppBar.BOTTOM_OUTPUT_1,AppConstants.AppBar.BOTTOM_CODE_1),
    ComponentData(R.string.comp_app_bar_bottom2,AppConstants.AppBar.BOTTOM_OUTPUT_2,AppConstants.AppBar.BOTTOM_CODE_2),
    ComponentData(R.string.comp_app_bar,AppConstants.AppBar.TOP_OUTPUT_0,AppConstants.AppBar.TOP_CODE_0),
    ComponentData(R.string.comp_app_bar_top1,AppConstants.AppBar.TOP_OUTPUT_1,AppConstants.AppBar.TOP_CODE_1),
    ComponentData(R.string.comp_app_bar_top2,AppConstants.AppBar.TOP_OUTPUT_2,AppConstants.AppBar.TOP_CODE_2),


    ComponentData(R.string.comp_badges,AppConstants.Badge.OUTPUT,AppConstants.Badge.CODE),
    ComponentData(R.string.comp_button_elevated,AppConstants.Button.ELEVATED_OUTPUT,AppConstants.Button.ELEVATED_CODE),
    ComponentData(R.string.comp_button_fill,AppConstants.Button.FILLED_OUTPUT,AppConstants.Button.FILLED_CODE),
    ComponentData(R.string.comp_button_outline,AppConstants.Button.OUTLINE_OUTPUT,AppConstants.Button.OUTLINE_CODE),
    ComponentData(R.string.comp_button_text,AppConstants.Button.TEXT_OUTPUT,AppConstants.Button.TEXT_CODE),
    ComponentData(R.string.comp_button_icon,AppConstants.Button.ICON_OUTPUT,AppConstants.Button.ICON_CODE),
    ComponentData(R.string.comp_button_segmented,AppConstants.Button.SEGMENTED_OUTPUT,AppConstants.Button.SEGMENTED_CODE),
    ComponentData(R.string.comp_button_fab,AppConstants.Button.FAB_OUTPUT,AppConstants.Button.FAB_CODE),
    ComponentData(R.string.comp_card,AppConstants.Card.CARD_OUTPUT,AppConstants.Card.CARD_CODE),
//    ComponentData(R.string.comp_carousel,"",""),
    ComponentData(R.string.comp_checkbox,AppConstants.CheckBox.OUTPUT,AppConstants.CheckBox.CODE),
    ComponentData(R.string.comp_chip,AppConstants.Chip.OUTPUT,AppConstants.Chip.CODE),
    ComponentData(R.string.comp_date_picker,AppConstants.DatePicker.OUTPUT,AppConstants.DatePicker.CODE),
    ComponentData(R.string.comp_date_range_picker,AppConstants.DateRangePicker.OUTPUT,AppConstants.DateRangePicker.CODE),
    ComponentData(R.string.comp_dialog,AppConstants.Dialog.OUTPUT,AppConstants.Dialog.CODE),
    ComponentData(R.string.comp_divider,AppConstants.Divider.OUTPUT,AppConstants.Divider.CODE),
    ComponentData(R.string.comp_list,AppConstants.LazyList.OUTPUT,AppConstants.LazyList.CODE),
    ComponentData(R.string.comp_popup,AppConstants.PopUp.OUTPUT,AppConstants.PopUp.CODE),

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