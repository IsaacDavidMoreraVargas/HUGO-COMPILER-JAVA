set var1=cd %~dp0
set var2=%CD%
cmd "/K %var1% && java HUGO ProcedimientoHUGO"
pause