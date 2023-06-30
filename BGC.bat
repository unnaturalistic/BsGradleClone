@echo off
rem ----------------------------------------------
rem Bullshit gradle clone launcher, 2023 version 1
rem ----------------------------------------------
set VERSION=02
echo Checking for update...
FOR /F "tokens=*" %%g IN ('curl https://raw.githubusercontent.com/unnaturalistic/BsGradleClone/main/version.txt > nul ') do (SET UPDATE=%%g)
IF %VERSION%==%UPDATE% (
echo Ver is latest
goto MainStart
)
echo Update is ready

:update
curl [INSERT LINK HERE] --output BsGradleClone.jar
echo Update Complete, Starting BGC
:MainStart
java -jar BsGradleClone.jar %1 %2 %3
exit /b