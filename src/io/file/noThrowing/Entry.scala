package script.io.file.noThrowing

import java.nio.file.Path
import java.nio.file.Files
import java.nio.file.LinkOption
import java.nio.file.attribute.FileAttribute
import java.nio.charset.Charset
import scala.collection.JavaConversions._

/** Operations for (non-directory) files.
  *
  *
  * These utilites are not atomic, and not to be used for secure running.
  * They assume uninterfered access.
  *
  * These utilities all throw errors freely. For the purposes of Sake,
  * this is assumed (Sake traps errors).
  *
  * These utilities are forceful. To do as asked, they will overwrite and
  * destroy.
*
* The methods are placed on a static object. Usage:
*
* {{{ Entry.create("/home/bill/lock.java")}}}
*
* This representaion is preferred due to the now extensive use
* of prefix notation in Java.
*
* @define obj entry
  */
object Entry 
//extends GenNonSymbolicFile
{

 
  //TODO: Should be an attribute version
  def create(path:Path)
      : Boolean =
  {
    if(Files.exists(path)) false
    else {
      Files.createFile(path)
      true
    }
  }

  /** Reads text from a $obj.
    * 
    * Not a good method for big $obj, but ok for utilities.
    *
    * @param charset Something like `StandardCharsets.UTF_8` is fine.
    */
  def read(path: Path, charset: Charset)
      : Either[Exception, Traversable[String]] =
  {
    try {
    Right(Files.readAllLines(path, charset))
    }
    catch {
     case e: Exception => Left(e)
    }
  }

  /** Write text to a $obj.
    * 
    * Creates a $obj if it doesn't exist, overwrites anything there.
    *
    * If it fails, may leave the file mangled.
    *
    * @param charset Something like `StandardCharsets.UTF_8` is fine.
    */
  def write(
    path: Path,
    lines: Seq[String]
      , charset: Charset
  )
      : Boolean =
  {
    Files.write( path,
      //Iterable<? extends CharSequence> lines,
      lines,
      charset
    )
    true
  }



  def delete(path: Path)
      : Boolean =
  {
    // Protect against dirs
    if (Files.exists(path) && !Files.isRegularFile(path)) false
    else {
      Files.deleteIfExists(path)
      !exists(path)
    }
  }


  def exists(path:Path, followLinks: Boolean): Boolean = {
    if (followLinks) {
      Files.isRegularFile(path)
    }
    else {
      Files.isRegularFile(path, LinkOption.NOFOLLOW_LINKS) 
    }
  }


  def exists(path:Path): Boolean = exists(path, false)

  /** Tests if a $obj exists and is executable.
    *
    * Will not follow symlinks.
    */
  def isExecutable(path:Path): Boolean = Files.isExecutable(path)

}//Entry
