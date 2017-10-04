name := "multimedia-launchdetector"

version := "0.1"

val circeVersion = "0.8.0"

scalaVersion := "2.12.3"

// https://mvnrepository.com/artifact/com.amazonaws/aws-java-sdk
libraryDependencies ++= Seq(
  //"com.amazonaws" % "aws-java-sdk" % "1.11.207",
  "com.amazonaws" % "aws-lambda-java-core" % "1.1.0",
  "com.amazonaws" % "aws-lambda-java-events" % "1.3.0"
)

// https://mvnrepository.com/artifact/com.gu/content-api-models-scala
libraryDependencies += "com.gu" % "content-api-models-scala" % "11.23" % "provided"

// https://mvnrepository.com/artifact/com.gu/content-api-models-json
libraryDependencies += "com.gu" % "content-api-models-json" % "11.23" % "provided"

// https://mvnrepository.com/artifact/org.scalatest/scalatest_2.12
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.4" % "test"


libraryDependencies ++= Seq(
  "io.circe" %% "circe-core",
  "io.circe" %% "circe-generic",
  "io.circe" %% "circe-parser"
).map(_ % circeVersion)

lazy val app = (project in file(".")).settings(
  organization := "com.theguardian",
  assemblyJarName in assembly := "first_test_lambda.jar"
)