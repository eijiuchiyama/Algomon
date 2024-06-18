package com.algomon.game.event

import com.algomon.game.component.AnimationModel
import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell
import com.badlogic.gdx.scenes.scene2d.Event
import com.badlogic.gdx.scenes.scene2d.Stage
import ktx.app.KtxScreen

fun Stage.fire(event: Event){
    this.root.fire(event)
}
data class MapChangeEvent(val map: TiledMap) : Event()

class ScreenChangeEvent(val path: String) : Event()

class CollisionDespawnEvent(val cell:Cell) : Event()

class EntityOpenEvent(val model: AnimationModel) : Event()
