package com.codexdroid.m3compose.ui.activities

import androidx.lifecycle.ViewModel
import com.codexdroid.m3compose.ui.utils.ComponentData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ScreenViewModel: ViewModel() {

    private val _componentData = MutableStateFlow(ComponentData())
    val componentData: StateFlow<ComponentData> = _componentData.asStateFlow()

    fun setSelectedComponent(componentData: ComponentData) {
        _componentData.update {
            it.copy(
                componentName = componentData.componentName,
                componentOutput = componentData.componentOutput,
                componentCode = componentData.componentCode
            )
        }
    }
}