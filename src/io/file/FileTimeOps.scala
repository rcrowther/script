package script.io.file

import java.nio.file.attribute.FileTime


/** Wraps Java FileTime.
  *
  * Gives Scala-consistent comparison, wrap and string operations.
  *
  * {{{
  * import sake.FileTimeOps._
  * }}}
  */
final class FileTimeOps(val v: FileTime) extends AnyVal
{
  def <(other: FileTime) = v.compareTo(other) < 0
  def >(other: FileTime) = v.compareTo(other) > 0
  def ==(other: FileTime) = v.compareTo(other) == 0
  def ===(other: FileTime) = v.equals(other)

  override def toString() = "FileTime(" + v + ')'

}//FileTimeOps

