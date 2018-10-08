package com.ursus_dei.concept_maps.controller

import com.ursus_dei.concept_maps.models.StateModel

object MainController {

  val state = new StateModel()

  //TODO Remove hardcoded values
  def windowTitle = state.title
  def windowWidth = 1000
  def windowHeight = 900

  def control = {
    println("under control")
    state.save
  }

}