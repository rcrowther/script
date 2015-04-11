package script.io

import java.nio.file.Path
import java.io.File
import java.nio.file.attribute.FileTime


import scala.language.implicitConversions


/** Helper code for filehandling.
  *
  * == File manipulation objects ==
  *
  * These helpers are split into three categories, Dir (a directory file),
  * Entry (a regular file) and Link (a soft symbolic link).
  *
  * The objects offer basic CRUD operations, where appropriate. They offer no
  * guarantees about copying attributes, as Java (currently) will not,
  * and to do so reduces their scope of operation substancially.
  *
  * == Class wraps ==
  *
  * Items include,
  *
  *  - [[script.io.file.PathOps]] - very useful, takes a Path and returns the entry (fileName), entry extensions, etc.
  *  - [[script.io.file.FileTimeOps]] - wraps `java.nio.file.attribute.FileTime` to make it ordered i.e. will work with '<', '==' etc.
  *  - [[script.io.file.StringOps]] - very simple, but reduces much code by wrapping String so it has a `toPath` method.
  *
  * == Usage ==
  *
  * Importing the complete helper package i.e.
  *
  *{{{
  * import script.io.file._
  *}}}
  *
  * imports the package object, which has implicits to the items
  * listed above, and alias definitions for the `throwing`
  * objects. For example, a string gains a
  * `toPath` method.
  */
package object file {

  implicit def stringToPath(v: String) : StringOps = new StringOps(v)

  implicit def fromPath(p: Path) = new PathOps(p)
  implicit def toPath(op: PathOps): Path = op.v

  implicit def augmentFileTime(x: FileTime): FileTimeOps = new FileTimeOps(x)
  implicit def unaugmentFileTime(x: FileTimeOps): FileTime = x.v

  val PathEmpty = new File("").toPath

  val Entry = script.io.file.throwing.Entry
  val Dir = script.io.file.throwing.Dir
  val Link = script.io.file.throwing.Link

}//support
