package script.io.file

import java.nio.file.Path
import java.io.File


/** Wraps String to convert to Path.
  *
  * {{{
  * import sake.StringOps._
  * }}}
  */
final class StringOps(val v:String)
    extends AnyVal
{
  def toPath: Path = new File(v).toPath
}



