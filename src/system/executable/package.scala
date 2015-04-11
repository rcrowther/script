package script.system



//import scala.language.implicitConversions



/** Helper code for finding and using executables.
  *
  * This package is intended for helping with shell invocation. The
  * code has little purpose if libraries have been imported and/or
  * placed on the classpath.
  *
  * [[script.system.executable.FindExecutable]] is at the base, templating more
  * refined classes targetting various known executables. If the
  * executable has no specialized class
  * [[script.system.executable.Generic]] can be used.
  *
  * The methods may return a [[script.system.executable.ExecuteData]],
  * which contains a Path and [[script.system.executable.Version]]. How
  * specialized classes handle this is their affair (they may return
  * `None`, to indicate they can not find anything).
  *
  */
package object executable {


}//support
