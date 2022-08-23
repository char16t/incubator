package com.manenkov.sandbox.iris

import ai.onnxruntime.{OnnxTensor, OnnxValue, OrtEnvironment, OrtSession}

import java.util
import scala.jdk.CollectionConverters._
import scala.jdk.OptionConverters._

object Main extends App {

  val ff = new JImpl
  println(ff.m(0.01f, 0.02f, 3f, 0.01f))
}
