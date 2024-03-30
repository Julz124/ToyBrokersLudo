val scala3Version = "3.1.1"
val scalacticVersion = "3.2.17"
val scalatestVersion = "3.2.17"
val scalaSwingVersion = "3.0.0"
val playJsonVersion = "2.10.4"

lazy val root = project
  .in(file("."))
  .dependsOn(model)
  .settings(
    name := "ToyBrokersLudo",
    commonSettings
  )
  .enablePlugins(JacocoCoverallsPlugin)

lazy val model = project
  .in(file("Model"))
  .settings(
    name := "Model",
    commonSettings
  ).enablePlugins(JacocoPlugin)

lazy val commonSettings: Seq[Def.Setting[_]] = Seq(
  scalaVersion := scala3Version,
  javacOptions ++= Seq("-encoding", "UTF-8"),
  libraryDependencies ++= Seq(
    "org.scalactic" %% "scalactic" % scalacticVersion,
    "org.scalatest" %% "scalatest" % scalatestVersion % "test",
    "org.scala-lang.modules" %% "scala-swing" % scalaSwingVersion cross CrossVersion.for3Use2_13,
    "com.typesafe.play" %% "play-json" % playJsonVersion cross CrossVersion.for3Use2_13
  ),
  jacocoReportSettings := JacocoReportSettings(
    "Jacoco Coverage Report",
    None,
    JacocoThresholds(),
    Seq(JacocoReportFormats.ScalaHTML, JacocoReportFormats.XML),
    "utf-8"
  )
)
