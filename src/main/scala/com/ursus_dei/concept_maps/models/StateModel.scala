package com.ursus_dei.concept_maps.models

import java.io._

class StateModel {

  val stateDirectory = System.getProperty("user.home")

  def title = { "Ursus Dei : Concept Maps"}

  def save = {

    val pw = new PrintWriter(new File(stateDirectory + "/.com.ursus_dei.concept_maps" ))
    pw.write("Hello, world")
    pw.close
  }

}
