package com.princesch.profdevelop.view.base

import com.princesch.profdevelop.model.data.AppState

interface View {
    fun renderData(appState: AppState)
}