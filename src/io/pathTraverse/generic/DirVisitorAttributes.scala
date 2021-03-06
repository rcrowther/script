package script.io.pathTraverse.generic

import java.nio.file._
import java.nio.file.attribute.BasicFileAttributes



/** A visitor for directory files.
  */
class DirVisitorAttributes[U](f: (BasicFileAttributes) => U)
extends SimpleFileVisitor[Path]
{


      override def preVisitDirectory(
        path: Path,
        attrs: BasicFileAttributes
      ) : FileVisitResult = try {
        f(attrs)
        FileVisitResult.CONTINUE
      } catch {
        case _ : Throwable => FileVisitResult.TERMINATE
      }
  

}//DirVisitor
