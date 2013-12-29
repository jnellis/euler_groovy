package net.jnellis.util

/**
 * Created with IntelliJ IDEA.
 * User: Joe
 * Date: 12/18/13
 * Time: 7:37 AM
 * To change this template use File | Settings | File Templates.
 */
class Benchmark {
  static def time(closure) {
    def start = System.currentTimeMillis()
    closure.call()
    def end = System.currentTimeMillis()
    (end - start) / 1000
  }
}
