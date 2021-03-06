package script.system.noThrowing


import java.io.File
import java.nio.file.{Paths, Path, Files}
import sys.process._

import scala.language.postfixOps

/** Provides shell utilities.
  *
  * Insert commandline options as seperate string items e.g.
  *
  * {{{ 
  *   sh("ls", "-a")
  * }}}
  *
  * If used for Bash-style shells, enter each switch and parameter
  * separately e.g.
  *
  * {{{ 
  *   sh(Seq("cp", "-v", "/home", "/usb"))
  * }}}
  *
  */
trait Shell {


  //TODO: Non-blocker? How does that work?

  /** Invoke a shell(like) command.
    * 
    * Catches stdOut as a string.
    *
    * Blocks.
    *
    * See [[http://www.scala-lang.org/api/current/index.html#scala.sys.process.ProcessBuilder]]
    */
  def sh(xs: Seq[String])
      : Either[Exception, String] =
  {
    try {
      //if (opts.verbosely) trace(xs.mkString("shell call", " ", ""))
      Right(xs !!)
    }
    catch {
      case e: Exception =>
        Left(e)
    }
  }

  /** Invoke a shell(like) command.
    *
    * Catches stdOut as a string.
    *
    * Blocks.
    */
  def sh(s: String): Either[Exception, String] = sh(Seq(s))


  /** Invoke a shell(like) command.
    * 
    * Catches stdOut and stdErr as a string.
    *
    * If errors are thrown (non-zero) returns stdErr.
    * Blocks.
    *
    * See [[http://www.scala-lang.org/api/current/index.html#scala.sys.process.ProcessBuilder]]
    */
  // This runt I trust... or it's the one I trust, not the same thing.
  //Throws non-zero errors?
  def shCatch(xs: Seq[String])
      : (Int, String, String) =
  {
    val bout = new StringBuilder()
    val berr = new StringBuilder()

    val pl = new ProcessLogger {
      def out(s: => String): Unit = {
        bout ++= s
        bout ++= System.lineSeparator()
      }
      def err(s: => String): Unit = {
        berr ++= s
        berr ++= System.lineSeparator()
      }
      def buffer[T](f: => T): T = f
    }

    val retCode = xs ! (pl)

    (retCode, berr.result(), bout.result())
  }

  /** Invoke a shell(like) command.
    * 
    * Catches stdOut and/or stdErr as a string.
    *
    * Blocks.
    *
    * See [[http://www.scala-lang.org/api/current/index.html#scala.sys.process.ProcessBuilder]]
    */
  // This, untested...
  def shCatch(xs: Seq[String], stdOut: Boolean, stdErr: Boolean)
      : Either[Exception, String] =
  {
    try {
      val b = new StringBuilder()
      val pl = new ProcessLogger {
        def out(s: => String): Unit = if(stdOut) {b ++= s; b ++= System.lineSeparator()}
        def err(s: => String): Unit = if(stdErr) {b ++= s; b ++= System.lineSeparator()}
        def buffer[T](f: => T): T = f
      }

      xs !! pl
      Right(b.result())
    }
    catch {
      case e: Exception =>
        Left(e)
    }
  }

  //TODO: can '!' throw an error?
  // Should return a bad exit code through Either?
  /** Invoke a shell(like) command, printing to out.
    *
    * Blocks.
    *
    * Throws an exception on non-zero exit codes.
    *
    * See [[http://www.scala-lang.org/api/current/index.html#scala.sys.process.ProcessBuilder]]
    */
  def shPrint(xs: Seq[String])
      : Either[Exception, Int] =
  {
    //if (options.verbosely) trace(seq.mkString)
    try {
      Right(xs !)
    }
    catch {
      case e: Exception =>
        Left(e)
    }
  }

  /** Invoke a shell(like) command, printing to out.
    *
    * Blocks.
    *
    * Throws an exception on non-zero exit codes.
    */
  def shPrint(s: String): Either[Exception, Int] = shPrint(Seq(s))

}//Utils
