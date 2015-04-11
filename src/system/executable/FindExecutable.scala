package script.system.executable

import java.nio.file.Path
import java.lang.System
import script.io.file._


/** templates classes and objects seeking system-wide executables.
*
* Asks that descendant classes can, or try to, return the path of an executable (via an instance of [[script.system.executable.ExecuteData]]) and a [[script.system.executable.Version]],
*  
*/
trait FindExecutable
    extends script.system.noThrowing.Shell
{
  /** Returns the version of the found executable.
    */
  def version : Option[Version]

  /**
    *@param customPath user can override the executable path
    *@param verbose if true called tools are made verbose
    *@param required
    *@param versionRequired
    */
  def find(
    customPath: Path,
    verbose: Boolean,
    required: Boolean,
    versionRequired: Version
  )
      : Option[ExecuteData]


  def find(
    verbose: Boolean,
    required: Boolean,
    versionRequired: Version
  )
      : Option[ExecuteData] =
  {
    find(
      PathEmpty,
      verbose,
      required,
      versionRequired
    )
  }

  /** Tells if the system is a Windows OS
    */
  // Something more sophisticated sometime, maybe,
  // or maybe this will do?
  def isWindows
      : Boolean =
  {
    System.getProperty("os.name").startsWith("Windows")
  }

}//FindExecutable



object FindExecutable {

  def apply(appName: String)
      : Generic =
  {
    new Generic(appName)
  }

}//FindExecutable
