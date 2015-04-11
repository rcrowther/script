package script


/** Helper code for system operations.
  *
  * == Packages ==
  *
  *  - [[script.system.executable]] - Systemizes the finding and running executables through the shell. 
  *
  * Currently feeble, they may improve?
  *
  * == Supplementary items ==
  *
  * Include,
  *
  *  - Shell traits - Launch system processes, via a shell, with Ruby-like sh(<string>) syntax.
  *
  *  - [[script.system.Path]] - can provide the `launchDirectory` of an application (the 'current working directory').
  *
  * == Usage ==
  *
  * Importing the complete helper package i.e.
  *
  *{{{
  * import script.system._
  *}}}
  *
  * imports the package object, which has implicit alias definitions for the `throwing`
  * objects.
  */
package object system {

  type Shell = script.system.throwing.Shell

}//support
