package com.plcoding.doodlekong.data.remote.ws.models

import com.plcoding.doodlekong.data.remote.ws.models.BaseModel
import com.plcoding.doodlekong.util.Constants.TYPE_DRAW_DATA

data class DrawData(
    val rooName: String,
    val color:Int,
    val thickness:Float,
    val fromX: Float,
    val fromY: Float,
    val toX: Float,
    val toY:Float,
    val motionEvent: Int
): BaseModel(TYPE_DRAW_DATA)