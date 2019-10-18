libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.0" % "test"
libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.5.25"

resolvers += "Central" at "http://central.maven.org/maven2/"

scalacOptions := Seq("-unchecked", "-deprecation", "-feature", "-language:postfixOps")

