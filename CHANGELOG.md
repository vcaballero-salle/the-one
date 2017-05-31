# SBT changes

The project has been restructured to comply with SBT (Simple Build Tool) default structure.
SBT manages dependencies now.
SBT is now needed to run the project. You can download sbt at: http://www.scala-sbt.org/download.html
* To run the project type `sbt run`.
This will run the project with the settings located in `extra/default_settings.txt`.
* To pass command line arguments to the run command run `sbt "run <other_settings"`.
* To execute tests issue `sbt test`

