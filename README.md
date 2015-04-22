EasyPACS - Dicom Server
=======================

For details:  http://mehmetsen80.github.io/EasyPACS/ 

EasyPACS is the latest PACS server for your dicom files. It uses DCM4CHEE tools to listen and converting dicom files into jpegs. It is the easiest way to store dicom files.

How To Setup JAVA Environment
=============================

EasyPACS uses Java 32 bit environment

1- Put clib_jiio.dll, clib_jiio_sse2.dll, clib_jiio_util.dll under C:\Program Files (x86)\Java\jre1.8.0_20\bin

2- Put clibwrapper_jiio.jar, jai_imageio.jar under C:\Program Files (x86)\Java\jdk1.8.0_45\jre\lib\ext\


Environmental Variables:
========================

User Defined:
Variable Name:  JAVA_HOME
Variable Value: C:\Program Files (x86)\Java\jdk1.8.0_20

System variables
Variable Name:  CLASSPATH
Variable Value: %JAVA_HOME%\jre\bin

