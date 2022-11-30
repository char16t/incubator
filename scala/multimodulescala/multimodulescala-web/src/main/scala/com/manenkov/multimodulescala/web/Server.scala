package com.manenkov.multimodulescala.web

import com.manenkov.multimodulescala.core.Hello

object Server {
  def m(a: Int, b: Int) = Hello.plus(a, b)
}
