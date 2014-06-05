import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "koumeServer"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    javaCore,
    javaJdbc,
    javaEbean,
    "mysql" % "mysql-connector-java" % "5.1.26",
	"com.typesafe" % "play-plugins-redis_2.10" % "2.1.1",//not the recent version, but this one is published
	"com.typesafe.play" %% "play-cache" % "2.2.0"
  )

  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here      
		resolvers += "Typesafe Releases" at "http://typesafe.artifactoryonline.com/typesafe",
		resolvers += "pk11 repo" at "http://pk11-scratch.googlecode.com/svn/trunk"
  )

}
