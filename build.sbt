val scala3Version = "3.1.1"
val scalacticVersion = "3.2.17"
val scalatestVersion = "3.2.17"
val scalaSwingVersion = "3.0.0"
val playJsonVersion = "2.10.4"

lazy val root = project
  .in(file("."))
  .dependsOn(model, tools, persistence, core, ui)
  .settings(
    name := "ToyBrokersLudo",
    commonSettings,
    jacocoReportSettings := JacocoReportSettings(
      "Jacoco Coverage Report",
      None,
      JacocoThresholds(),
      Seq(JacocoReportFormats.ScalaHTML, JacocoReportFormats.XML),
      "utf-8"
    ),
    jacocoExcludes := Seq(
      "de.htwg.se.mill.Mill*",
      "de.htwg.se.mill.util*"
    ),
    jacocoCoverallsServiceName := "github-actions",
    jacocoCoverallsBranch := sys.env.get("CI_BRANCH"),
    jacocoCoverallsPullRequest := sys.env.get("GITHUB_EVENT_NAME"),
    jacocoCoverallsRepoToken := sys.env.get("COVERALLS_REPO_TOKEN")
  )
  .enablePlugins(JacocoCoverallsPlugin)

lazy val ui = project
  .in(file("UI"))
  .dependsOn(model, tools, persistence, core)
  .settings(
    name := "UI",
    commonSettings
  ).enablePlugins(JacocoCoverallsPlugin)

lazy val core = project
  .in(file("Core"))
  .dependsOn(model, tools, persistence)
  .settings(
    name := "Core",
    commonSettings
  ).enablePlugins(JacocoCoverallsPlugin)

lazy val persistence = project
  .in(file("Persistence"))
  .dependsOn(model, tools)
  .settings(
    name := "Persistence",
    commonSettings
  ).enablePlugins(JacocoCoverallsPlugin)

lazy val tools = project
  .in(file("Tools"))
  .dependsOn(model)
  .settings(
    name := "Tools",
    commonSettings
  ).enablePlugins(JacocoCoverallsPlugin)

lazy val model = project
  .in(file("Model"))
  .settings(
    name := "Model",
    commonSettings
  ).enablePlugins(JacocoCoverallsPlugin)

lazy val commonSettings: Seq[Def.Setting[_]] = Seq(
  scalaVersion := scala3Version,
  javacOptions ++= Seq("-encoding", "UTF-8"),
  libraryDependencies ++= Seq(
    "org.scalactic" %% "scalactic" % scalacticVersion,
    "org.scalatest" %% "scalatest" % scalatestVersion % "test",
    "org.scala-lang.modules" %% "scala-swing" % scalaSwingVersion cross CrossVersion.for3Use2_13,
    "com.typesafe.play" %% "play-json" % playJsonVersion cross CrossVersion.for3Use2_13
  )
)
