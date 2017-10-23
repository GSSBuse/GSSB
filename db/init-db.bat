@echo off
rem /**
rem  * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
rem  *
rem  * Author: ThinkGem@163.com
rem  */
echo.
echo [INFO] rebuild database
echo.
pause
echo.
echo [INFO] this operation will clear database¡£
echo.
echo [INFO] continues ?
echo.
pause
echo.
echo [INFO] are you sure ? or close the window¡£
echo.
pause
echo.

cd %~dp0
cd ..

call mvn antrun:run -Pinit-db

cd db
pause