@echo off

echo Iniciando Eureka...
start cmd /k "cd eureka\eureka && mvnw.cmd spring-boot:run"

timeout /t 10

echo Iniciando Gateway...
start cmd /k "cd gateway\gateway && mvnw.cmd spring-boot:run"
timeout /t 10

echo Iniciando ms-clinica...
start cmd /k "cd ms-clinica\ms-clinica && mvnw.cmd spring-boot:run"

timeout /t 10

echo Iniciando ms-mascotas...
start cmd /k "cd ms-mascotas\ms-mascotas && mvnw.cmd spring-boot:run"

timeout /t 10

echo Iniciando ms-ubicacion...
start cmd /k "cd ms-ubicacion\ms-ubicacion && mvnw.cmd spring-boot:run"

echo.
echo Todos los servicios fueron iniciados.
pause