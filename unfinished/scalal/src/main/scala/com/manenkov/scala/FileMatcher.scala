package com.manenkov.scala

import java.io.File

object FileMatcher {
  private def filesHere = new java.io.File(".").listFiles

  def filesEnding(query: String): Array[File] =
    filesMatching(query, _.endsWith(_))

  def filesContaining(query: String): Array[File] =
    filesMatching(query, _.contains(_))

  def filesRegex(query: String): Array[File] =
    filesMatching(query, _.matches(_))

  def filesMatching(query: String, matcher: (String, String) => Boolean): Array[File] =
    for (file <- filesHere; if matcher(file.getName, query))
      yield file
}
