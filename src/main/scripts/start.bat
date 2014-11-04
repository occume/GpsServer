@echo off
setlocal EnableDelayedExpansion
set classpath=.
for %%i in ("..\lib\*.jar") do set classpath=!classpath!;%%i
for %%j in ("..\lib_ext\*.jar") do set classpath=!classpath!;%%j
@REM launch echo %classpath%
endlocal & set classpath=%classpath%


start javaw bootstrap.Launcher start %1 %2
pause
