ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.2.0"

lazy val root = (project in file("."))
  .settings(
    name := "ToyBrokersLudo",
    libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.14",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.14" % "test",
    //libraryDependencies +="org.apache.commons" % "commons-lang3" % "3.4",
    //libraryDependencies += "org.apache.commons" % "commons-io" % "1.3.2",
    //libraryDependencies += "org.scoverage" % "sbt-scoverage_2.12_1.0" % "2.0.6"
  )


