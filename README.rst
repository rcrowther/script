======
Script
======

Java for the weary
==================
``script`` aims to be most trivial Scala library ever. In places, it is currently bad but, where it suceeds, it is very trivial, ::

    import script.file.Entry

    val txt: String = Entry.read(path)


I've used it more than once, so it gets to be split away. It was/is intended for purposes such as scripting, REPL work, and fly testing.

Compiling (and jars)
====================
``script`` compiles with ``ssc``. If this is not available, use the shell script. A compile script exists because ``script`` is trivial. 

...and if that doesn't fit your workflow, rebuild the folders and write your definitions for Eclipse/SBT/Maven, whatever.

Documentation
=============
Use SSC or the ``make_docs`` script.

