package script.io.pathTraverse.generic

import java.nio.file._
import java.nio.file.attribute.BasicFileAttributes



/** A visitor for entry files.
*
* Note this icludes symlinks to regular files, even if not following
* directory links.
  */
class EntryVisitorPathAttributes[U](f: ((Path, BasicFileAttributes)) => U) extends SimpleFileVisitor[Path] {

  override def visitFile(
    path: Path,
    attrs: BasicFileAttributes
  ) : FileVisitResult = try {
    // The visitor will pick up every file
    if(Files.isRegularFile(path)) f(path, attrs)
    FileVisitResult.CONTINUE
  } catch {
    case _ : Throwable => FileVisitResult.TERMINATE
  }
}
