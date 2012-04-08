import sbt._
import com.github.siasia._
import WebPlugin._
import PluginKeys._
import Keys._

object Build extends sbt.Build {
  import Dependencies._

  lazy val myProject = Project("MarkitScala", file("."))
    .settings(WebPlugin.webSettings: _*)
    .settings(port in config("container") := 8080)
    .settings(
      organization  := "com.webwino",
      version       := "0.1.0",
      scalaVersion  := "2.9.1",
      scalacOptions := Seq("-deprecation", "-encoding", "utf8"),
      resolvers     ++= Dependencies.resolutionRepos,
      libraryDependencies ++= Seq(
        Compile.akkaActor,
        Compile.sprayServer,
        Compile.sprayCan,
        Compile.jodaTime,
        Compile.jodaConvert,
        Compile.casbah,
        Compile.liftJson,
        Compile.apacheHttp,
        Test.specs2,
        Container.jettyWebApp,
        Container.akkaSlf4j,
        Container.slf4j,
        Container.logback
      )
    )
}

object Dependencies {
  val resolutionRepos = Seq(
    ScalaToolsSnapshots,
    "Typesafe repo" at "http://repo.typesafe.com/typesafe/releases/",
    "spray repo" at "http://repo.spray.cc/"
  )

  object V {
    val akka    = "2.0"
    val sprayServer   = "1.0-M1"
    val sprayCan = "1.0-M1"
    val specs2  = "1.7.1"
    val jetty   = "8.1.0.v20120127"
    val slf4j   = "1.6.4"
    val logback = "1.0.0"
    val jodaTime = "2.0"
    val jodaConvert = "1.2"
    val casbah = "2.1.5-1"
    val liftJson = "2.4"
    val apacheHttp = "3.1"
  }

  object Compile {
    val akkaActor   = "com.typesafe.akka"         %  "akka-actor"      % V.akka        % "compile"
    val sprayServer = "cc.spray"                  %  "spray-server"    % V.sprayServer % "compile"
    val sprayCan    = "cc.spray"                  %  "spray-can"       % V.sprayCan    % "compile"
    val jodaTime    = "joda-time"                 %  "joda-time"       % V.jodaTime    % "compile"
    val jodaConvert = "org.joda"                  %  "joda-convert"    % V.jodaConvert % "compile"
    val casbah      = "com.mongodb.casbah"        %  "casbah_2.9.1"    % V.casbah      % "compile"
    val liftJson   = "net.liftweb"                  %%  "lift-json"     % V.liftJson   % "compile"
    val apacheHttp  = "commons-httpclient"        % "commons-httpclient" % V.apacheHttp % "compile"
  }

  object Test {
    val specs2      = "org.specs2"                %% "specs2"          % V.specs2  % "test"
  }

  object Container {
    val jettyWebApp = "org.eclipse.jetty"         %  "jetty-webapp"    % V.jetty   % "container"
    val akkaSlf4j   = "com.typesafe.akka"         %  "akka-slf4j"      % V.akka
    val slf4j       = "org.slf4j"                 %  "slf4j-api"       % V.slf4j
    val logback     = "ch.qos.logback"            %  "logback-classic" % V.logback
  }
}