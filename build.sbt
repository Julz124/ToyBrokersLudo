val scala3Version = "3.1.1"

lazy val root = project
  .in(file("."))
  .settings(
    name := "ToyBrokersLudo",
    version := "0.1.0-SNAPSHOT",
    scalaVersion := scala3Version,
    javacOptions ++= Seq("-encoding", "UTF-8"),
    libraryDependencies ++= Seq(
      "org.scalactic" %% "scalactic" % "3.2.13",
      "org.scalatest" %% "scalatest" % "3.2.13" % "test"
    ),
    libraryDependencies += ("org.scala-lang.modules" %% "scala-swing" % "3.0.0").cross(CrossVersion.for3Use2_13),
    libraryDependencies += ("com.typesafe.play" %% "play-json" % "2.9.3").cross(CrossVersion.for3Use2_13),// JSON
    libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "2.0.1", //XML
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