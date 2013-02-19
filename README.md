cy-garuda
=========

Cytoscape-Garuda bridge app for Cytoscape 3.
This is still an experimental project.

= Important =
You need to install Garuda dependencies locally:

mvn install:install-file -Dfile=./GarudaBackend.jar -DgroupId=org.cytoscape -DartifactId=garuda-backend -Dversion=0.0.1-SNAPSHOT -Dpackaging=jar

mvn install:install-file -Dfile=./garuda-csr.jar -DgroupId=org.cytoscape -DartifactId=garuda-csr -Dversion=0.0.1-SNAPSHOT -Dpackaging=jar

