package com.manenkov.upsidemind.service

/**
  * Much magic. Very Lambda. Wow!
  *
  * These types provide union types (i.e. val a = (String or Int) within Scala. See here for good discussion on
  * how to represent union types in Scala (hat tip Miles Sabin)
  *   http://stackoverflow.com/a/37450446/1591351
  *
  * Core idea is to adapt Demorgan's Law:
  *   $$ \lnot(A \cup B) \Leftrightarrow (\lnot A \cap \lnot B) $$
  *
  * To type Algebra, which becomes:
  *   trait inv[-A]
  *   inv[A or B] :> (inv[A] with inv[B])
  *
  * Where we also use a contra-variant type, inv[-A], to give us a pseudo negation.
  *
  * The rest is to make the recursion work, so that when we write foo[A Or B Or C] (which becomes Or[Or[A,B],C])
  *
  * https://gist.github.com/aishfenton/2bb3bfa12e0321acfc904a71dda9bfbb
  */
object UnionType {

  trait inv[-A] {}

  sealed trait OrR {
    type L <: OrR
    type R
    type invIntersect
    type intersect
  }

  sealed class Or[A <: OrR, B] extends OrR {
    type L = A
    type R = B

    type intersect = (L#intersect with R)
    type invIntersect = (L#invIntersect with inv[R])
    type check[X] = invIntersect <:< inv[X]
  }

  object UNil extends OrR {
    type intersect = Any
    type invIntersect = inv[Nothing]
  }
  type UNil = UNil.type

}
