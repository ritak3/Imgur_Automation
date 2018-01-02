set path=D:\WebDriverFWONSFDC\OTHERS\apache-ant-1.9.6\bin;C:\Program Files\Java\jdk1.7.0_80\bin
@echo off

cd D:\WebDriverFWONSFDC\DEMO

call ant -buildfile build.xml create_run_jar

exit