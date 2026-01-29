@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete test data directory
if exist data (
    rmdir /s /q data
)

REM delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT

REM compile the code into the bin folder
javac -Xlint:none -d ..\bin ^
  ..\src\main\java\sophon\*.java ^
  ..\src\main\java\sophon\command\*.java ^
  ..\src\main\java\sophon\parser\*.java ^
  ..\src\main\java\sophon\storage\*.java ^
  ..\src\main\java\sophon\task\*.java ^
  ..\src\main\java\sophon\ui\*.java ^
  ..\src\main\java\sophon\exception\*.java


IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ..\bin sophon.Sophon < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT
