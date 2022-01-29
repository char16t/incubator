package com.manenkov.upsidemind.service

case class Request (
  jsonrpc: String,
  id: Int,
  method: String,
  params: List[Int],
)
